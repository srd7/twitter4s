package twitter4s.api

import twitter4j.User
import twitter4j.Relationship
import twitter4j.IDs
import twitter4s.ResponseList
import twitter4j.Friendship

trait FriendshipMethods {
	def createFriendship(screenName: Option[String] = None, userId: Option[Long] = None, follow: Option[Boolean] = None): User
	
	def destroyFriendship(screenName: Option[String] = None, userId: Option[Long] = None): User
	
	def existsFriendship(userA: User, userB: User): Boolean
	
	def showFriendship(sourceScreenName: Option[String] = None, targetScreenName: Option[String] = None, sourceId: Option[Long] = None, targetId: Option[Long] = None): Relationship
	
	def getIncomingFriendships(cursor: Long): IDs
	
	def getOutgoingFriendships(cursor: Long): IDs
	
	def lookupFriendships(screenNames: Option[Array[String]] = None, ids: Option[Array[Long]] = None): ResponseList[Friendship]
	
	def updateFriendship(enableDeviceNotification: Boolean, retweets: Boolean, screenName: Option[String] = None, userId: Option[Long] = None): Relationship
	
	def getNoRetweetIds: IDs
}