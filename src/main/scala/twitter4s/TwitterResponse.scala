package twitter4s

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait TwitterResponse {
  /**
   * Type annotation is twitter4j original response
   */
  type Tw4jResponse <: twitter4j.TwitterResponse

  /**
   * get rate limit status from twitter response.
   * 
   * @return rate limit status from twitter response.
   * @since Twitter4S 1.0.0
   */
  def rateLimitStatus:RateLimitStatus

  /**
   * get access level
   * 
   * @return access level from twitter response.
   * @since Twitter4S 1.0.0
   */
  def accessLevel: Int

  /**
   * get twitter4j original response object.
   * 
   * @return response object.
   * @since Twitter4S 1.0.0
   */
  def tw4jObj:Tw4jResponse
}