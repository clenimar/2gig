
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/clenimar/Desktop/workspace/2gig/conf/routes
// @DATE:Mon Oct 26 14:04:41 BRT 2015


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
