package twitter4s.internal.json

import twitter4s.Relationship

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class RelationshipImpl(twt4jRelationship: twitter4j.Relationship) extends Relationship {
  type Tw4jResponse = twitter4j.Relationship

  def sourceUserId = twt4jRelationship.getSourceUserId()

  def targetUserId = twt4jRelationship.getTargetUserId()

  def isSourceBlockingTarget = twt4jRelationship.isSourceBlockingTarget()

  def sourceUserScreenName = twt4jRelationship.getSourceUserScreenName()

  def targetUserScreenName = twt4jRelationship.getTargetUserScreenName()

  def isSourceFollowingTarget = twt4jRelationship.isSourceFollowingTarget()

  def isTargetFollowingSource = twt4jRelationship.isTargetFollowingSource()

  def isSourceFollowedByTarget = twt4jRelationship.isSourceFollowedByTarget()

  def isTargetFollowedBySource = twt4jRelationship.isTargetFollowedBySource()

  def isSourceNotificationsEnabled = twt4jRelationship.isSourceNotificationsEnabled()

  def rateLimitStatus = RateLimitStatusImpl(twt4jRelationship.getRateLimitStatus())

  def accessLevel = twt4jRelationship.getAccessLevel()

  def tw4jObj = twt4jRelationship

}