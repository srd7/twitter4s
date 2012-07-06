package twitter4s.api

import twitter4j.UserList
import twitter4s.PagableResponseList
import twitter4j.Paging
import twitter4s.ResponseList
import twitter4j.Status

trait ListMethods {
	def createUserList(listName: String, isPublicList: Boolean, description: String): UserList
	
	def updateUserList(listId: Int, newListName: String, isPulibcList: Boolean, newDescription: String): UserList
	
	def getUserLists(cursor: Long, listOwnerScreenName: Option[String] = None, listOwnerUserId: Option[Long] = None): PagableResponseList[UserList]
	
	def showUserList(listId: Int): UserList
	
	def destroyUserList(listId: Int): UserList
	
	def getUserListStatuses(listId: Int, paging: Paging): ResponseList[Status]
	
	def getUserListMemberships(cursor: Long, listMemberId: Option[Long] = None, listMemberScreenName: Option[String] = None, filterToOwnedLists: Option[Boolean]): PagableResponseList[UserList]
	
	def getUserListSubscriptions(cursor: Long, listMemberScreenName: String): PagableResponseList[UserList]
	
	def getAllUserLists(screenName: Option[String] = None, userId: Option[Long] = None): ResponseList[UserList]
}