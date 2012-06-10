package twitter4s
import twitter4j.RateLimitStatusListener
import twitter4j.auth.Authorization
import twitter4j.conf.Configuration
import twitter4s.auth.OAuthSupport
import twitter4j.auth.AccessToken
import twitter4j.auth.RequestToken

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait TwitterBase extends AnyRef with OAuthSupport {
  def getScreenName: String
  
  def getId: Long
  
  def addRateLimitStatusListener(listener: RateLimitStatusListener): Unit
  
  def getAuthorization: Authorization
  
  def getConfiguration: Configuration
  
  def shotdown: Unit
  // TODO create trait HttpResponseListener
}