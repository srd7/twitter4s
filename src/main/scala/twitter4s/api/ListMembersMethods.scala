package twitter4s.api

import twitter4j.User
import twitter4j.UserList
import twitter4s.PagableResponseList

trait ListMembersMethods {
	def getUserListMembers(listId: Int, cursor: Long): PagableResponseList[User]
	
	def addUserListMember(listId: Int, userId: Long): UserList
	
	def addUserListMembers(listId: Int, userIds: Option[Array[Long]] = None, screenNames: Option[Array[String]] = None): UserList
	
	def deleteUserListMember(listId: Int, userId: Long): UserList
	
	def showUserListMembership(listId: Int, userId: Long): User
}