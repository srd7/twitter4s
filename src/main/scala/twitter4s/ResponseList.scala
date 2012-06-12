package twitter4s

import scala.collection.JavaConverters.asScalaBufferConverter
import scala.collection.mutable.Buffer

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class ResponseList[T](twt4jResponseList: twitter4j.ResponseList[T]) {
  
  /**
   * scala converted list
   */
  val listAsScala = twt4jResponseList.asScala
  
  /**
   * rate limit status
   */
  val rateLimitStatus = twt4jResponseList.getRateLimitStatus()
  
  /**
   * feature specific rate limit status
   */
  val featureSpecificRateLimitStatus = twt4jResponseList.getFeatureSpecificRateLimitStatus()
  
  /**
   * access level
   */
  val accessLevel = twt4jResponseList.getAccessLevel()
  
  /**
   * Get scala converted list method.
   */
  def apply():Buffer[T] = listAsScala
  
  /**
   * Get object in scala converted list method.
   */
  def apply(idx: Int):T = listAsScala(idx)
}