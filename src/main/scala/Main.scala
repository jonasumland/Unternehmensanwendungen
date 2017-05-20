/**
  * Created by saeed on 20.05.2017.
  */
object Main {
  def main(args: Array[String]) : Unit = {
    val set = sqlRunner.runSql("SELECT DISTINCT MANDT, ERDAT FROM VBAK")
    val columns = set.apply(0).keys
    for(m <- set) {
      columns.foreach((s: String) => println(m(s)))
    }
  }
}
