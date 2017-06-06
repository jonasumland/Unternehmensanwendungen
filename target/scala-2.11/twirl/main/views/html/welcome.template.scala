
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object welcome_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class welcome extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String, style: String = "scala"):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.44*/("""

"""),_display_(/*3.2*/defining(play.core.PlayVersion.current)/*3.41*/ { version =>_display_(Seq[Any](format.raw/*3.54*/("""

"""),format.raw/*5.1*/("""<link rel="stylesheet" media="screen" href="/@documentation/resources/style/main.css">

<head>
  <title>
    Call Center App
  </title>
</head>

<section id="top">
  <div class="wrapper" align="right">
    <h1>"""),_display_(/*15.10*/message),format.raw/*15.17*/("""</h1>
  </div>
</section>

<div id="content" class="wrapper doc">
<article>

  <h2>Übung 1</h2>



  <p>

  """),_display_(/*28.4*/helper/*28.10*/.form(action=routes.HomeController.submitKundenInfo())/*28.64*/{_display_(Seq[Any](format.raw/*28.65*/("""
    """),format.raw/*29.5*/("""<table>
      <tr>
        <td width ="30%"> Name: </td> <td> <input type='text' name='Name' hint="help" /> </td>
      </tr>
       <td>Postleitzahl: </td> <td><input type='text' name='Postleitzahl' /></td>
      </tr>
       <td width="30%">Kundennummer:</td> <td> <input type='text' name='Kundennummer' /></td>
      </tr>
    </table>
    </br>
    <input type='submit' name='mysubmit' value='Submit Query' />
  """)))}),format.raw/*40.4*/("""
  """),format.raw/*41.3*/("""</p>



</div>
""")))}),format.raw/*46.2*/("""
"""))
      }
    }
  }

  def render(message:String,style:String): play.twirl.api.HtmlFormat.Appendable = apply(message,style)

  def f:((String,String) => play.twirl.api.HtmlFormat.Appendable) = (message,style) => apply(message,style)

  def ref: this.type = this

}


}

/**/
object welcome extends welcome_Scope0.welcome
              /*
                  -- GENERATED --
                  DATE: Tue Jun 06 16:10:16 CEST 2017
                  SOURCE: D:/Studium/UNA/Unternehmensanwendungen/app/views/welcome.scala.html
                  HASH: 7dbf9f102924c0d7aa3099871df33339ba154ac8
                  MATRIX: 538->1|675->43|703->46|750->85|800->98|828->100|1066->312|1094->319|1229->428|1244->434|1307->488|1346->489|1378->494|1825->911|1855->914|1901->930
                  LINES: 20->1|25->1|27->3|27->3|27->3|29->5|39->15|39->15|52->28|52->28|52->28|52->28|53->29|64->40|65->41|70->46
                  -- GENERATED --
              */
          