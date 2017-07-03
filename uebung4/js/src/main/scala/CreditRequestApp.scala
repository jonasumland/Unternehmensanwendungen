import com.sap.marmolata.utils.validation.withFuture.StdValidators
import com.sap.marmolata.app.MarmolataClient
import com.sap.marmolata.app.client.MarmolataShell
import com.sap.marmolata.ui._
import com.sap.marmolata.ui.dataImplicits._
import com.sap.marmolata.ui.extensions.implicitExtensions._
import com.sap.marmolata.ui.layout._
import com.sap.marmolata.utils.validation.{Result, Success}
import com.sap.marmolata.ui.suggestions.{SimpleSuggestion, SimpleSuggestionProvider}
import com.sap.marmolata.utils.Uri
import reactive.library._

import scala.concurrent.Future
import scala.language.existentials
import scala.concurrent.ExecutionContext.Implicits.global

@MarmolataClient(CreditRequestApi)
object CreditRequestApp extends MarmolataShell {

  import reactive.library.unsafeImplicits._
  import com.sap.marmolata.utils.builder.StaticBuilder

  val amount = Input[Int]()
    .initialValue("0")
    .validator(
      StdValidators.ltEq(1000000)
    ).build

    val duration = Input[Int]()
    .initialValue("12")
    .validator(
      (StdValidators.gtEq(1) and StdValidators.ltEq(48))
    ).build

  val suggestion = SimpleSuggestion().value("suggestion").build
  val sequence = Seq(suggestion)
  val signal = Signal.Const(sequence)
  val purpose = Input[String]()
    .initialValue("Car")
    .validator(
      StdValidators.longerThan(2)
    ).suggestionProvider(
      SimpleSuggestionProvider[String](signal)
    ).build

  val income = Input[Int]()
    .initialValue("5000")
    .validator(
      StdValidators.gtEq(500)
    ).build

  val name = Input[String]()
    .initialValue("Enter Name here")
    .validator(
      StdValidators.longerThan(2)
    )
    .build

  val employment = Input[String]()
    .initialValue("employee")
    .validator(
      StdValidators.longerThan(2)
    )
    .build

  val martial = Input[String]()
    .initialValue("single")
    .validator(
      StdValidators.longerThan(2)
    )
    .build

  def toBool(s: Signal[Option[Result[_]]]): Signal[Boolean] = s.map(_ match {
     case Some(Success(v)) => true
     case _ => false
    })
  //see https://sapui5.hana.ondemand.com/iconExplorer.html for more icons
  val uri = new Uri("sap-icon://accept")
  val button = Button()
    .text("Calculate")
    .icon(Signal.Const(Option(uri)))
    .enabled(
      (toBool(amount.validatedValue) |@| toBool(duration.validatedValue) |@| toBool(income.validatedValue) ).map(
        {case (a,b,c) => a && b && c }
      )
    ).build

  val formContainerRequest =
    FormContainer().title(FormTitle().text("General Data")).elements(
      Seq(
        FormElement().label("Amount (EUR)").fields(amount),
        FormElement().label("Duration (Month)").fields(duration),
        FormElement().label("Purpose").fields(purpose)
      )
    )

  val formContainerPersonalData =
    FormContainer().title(FormTitle().text("Personal Data")).elements(
      Seq(
        FormElement().label("Name").fields(name),
        FormElement().label("Employment Status").fields(employment),
        FormElement().label("Martial Status").fields(martial),
        FormElement().label("Income (EUR)").fields(income)
      )
    )

  val tblRowProvider: Var[RowProvider[Credit.Offer]] = Var(EmptyRowProvider)

  val table = Table[Credit.Offer]()
    .columns(
        Column()
          .heading("Bank")
          .representation[Credit.Offer, String](row => s"${row.str}")
          .build(),
        Column()
          .heading("Zinssatz")
          .representation[Credit.Offer, String](row => s"${"%.2f".format(row.dbl)}")
          .build(),
      Column()
        .heading("Monthly Rate")
        .representation[Credit.Offer, String](row => s"150")
        .build()
    )
    .selectionMode(SelectionMode.None)
    .content(tblRowProvider)
  val page1 =
    Page().title("Credit request")
      .content(
        Form().title(FormTitle()
          .text("Credit Request")
        ).containers(
          Seq(
            formContainerRequest,
            formContainerPersonalData
          )
        )  above
          button.asBuilder
      ).build()



  val page2 =
    Page().title("Credit offer list")
      .showNavButton(true)
      .content(table)
      .build()

  val pageTransitions: EventSource[PageTransition] = EventSource()

  button.clicks.observe(_ => {
    pageTransitions := PageTransition(StaticBuilder(page2))
    callService()
  })

  page2.navButtonPress.observe(_ => {
    pageTransitions := PageTransition(StaticBuilder(page1), PageTransitionEffect.SlideRight)
  })

  def callService(): Unit = {
    val result: Future[Seq[Credit.Offer]] = Server.getQuotes().call
    val resRowProvider: Future[RowProvider[Credit.Offer]] = result.map(rows => {
      StaticRowProvider(rows)
    })
    resRowProvider.foreach(rowProvider => {tblRowProvider := rowProvider})
  }

  val render = App().initialPage(page1).pageTransitions(pageTransitions).build()
}
