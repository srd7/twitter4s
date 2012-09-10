package twitter4s

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
object Authorization {
  def apply(conf: twitter4j.conf.Configuration) = {
    twitter4j.auth.AuthorizationFactory.getInstance(conf)
  }
}