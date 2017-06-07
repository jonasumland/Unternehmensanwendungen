
// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Unternehmensanwendungen/Aufgaben/Unternehmensanwendungen/conf/routes
// @DATE:Wed Jun 07 14:38:42 CEST 2017


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
