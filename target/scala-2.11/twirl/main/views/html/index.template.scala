
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object index_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /*
 * This template takes a single argument, a String containing a
 * message to display.
 */
  def apply/*5.2*/(message: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.19*/("""

"""),format.raw/*11.4*/("""
"""),_display_(/*12.2*/main("Callcenter App")/*12.24*/ {_display_(Seq[Any](format.raw/*12.26*/("""

    """),format.raw/*17.8*/("""
    """),_display_(/*18.6*/welcome(message)),format.raw/*18.22*/("""

""")))}),format.raw/*20.2*/("""
"""))
      }
    }
  }

  def render(message:String): play.twirl.api.HtmlFormat.Appendable = apply(message)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (message) => apply(message)

  def ref: this.type = this

}


}

/*
 * This template takes a single argument, a String containing a
 * message to display.
 */
object index extends index_Scope0.index
              /*
                  -- GENERATED --
                  DATE: Wed Jun 07 14:38:42 CEST 2017
                  SOURCE: D:/Unternehmensanwendungen/Aufgaben/Unternehmensanwendungen/app/views/index.scala.html
                  HASH: 40d4ccc6a3ffc42d7532a22f8e1122aefec130af
                  MATRIX: 619->99|731->116|762->318|791->321|822->343|862->345|897->479|930->486|967->502|1002->507
                  LINES: 23->5|28->5|30->11|31->12|31->12|31->12|33->17|34->18|34->18|36->20
                  -- GENERATED --
              */
          