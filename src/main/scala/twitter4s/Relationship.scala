package twitter4s
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
  
  /**
   * @since Twitter4S 2.0.0
   */
  def isSourceWantRetweets: Boolean
}