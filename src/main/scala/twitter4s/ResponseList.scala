package twitter4s
import scala.collection.mutable.Buffer
import twitter4j.RateLimitStatus

trait ResponseList[T] extends TwitterResponse {
  /**
   * get converted scala list from twitter4j.ResponseList
   * 
   * @return converted list
   * @since Twitter4S 1.0.0
   */
  def listAsScala:Buffer[T]
  
  def featureSpecificRateLimitStatus:RateLimitStatus
  
  def apply(idx: Int):T
}