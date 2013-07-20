package twitter4s.api.impl

import twitter4j.Paging
import twitter4s.UserList
import twitter4s.api.ListsResources
import scala.util.Either
import twitter4s.Users
import twitter4s.PagableResponseList
import twitter4s.Twitter
import twitter4s.ResponseList
import twitter4s.User

trait ListsResourcesImpl extends ListsResources {
  self: Twitter => 
    
  /**
   * {@inheritDoc}
   */
  def createUserList(listName: String, isPublicList: Boolean, description: String): UserList = {
    twitter4jObj.createUserList(listName, isPublicList, description)
  }

  /**
   * {@inheritDoc}
   */
  def updateUserList(listId: Int, newListName: String, isPublicList: Boolean, newDescription: String): UserList = {
    twitter4jObj.updateUserList(listId, newListName, isPublicList, newDescription)
  }

  /**
   * {@inheritDoc}
   */
  def getUserLists(
      listOwnerSpecificUser: User.SpecificInfo,
      cursor: Long): PagableResponseList[twitter4j.UserList] = {
    require(listOwnerSpecificUser != null)
    
    // TODO cursorは指定しなくなった
    // TODO PagableResponseListからResponseListに戻り値が変更
    listOwnerSpecificUser match {
      case Right(listOwnerUserId) => null//twitter4jObj.getUserLists(listOwnerUserId, cursor)
      case Left(listOwnerScreenName) => null//twitter4jObj.getUserLists(listOwnerScreenName, cursor)
    }
  }

  /**
   * {@inheritDoc}
   */
  def showUserList(listId: Int): UserList = {
    twitter4jObj.showUserList(listId)
  }

  /**
   * {@inheritDoc}
   */
  def destroyUserList(listId: Int): UserList = {
    twitter4jObj.destroyUserList(listId)
  }

  /**
   * {@inheritDoc}
   */
  def getUserListStatuses(listId: Int, paging: twitter4j.Paging): ResponseList[twitter4j.Status] = {
    twitter4jObj.getUserListStatuses(listId, paging)
  }

  /**
   * {@inheritDoc}
   */
  def getUserListMemberships(
      listMemberSpecificUser: User.SpecificInfo = null,
      cursor: Long,
      filterToOwnedLists: java.lang.Boolean = null): PagableResponseList[twitter4j.UserList] = {
    (Option(listMemberSpecificUser)) match {
      case None => twitter4jObj.getUserListMemberships(cursor)
      case Some(listMemberSpecificUser) => (listMemberSpecificUser, Option(filterToOwnedLists)) match {
        case (Right(listMemberId), None) => twitter4jObj.getUserListMemberships(listMemberId, cursor)
        case (Right(listMemberId), Some(filterToOwnedLists)) => twitter4jObj.getUserListMemberships(listMemberId, cursor, filterToOwnedLists)
        case (Left(listMemberScreenName), None) => twitter4jObj.getUserListMemberships(listMemberScreenName, cursor)
        case (Left(listMemberScreenName), Some(filterToOwnedLists)) => twitter4jObj.getUserListMemberships(listMemberScreenName, cursor, filterToOwnedLists)
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  def getUserListSubscriptions(cursor: Long, listMemberScreenName: String): PagableResponseList[twitter4j.UserList] = {
    twitter4jObj.getUserListSubscriptions(listMemberScreenName, cursor)
  }

  /**
   * {@inheritDoc}
   */
  def getAllUserLists(specificUser: User.SpecificInfo): ResponseList[twitter4j.UserList] = {
    require(specificUser != null)
    
    // TODO メソッド削除
//    specificUser match {
//      case Right(userId) => twitter4jObj.getAllUserLists(userId)
//      case Left(screenName) => twitter4jObj.getAllUserLists(screenName)
//    }
    null
  }
  
  /**
   * {@inheritDoc}
   */
  def getUserListMembers(listId: Int, cursor: Long): PagableResponseList[twitter4j.User] = {
    twitter4jObj.getUserListMembers(listId, cursor)
  }
  
  /**
   * {@inheritDoc}
   */
  def addUserListMember(listId: Int, userId: Long): UserList = {
    twitter4jObj.addUserListMember(listId, userId)
  }
  
  /**
   * {@inheritDoc}
   */
  def addUserListMembers(
      listId: Int,
      specificUsers: Users.SpecificInfo): UserList = {
    require(specificUsers != null)
    
    specificUsers match {
      case Right(userIds) => twitter4jObj.addUserListMembers(listId, userIds)
      case Left(screenNames) => twitter4jObj.addUserListMembers(listId, screenNames)
    }
  }

  /**
   * {@inheritDoc}
   */
  def deleteUserListMember(listId: Int, userId: Long): UserList = {
    twitter4jObj.deleteUserListMember(listId, userId)
  }
  
  /**
   * {@inheritDoc}
   */
  def showUserListMembership(listId: Int, userId: Long): User = {
    twitter4jObj.showUserListMembership(listId, userId)
  }
  
  /**
   * {@inheritDoc}
   */
  def getUserListSubscribers(listId: Int, cursor: Long): PagableResponseList[twitter4j.User] = {
    twitter4jObj.getUserListSubscribers(listId, cursor)
  }

  /**
   * {@inheritDoc}
   */
  def createUserListSubscription(listId: Int): UserList = {
    twitter4jObj.createUserListSubscription(listId)
  }

  /**
   * {@inheritDoc}
   */
  def destroyUserListSubscription(listId: Int): UserList = {
    twitter4jObj.destroyUserListSubscription(listId)
  }

  /**
   * {@inheritDoc}
   */
  def showUserListSubscription(listId: Int, userId: Long): User = {
    twitter4jObj.showUserListSubscription(listId, userId)
  }
}