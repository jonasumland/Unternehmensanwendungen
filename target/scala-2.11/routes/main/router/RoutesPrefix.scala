
// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Studium/UNA/Unternehmensanwendungen/conf/routes
// @DATE:Tue Jun 06 16:10:16 CEST 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
