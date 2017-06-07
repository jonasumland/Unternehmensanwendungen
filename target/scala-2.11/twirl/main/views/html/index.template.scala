
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
                  DATE: Wed Jun 07 13:45:16 CEST 2017
                  SOURCE: /home/jonas/Desktop/Unternehmensanwendungen/app/views/index.scala.html
                  HASH: 5634ba481ed920b02fb03d903ab695c6b7c86d5e
                  MATRIX: 616->95|728->112|757->308|785->310|816->332|856->334|889->463|921->469|958->485|991->488
                  LINES: 23->5|28->5|30->11|31->12|31->12|31->12|33->17|34->18|34->18|36->20
                  -- GENERATED --
              */
          