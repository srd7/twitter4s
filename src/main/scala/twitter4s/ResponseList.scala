package twitter4s
import scala.collection.mutable.Buffer
import twitter4j.RateLimitStatus

trait ResponseList[T] {
  def listAsScala:Buffer[T]
  
  def rateLimitStatus:RateLimitStatus
  
  def featureSpecificRateLimitStatus:RateLimitStatus
  
  def accessLevel:Int
  
  def apply(idx: Int):T
  
  def tw4jObj:Any
}