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
class UserInputController @Inject()(actorSystem: ActorSystem)(db: Database)(implicit exec: ExecutionContext) extends Controller {

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */

  def findeKundeWithNameAndPLZ(name: String, plz: String): Vector[Map[String,Object]] = {
    var query1 = s"""SELECT KUNNR, NAME1, PSTLZ FROM SAPHPB.KNA1 WHERE UPPER(NAME1) LIKE UPPER('%$name%') AND PSTLZ LIKE '%$plz%' AND MANDT='400'"""
    val set1 = controllers.sqlRunner.runSql(query1)
    return(set1)
  }

  def findeKundeWithKundennummer(kundenNr: String): Vector[Map[String,Object]] = {
    var query1 = s"""SELECT KUNNR, NAME1, PSTLZ FROM SAPHPB.KNA1 WHERE KUNNR LIKE '%$kundenNr%' AND MANDT='400'"""
    val set1 = controllers.sqlRunner.runSql(query1)
    return(set1)
  }

  def findeKundeWithAll(name: String, plz: String, kn:String): Vector[Map[String,Object]] = {
    var query1 = s"""SELECT KUNNR FROM SAPHPB.KNA1 WHERE UPPER(NAME1) LIKE UPPER('%$name%')OR KUNNR LIKE '%$kn%' OR PSTLZ='$plz' AND MANDT='400'"""
    val set1 = controllers.sqlRunner.runSql(query1)
    return(set1)
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

    val kundeNmPlz = findeKundeWithNameAndPLZ(name,plz)
    val kundeKn = findeKundeWithKundennummer(kundenNr)
    val kundeNmOrPlz = findeKundeWithNameAndPLZ(name,plz)

    kundeKn.length match {
      case 0 =>

      case _ =>
        Ok(views.html.welcome(kundeKn))
    }

    kundeNmPlz.length match {
      case 0 =>

      case _ =>
        Ok(views.html.welcome(kundeNmPlz))
    }

    kundeNmOrPlz.length match {
      case 0 =>

      case _ =>
        Ok(views.html.welcome(kundeNmOrPlz))
    }

    Ok(views.html.welcome(Vector(Map("ERROR" -> "NOTHING FOUND"))))
  }

}
