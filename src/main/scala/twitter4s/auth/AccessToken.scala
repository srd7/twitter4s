package twitter4s.auth

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
object AccessToken {
  def apply(token: String, tokenSecret: String) = {
    new twitter4j.auth.AccessToken(token, tokenSecret)
  }
}