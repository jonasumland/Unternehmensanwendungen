import java.sql.DriverManager
import java.sql.Connection
import java.sql.ResultSet

object sqlRunner {

  def buildMap(queryResult: ResultSet, colNames: Seq[String]): Option[Map[String, Object]] =
    if (queryResult.next())
      Some(colNames.map(n => n -> queryResult.getObject(n)).toMap)
    else
      None

  def realize(queryResult: ResultSet): Vector[Map[String, Object]] = {
    val md = queryResult.getMetaData
    val colNames = (1 to md.getColumnCount) map md.getColumnName
    Iterator.continually(buildMap(queryResult, colNames)).takeWhile(!_.isEmpty).map(_.get).toVector
  }

  def runSql(query: String)  : Vector[Map[String, Object]] = {
    // connect to the database named "mysql" on the localhost
    val url = "jdbc:sap://192.168.31.39:30215/?currentschema=SAPHPB"
    val username = "STUDENTS"
    val password = "Enterprise2017"

    // there's probably a better way to do this
    var connection:Connection = null
    var result: Vector[Map[String, Object]] = null

    try {
      // make the connection
      Class.forName("com.sap.db.jdbc.Driver")
      connection = DriverManager.getConnection(url, username, password)

      // create the statement, and run the select query
      val statement = connection.createStatement()
      val rs = statement.executeQuery(query)
      result = realize(rs)
    } catch {
      case e: Throwable => {
        e.printStackTrace
      }
    }
    connection.close()
    return(result)
  }

}
