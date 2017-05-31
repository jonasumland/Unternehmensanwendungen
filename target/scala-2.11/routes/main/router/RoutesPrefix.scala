
// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Studium/UNA/Unternehmensanwendungen/conf/routes
// @DATE:Wed May 31 14:20:28 CEST 2017


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
