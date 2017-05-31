
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


"""),format.raw/*4.1*/("""<link rel="stylesheet" media="screen" href="/@documentation/resources/style/main.css">

<section id="top">
  <div class="wrapper" align="center">
    <h1 align="center">Kundenübersicht</h1>
  </div>
</section>


<div id="content">
  <article>
    <p>
      <h3> Kundendaten </h3>
      <table style="width:100%">
        """),_display_(/*18.10*/defining(set1.apply(0).keys)/*18.38*/ { columns =>_display_(Seq[Any](format.raw/*18.51*/("""
          """),_display_(/*19.12*/for(key <- columns) yield /*19.31*/{_display_(Seq[Any](format.raw/*19.32*/("""
            """),format.raw/*20.13*/("""<tr>
              <th> """),_display_(/*21.21*/key),format.raw/*21.24*/(""" """),format.raw/*21.25*/("""</th>
              """),_display_(/*22.16*/for(m <- set1) yield /*22.30*/{_display_(Seq[Any](format.raw/*22.31*/("""
                """),format.raw/*23.17*/("""<td> """),_display_(/*23.23*/m(key)/*23.29*/.toString),format.raw/*23.38*/(""" """),format.raw/*23.39*/("""</td>
              """)))}),format.raw/*24.16*/("""
           """),format.raw/*25.12*/("""</tr>
          """)))}),format.raw/*26.12*/("""
        """)))}),format.raw/*27.10*/("""
      """),format.raw/*28.7*/("""</table>
    </p>
    <p>
      <h3> Letzte Verkäufe </h3>
      <table style="width:100%">
        """),_display_(/*33.10*/defining(set2.apply(0).keys)/*33.38*/ { columns =>_display_(Seq[Any](format.raw/*33.51*/("""
          """),_display_(/*34.12*/for(key <- columns) yield /*34.31*/{_display_(Seq[Any](format.raw/*34.32*/("""
            """),format.raw/*35.13*/("""<tr>
              <th> """),_display_(/*36.21*/key),format.raw/*36.24*/(""" """),format.raw/*36.25*/("""</th>
              """),_display_(/*37.16*/for(m <- set2) yield /*37.30*/{_display_(Seq[Any](format.raw/*37.31*/("""
                """),format.raw/*38.17*/("""<td> """),_display_(/*38.23*/m(key)/*38.29*/.toString),format.raw/*38.38*/(""" """),format.raw/*38.39*/("""</td>
              """)))}),format.raw/*39.16*/("""
           """),format.raw/*40.12*/("""</tr>
          """)))}),format.raw/*41.12*/("""
        """)))}),format.raw/*42.10*/("""
      """),format.raw/*43.7*/("""</table>
    </p>
    <p>
      <h3> Einnahmen </h3>
      <table style="width:100%">
        """),_display_(/*48.10*/defining(set3.apply(0).keys)/*48.38*/ { columns =>_display_(Seq[Any](format.raw/*48.51*/("""
          """),_display_(/*49.12*/for(key <- columns) yield /*49.31*/{_display_(Seq[Any](format.raw/*49.32*/("""
            """),format.raw/*50.13*/("""<tr>
              <th> """),_display_(/*51.21*/key),format.raw/*51.24*/(""" """),format.raw/*51.25*/("""</th>
              """),_display_(/*52.16*/for(m <- set3) yield /*52.30*/{_display_(Seq[Any](format.raw/*52.31*/("""
                """),format.raw/*53.17*/("""<td> """),_display_(/*53.23*/m(key)/*53.29*/.toString),format.raw/*53.38*/(""" """),format.raw/*53.39*/("""</td>
              """)))}),format.raw/*54.16*/("""
           """),format.raw/*55.12*/("""</tr>
          """)))}),format.raw/*56.12*/("""
        """)))}),format.raw/*57.10*/("""
      """),format.raw/*58.7*/("""</table>
    </p>
</div>
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
                  DATE: Wed May 31 14:20:28 CEST 2017
                  SOURCE: D:/Studium/UNA/Unternehmensanwendungen/app/views/table.scala.html
                  HASH: 3905e8163dbf68008bb57359ca2604f1feab5de2
                  MATRIX: 604->1|803->104|832->107|1181->430|1218->458|1269->471|1308->483|1343->502|1382->503|1423->516|1475->541|1499->544|1528->545|1576->566|1606->580|1645->581|1690->598|1723->604|1738->610|1768->619|1797->620|1849->641|1889->653|1937->670|1978->680|2012->687|2140->788|2177->816|2228->829|2267->841|2302->860|2341->861|2382->874|2434->899|2458->902|2487->903|2535->924|2565->938|2604->939|2649->956|2682->962|2697->968|2727->977|2756->978|2808->999|2848->1011|2896->1028|2937->1038|2971->1045|3093->1140|3130->1168|3181->1181|3220->1193|3255->1212|3294->1213|3335->1226|3387->1251|3411->1254|3440->1255|3488->1276|3518->1290|3557->1291|3602->1308|3635->1314|3650->1320|3680->1329|3709->1330|3761->1351|3801->1363|3849->1380|3890->1390|3924->1397
                  LINES: 20->1|25->1|28->4|42->18|42->18|42->18|43->19|43->19|43->19|44->20|45->21|45->21|45->21|46->22|46->22|46->22|47->23|47->23|47->23|47->23|47->23|48->24|49->25|50->26|51->27|52->28|57->33|57->33|57->33|58->34|58->34|58->34|59->35|60->36|60->36|60->36|61->37|61->37|61->37|62->38|62->38|62->38|62->38|62->38|63->39|64->40|65->41|66->42|67->43|72->48|72->48|72->48|73->49|73->49|73->49|74->50|75->51|75->51|75->51|76->52|76->52|76->52|77->53|77->53|77->53|77->53|77->53|78->54|79->55|80->56|81->57|82->58
                  -- GENERATED --
              */
          