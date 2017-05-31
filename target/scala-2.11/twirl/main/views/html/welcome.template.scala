
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

<section id="top">
  <div class="wrapper" align="right">
    <h1>"""),_display_(/*9.10*/message),format.raw/*9.17*/("""</h1>
  </div>
</section>

<div id="content" class="wrapper doc">
<article>

  <h2>Übung 1</h2>


	<p>

	"""),_display_(/*21.3*/helper/*21.9*/.form(action=routes.HomeController.submitNamePostleitzahl())/*21.69*/{_display_(Seq[Any](format.raw/*21.70*/("""
  """),format.raw/*22.3*/("""<table>
    <tr>
    <td width ="30%"> Name: </td> <td> <input type='text' name='Name' hint="help" /> </td>
    </tr>
    	<td>Postleitzahl: </td> <td><input type='text' name='Postleitzahl' /></td>
    </tr>
  </table>
  </br>
  <input type='submit' name='mysubmit' value='Submit Query' />
	""")))}),format.raw/*31.3*/("""
  """),format.raw/*32.3*/("""</p>

  <p>
    """),_display_(/*35.6*/helper/*35.12*/.form(action=routes.HomeController.submitKundennummer())/*35.68*/{_display_(Seq[Any](format.raw/*35.69*/("""
    """),format.raw/*36.5*/("""<table>
      <tr>
        <td width="30%">Kundennummer:</td> <td> <input type='text' name='Kundennummer' /></td>
      </tr>
    </table>
    </br>
    <input type='submit' name='mysubmit' value='Submit Query' />
  	""")))}),format.raw/*43.5*/("""
  """),format.raw/*44.3*/("""</p>



</div>
""")))}),format.raw/*49.2*/("""
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
                  DATE: Wed May 31 13:52:05 CEST 2017
                  SOURCE: D:/Unternehmensanwendungen/Uebungen/app/views/welcome.scala.html
                  HASH: 7f15c941cbd9487075ba1333497f02f3aaff3fec
                  MATRIX: 538->1|675->43|705->48|752->87|802->100|832->104|1016->263|1043->270|1187->388|1201->394|1270->454|1309->455|1340->459|1671->760|1702->764|1748->784|1763->790|1828->846|1867->847|1900->853|2155->1078|2186->1082|2237->1103
                  LINES: 20->1|25->1|27->3|27->3|27->3|29->5|33->9|33->9|45->21|45->21|45->21|45->21|46->22|55->31|56->32|59->35|59->35|59->35|59->35|60->36|67->43|68->44|73->49
                  -- GENERATED --
              */
          