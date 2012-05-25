package twitter4s.api

import twitter4j.IDs

trait FriendsFollowersMethods {
	def getFriendsIDs(cursor: Long, userId: Option[Long] = None, screenName: Option[String] = None): IDs
	
	def getFollowersIDs(cursor: Long, userId: Option[Long] = None, screenName: Option[String] = None): IDs
}