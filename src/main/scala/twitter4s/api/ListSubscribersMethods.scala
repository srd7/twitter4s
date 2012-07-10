package twitter4s.api

import twitter4j.PagableResponseList
import twitter4j.User
import twitter4j.UserList

trait ListSubscribersMethods {
	def getUserListSubscribers(listId: Int, cursor: Long): PagableResponseList[User]
	
	def createUserListSubscription(listId: Int): UserList
	
	def destroyUserListSubscription(listId: Int): UserList
	
	def showUserListSubscription(listId: Int, userId: Long): User
}