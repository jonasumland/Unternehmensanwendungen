
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object table_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class table extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[Vector[Map[String, Object]],Vector[Map[String, Object]],Vector[Map[String, Object]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(set1: Vector[Map[String, Object]],set2: Vector[Map[String, Object]],set3: Vector[Map[String, Object]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.105*/("""

"""),format.raw/*3.1*/("""<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

"""),_display_(/*5.2*/navbar()),format.raw/*5.10*/("""

"""),format.raw/*7.1*/("""<h3> Kundendaten </h3>
<table class="table table-striped">
  """),_display_(/*9.4*/defining(set1.apply(0).keys)/*9.32*/ { columns =>_display_(Seq[Any](format.raw/*9.45*/("""
    """),_display_(/*10.6*/for(key <- columns) yield /*10.25*/{_display_(Seq[Any](format.raw/*10.26*/("""
      """),format.raw/*11.7*/("""<tr>
        <th> """),_display_(/*12.15*/key),format.raw/*12.18*/(""" """),format.raw/*12.19*/("""</th>
        """),_display_(/*13.10*/for(m <- set1) yield /*13.24*/{_display_(Seq[Any](format.raw/*13.25*/("""
          """),format.raw/*14.11*/("""<td> """),_display_(/*14.17*/m(key)/*14.23*/.toString),format.raw/*14.32*/(""" """),format.raw/*14.33*/("""</td>
        """)))}),format.raw/*15.10*/("""
     """),format.raw/*16.6*/("""</tr>
    """)))}),format.raw/*17.6*/("""
  """)))}),format.raw/*18.4*/("""
"""),format.raw/*19.1*/("""</table>
<h3> Letzte Verkäufe </h3>
  <table class="table table-striped">
    """),_display_(/*22.6*/defining(set2.apply(0).keys)/*22.34*/ { columns =>_display_(Seq[Any](format.raw/*22.47*/("""
      """),_display_(/*23.8*/for(key <- columns) yield /*23.27*/{_display_(Seq[Any](format.raw/*23.28*/("""
        """),format.raw/*24.9*/("""<tr>
          <th> """),_display_(/*25.17*/key),format.raw/*25.20*/(""" """),format.raw/*25.21*/("""</th>
          """),_display_(/*26.12*/for(m <- set2) yield /*26.26*/{_display_(Seq[Any](format.raw/*26.27*/("""
            """),format.raw/*27.13*/("""<td> """),_display_(/*27.19*/m(key)/*27.25*/.toString),format.raw/*27.34*/(""" """),format.raw/*27.35*/("""</td>
          """)))}),format.raw/*28.12*/("""
       """),format.raw/*29.8*/("""</tr>
      """)))}),format.raw/*30.8*/("""
    """)))}),format.raw/*31.6*/("""
  """),format.raw/*32.3*/("""</table>
<h3> Einnahmen </h3>
<table class="table table-striped">
  """),_display_(/*35.4*/defining(set3.apply(0).keys)/*35.32*/ { columns =>_display_(Seq[Any](format.raw/*35.45*/("""
    """),_display_(/*36.6*/for(key <- columns) yield /*36.25*/{_display_(Seq[Any](format.raw/*36.26*/("""
      """),format.raw/*37.7*/("""<tr>
        <th> """),_display_(/*38.15*/key),format.raw/*38.18*/(""" """),format.raw/*38.19*/("""</th>
        """),_display_(/*39.10*/for(m <- set3) yield /*39.24*/{_display_(Seq[Any](format.raw/*39.25*/("""
          """),format.raw/*40.11*/("""<td> """),_display_(/*40.17*/m(key)/*40.23*/.toString),format.raw/*40.32*/(""" """),format.raw/*40.33*/("""</td>
        """)))}),format.raw/*41.10*/("""
     """),format.raw/*42.6*/("""</tr>
    """)))}),format.raw/*43.6*/("""
  """)))}),format.raw/*44.4*/("""
"""),format.raw/*45.1*/("""</table>
"""))
      }
    }
  }

  def render(set1:Vector[Map[String, Object]],set2:Vector[Map[String, Object]],set3:Vector[Map[String, Object]]): play.twirl.api.HtmlFormat.Appendable = apply(set1,set2,set3)

  def f:((Vector[Map[String, Object]],Vector[Map[String, Object]],Vector[Map[String, Object]]) => play.twirl.api.HtmlFormat.Appendable) = (set1,set2,set3) => apply(set1,set2,set3)

  def ref: this.type = this

}


}

/**/
object table extends table_Scope0.table
              /*
                  -- GENERATED --
                  DATE: Wed Jun 07 13:41:35 CEST 2017
                  SOURCE: /home/jonas/Desktop/Unternehmensanwendungen/app/views/table.scala.html
                  HASH: 8bb662e0304670901b3568e55fda1d98d275547b
                  MATRIX: 604->1|803->104|831->106|959->209|987->217|1015->219|1102->281|1138->309|1188->322|1220->328|1255->347|1294->348|1328->355|1374->374|1398->377|1427->378|1469->393|1499->407|1538->408|1577->419|1610->425|1625->431|1655->440|1684->441|1730->456|1763->462|1804->473|1838->477|1866->478|1971->557|2008->585|2059->598|2093->606|2128->625|2167->626|2203->635|2251->656|2275->659|2304->660|2348->677|2378->691|2417->692|2458->705|2491->711|2506->717|2536->726|2565->727|2613->744|2648->752|2691->765|2727->771|2757->774|2852->843|2889->871|2940->884|2972->890|3007->909|3046->910|3080->917|3126->936|3150->939|3179->940|3221->955|3251->969|3290->970|3329->981|3362->987|3377->993|3407->1002|3436->1003|3482->1018|3515->1024|3556->1035|3590->1039|3618->1040
                  LINES: 20->1|25->1|27->3|29->5|29->5|31->7|33->9|33->9|33->9|34->10|34->10|34->10|35->11|36->12|36->12|36->12|37->13|37->13|37->13|38->14|38->14|38->14|38->14|38->14|39->15|40->16|41->17|42->18|43->19|46->22|46->22|46->22|47->23|47->23|47->23|48->24|49->25|49->25|49->25|50->26|50->26|50->26|51->27|51->27|51->27|51->27|51->27|52->28|53->29|54->30|55->31|56->32|59->35|59->35|59->35|60->36|60->36|60->36|61->37|62->38|62->38|62->38|63->39|63->39|63->39|64->40|64->40|64->40|64->40|64->40|65->41|66->42|67->43|68->44|69->45
                  -- GENERATED --
              */
          