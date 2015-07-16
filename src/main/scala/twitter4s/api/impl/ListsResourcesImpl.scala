package twitter4s.api.impl

/*
 * Copyright (C) 2014 Shinsuke Abe
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
import twitter4s.api.ListsResources
import twitter4s.Users
import twitter4s.PagableResponseList
import twitter4s.Twitter
import twitter4s.ResponseList
import twitter4s.User

/**
 * @author mao.instantlife at gmail.com
 */
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
  def updateUserList(listSpecificInfo: UserList.SpecificInfo, newListName: String, isPublicList: Boolean, newDescription: String): UserList = {
    require(listSpecificInfo != null)

    listSpecificInfo match {
      case Left(listId) => twitter4jObj.updateUserList(listId, newListName, isPublicList, newDescription)
      case Right((listOwnerUser, slug)) =>
        require(listOwnerUser != null)

        listOwnerUser match {
          case Left(screenName) => twitter4jObj.updateUserList(screenName, slug, newListName, isPublicList, newDescription)
          case Right(userId) => twitter4jObj.updateUserList(userId, slug, newListName, isPublicList, newDescription)
        }
    }
  }

  /**
   * {@inheritDoc}
   */
  def getUserLists(listOwnerSpecificUser: User.SpecificInfo): ResponseList[twitter4j.UserList] = {
    require(listOwnerSpecificUser != null)

    listOwnerSpecificUser match {
      case Right(listOwnerUserId) => twitter4jObj.getUserLists(listOwnerUserId)
      case Left(listOwnerScreenName) => twitter4jObj.getUserLists(listOwnerScreenName)
    }
  }

  /**
   * {@inheritDoc}
   */
  def showUserList(listSpecificInfo: UserList.SpecificInfo): UserList = {
    require(listSpecificInfo != null)

    listSpecificInfo match {
      case Left(listId) => twitter4jObj.showUserList(listId)
      case Right((listOwnerUser, slug)) =>
        require(listOwnerUser != null)

        listOwnerUser match {
	      case Left(screenName) => twitter4jObj.showUserList(screenName, slug)
	      case Right(userId) => twitter4jObj.showUserList(userId, slug)
        }
    }
  }

  /**
   * {@inheritDoc}
   */
  def destroyUserList(listSpecificInfo: UserList.SpecificInfo): UserList = {
    require(listSpecificInfo != null)

    listSpecificInfo match {
      case Left(listId) => twitter4jObj.destroyUserList(listId)
      case Right((userSpecificInfo, slug)) => {
        require(userSpecificInfo != null)

        userSpecificInfo match {
          case Left(screenName) => twitter4jObj.destroyUserList(screenName, slug)
          case Right(userId) => twitter4jObj.destroyUserList(userId, slug)
        }
      }
    }

  }

  /**
   * {@inheritDoc}
   */
  def getUserListStatuses(listSpecificInfo: UserList.SpecificInfo, paging: twitter4j.Paging): ResponseList[twitter4j.Status] = {
    require(listSpecificInfo != null)

    listSpecificInfo match {
      case Left(listId) => twitter4jObj.getUserListStatuses(listId, paging)
      case Right((listOwnerUser, slug)) =>
        require(listOwnerUser != null)

        listOwnerUser match {
          case Left(screenName) => twitter4jObj.getUserListStatuses(screenName, slug, paging)
          case Right(userId) => twitter4jObj.getUserListStatuses(userId, slug, paging)
        }
    }
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
  def getUserListMembers(listSpecificInfo: UserList.SpecificInfo, cursor: Long): PagableResponseList[twitter4j.User] = {
    require(listSpecificInfo != null)

    listSpecificInfo match {
      case Left(listId) => twitter4jObj.getUserListMembers(listId, cursor)
      case Right((userSpecificInfo, slug)) => {
        require(userSpecificInfo != null)

        userSpecificInfo match {
          case Left(screenName) => twitter4jObj.getUserListMembers(screenName, slug, cursor)
          case Right(userId) => twitter4jObj.getUserListMembers(userId, slug, cursor)
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  def createUserListMember(listSpecificInfo: UserList.SpecificInfo, userId: Long): UserList = {
    require(listSpecificInfo != null)

    listSpecificInfo match {
      case Left(listId) => twitter4jObj.createUserListMember(listId, userId)
      case Right((specificUser, slug)) => {
        require(specificUser != null)

        specificUser match {
          case Left(screenName) => twitter4jObj.createUserListMember(screenName, slug, userId)
          case Right(specificUserId) => twitter4jObj.createUserListMember(specificUserId, slug, userId)
        }
      }
    }
  }

  /**
   * {@inheitDoc}
   */
  def createUserListMembers(listSpecificInfo: UserList.SpecificInfo, specificUsers: Users.SpecificInfo): UserList = {
    require(listSpecificInfo != null && specificUsers != null)

    listSpecificInfo match {
      case Left(listId) =>
        specificUsers match {
          case Left(screenNames) => twitter4jObj.createUserListMembers(listId, screenNames)
          case Right(userIds) => twitter4jObj.createUserListMembers(listId, userIds)
        }
      case Right((specificUser, slug)) =>
        require(specificUser != null)

        specificUser match {
          case Left(screenName) =>
            specificUsers match {
              case Left(screenNames) => twitter4jObj.createUserListMembers(screenName, slug, screenNames)
              case Right(userIds) => twitter4jObj.createUserListMembers(screenName, slug, userIds)
            }
          case Right(userId) =>
            specificUsers match {
              case Left(screenNames) => twitter4jObj.createUserListMembers(userId, slug, screenNames)
              case Right(userIds) => twitter4jObj.createUserListMembers(userId, slug, userIds)
            }
        }
    }
  }

  /**
   * {@inheritDoc}
   */
  def destroyUserListMember(listSpecificInfo: UserList.SpecificInfo, userId: Long): UserList = {
    require(listSpecificInfo != null)

    listSpecificInfo match {
      case Left(listId) => twitter4jObj.destroyUserListMember(listId, userId)
      case Right((userSpecificInfo, slug)) => {
        require(userSpecificInfo != null)

        userSpecificInfo match {
          case Left(screenName) => twitter4jObj.destroyUserListMember(screenName, slug, userId)
          case Right(ownerUserId) => twitter4jObj.destroyUserListMember(ownerUserId, slug, userId)
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  def showUserListMembership(listSpecificInfo: UserList.SpecificInfo, userId: Long): User = {
    require(listSpecificInfo != null)

    listSpecificInfo match {
      case Left(listId) => twitter4jObj.showUserListMembership(listId, userId)
      case Right((userSpecificInfo, slug)) => {
        require(userSpecificInfo != null)

        userSpecificInfo match {
          case Left(screenName) => twitter4jObj.showUserListMembership(screenName, slug, userId)
          case Right(ownerUserId) => twitter4jObj.showUserListMembership(ownerUserId, slug, userId)
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  def getUserListSubscribers(listSpecificInfo: UserList.SpecificInfo, cursor: Long): PagableResponseList[twitter4j.User] = {
    require(listSpecificInfo != null)

    listSpecificInfo match {
      case Left(listId) => twitter4jObj.getUserListSubscribers(listId, cursor)
      case Right((userSpecificInfo, slug)) => {
        require(userSpecificInfo != null)

        userSpecificInfo match {
          case Left(screenName) => twitter4jObj.getUserListSubscribers(screenName, slug, cursor)
          case Right(userId) => twitter4jObj.getUserListSubscribers(userId, slug, cursor)
        }
      }
    }

  }

  /**
   * {@inheritDoc}
   */
  def createUserListSubscription(listSpecificInfo: UserList.SpecificInfo): UserList = {
    require(listSpecificInfo != null)

    listSpecificInfo match {
      case Left(listId) => twitter4jObj.createUserListSubscription(listId)
      case Right((userSpecificInfo, slug)) => {
        require(userSpecificInfo != null)

        userSpecificInfo match {
          case Left(screenName) => twitter4jObj.createUserListSubscription(screenName, slug)
          case Right(userId) => twitter4jObj.createUserListSubscription(userId, slug)
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  def destroyUserListSubscription(listSpecificInfo: UserList.SpecificInfo): UserList = {
    require(listSpecificInfo != null)

    listSpecificInfo match {
      case Left(listId) => twitter4jObj.destroyUserListSubscription(listId)
      case Right((userSpecificInfo, slug)) => {
        require(userSpecificInfo != null)

        userSpecificInfo match {
          case Left(screenName) => twitter4jObj.destroyUserListSubscription(screenName, slug)
          case Right(userId) => twitter4jObj.destroyUserListSubscription(userId, slug)
        }
      }
    }

  }

  /**
   * {@inheritDoc}
   */
  def showUserListSubscription(listSpecificInfo: UserList.SpecificInfo, userId: Long): User = {
    require(listSpecificInfo != null)

    listSpecificInfo match {
      case Left(listId) => twitter4jObj.showUserListSubscription(listId, userId)
      case Right((userSpecificInfo, slug)) => {
        require(userSpecificInfo != null)

        userSpecificInfo match {
          case Left(screenName) => twitter4jObj.showUserListSubscription(screenName, slug, userId)
          case Right(ownerUserId) => twitter4jObj.showUserListSubscription(ownerUserId, slug, userId)
        }
      }
    }

  }
}
