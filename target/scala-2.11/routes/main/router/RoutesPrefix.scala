
// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Unternehmensanwendungen/Uebungen/conf/routes
// @DATE:Wed May 31 13:52:05 CEST 2017


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
