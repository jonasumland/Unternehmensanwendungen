package jdbc

import java.sql.DriverManager
import java.sql.Connection

/**
  * A Scala JDBC connection example by Alvin Alexander,
  * http://alvinalexander.com
  */
object ScalaJdbcConnectSelect {

  def main(args: Array[String]) {
    // connect to the database named "mysql" on the localhost
    val url = "jdbc:sap://192.168.31.39:30215/?currentschema=SAPHPB"
    val username = "STUDENTS"
    val password = "Enterprise2017"

    // there's probably a better way to do this
    var connection:Connection = null

    try {
      // make the connection
      Class.forName("com.sap.db.jdbc.Driver")
      connection = DriverManager.getConnection(url, username, password)

      // create the statement, and run the select query
      val statement = connection.createStatement()
      val resultSet = statement.executeQuery("SELECT DISTINCT MANDT FROM VBAK")
      while ( resultSet.next() ) {
        val mandt = resultSet.getString("MANDT")
        println("mandt= " + mandt)
      }
    } catch {
      case e: Throwable => e.printStackTrace
    }
    connection.close()
  }

}