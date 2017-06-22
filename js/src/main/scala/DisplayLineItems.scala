

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
import com.sap.marmolata.ui.layout.Vertical
import reactive.library._
import com.sap.marmolata.ui.dataImplicits._

import scala.concurrent.ExecutionContext.Implicits.global
import com.sap.marmolata.utils.builder.StaticBuilder

@MarmolataClient(com.sap.marmolata.data.query.untyped.QueryExecAPI)
object DisplayLineItems extends MarmolataShell {

  val query = sql"select KUNNR,NAME1,ORT01,PSTLZ from KNA1"
  val filter = FilterBar.datasource(query).build
  val table = Table.datasource(filter.output).selectionMode(SelectionMode.Single).build

  val selectedColOfRow: Signal[Option[String]] = table.selectedRows.map(_.headOption.map(_.KUNNR.value))
  val kundennummer = selectedColOfRow.map(_.getOrElse("USCU_L01"))
  val query2 = sql"select KUNNR,NAME1,ORT01,PSTLZ from KNA1 where KUNNR=${kundennummer}"
  val tableTarget = Table.datasource(query2).selectionMode(SelectionMode.None).build


    val button = Button().text("Go to page 2").build

  val page1 = Page().title("Customer Selection").content(button above filter above table).build()
  val page2 = Page().title("Customer Details").showNavButton(true).content(tableTarget).build()


  val pageTransitions: EventSource[PageTransition] = EventSource()


  table.selectedRows.observe(_ => pageTransitions := PageTransition(StaticBuilder(page2)))
  button.clicks.observe(_ => pageTransitions := PageTransition(StaticBuilder(page2)))
  page2.navButtonPress.observe(_ => pageTransitions := PageTransition(StaticBuilder(page1), PageTransitionEffect.SlideRight))
  //page2.navButtonPress.observe(_ => pageTransitions := PageTransition(StaticBuilder(page1.build()), PageTransitionEffect.SlideRight))
  val render = App().initialPage(page1).pageTransitions(pageTransitions).build

/*  val query = sql"""select KUNNR, NAME1, ORT01, PSTLZ from KNA1"""  //contains(KUNNR, '', fuzzy(0.1) and contains(name1, '', fuzzy(0.1)) and  contains(ort01, '', fuzzy(0.1)) and contains(pstlz, '', fuzzy(0.1))"""
  val filter = FilterBar.datasource(query).build

//val render = App().initialPage(Page().content(filter above table)).build

  val render = App().initialPage(Page().content(filter above helloLabel above input)).build*/
}
