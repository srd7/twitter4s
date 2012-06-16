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
  /**
   * Get screenName from twitter object.
   * <br/>This method calls twitter4j.Twitter.getScreenName.
   * 
   * @return screen name
   * @since Twitter4S 1.0.0
   */
  def screenName: String
  
  /**
   * Get user id from twitter object.
   * <br/>This method calls twitter4j.getId.
   * 
   * @return user id
   * @since Twitter4S 1.0.0
   */
  def id: Long
  
  /**
   * Add rate limit status listener to twitter object.
   * <br/>This method calls twitter4j.addRateLimitStatusListener(listener)
   * 
   * @param listener (required) twitter4j.RateLimitStatusListener object for add.
   * @since Twitter4S 1.0.0
   */
  def addRateLimitStatusListener(listener: RateLimitStatusListener): Unit
  
  /**
   * Get authorization object from twitter object.
   * <br/>This method calls twitter4j.getAuthorization.
   * 
   * @return authorization object
   * @since Twitter4S 1.0.0
   */
  def authorization: Authorization
  
  /**
   * Get configuration object from twitter object.
   * <br/>This method calls twitter4j.getConfiguration.
   * 
   * @return configuration object
   * @since Twitter4S 1.0.0
   */
  def configuration: Configuration
  
  /**
   * Shotdown twitter object.
   * <br/>This method calls twitter4j.shotdown.
   * 
   * @since Twitter4S 1.0.0
   */
  def shotdown: Unit
}