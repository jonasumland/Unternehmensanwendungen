
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
                  DATE: Wed Jun 07 14:38:42 CEST 2017
                  SOURCE: D:/Unternehmensanwendungen/Aufgaben/Unternehmensanwendungen/app/views/table.scala.html
                  HASH: 3acfa28d879a806e339f5a015ab6d7aa8df7943b
                  MATRIX: 604->1|803->104|833->108|963->213|991->221|1021->225|1110->289|1146->317|1196->330|1229->337|1264->356|1303->357|1338->365|1385->385|1409->388|1438->389|1481->405|1511->419|1550->420|1590->432|1623->438|1638->444|1668->453|1697->454|1744->470|1778->477|1820->489|1855->494|1884->496|1992->578|2029->606|2080->619|2115->628|2150->647|2189->648|2226->658|2275->680|2299->683|2328->684|2373->702|2403->716|2442->717|2484->731|2517->737|2532->743|2562->752|2591->753|2640->771|2676->780|2720->794|2757->801|2788->805|2886->877|2923->905|2974->918|3007->925|3042->944|3081->945|3116->953|3163->973|3187->976|3216->977|3259->993|3289->1007|3328->1008|3368->1020|3401->1026|3416->1032|3446->1041|3475->1042|3522->1058|3556->1065|3598->1077|3633->1082|3662->1084
                  LINES: 20->1|25->1|27->3|29->5|29->5|31->7|33->9|33->9|33->9|34->10|34->10|34->10|35->11|36->12|36->12|36->12|37->13|37->13|37->13|38->14|38->14|38->14|38->14|38->14|39->15|40->16|41->17|42->18|43->19|46->22|46->22|46->22|47->23|47->23|47->23|48->24|49->25|49->25|49->25|50->26|50->26|50->26|51->27|51->27|51->27|51->27|51->27|52->28|53->29|54->30|55->31|56->32|59->35|59->35|59->35|60->36|60->36|60->36|61->37|62->38|62->38|62->38|63->39|63->39|63->39|64->40|64->40|64->40|64->40|64->40|65->41|66->42|67->43|68->44|69->45
                  -- GENERATED --
              */
          