package twitter4s.internal.json
/*
 * Copyright (C) 2012 Shinsuke Abe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import twitter4s.Relationship
import twitter4s.AccessLevel

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

  def accessLevel = AccessLevel(twt4jRelationship.getAccessLevel())

  def tw4jObj = twt4jRelationship
  
  /**
   * @since Twitter4S 2.0.0
   */
  def isSourceWantRetweets = twt4jRelationship.isSourceWantRetweets()

  /**
   * checks if source user can send dm to target user
   *
   * @since Twitter4S 2.1.0
   */
  def canSourceDm: Boolean = twt4jRelationship.canSourceDm
}