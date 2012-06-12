package twitter4s.api

import twitter4j.User
import twitter4s.ResponseList
import twitter4j.IDs

trait BlockMethods {
	def createBlock(screenName: Option[String] = None, userId: Option[Long] = None): User
	
	def destroyBlock(screenName: Option[String] = None, userId: Option[Long] = None): User
	
	def existsBlock(screenName: Option[String] = None, userId: Option[Long] = None): Boolean
	
	def getBlockingUsers(page: Option[Int]): ResponseList[User]
	
	def getBlockingUsersIDs: IDs
}