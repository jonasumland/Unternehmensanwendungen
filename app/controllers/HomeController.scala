package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import java.sql.DriverManager
import java.sql.Connection
import java.sql.ResultSet
import play.api.libs.json._
import scala.concurrent._
import scala.concurrent.duration._
import akka.actor.ActorSystem
import play.api.db._
import play.api.Play.current
import java.util.Date
import java.text._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */

object sqlRunner {

  def buildMap(queryResult: ResultSet, colNames: Seq[String]): Option[Map[String, Object]] =
    if (queryResult.next())
      Some(colNames.map(n => n -> queryResult.getObject(n)).toMap)
    else
      None

  def realize(queryResult: ResultSet): Vector[Map[String, Object]] = {
    val md = queryResult.getMetaData
    val colNames = (1 to md.getColumnCount) map md.getColumnLabel
    Iterator.continually(buildMap(queryResult, colNames)).takeWhile(!_.isEmpty).map(_.get).toVector
  }

  def runSql(query: String): Vector[Map[String, Object]] = {
    // connect to the database named "mysql" on the localhost
    val url = "jdbc:sap://192.168.31.39:30215/?currentschema=SAPHPB"
    val username = "STUDENTS"
    val password = "Enterprise2017"

    // there's probably a better way to do this
    var connection: Connection = null
    var result: Vector[Map[String, Object]] = null

    try {
      // make the connection
      Class.forName("com.sap.db.jdbc.Driver")
      connection = DriverManager.getConnection(url, username, password)

      // create the statement, and run the select query
      val statement = connection.createStatement()
      val rs = statement.executeQuery(query)
      result = realize(rs)
    }
    catch {
      case e: Throwable => {
        e.printStackTrace
      }
    } finally {
      if (connection != null) {
        connection.close()
      }

    }

    return (result)
  }

}


@Singleton
class HomeController @Inject()(actorSystem: ActorSystem)(db: Database)(implicit exec: ExecutionContext) extends Controller {

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */


  def index = Action {

    Ok(views.html.index("Your SAP HANA is ready."))
  }

  def findeKunde(name: String, plz: String): String = {
    var query1 = s"""SELECT KUNNR FROM SAPHPB.KNA1 WHERE UPPER(NAME1) LIKE UPPER('%$name%') AND PSTLZ='$plz' AND MANDT='400'"""
    val set1 = sqlRunner.runSql(query1)
    println(set1)
    val kundenNummer: String = set1(0)("KUNNR").toString
    return (kundenNummer)
  }


  def kundenInfo(kundenNr: String): Vector[Map[String, Object]] = {
    val set1 = sqlRunner.runSql(s"SELECT KUNNR AS KUNDENNUMMER,NAME1 AS KUNDENNAME,TELF1 AS TELEFONNUMMER,LAND1 AS LAND,ORT01 AS ORT,STRAS AS STRASSE,PSTLZ AS POSTLEITZAHL,REGIO AS REGION FROM SAPHPB.KNA1 WHERE KUNNR='$kundenNr'")
    return set1
  }


  def last10Sells(kundenNr: String): Vector[Map[String, Object]] = {
    var query2 =
      s""" SELECT TOP 10 k.ERDAT AS EINGANGSTAG, k.ERZET AS EINGANGSZEIT,p.MATNR AS MATERIALNUMMER,p.ARKTX AS ARTIKEL,ZIEME AS ZIELMENGE,k.WAERK AS DOKUMENTENWAEHRUNG,p.NETPR AS STUECKPREIS, p.NETWR AS PREIS FROM SAPHPB.VBAK k
                  JOIN SAPHPB.KNA1 s ON s.KUNNR = k.KUNNR AND s.MANDT = k.MANDT
                  JOIN SAPHPB.VBAP p ON p.VBELN=k.VBELN AND p.MANDT = k.MANDT
                  WHERE s.KUNNR='$kundenNr'
                  ORDER BY k.ERDAT DESC, k.ERZET DESC""";
    val set2 = sqlRunner.runSql(query2)

    // making the date great again
    val keys2 = set2.apply(0).keys
    var newSet2 = scala.collection.immutable.Vector[Map[String, Object]]()
    for (m <- set2) {
      var newMap = scala.collection.immutable.Map[String, Object]()
      for (col <- keys2) {
        col match {
          case "EINGANGSTAG" =>
            var sdf = new SimpleDateFormat("yyyymmdd").parse(m(col).toString)
            val ans = new SimpleDateFormat("yyyy-mm-dd").format(sdf)
            newMap += col -> ans
          case "EINGANGSZEIT" =>
            var sdf = new SimpleDateFormat("hhmmss").parse(m(col).toString)
            val ans = new SimpleDateFormat("hh:mm:ss").format(sdf)
            newMap += col -> ans
          case "PREIS" =>
            val decFormat = new DecimalFormat("#,###,###,##0.00")
            val ans = decFormat.format(m(col))
            newMap += col -> ans
          case _ =>
            val temp: String = m(col).toString
            newMap += col -> temp
        }
      }
      newSet2 = newSet2 :+ newMap
    }
    return newSet2
  }

  def calculateProfit(kundenNr: String): Vector[Map[String, Object]] = {
    var query3 =
      s"""SELECT ac.RKCUR AS FIRMENWAEHRUNG,-SUM(ac.KSL) AS UMSATZ, CASE WHEN(UMSATZAB IS NULL) THEN(-SUM(ac.KSL)) ELSE(-(SUM(ac.KSL)+ u.UMSATZAB))END AS ERGEBNIS FROM SAPHPB.ACDOCA_VIEW ac
                LEFT OUTER JOIN (SELECT SUM(KSL) AS UMSATZAB, a.KUNNR  FROM SAPHPB.ACDOCA_VIEW a
                JOIN SAPHPB.KNA1 s
                ON s.KUNNR = a.KUNNR
                WHERE (a.GJAHR='2017' OR a.GJAHR='2016') AND a.KUNNR = '$kundenNr' AND a.RACCT = '0050301000'
                GROUP BY a.KUNNR) u
                ON u.KUNNR= ac.KUNNR
                JOIN SAPHPB.KNA1 s
                ON s.KUNNR = ac.KUNNR
                WHERE (ac.GJAHR='2017' OR ac.GJAHR='2016') AND ac.KUNNR = '$kundenNr' AND ac.RACCT = '0041000000'
                GROUP BY ac.KUNNR, u.UMSATZAB, ac.RHCUR"""

    val set3 = sqlRunner.runSql(query3)

    //making SET 3 great again
    val keys3 = set3.apply(0).keys
    var newSet3 = scala.collection.immutable.Vector[Map[String, Object]]()
    for (m <- set3) {
      var newMap = scala.collection.immutable.Map[String, Object]()
      for (col <- keys3) {
        col match {
          case "ERGEBNIS" =>
            val decFormat = new DecimalFormat("#,###,###,##0.00")
            val ans = decFormat.format(m(col))
            newMap += col -> ans
          case "UMSATZ" =>
            val decFormat = new DecimalFormat("#,###,###,##0.00")
            val ans = decFormat.format(m(col))
            newMap += col -> ans
          case _ =>
            val temp = m getOrElse(col, "")
            newMap += col -> temp
        }
      }
      newSet3 = newSet3 :+ newMap
    }
    return newSet3
  }

  def abzahlzeit(kundenNr: String): Vector[Map[String, Object]] = {
    val set4 = sqlRunner.runSql(
      s"""SELECT AVG(CASE WHEN(AUGDT='00000000') THEN DAYS_BETWEEN(BUDAT,NOW()) ELSE DAYS_BETWEEN(BUDAT,AUGDT)END) AS DURCHSCHNITTLICHEABZAHLZEIT
FROM SAPHPB.ACDOCA_VIEW WHERE RACCT='0012100000' AND DRCRK='S' AND GJAHR='2016' AND BUDAT!='00000000' AND KUNNR='$kundenNr'
""")
    return set4
  }

  def umsatzHitliste(kundenNr: String, startDate: String, endDate: String): Vector[Map[String, Object]] = {
    val set5 = sqlRunner.runSql(
      s"""SELECT -(SUM(ac.KSL)) AS UMSATZ, ac.MATNR as MATERIALNUMMER,GEWEI AS GEWICHTSEINHEIT, NTGEW AS NETTOGEWICHT, BRGEW AS BRUTTOGEWICHT, ERNAM AS HERSTELLER, MTART AS MATERIALART, ac.RKCUR AS WAEHRUNG  FROM SAPHPB.ACDOCA_VIEW ac
JOIN MARA m
ON m.MATNR=ac.MATNR
WHERE BUDAT BETWEEN '$startDate' AND '$endDate' AND UPPER(ac.KUNNR) LIKE (UPPER('%$kundenNr%'))  AND ac.RACCT = '0041000000'
GROUP BY ac.MATNR, GEWEI, NTGEW, BRGEW, ERNAM, MTART
ORDER BY -SUM(ac.KSL) DESC
""")
    val keys5 = set5.apply(0).keys
    var newSet5 = scala.collection.immutable.Vector[Map[String, Object]]()
    for (m <- set5) {
      var newMap = scala.collection.immutable.Map[String, Object]()
      for (col <- keys5) {
        col match {
          case "UMSATZ" =>
            val decFormat = new DecimalFormat("#,###,###,##0.00")
            val ans = decFormat.format(m(col))
            newMap += col -> ans
          case _ =>
            val temp: String = m(col).toString
            newMap += col -> temp
        }
      }
      newSet5 = newSet5 :+ newMap
    }
    return newSet5
  }

  def umsatzProdukt(kundenNr: String, produkt: String, startDate: String, endDate: String): Vector[Map[String, Object]] = {
    val set6 = sqlRunner.runSql(
      s"""SELECT TOP 10 -(SUM(ac.KSL))/(UMSATZ/100) AS UMSATZANTEIL, ac.MATNR, ac.RKCUR AS WAEHRUNG   FROM SAPHPB.ACDOCA_VIEW ac
JOIN (SELECT -(SUM(KSL)) AS UMSATZ, KUNNR FROM SAPHPB.ACDOCA_VIEW
WHERE BUDAT BETWEEN '$startDate' AND '$endDate' AND UPPER(ac.KUNNR) = LIKE(UPPER('%$kundenNr%')) AND RACCT = '0041000000'
GROUP BY KUNNR
) u
ON u.KUNNR = ac.KUNNR
WHERE BUDAT BETWEEN '$startDate' AND '$endDate' AND UPPER(ac.KUNNR) = LIKE(UPPER('%$kundenNr%')) AND ac.RACCT = '0041000000' AND UPPER(ac.MATNR) = LIKE(UPPER('%$produkt%'))
GROUP BY u.UMSATZ, ac.MATNR
""")
    return set6
  }

  def umsatzRegion(region: String, startDate: String, endDate: String): Vector[Map[String, Object]] = {
    val set7 = sqlRunner.runSql(
      s"""SELECT -(SUM(ac.KSL)) AS UMSATZ, REGIO AS REGION, RKCUR AS WAEHRUNG  FROM SAPHPB.ACDOCA_VIEW ac
JOIN SAPHPB.KNA1 k
ON k.KUNNR = ac.KUNNR
WHERE BUDAT BETWEEN '$startDate' AND '$endDate' AND ac.RACCT = '0041000000' AND REGIO =LIKE(UPPER('%$region%'))
GROUP BY LAND1
""")
    val keys7 = set7.apply(0).keys
    var newSet7 = scala.collection.immutable.Vector[Map[String, Object]]()
    for (m <- set7) {
      var newMap = scala.collection.immutable.Map[String, Object]()
      for (col <- keys7) {
        col match {
          case "UMSATZ" =>
            val decFormat = new DecimalFormat("#,###,###,##0.00")
            val ans = decFormat.format(m(col))
            newMap += col -> ans
          case _ =>
            val temp: String = m(col).toString
            newMap += col -> temp
        }
      }
      newSet7 = newSet7 :+ newMap
    }
    return newSet7
  }

  def umsatzLand(land: String, startDate: String, endDate: String): Vector[Map[String, Object]] = {
    val set8 = sqlRunner.runSql(
      s"""SELECT -(SUM(ac.KSL)) AS UMSATZ, LAND1 as LAND, RKCUR AS WAEHRUNG  FROM SAPHPB.ACDOCA_VIEW ac
JOIN SAPHPB.KNA1 k
ON k.KUNNR = ac.KUNNR
WHERE BUDAT BETWEEN '$startDate' AND '$endDate' AND ac.RACCT = '0041000000' AND LAND1 =LIKE(UPPER('%$land%'))
GROUP BY LAND1
""")
    val keys8 = set8.apply(0).keys
    var newSet8 = scala.collection.immutable.Vector[Map[String, Object]]()
    for (m <- set8) {
      var newMap = scala.collection.immutable.Map[String, Object]()
      for (col <- keys8) {
        col match {
          case "UMSATZ" =>
            val decFormat = new DecimalFormat("#,###,###,##0.00")
            val ans = decFormat.format(m(col))
            newMap += col -> ans
          case _ =>
            val temp: String = m(col).toString
            newMap += col -> temp
        }
      }
      newSet8 = newSet8 :+ newMap
    }
    return newSet8
  }

  def receiveInfoFromDatabse(kundenNr: String): Vector[Vector[Map[String, Object]]] = {
    return (Vector(kundenInfo(kundenNr), last10Sells(kundenNr), calculateProfit(kundenNr)))
  }

  val formS = Form(
    tuple(
      "Name" -> text,
      "Postleitzahl" -> text,
      "Kundennummer" -> text
    )
  )

  def submitKundenInfo = Action { implicit request =>
    val (name, plz, kundenNr) = formS.bindFromRequest.get
    var kn = ""
    kundenNr match {
      case null =>
        kn = findeKunde(name, plz)
      case "" =>
        kn = findeKunde(name, plz)
      case _ =>
        kn = kundenNr.toUpperCase
    }

    val sets = receiveInfoFromDatabse(kn)
    val set1 = sets(0)
    val set2 = sets(1)
    val set3 = sets(2)

    set1 match {
      case null => Ok(views.html.welcome("Invalid Input"))
      case Vector() => Ok(views.html.welcome("Invalid Input"))
      case _ => Ok(views.html.table(set1, set2, set3))
    }
  }

}

