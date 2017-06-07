
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object main_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /*
 * This template is called from the `index` template. This template
 * handles the rendering of the page header and body tags. It takes
 * two arguments, a `String` for the title of the page and an `Html`
 * object to insert into the body of the page.
 */
  def apply/*7.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*7.32*/("""

"""),format.raw/*9.1*/("""<!DOCTYPE html>
<html lang="en">
    <head>
        """),format.raw/*12.62*/("""
        """),format.raw/*13.9*/("""<title>"""),_display_(/*13.17*/title),format.raw/*13.22*/("""</title>
        <!-- <link rel="stylesheet" media="screen" href=""""),_display_(/*14.59*/routes/*14.65*/.Assets.versioned("stylesheets/main.css")),format.raw/*14.106*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*15.59*/routes/*15.65*/.Assets.versioned("images/favicon.png")),format.raw/*15.104*/("""">-->
        <script src=""""),_display_(/*16.23*/routes/*16.29*/.Assets.versioned("javascripts/hello.js")),format.raw/*16.70*/("""" type="text/javascript"></script>
    </head>
    <body>
        """),format.raw/*20.32*/("""
        """),_display_(/*21.10*/content),format.raw/*21.17*/("""
    """),format.raw/*22.5*/("""</body>
</html>
"""))
      }
    }
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}


}

/*
 * This template is called from the `index` template. This template
 * handles the rendering of the page header and body tags. It takes
 * two arguments, a `String` for the title of the page and an `Html`
 * object to insert into the body of the page.
 */
object main extends main_Scope0.main
              /*
                  -- GENERATED --
                  DATE: Wed Jun 07 13:36:29 CEST 2017
                  SOURCE: /home/jonas/Desktop/Unternehmensanwendungen/app/views/main.scala.html
                  HASH: 2d9e8d8e86705618a6ee54c3ce1fbb5c98d13c45
                  MATRIX: 784->260|909->290|937->292|1017->397|1053->406|1088->414|1114->419|1208->486|1223->492|1286->533|1374->594|1389->600|1450->639|1505->667|1520->673|1582->714|1676->870|1713->880|1741->887|1773->892
                  LINES: 25->7|30->7|32->9|35->12|36->13|36->13|36->13|37->14|37->14|37->14|38->15|38->15|38->15|39->16|39->16|39->16|42->20|43->21|43->21|44->22
                  -- GENERATED --
              */
          