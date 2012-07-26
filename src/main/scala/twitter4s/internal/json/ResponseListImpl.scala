package twitter4s.internal.json

import scala.collection.JavaConverters.asScalaBufferConverter
import scala.collection.mutable.Buffer
import twitter4s.ResponseList

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class ResponseListImpl[T](twt4jResponseList: twitter4j.ResponseList[T]) extends ResponseList[T] {
  
  type Tw4jResponse = twitter4j.ResponseList[T]
  
  /**
   * scala converted list
   */
  def listAsScala = twt4jResponseList.asScala
  
  /**
   * rate limit status
   */
  def rateLimitStatus = twt4jResponseList.getRateLimitStatus()
  
  /**
   * feature specific rate limit status
   */
  def featureSpecificRateLimitStatus = twt4jResponseList.getFeatureSpecificRateLimitStatus()
  
  /**
   * access level
   */
  def accessLevel = twt4jResponseList.getAccessLevel()
  
  /**
   * Get object in scala converted list method.
   */
  def apply(idx: Int):T = listAsScala(idx)
  
  /**
   * Get Twitter4J Object
   */
  def tw4jObj = twt4jResponseList
}