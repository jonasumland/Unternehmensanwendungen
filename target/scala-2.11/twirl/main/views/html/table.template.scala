
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




"""),format.raw/*6.1*/("""<link rel="stylesheet" media="screen" href="/@documentation/resources/style/main.css">

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
        """),_display_(/*20.10*/defining(set1.apply(0).keys)/*20.38*/ { columns =>_display_(Seq[Any](format.raw/*20.51*/("""
          """),_display_(/*21.12*/for(key <- columns) yield /*21.31*/{_display_(Seq[Any](format.raw/*21.32*/("""
            """),format.raw/*22.13*/("""<tr>
              <th> """),_display_(/*23.21*/key),format.raw/*23.24*/(""" """),format.raw/*23.25*/("""</th>
              """),_display_(/*24.16*/for(m <- set1) yield /*24.30*/{_display_(Seq[Any](format.raw/*24.31*/("""
                """),format.raw/*25.17*/("""<td> """),_display_(/*25.23*/m(key)/*25.29*/.toString),format.raw/*25.38*/(""" """),format.raw/*25.39*/("""</td>
              """)))}),format.raw/*26.16*/("""
           """),format.raw/*27.12*/("""</tr>
          """)))}),format.raw/*28.12*/("""
        """)))}),format.raw/*29.10*/("""
      """),format.raw/*30.7*/("""</table>
    </p>
    <p>
      <h3> Letzte Verkäufe </h3>
      <table style="width:100%">
        """),_display_(/*35.10*/defining(set2.apply(0).keys)/*35.38*/ { columns =>_display_(Seq[Any](format.raw/*35.51*/("""
          """),_display_(/*36.12*/for(key <- columns) yield /*36.31*/{_display_(Seq[Any](format.raw/*36.32*/("""
            """),format.raw/*37.13*/("""<tr>
              <th> """),_display_(/*38.21*/key),format.raw/*38.24*/(""" """),format.raw/*38.25*/("""</th>
              """),_display_(/*39.16*/for(m <- set2) yield /*39.30*/{_display_(Seq[Any](format.raw/*39.31*/("""
                """),format.raw/*40.17*/("""<td> """),_display_(/*40.23*/m(key)/*40.29*/.toString),format.raw/*40.38*/(""" """),format.raw/*40.39*/("""</td>
              """)))}),format.raw/*41.16*/("""
           """),format.raw/*42.12*/("""</tr>
          """)))}),format.raw/*43.12*/("""
        """)))}),format.raw/*44.10*/("""
      """),format.raw/*45.7*/("""</table>
    </p>
    <p>
      <h3> Einnahmen </h3>
      <table style="width:100%">
        """),_display_(/*50.10*/defining(set3.apply(0).keys)/*50.38*/ { columns =>_display_(Seq[Any](format.raw/*50.51*/("""
          """),_display_(/*51.12*/for(key <- columns) yield /*51.31*/{_display_(Seq[Any](format.raw/*51.32*/("""
            """),format.raw/*52.13*/("""<tr>
              <th> """),_display_(/*53.21*/key),format.raw/*53.24*/(""" """),format.raw/*53.25*/("""</th>
              """),_display_(/*54.16*/for(m <- set3) yield /*54.30*/{_display_(Seq[Any](format.raw/*54.31*/("""
                """),format.raw/*55.17*/("""<td> """),_display_(/*55.23*/m(key)/*55.29*/.toString),format.raw/*55.38*/(""" """),format.raw/*55.39*/("""</td>
              """)))}),format.raw/*56.16*/("""
           """),format.raw/*57.12*/("""</tr>
          """)))}),format.raw/*58.12*/("""
        """)))}),format.raw/*59.10*/("""
      """),format.raw/*60.7*/("""</table>
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
                  DATE: Wed May 31 13:52:05 CEST 2017
                  SOURCE: D:/Unternehmensanwendungen/Uebungen/app/views/table.scala.html
                  HASH: 83ee6c0ebf477cf54d77307dddaba7eb031eb452
                  MATRIX: 604->1|803->104|839->114|1202->451|1239->479|1290->492|1330->505|1365->524|1404->525|1446->539|1499->565|1523->568|1552->569|1601->591|1631->605|1670->606|1716->624|1749->630|1764->636|1794->645|1823->646|1876->668|1917->681|1966->699|2008->710|2043->718|2176->824|2213->852|2264->865|2304->878|2339->897|2378->898|2420->912|2473->938|2497->941|2526->942|2575->964|2605->978|2644->979|2690->997|2723->1003|2738->1009|2768->1018|2797->1019|2850->1041|2891->1054|2940->1072|2982->1083|3017->1091|3144->1191|3181->1219|3232->1232|3272->1245|3307->1264|3346->1265|3388->1279|3441->1305|3465->1308|3494->1309|3543->1331|3573->1345|3612->1346|3658->1364|3691->1370|3706->1376|3736->1385|3765->1386|3818->1408|3859->1421|3908->1439|3950->1450|3985->1458
                  LINES: 20->1|25->1|30->6|44->20|44->20|44->20|45->21|45->21|45->21|46->22|47->23|47->23|47->23|48->24|48->24|48->24|49->25|49->25|49->25|49->25|49->25|50->26|51->27|52->28|53->29|54->30|59->35|59->35|59->35|60->36|60->36|60->36|61->37|62->38|62->38|62->38|63->39|63->39|63->39|64->40|64->40|64->40|64->40|64->40|65->41|66->42|67->43|68->44|69->45|74->50|74->50|74->50|75->51|75->51|75->51|76->52|77->53|77->53|77->53|78->54|78->54|78->54|79->55|79->55|79->55|79->55|79->55|80->56|81->57|82->58|83->59|84->60
                  -- GENERATED --
              */
          