package twitter4s.internal.json

import twitter4s.UserList
import java.net.URI
import twitter4s.AccessLevel

case class UserListImpl(twt4jUserList: twitter4j.UserList) extends UserList {
  type Tw4jResponse = twitter4j.UserList

  def compare(that: UserList) = this.id - that.id

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