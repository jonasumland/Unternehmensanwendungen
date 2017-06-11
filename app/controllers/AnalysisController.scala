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


@Singleton
class AnalysisController @Inject()(actorSystem: ActorSystem)(db: Database)(implicit exec: ExecutionContext) extends Controller {

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */


  // for all following Sets: if the input for kundenNr is empty, give the function an empty String as value for it. The same applies for produkt in Umsatzprodukt

  def getLaender(): Vector[Map[String, Object]] = {
    val set = sqlRunner.runSql(s"SELECT DISTINCT LAND1 AS LAND FROM KNA1'")
    return set
  }

  def getRegio(): Vector[Map[String, Object]] = {
    val set = sqlRunner.runSql(s"SELECT DISTINCT REGIO AS REGION FROM KNA1'")
    return set
  }

  def umsatzHitliste(kundenNr: String, startDate: String, endDate: String): Vector[Map[String, Object]] = {
    val origset5 = sqlRunner.runSql(
      s"""SELECT -(SUM(ac.KSL)) AS UMSATZ, ac.MATNR as MATERIALNUMMER,GEWEI AS GEWICHTSEINHEIT, NTGEW AS NETTOGEWICHT, BRGEW AS BRUTTOGEWICHT, ERNAM AS HERSTELLER, MTART AS MATERIALART, ac.RKCUR AS WAEHRUNG  FROM SAPHPB.ACDOCA_VIEW ac
JOIN SAPHPB.MARA m
ON m.MATNR=ac.MATNR
WHERE BUDAT BETWEEN '$startDate' AND '$endDate' AND UPPER(ac.KUNNR) LIKE (UPPER('%$kundenNr%'))  AND ac.RACCT = '0041000000'
GROUP BY ac.MATNR, GEWEI, NTGEW, BRGEW, ERNAM, MTART, RKCUR
ORDER BY -SUM(ac.KSL) DESC
""")
    println(origset5)
    val set5 = fillIfEmpty(origset5)

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
      s"""SELECT ROUND(-(SUM(ac.KSL))/(UMSATZ/100),5) AS UMSATZANTEIL, ac.MATNR   FROM SAPHPB.ACDOCA_VIEW ac
JOIN (SELECT -(SUM(KSL)) AS UMSATZ, RACCT FROM SAPHPB.ACDOCA_VIEW
WHERE BUDAT BETWEEN '$startDate' AND '$endDate' AND UPPER(KUNNR) LIKE(UPPER('%$kundenNr%')) AND RACCT = '0041000000'
GROUP BY RACCT) u
ON u.RACCT = ac.RACCT
WHERE BUDAT BETWEEN '$startDate' AND '$endDate' AND UPPER(ac.KUNNR) LIKE(UPPER('%$kundenNr%')) AND ac.RACCT = '0041000000' AND UPPER(ac.MATNR) LIKE(UPPER('%$produkt%'))
GROUP BY u.UMSATZ, ac.MATNR
ORDER BY UMSATZANTEIL DESC
""")


    return fillIfEmpty(set6)
  }

  def umsatzRegion(region: String, startDate: String, endDate: String): Vector[Map[String, Object]] = {
    val origset7 = sqlRunner.runSql(
      s"""SELECT CASE WHEN(-(SUM(ac.KSL)) IS NULL) THEN 0 ELSE -SUM(ac.KSL) END AS UMSATZ, REGIO AS REGION, RKCUR AS WAEHRUNG  FROM SAPHPB.ACDOCA_VIEW ac
JOIN SAPHPB.KNA1 k
ON k.KUNNR = ac.KUNNR
WHERE BUDAT BETWEEN '$startDate' AND '$endDate' AND ac.RACCT = '0041000000' AND REGIO LIKE(UPPER('%$region%'))
GROUP BY REGIO, RKCUR
ORDER BY UMSATZ DESC
""")
    val set7 = fillIfEmpty(origset7)

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
    val origset8 = sqlRunner.runSql(
      s"""SELECT CASE WHEN(-(SUM(ac.KSL)) IS NULL) THEN 0 ELSE -SUM(ac.KSL) END AS UMSATZ, LAND1 as LAND, RKCUR AS WAEHRUNG  FROM SAPHPB.ACDOCA_VIEW ac
JOIN SAPHPB.KNA1 k
ON k.KUNNR = ac.KUNNR
WHERE BUDAT BETWEEN '$startDate' AND '$endDate' AND ac.RACCT = '0041000000' AND LAND1 LIKE(UPPER('%$land%'))
GROUP BY LAND1, RKCUR
ORDER BY UMSATZ DESC
""")
    val set8 = fillIfEmpty(origset8)

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

  def findeKunde(name: String, plz: String): String = {
    var query1 = s"""SELECT KUNNR FROM SAPHPB.KNA1 WHERE UPPER(NAME1) LIKE UPPER('%$name%') AND PSTLZ='$plz' AND MANDT='400'"""
    val set1 = sqlRunner.runSql(query1)
    if(set1.length > 0)
      {
        return set1(0)("KUNNR").toString
      }else{
      return ""
    }

  }

  val formS = Form(
    tuple(
      "Name" -> text,
      "Postleitzahl" -> text,
      "Kundennummer" -> text,
      "Produkt" -> text,
      "Region" -> text,
      "Land" -> text,
      "Von" -> text,
      "Bis" -> text

    )
  )

  def fillIfEmpty(inputSet: Vector[Map[String,Object]]): Vector[Map[String,Object]] =
  {
     if(inputSet.length == 0)
       {
         return(Vector(Map("NO VALUES FOUND" -> "-")))
       }
    else{
       return inputSet
     }
  }

  def convertDate(inputDate: String): String = {
    var substrings = inputDate.split("/")
    var month = substrings(1)
    var day = substrings(2)
    var year = substrings(0)
     return(year + month + day)
  }

  def analyse = Action { implicit request =>
    val (name, plz, kn,produkt, region , land ,von,bis) = formS.bindFromRequest.get

    println(von)
    println(bis)

    var kNr = ""
    if(kn == "")
      {
        kNr = findeKunde(name,plz)
      }
    else{
      kNr = kn
    }
    // CONVERT DATE
    val convertedVon = convertDate(von)
    val convertedBis = convertDate(bis)

    //GET FROM DATABASE
    val hitList = umsatzHitliste(kNr, convertedVon, convertedBis)
    val umsatzEinProdukt = umsatzProdukt(kNr,produkt,convertedVon,convertedBis)
    val umsatzReg = umsatzRegion(region,convertedVon,convertedBis)
    val umsatzLd = umsatzLand(land,convertedVon,convertedBis)


    Ok(views.html.analysisResult(hitList,umsatzEinProdukt,umsatzReg,umsatzLd))
  }

  def index = Action { implicit request =>
    Ok(views.html.analysisHome(Vector(Map())))
  }
}
