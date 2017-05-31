
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

	"""),_display_(/*27.3*/helper/*27.9*/.form(action=routes.HomeController.submitNamePostleitzahl())/*27.69*/{_display_(Seq[Any](format.raw/*27.70*/("""
  """),format.raw/*28.3*/("""<table>
    <tr>
    <td width ="30%"> Name: </td> <td> <input type='text' name='Name' hint="help" /> </td>
    </tr>
    	<td>Postleitzahl: </td> <td><input type='text' name='Postleitzahl' /></td>
    </tr>
  </table>
  </br>
  <input type='submit' name='mysubmit' value='Submit Query' />
	""")))}),format.raw/*37.3*/("""
  """),format.raw/*38.3*/("""</p>

  <p>
    """),_display_(/*41.6*/helper/*41.12*/.form(action=routes.HomeController.submitKundennummer())/*41.68*/{_display_(Seq[Any](format.raw/*41.69*/("""
    """),format.raw/*42.5*/("""<table>
      <tr>
        <td width="30%">Kundennummer:</td> <td> <input type='text' name='Kundennummer' /></td>
      </tr>
    </table>
    </br>
    <input type='submit' name='mysubmit' value='Submit Query' />
  	""")))}),format.raw/*49.5*/("""
  """),format.raw/*50.3*/("""</p>



</div>
""")))}),format.raw/*55.2*/("""
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
                  DATE: Wed May 31 14:28:42 CEST 2017
                  SOURCE: D:/Studium/UNA/Unternehmensanwendungen/app/views/welcome.scala.html
                  HASH: d2f07a6da2cce0308cc783790db8805b87e9f66e
                  MATRIX: 538->1|675->43|703->46|750->85|800->98|828->100|1066->312|1094->319|1226->425|1240->431|1309->491|1348->492|1378->495|1700->787|1730->790|1773->807|1788->813|1853->869|1892->870|1924->875|2172->1093|2202->1096|2248->1112
                  LINES: 20->1|25->1|27->3|27->3|27->3|29->5|39->15|39->15|51->27|51->27|51->27|51->27|52->28|61->37|62->38|65->41|65->41|65->41|65->41|66->42|73->49|74->50|79->55
                  -- GENERATED --
              */
          