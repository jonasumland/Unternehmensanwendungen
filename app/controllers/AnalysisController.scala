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

  def umsatzHitliste(kundenNr: String, startDate: String, endDate: String): Vector[Map[String, Object]] = {
    val set5 = sqlRunner.runSql(
      s"""SELECT -(SUM(ac.KSL)) AS UMSATZ, ac.MATNR as MATERIALNUMMER,GEWEI AS GEWICHTSEINHEIT, NTGEW AS NETTOGEWICHT, BRGEW AS BRUTTOGEWICHT, ERNAM AS HERSTELLER, MTART AS MATERIALART, ac.RKCUR AS WAEHRUNG  FROM SAPHPB.ACDOCA_VIEW ac
JOIN SAPHPB.MARA m
ON m.MATNR=ac.MATNR
WHERE BUDAT BETWEEN '$startDate' AND '$endDate' AND UPPER(ac.KUNNR) LIKE (UPPER('%$kundenNr%'))  AND ac.RACCT = '0041000000'
GROUP BY ac.MATNR, GEWEI, NTGEW, BRGEW, ERNAM, MTART, RKCUR
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
      s"""SELECT TOP 10 -(SUM(ac.KSL))/(UMSATZ/100) AS UMSATZANTEIL, ac.MATNR   FROM SAPHPB.ACDOCA_VIEW ac
JOIN (SELECT -(SUM(KSL)) AS UMSATZ, KUNNR FROM SAPHPB.ACDOCA_VIEW
WHERE BUDAT BETWEEN '$startDate' AND '$endDate' AND UPPER(KUNNR) LIKE(UPPER('%$kundenNr%')) AND RACCT = '0041000000'
GROUP BY KUNNR) u
ON u.KUNNR = ac.KUNNR
WHERE BUDAT BETWEEN '$startDate' AND '$endDate' AND UPPER(ac.KUNNR) LIKE(UPPER('%$kundenNr%')) AND ac.RACCT = '0041000000' AND UPPER(ac.MATNR) LIKE(UPPER('%$produkt%'))
GROUP BY u.UMSATZ, ac.MATNR
""")
    return set6
  }

  def umsatzRegion(region: String, startDate: String, endDate: String): Vector[Map[String, Object]] = {
    val set7 = sqlRunner.runSql(
      s"""SELECT CASE WHEN(-(SUM(ac.KSL)) IS NULL) THEN 0 ELSE -SUM(ac.KSL) END AS UMSATZ, REGIO AS REGION, RKCUR AS WAEHRUNG  FROM SAPHPB.ACDOCA_VIEW ac
JOIN SAPHPB.KNA1 k
ON k.KUNNR = ac.KUNNR
WHERE BUDAT BETWEEN '$startDate' AND '$endDate' AND ac.RACCT = '0041000000' AND REGIO LIKE(UPPER('%$region%'))
GROUP BY REGIO, RKCUR
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
      s"""SELECT CASE WHEN(-(SUM(ac.KSL)) IS NULL) THEN 0 ELSE -SUM(ac.KSL) END AS UMSATZ, LAND1 as LAND, RKCUR AS WAEHRUNG  FROM SAPHPB.ACDOCA_VIEW ac
JOIN SAPHPB.KNA1 k
ON k.KUNNR = ac.KUNNR
WHERE BUDAT BETWEEN '$startDate' AND '$endDate' AND ac.RACCT = '0041000000' AND LAND1 LIKE(UPPER('%$land%'))
GROUP BY LAND1, RKCUR
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

  def findeKunde(name: String, plz: String): String = {
    var query1 = s"""SELECT KUNNR FROM SAPHPB.KNA1 WHERE UPPER(NAME1) LIKE UPPER('%$name%') AND PSTLZ='$plz' AND MANDT='400'"""
    val set1 = sqlRunner.runSql(query1)
    println(set1)
    val kundenNummer: String = set1(0)("KUNNR").toString
    return (kundenNummer)
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
  def convertDate(inputDate: String): String = {
    var substrings = inputDate.split("/")
    var month = substrings(0)
    var day = substrings(1)
    var year = substrings(2)
     return(year + month + day)
  }

  def analyse = Action { implicit request =>
    val (name, plz, kn,produkt, region , land ,von,bis) = formS.bindFromRequest.get

    var kNr = ""
    if(kn == "")
      {
        kNr = findeKunde(name,plz)
      }
    else{
      kNr = kn
    }
    val convertedVon = convertDate(von)
    val convertedBis = convertDate(bis)
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
