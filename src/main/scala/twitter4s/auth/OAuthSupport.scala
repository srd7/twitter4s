package twitter4s.auth

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
  
  def setOAuthConsumer(consumerKey: ConsumerKey): Unit
  
  def getOAuthRequestToken(callbackURL: String = null, xAuthAccessType: String = null): twitter4j.auth.RequestToken
  
  def getOAuthAccessToken(
      oauthVerifier: String = null,
      requestToken: twitter4j.auth.RequestToken = null,
      screenName: String = null,
      password: String = null): twitter4j.auth.AccessToken
  
  def setOAuthAccessToken(accessToken: twitter4j.auth.AccessToken)
}