import com.sap.marmolata.ui.App

import scala.language.existentials
import com.sap.marmolata.app.MarmolataClient
import com.sap.marmolata.app.client.MarmolataShell
import com.sap.marmolata.data.query.untyped.ClientDataApi
import com.sap.marmolata.data.sql._
import com.sap.marmolata.data.types._
import com.sap.marmolata.erp._
import com.sap.marmolata.ui
import com.sap.marmolata.ui.{List => _, _}
import com.sap.marmolata.ui.layout.{Form, FormContainer, FormElement, FormTitle, Vertical, _}
import reactive.library._
import com.sap.marmolata.ui.dataImplicits._

import scala.concurrent.ExecutionContext.Implicits.global
import com.sap.marmolata.utils.builder.StaticBuilder
import com.sap.marmolata.ui.extensions.implicitExtensions._

@MarmolataClient(com.sap.marmolata.data.query.untyped.QueryExecAPI)
object DisplayLineItems extends MarmolataShell {

  val query = sql"select KUNNR,NAME1,ORT01,PSTLZ from KNA1"
  val filter = FilterBar.datasource(query).build
  val table = Table.datasource(filter.output).selectionMode(SelectionMode.Single).build

  val selectedColOfRowKundenNr: Signal[Option[String]] = table.selectedRows.map(_.headOption.map(_.KUNNR.value))
  val kundennummer = selectedColOfRowKundenNr.map(_.getOrElse("USCU_L01"))

  val selectedColOfRowName: Signal[Option[String]] = table.selectedRows.map(_.headOption.map(_.NAME1.value))
  val name = selectedColOfRowName.map(_.getOrElse("USCU_L01"))

  val selectedColOfRowOrt: Signal[Option[String]] = table.selectedRows.map(_.headOption.map(_.ORT01.value))
  val ort = selectedColOfRowOrt.map(_.getOrElse("USCU_L01"))

  val selectedColOfRowPlz: Signal[Option[String]] = table.selectedRows.map(_.headOption.map(_.PSTLZ.value))
  val plz = selectedColOfRowPlz.map(_.getOrElse("USCU_L01"))

  val query3 = sql"select BUDAT, RACCT, RHCUR, BELNR, RBUKRS, KOART, HSL from ACDOCA where KUNNR=${kundennummer}"
  val filter3 = FilterBar.datasource(query3).build
  val table3 = Table.datasource(filter3.output).selectionMode(SelectionMode.None).build

  val form = Form()
    .title(FormTitle().text("Information"))
    .containers(
      FormContainer()
        .title(FormTitle().text("Customer"))
        .elements(
          Seq(
            FormElement().label("Kundennummer").fields(Text().text(kundennummer)),
            FormElement().label("Name").fields(Text().text(name)),
            FormElement().label("PLZ").fields(Text().text(plz)),
              FormElement().label("ORT").fields(Text().text(ort))
          ))).build()

  val button = Button().text("Go Customer Details").build

  val page1 = Page().title("Customer Selection").content(button above filter above table).build()
  val page2 = Page().title("Customer Details").showNavButton(true).content(form above filter3 above table3).build()

  val pageTransitions: EventSource[PageTransition] = EventSource()

  table.selectedRows.observe(_ => pageTransitions := PageTransition(StaticBuilder(page2)))
  button.clicks.observe(_ => pageTransitions := PageTransition(StaticBuilder(page2)))
  page2.navButtonPress.observe(_ => pageTransitions := PageTransition(StaticBuilder(page1), PageTransitionEffect.SlideRight))

  val render = App().initialPage(page1).pageTransitions(pageTransitions).build
}
