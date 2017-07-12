import boopickle.DefaultBasic._

object Credit {
  case class Offer(str: String, raten: Double, dbl: Double)
  implicit val pickler: Pickler[Offer] = PicklerGenerator.generatePickler[Offer]
}

case class DataSet(bank: String, zins: Double, grenzen: Double, raten: Double, studentMax: Int)

trait CreditRequestApi  {
  def getQuotes(Amount:String, Income: String, Duration: String, Marital: String, Employment: String ): Seq[Credit.Offer]
}


trait CreditRequestApiImpl extends CreditRequestApi {
  val rnd = scala.util.Random

  override def getQuotes(Amount:String, Income: String, Duration: String, Marital: String, Employment: String): Seq[Credit.Offer] = {
    val amount = Amount.toInt
    val income = Income.toInt
    val duration = Duration.toInt
    val bank : List[String] = List("Commerzbank", "Deutsche Bank", "Postbank",
      "Sparkasse", "Consorsbank", "ING-DiBa", "Comdirect", "Targobank")
    val zins : List[Double] = List(8.7, 8.8, 9.1, 11.1, 9.3, 10.0, 8.2, 9.6)
    var grenzen : List[Double] = List(0.05, 0.15, 0.3, 0.4, 0.2, 0.3, 0.45, 0.1)
    val maxStudent : List[Int] = List(1000, 1500, 1250, 1800, 2000, 2500, 500, 1500)
    val sollzins = zins.map( x => (amount/100)*x*(duration)/12)
    val raten = sollzins.map( x => (x+amount) / duration)
    if(Marital == "Married") {
      grenzen = grenzen.map(_ * 2)
    }
    var dataSets = ((0 until (bank.length)) map { i => DataSet(bank(i), zins(i), grenzen(i), raten(i), maxStudent(i)) })
    dataSets = dataSets.filter(dS => (dS.raten/income)<= dS.grenzen && (Employment != "Student" || amount <= dS.studentMax ))
    val offer = dataSets map (dS => Credit.Offer(dS.bank,dS.raten,dS.zins))
    return offer.asInstanceOf[Seq[Credit.Offer]].sortWith(_.raten < _.raten)
  }
}
