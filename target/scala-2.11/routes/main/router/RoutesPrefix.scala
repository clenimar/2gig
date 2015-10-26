
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/clenimar/workspace/activator-dist-1.3.6/2gig/conf/routes
// @DATE:Sun Oct 25 17:55:36 BRT 2015


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
