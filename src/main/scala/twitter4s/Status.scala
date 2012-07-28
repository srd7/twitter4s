package twitter4s
import twitter4j.Annotations

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait Status extends TwitterResponse with Twt with EntitySupport {
  def isTruncated: Boolean
  
  def inReplyToUserId: Long
  
  def inReplyToScreenName: String
  
  def isFavorited: Boolean
  
  def user: User
  
  def isRetweet: Boolean
  
  def retweetedStatus: Status
  
  def contributors: Array[Long]
  
  def retweetCount: Long
  
  def isRetweetedByMe: Boolean
  
  def annotations: Annotations
}