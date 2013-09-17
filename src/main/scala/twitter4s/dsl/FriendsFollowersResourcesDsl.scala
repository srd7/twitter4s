package twitter4s.dsl

import twitter4s.api.FriendsFollowersResources
import twitter4s._
import twitter4s.api.impl.FriendsFollowersResourcesImpl

/**
 * @author mao.instantlife at gmail.com
 */
trait FriendsFollowersResourcesDsl extends Twitter4sDslBase with FriendsFollowersResources {
  type ResourcesType = FriendsFollowersResourcesImpl
  protected val twitter4sResources = new Twitter(twitter4jResources) with ResourcesType

  // bellow methods implements are a cliche.
  def updateFriendship(specificUser: User.SpecificInfo, enableDeviceNotification: Boolean, retweets: Boolean) = resources.updateFriendship(specificUser, enableDeviceNotification, retweets)
  def getFriendsList(specificUser: User.SpecificInfo, cursor: Long) = resources.getFriendsList(specificUser, cursor)
  def getFollowersList(specificUser: User.SpecificInfo, cursor: Long) = getFollowersList(specificUser, cursor)
  def showFriendship(sourceSpecificUser: User.SpecificInfo, targetSpecificUser: User.SpecificInfo) = showFriendship(sourceSpecificUser, targetSpecificUser)
  def getIncomingFriendships(cursor: Long) = resources.getIncomingFriendships(cursor)
  def getOutgoingFriendships(cursor: Long) = resources.getOutgoingFriendships(cursor)
  def lookupFriendships(specificUsers: Users.SpecificInfo) = resources.lookupFriendships(specificUsers)
  def destroyFriendship(specificUser: User.SpecificInfo) = resources.destroyFriendship(specificUser)
  def createFriendship(specificUser: User.SpecificInfo, follow: java.lang.Boolean = null) = resources.createFriendship(specificUser, follow)
  def getFriendsIDs(cursor: Long, specificUser: User.SpecificInfo = null) = resources.getFriendsIDs(cursor, specificUser)
  def getFollowersIDs(cursor: Long, specificUser: User.SpecificInfo = null) = resources.getFollowersIDs(cursor, specificUser)
}
