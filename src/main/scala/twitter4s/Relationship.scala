package twitter4s

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait Relationship extends TwitterResponse {
  def sourceUserId: Long
  
  def targetUserId: Long
  
  def isSourceBlockingTarget: Boolean
  
  def sourceUserScreenName: String
  
  def targetUserScreenName: String
  
  def isSourceFollowingTarget: Boolean
  
  def isTargetFollowingSource: Boolean
  
  def isSourceFollowedByTarget: Boolean
  
  def isTargetFollowedBySource: Boolean
  
  def isSourceNotificationsEnabled: Boolean
}