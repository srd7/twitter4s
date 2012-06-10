package twitter4s.auth
import twitter4j.auth.RequestToken
import twitter4j.auth.AccessToken

trait OAuthSupport {
  def setOAuthConsumer(consumerKey: String, consumerSecret: String): Unit
  
  def getOAuthRequestToken(callbackURL: Option[String], xAuthAccessType: Option[String]): RequestToken
  
  def getOAuthAccessToken(oauthVerifier: Option[String], requestToken: Option[RequestToken], screenName: Option[String], password: Option[String]): AccessToken
  
  def setOAuthAccessToken(accessToken: AccessToken)
}