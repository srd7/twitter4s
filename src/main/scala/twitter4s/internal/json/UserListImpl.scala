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
import twitter4s.UserList
import java.net.URI
import twitter4s.AccessLevel

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class UserListImpl(twt4jUserList: twitter4j.UserList) extends UserList {
  type Tw4jResponse = twitter4j.UserList

  def compare(that: UserList) = (this.id - that.id).toInt

  def id = twt4jUserList.getId()

  def name = twt4jUserList.getName()

  def fullName = twt4jUserList.getFullName()

  def slug = twt4jUserList.getSlug()

  def description = twt4jUserList.getDescription()

  def subscriberCount = twt4jUserList.getSubscriberCount()

  def memberCount = twt4jUserList.getMemberCount()

  def uri = twt4jUserList.getURI()

  def isPublic = twt4jUserList.isPublic()

  def user = UserImpl(twt4jUserList.getUser())

  def isFollowing = twt4jUserList.isFollowing()

  def rateLimitStatus = RateLimitStatusImpl(twt4jUserList.getRateLimitStatus())

  def accessLevel = AccessLevel(twt4jUserList.getAccessLevel())

  def tw4jObj = twt4jUserList
}
