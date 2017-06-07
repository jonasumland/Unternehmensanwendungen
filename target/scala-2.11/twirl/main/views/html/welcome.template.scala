
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

class welcome extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.19*/("""
"""),format.raw/*2.1*/("""<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


"""),_display_(/*5.2*/navbar()),format.raw/*5.10*/("""
"""),format.raw/*6.1*/("""<div class="container-fluid text-center">
  <div class="row content">
    <div class="col-sm-2 sidenav">
    </div>

<div class="col-sm-8 text-left">
      <h2 align = "center">Übung 1</h2>

  <div class="row">
          """),_display_(/*15.12*/helper/*15.18*/.form(action=routes.HomeController.submitKundenInfo())/*15.72*/{_display_(Seq[Any](format.raw/*15.73*/("""
            """),format.raw/*16.13*/("""<form class="form-horizontal">
             <div class="form-group">
               <label class="control-label col-sm-2">Name:</label>
               <div class="col-sm-10">
                 <input class="form-control" name='Name' placeholder="Enter name">
               </div>
             </div>
             <div class="form-group">
               <label class="control-label col-sm-2" >Postleitzahl:</label>
               <div class="col-sm-10">
                 <input class="form-control" name='Postleitzahl' placeholder="Enter PLZ">
               </div>
             </div>
             <div class="form-group">
               <label class="control-label col-sm-2">Kundennummer:</label>
               <div class="col-sm-10">
                 <input class="form-control" name='Kundennummer' placeholder="Enter PLZ">
               </div>
             </div>
             <div class="form-group">
               <div class="col-sm-offset-2 col-sm-10">
                 <button type="submit" name='mysubmit' class="btn btn-default">Submit</button>
               </div>
             </div>
            </form>
          """)))}),format.raw/*41.12*/("""
        """),format.raw/*42.9*/("""</div>
        """),_display_(/*43.10*/message),format.raw/*43.17*/("""
"""),format.raw/*44.1*/("""</div>




</div>
"""))
      }
    }
  }

  def render(message:String): play.twirl.api.HtmlFormat.Appendable = apply(message)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (message) => apply(message)

  def ref: this.type = this

}


}

/**/
object welcome extends welcome_Scope0.welcome
              /*
                  -- GENERATED --
                  DATE: Wed Jun 07 14:38:42 CEST 2017
                  SOURCE: D:/Unternehmensanwendungen/Aufgaben/Unternehmensanwendungen/app/views/welcome.scala.html
                  HASH: b64ab7973f0f72b04a8481659a24c11f5ea1665b
                  MATRIX: 531->1|643->18|671->20|803->127|831->135|859->137|1117->368|1132->374|1195->428|1234->429|1276->443|2462->1598|2499->1608|2543->1625|2571->1632|2600->1634
                  LINES: 20->1|25->1|26->2|29->5|29->5|30->6|39->15|39->15|39->15|39->15|40->16|65->41|66->42|67->43|67->43|68->44
                  -- GENERATED --
              */
          