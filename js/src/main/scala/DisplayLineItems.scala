

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


@MarmolataClient(com.sap.marmolata.data.query.untyped.QueryExecAPI)
object DisplayLineItems extends MarmolataShell {

  val query = sql"select KUNNR , NAME1,ORT01,PSTLZ from KNA1"
  val filter = FilterBar.datasource(query).build

  val table = Table.datasource(filter.output).selectionMode(SelectionMode.Single).build
  //val render = App().initialPage(Page().content(filter above table)).build

  val button = Button().text("Go to page 2").build
    val page1 = Page().title("Page 1").content(button above filter above table)
    val page2 = Page().title("Page 2").showNavButton(true).content(Label("Hello 2")).build()


    val pageTransitions: EventSource[PageTransition] = EventSource()
    import com.sap.marmolata.utils.builder.StaticBuilder
    button.clicks.observe(_ => pageTransitions := PageTransition(StaticBuilder(page2)))
    page2.navButtonPress.observe(_ => pageTransitions := PageTransition(StaticBuilder(page1.build()), PageTransitionEffect.SlideRight))

    val render = App().initialPage(page1).pageTransitions(pageTransitions).build

/*  val query = sql"""select KUNNR, NAME1, ORT01, PSTLZ from KNA1"""  //contains(KUNNR, '', fuzzy(0.1) and contains(name1, '', fuzzy(0.1)) and  contains(ort01, '', fuzzy(0.1)) and contains(pstlz, '', fuzzy(0.1))"""
  val filter = FilterBar.datasource(query).build

  type Row = (String, String, Int)
  val input:Table[Row] =
    Table[Row]()
      .title(Label("Available persons").build)
      .visibleRowCountMode(VisibleRowCountMode.Fixed)
      .selectionMode(SelectionMode.Single)
      .columns(
        Column().headingWithLabel("Name").controlFromText((a: Row) => a._1).build,
        Column().headingWithLabel("City").controlFromText((a: Row) => a._2).build,
        Column().headingWithLabel("Age").controlFromText((a: Row) => a._3.toString).build
      )
      .content(StaticRowProvider(Seq(("Hans", "Potsdam", 24), ("Julia", "Berlin", 23)))).build()
  val helloLabel = Label(
    input.selectedRows.map(rows => {
      rows.headOption.map(row => s"hello ${row._1}").getOrElse("")
    })).build

  val render = App().initialPage(Page().content(filter above helloLabel above input)).build*/
}
