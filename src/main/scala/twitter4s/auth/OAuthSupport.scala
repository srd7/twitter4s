package twitter4s.auth
import twitter4j.auth.RequestToken
import twitter4j.auth.AccessToken

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait OAuthSupport {
  /**
   * sets the OAuth consumer key and consumer secret
   * <br/>This method call twitter4j.Twitter.setOAuthConsumer(consumerKey, consumerSecret)
   *
   * @param consumerKey OAuth consumer key
   * @param consumerSecret OAuth consumer secret
   * @throws IllegalStateException when OAuth consumer has already been set, or the instance is using basic authorization
   * @since Twitter4S 1.0.0
   */
  def setOAuthConsumer(consumerKey: String, consumerSecret: String): Unit
  
  def getOAuthRequestToken(callbackURL: Option[String], xAuthAccessType: Option[String]): RequestToken
  
  def getOAuthAccessToken(oauthVerifier: Option[String], requestToken: Option[RequestToken], screenName: Option[String], password: Option[String]): AccessToken
  
  def setOAuthAccessToken(accessToken: AccessToken)
}