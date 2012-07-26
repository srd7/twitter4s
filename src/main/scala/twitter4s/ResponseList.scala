package twitter4s
import scala.collection.mutable.Buffer
import twitter4j.RateLimitStatus

trait ResponseList[T] extends TwitterResponse {
  type Tw4jResponse = twitter4j.ResponseList[T]
  
  def listAsScala:Buffer[T]
  
  def featureSpecificRateLimitStatus:RateLimitStatus
  
  def apply(idx: Int):T
}