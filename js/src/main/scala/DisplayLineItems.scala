

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

  val query = sql"select KUNNR , NAME1,ORT01,PSTLZ from KNA1"
  val filter = FilterBar.datasource(query).build
  val table = Table.datasource(filter.output).selectionMode(SelectionMode.Single).build


  val kunnr = table.selectedRows.map(rows => {
    rows.headOption.map(row => s"SELECT KUNNR FROM KNA1 WHERE KUNNR = '${row.KUNNR.value}'").getOrElse(s"SELECT KUNNR FROM KNA1 WHERE KUNNR = 'NULL'")})
  val selectedColOfRow: Signal[Option[String]] = table.selectedRows.map(_.headOption.map(_.KUNNR.value))
  val query2 = sql"select KUNNR , NAME1,ORT01,PSTLZ from KNA1 where KUNNR=${selectedColOfRow.map(_.getOrElse(""))}"
  val filter2 = FilterBar.datasource(query2).build
  val tableTarget = Table.datasource(filter2.output).selectionMode(SelectionMode.None).build

    val button = Button().text("Go to page 2").build

  val page1 = Page().title("Page 1").content(button above filter above table).build()
  val page2 = Page().title("Page 2").showNavButton(true).content(Label("Hello page 2").build).build()


  val pageTransitions: EventSource[PageTransition] = EventSource()

  button.clicks.observe(_ => pageTransitions := PageTransition(StaticBuilder(page2)))
  page2.navButtonPress.observe(_ => pageTransitions := PageTransition(StaticBuilder(page1), PageTransitionEffect.SlideRight))
  //page2.navButtonPress.observe(_ => pageTransitions := PageTransition(StaticBuilder(page1.build()), PageTransitionEffect.SlideRight))
  val render = App().initialPage(page1).pageTransitions(pageTransitions).build

/*  val query = sql"""select KUNNR, NAME1, ORT01, PSTLZ from KNA1"""  //contains(KUNNR, '', fuzzy(0.1) and contains(name1, '', fuzzy(0.1)) and  contains(ort01, '', fuzzy(0.1)) and contains(pstlz, '', fuzzy(0.1))"""
  val filter = FilterBar.datasource(query).build

//val render = App().initialPage(Page().content(filter above table)).build

  val render = App().initialPage(Page().content(filter above helloLabel above input)).build*/
}
