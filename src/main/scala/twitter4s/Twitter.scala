package twitter4s
/*
 * Copyright (C) 2012 Shinsuke Abe
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
import twitter4s._
import api.impl._
import java.io.File
import java.io.InputStream
import java.util.Date
import twitter4j.api.HelpResources.Language
import twitter4j.auth.AccessToken
import twitter4j.auth.RequestToken
import twitter4j.Category
import twitter4j.Friendship
import twitter4j.Location
import twitter4j.RateLimitStatusListener
import twitter4j.TwitterFactory
import auth.ConsumerKey

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class Twitter(twitter4jObj: twitter4j.Twitter) extends TwitterBase with TwitterAPIs {
  
  /* TwitterBase method */
  /**
   * {@inheritDoc}
   */
  def screenName: String = {
    twitter4jObj.getScreenName()
  }
  
  /**
   * {@inheritDoc}
   */
  def id: Long = {
    twitter4jObj.getId()
  }
  
  /**
   * {@inheritDoc}
   */
  def addRateLimitStatusListener(listener: RateLimitStatusListener) {
    twitter4jObj.addRateLimitStatusListener(listener)
  }
  
  /**
   * {@inheritDoc}
   */
  def authorization: twitter4j.auth.Authorization = {
    twitter4jObj.getAuthorization()
  }
  
  /**
   * {@inheritDoc}
   */
  def configuration: twitter4j.conf.Configuration = {
    twitter4jObj.getConfiguration()
  }
  
  /**
   * {@inheritDoc}
   */
  def shotdown {
    twitter4jObj.shutdown()
  }
  
  /* OAuthSupport */
  /**
   * {@inheritDoc}
   */
  def setOAuthConsumer(consumerKey: String, consumerSecret: String) {
    twitter4jObj.setOAuthConsumer(consumerKey, consumerSecret)
  }
  
  /**
   * {@ineritDoc}
   */
  def setOAuthConsumer(consumerKey: ConsumerKey) {
    twitter4jObj.setOAuthConsumer(consumerKey.consumerKey, consumerKey.consumerSecret)
  }
  
  /**
   * {@inheritDoc}
   */
  def getOAuthRequestToken(callbackURL: String = null, xAuthAccessType: String = null): RequestToken = {
    (Option(callbackURL), Option(xAuthAccessType)) match {
      case (None, None) => twitter4jObj.getOAuthRequestToken()
      case (Some(callbackURL), None) => twitter4jObj.getOAuthRequestToken(callbackURL)
      case (Some(callbackURL), Some(xAuthAccessType)) => twitter4jObj.getOAuthRequestToken(callbackURL, xAuthAccessType)
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def getOAuthAccessToken(
      oauthVerifier: String = null,
      requestToken: RequestToken = null,
      screenName: String = null,
      password: String = null): AccessToken = {
    (Option(oauthVerifier), Option(requestToken), Option(screenName), Option(password)) match {
      case (None, None, None, None) => twitter4jObj.getOAuthAccessToken()
      case (Some(oauthVerifier), None, None, None) => twitter4jObj.getOAuthAccessToken(oauthVerifier)
      case (Some(oauthVerifier), Some(requestToken), None, None) => twitter4jObj.getOAuthAccessToken(requestToken, oauthVerifier)
      case (None, Some(requestToken), None, None) => twitter4jObj.getOAuthAccessToken(requestToken)
      case (None, None, Some(screenName), Some(password)) => twitter4jObj.getOAuthAccessToken(screenName, password)
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def setOAuthAccessToken(accessToken: AccessToken) {
    twitter4jObj.setOAuthAccessToken(accessToken)
  }
  
  /* AccountMethods */
  /**
   * {@inheritDoc}
   */
  def verifyCredentials: User = {
    twitter4jObj.verifyCredentials()
  }
  
  /**
   * {@inherited}
   */
  def updateProfileColors(
      profileBackgroundColor: String = null,
      profileTextColor: String = null,
      profileLinkColor: String = null,
      profileSidebarFillColor: String = null,
      profileSidebarBorderColor: String = null): User = {
    twitter4jObj.updateProfileColors(profileBackgroundColor, profileTextColor, profileLinkColor, profileSidebarFillColor, profileSidebarBorderColor)
  }
  
  /**
   * {@inheritDoc}
   */
  def updateProfileImage(imageResource: ImageResource.SpecificResource): User = {
    require(imageResource != null)
    
    imageResource match {
      case Left(imageStream) => twitter4jObj.updateProfileImage(imageStream)
      case Right(imageFile) => twitter4jObj.updateProfileImage(imageFile)
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def updateProfileBackgroundImage(
      imageResource: ImageResource.SpecificResource,
      tile: Boolean): User = {
    require(imageResource != null)
    
    imageResource match {
      case Left(imageStream) => twitter4jObj.updateProfileBackgroundImage(imageStream, tile)
      case Right(imageFile) => twitter4jObj.updateProfileBackgroundImage(imageFile, tile)
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def updateProfile(
      name: String = null,
      url: String = null,
      location: String = null,
      description: String = null): User = {
    twitter4jObj.updateProfile(name, url, location, description)
  }
  
  /**
   * {@inheritDoc}
   */
  def getAccountTotals: AccountTotals = {
    // TODO 削除メソッド
//    twitter4jObj.getAccountTotals()
    null
  }
  
  /**
   * {@inheritDoc}
   */
  def getAccountSettings: AccountSettings = {
    twitter4jObj.getAccountSettings()
  }
  
  /**
   * {@inheritDoc}
   */
  def updateAccountSettings(
      trendLocationWoeid: java.lang.Integer = null,
      sleepTimeEnabled: java.lang.Boolean = null,
      startSleepTime: String = null,
      endSleepTime: String = null,
      timeZone: String = null,
      lang: String = null): AccountSettings = {
    twitter4jObj.updateAccountSettings(trendLocationWoeid, sleepTimeEnabled, startSleepTime, endSleepTime, timeZone, lang)
  }
  
  /* BlockMethods */
  /**
   * {@inheritDoc}
   */
  def createBlock(specificUser: User.SpecificInfo): User = {
    require(specificUser != null)
    
    specificUser match {
      case Right(userId) => twitter4jObj.createBlock(userId)
      case Left(screenName) => twitter4jObj.createBlock(screenName)
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def destroyBlock(specificUser: User.SpecificInfo): User = {
    require(specificUser != null)
    
    specificUser match {
      case Right(userId) => twitter4jObj.destroyBlock(userId)
      case Left(screenName) => twitter4jObj.destroyBlock(screenName) 
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def existsBlock(specificUser: User.SpecificInfo): Boolean = {
    require(specificUser != null)
    
    // TODO 削除メソッド
//    specificUser match {
//      case Right(userId) => twitter4jObj.existsBlock(userId)
//      case Left(screenName) => twitter4jObj.existsBlock(screenName)
//    }
    false
  }
  
  /**
   * {@inheritDoc}
   */
  def getBlockingUsers(page: java.lang.Integer = null): ResponseList[twitter4j.User] = {
    // TODO 削除メソッド or メソッド変更
//    Option(page) match {
//      case Some(page) => twitter4jObj.getBlockingUsers(page)
//      case None => twitter4jObj.getBlockingUsers()
//    }
    null
  }
  
  /**
   * {@inheritDoc}
   */
  def getBlockingUsersIDs: IDs = {
    // TODO 削除メソッド or メソッド変更
//    twitter4jObj.getBlockingUsersIDs()
    null
  }
  
  /* DirectMessageMethods */
  /**
   * {@inheritDoc}
   */
  def getDirectMessages(paging: twitter4j.Paging = null): ResponseList[twitter4j.DirectMessage] = {
    Option(paging) match {
      case Some(paging) => twitter4jObj.getDirectMessages(paging)
      case None => twitter4jObj.getDirectMessages()
    }
  }

  /**
   * {@inheritDoc}
   */
  def getSentDirectMessages(paging: twitter4j.Paging = null): ResponseList[twitter4j.DirectMessage] = {
    Option(paging) match {
      case Some(paging) => twitter4jObj.getSentDirectMessages(paging)
      case None => twitter4jObj.getSentDirectMessages()
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def sendDirectMessage(specificUser: User.SpecificInfo, text: String): DirectMessage = {
    require(specificUser != null)
    
    specificUser match {
      case Right(userId) => twitter4jObj.sendDirectMessage(userId, text)
      case Left(screenName) => twitter4jObj.sendDirectMessage(screenName, text)
    }
  }

  /**
   * {@inheritDoc}
   */
  def destroyDirectMessage(id: Long): DirectMessage = {
    twitter4jObj.destroyDirectMessage(id)
  }

  /**
   * {@inheritDoc}
   */
  def showDirectMessage(id: Long): DirectMessage = {
    twitter4jObj.showDirectMessage(id)
  }
  
  /* FavoriteMethods */
  /**
   * {@inhritDoc}
   */
  def getFavorites(
      id: String = null,
      page: Page.PageSpecific = null): ResponseList[twitter4j.Status] = {
    (Option(id), Option(page)) match {
      case (None, None) => twitter4jObj.getFavorites()
      case (Some(id), None) => twitter4jObj.getFavorites(id)
      case (Some(id), Some(page)) => page match {
        // TODO getFavoritesからpageNumberのパターンがなくなった
        case Left(pageNumber) => null//twitter4jObj.getFavorites(id, pageNumber)
        case Right(paging) => twitter4jObj.getFavorites(id, paging)
      }
      case (None, Some(page)) => page match {
        // TODO getFavoritesからpageNumberのパターンがなくなった
        case Left(pageNumber) => null//twitter4jObj.getFavorites(pageNumber)
        case Right(paging) => twitter4jObj.getFavorites(paging)
      }
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def createFavorite(id: Long): Status = {
    twitter4jObj.createFavorite(id)
  }
  
  /**
   * {@inhritDoc}
   */
  def destroyFavorite(id: Long): Status = {
    twitter4jObj.destroyFavorite(id)
  }
  
  /* FriednsFollowersMethods */
  /**
   * {@inheritDoc}
   */
  def getFriendsIDs(cursor: Long, specificUser: User.SpecificInfo = null): IDs = {
    Option(specificUser) match {
      case Some(specificUser) => specificUser match {
        case Right(userId) => twitter4jObj.getFriendsIDs(userId, cursor)
        case Left(screenName) => twitter4jObj.getFriendsIDs(screenName, cursor)
      }
      case None => twitter4jObj.getFriendsIDs(cursor)
    }
  }

  /**
   * {@inheritDoc}
   */
  def getFollowersIDs(cursor: Long, specificUser: User.SpecificInfo = null): IDs = {
    Option(specificUser) match {
      case Some(specificUser) => specificUser match {
        case Right(userId) => twitter4jObj.getFollowersIDs(userId, cursor)
        case Left(screenName) => twitter4jObj.getFollowersIDs(screenName, cursor)
      }
      case None => twitter4jObj.getFollowersIDs(cursor)
    }
  }
  
  /* FriendshipMethods */
  /**
   * {@inheritDoc}
   */
  def createFriendship(
      specificUser: User.SpecificInfo,
      follow: java.lang.Boolean = null): User = {
    require(specificUser != null)
    
    Option(follow) match {
      case None => specificUser match {
        case Right(userId) => twitter4jObj.createFriendship(userId)
        case Left(screenName) => twitter4jObj.createFriendship(screenName)
      }
      case Some(follow) => specificUser match {
        case Right(userId) => twitter4jObj.createFriendship(userId, follow)
        case Left(screenName) => twitter4jObj.createFriendship(screenName, follow)
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  def destroyFriendship(specificUser: User.SpecificInfo): User = {
    require(specificUser != null)
    
    specificUser match {
      case Right(userId) => twitter4jObj.destroyFriendship(userId)
      case Left(screenName) => twitter4jObj.destroyFriendship(screenName)
    }
  }

  /**
   * {@inheritDoc}
   */
  def existsFriendship(userA: String, userB: String): Boolean = {
    // TODO 削除メソッド
//    twitter4jObj.existsFriendship(userA, userB)
    false
  }

  /**
   * {@inheritDoc}
   */
  def showFriendship(
      sourceSpecificUser: User.SpecificInfo,
      targetSpecificUser: User.SpecificInfo): Relationship = {
    require(sourceSpecificUser != null && targetSpecificUser != null)
    require((sourceSpecificUser.isRight && targetSpecificUser.isRight) ||
        (sourceSpecificUser.isLeft && targetSpecificUser.isLeft))
    
    (sourceSpecificUser, targetSpecificUser) match {
      case (Right(sourceId), Right(targetId)) => twitter4jObj.showFriendship(sourceId, targetId)
      case (Left(sourceScreenName), Left(targetScreenName)) => twitter4jObj.showFriendship(sourceScreenName, targetScreenName)
    }
  }

  /**
   * {@inheritDoc}
   */
  def getIncomingFriendships(cursor: Long): IDs = {
    twitter4jObj.getIncomingFriendships(cursor)
  }

  /**
   * {@inheritDoc}
   */
  def getOutgoingFriendships(cursor: Long): IDs = {
    twitter4jObj.getOutgoingFriendships(cursor)
  }

  /**
   * {@inheritDoc}
   */
  def lookupFriendships(specificUsers: Users.SpecificInfo): ResponseList[Friendship] = {
    require(specificUsers != null)
    
    specificUsers match {
      case Right(ids) => twitter4jObj.lookupFriendships(ids)
      case Left(screenNames) => twitter4jObj.lookupFriendships(screenNames)
    }
  }

  /**
   * {@inheritDoc}
   */
  def updateFriendship(
      specificUser: User.SpecificInfo,
      enableDeviceNotification: Boolean,
      retweets: Boolean): Relationship = {
    require(specificUser != null)
    
    specificUser match {
      case Right(userId) => twitter4jObj.updateFriendship(userId, enableDeviceNotification, retweets)
      case Left(screenName) => twitter4jObj.updateFriendship(screenName, enableDeviceNotification, retweets)
    }
  }

  /**
   * {@inheritDoc}
   */
  def getNoRetweetIds: IDs = {
    // TODO メソッド削除
//    twitter4jObj.getNoRetweetIds()
    null
  }
  
  /* ListMemberMethods */
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
  
  /* ListMethods */
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
  
  /* ListSubscribersMethods */
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
  
  /* NewTwitterMethods */
  /**
   * {@inheritDoc}
   */
  def getRelatedResults(statusId: Long): RelatedResults = {
    twitter4jObj.getRelatedResults(statusId)
  }
  
  /* NotificationMethods */
  /**
   * {@inheritDoc}
   */
  def enableNotification(specificUser: User.SpecificInfo): User = {
    require(specificUser != null)
    
    // TODO メソッド削除
//    specificUser match {
//      case Right(userId) => twitter4jObj.enableNotification(userId)
//      case Left(screenName) => twitter4jObj.enableNotification(screenName)
//    }
    null
  }

  /**
   * {@inheritDoc}
   */
  def disableNotification(specificUser: User.SpecificInfo): User = {
    require(specificUser != null)
    
    // TODO メソッド削除
//    specificUser match {
//      case Right(userId) => twitter4jObj.disableNotification(userId)
//      case Left(screenName) => twitter4jObj.disableNotification(screenName)
//    }
    null
  }
  
  /* SavedSearchedMethods */
  /**
   * {@inheritDoc}
   */
  def getSavedSearches: ResponseList[twitter4j.SavedSearch] = {
    twitter4jObj.getSavedSearches()
  }

  /**
   * {@inheritDoc}
   */
  def showSavedSearch(id: Int): SavedSearch = {
    twitter4jObj.showSavedSearch(id)
  }

  /**
   * {@inheritDoc}
   */
  def createSavedSearch(query: String): SavedSearch = {
    twitter4jObj.createSavedSearch(query)
  }

  /**
   * {@inheritDoc}
   */
  def destroySavedSearch(id: Int): SavedSearch = {
    twitter4jObj.destroySavedSearch(id)
  }
  
  /* SearchMethods */
  /**
   * {@inheritDoc}
   */
  def search(query: twitter4j.Query): QueryResult = {
    twitter4jObj.search(query)
  }
  
  /* SpamReportingMethods */
  /**
   * {@inheritDoc}
   */
  def reportSpam(specificUser: User.SpecificInfo): User = {
    require(specificUser != null)
    
    specificUser match {
      case Right(userId) => twitter4jObj.reportSpam(userId)
      case Left(screenName) => twitter4jObj.reportSpam(screenName)
    } 
  }
  
  /* StatusMethods */
  /**
   * {@inheritDoc}
   */
  def showStatus(id: Long): Status = {
    twitter4jObj.showStatus(id)
  }

  /**
   * {@inheritDoc}
   */
  def updateStatus(status: Status.StatusSpecific): Status = {
    require(status != null)
    
    status match {
      case Left(text) => twitter4jObj.updateStatus(text)
      case Right(latestStatus) => twitter4jObj.updateStatus(latestStatus)
    }
  }

  /**
   * {@inheritDoc}
   */
  def destroyStatus(statusId: Long): Status = {
    twitter4jObj.destroyStatus(statusId)
  }

  /**
   * {@inheritDoc}
   */
  def retweetStatus(statusId: Long): Status = {
    twitter4jObj.retweetStatus(statusId)
  }
  
  /**
   * {@inheritDoc}
   */
  def getRetweets(statusId: Long): ResponseList[twitter4j.Status] = {
    twitter4jObj.getRetweets(statusId)
  }
  
  /**
   * {@inheritDoc}
   */
  def getRetweetedBy(statusId: Long, paging: twitter4j.Paging = null): ResponseList[twitter4j.User] = {
    // TODO メソッド削除
//    Option(paging) match {
//      case Some(paging) => twitter4jObj.getRetweetedBy(statusId, paging)
//      case None => twitter4jObj.getRetweetedBy(statusId)
//    }
    null
  }
  
  /**
   * {@inheritDoc}
   */
  def getRetweetedByIDs(statusId: Long, paging: twitter4j.Paging = null): IDs = {
    // TODO メソッド削除
//    Option(paging) match {
//      case Some(paging) => twitter4jObj.getRetweetedByIDs(statusId, paging)
//      case None => twitter4jObj.getRetweetedByIDs(statusId)
//    }
    null
  }
  
  /* TimelineMethods */
  /**
   * {@inheritDoc}
   */
  def getHomeTimeline(paging: twitter4j.Paging = null): ResponseList[twitter4j.Status] = {
    Option(paging) match {
      case Some(paging) => twitter4jObj.getHomeTimeline(paging)
      case None => twitter4jObj.getHomeTimeline()
    }
  }
  
  /**
   * {@inheritedDoc}
   */
  def getUserTimeline(
      specificUser: User.SpecificInfo = null,
      paging: twitter4j.Paging = null): ResponseList[twitter4j.Status] = {
    (Option(specificUser), Option(paging)) match {
      case (None, None) => twitter4jObj.getUserTimeline()
      case (None, Some(paging)) => twitter4jObj.getUserTimeline(paging)
      case (Some(speficifUser), None) => specificUser match {
        case Right(userId) => twitter4jObj.getUserTimeline(userId)
        case Left(screenName) => twitter4jObj.getUserTimeline(screenName)
      }
      case (Some(specificUser), Some(paging)) => specificUser match {
        case Right(userId) => twitter4jObj.getUserTimeline(userId, paging)
        case Left(screenName) => twitter4jObj.getUserTimeline(screenName, paging)
      }
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def getMentions(paging: twitter4j.Paging = null): ResponseList[twitter4j.Status] = {
    Option(paging) match {
      case None => twitter4jObj.getMentions()
      case Some(paging) => twitter4jObj.getMentions(paging)
    }
  }

  /**
   * {@inheritDoc}
   */
  def getRetweetedByMe(paging: twitter4j.Paging = null): ResponseList[twitter4j.Status] = {
    // TODO メソッド削除
//    Option(paging) match {
//      case Some(paging) => twitter4jObj.getRetweetedByMe(paging)
//      case None => twitter4jObj.getRetweetedByMe()
//    }
    null
  }
  
  /**
   * {@inheritDoc}
   */
  def getRetweetedToMe(paging: twitter4j.Paging = null): ResponseList[twitter4j.Status] = {
    // TODO メソッド削除
//    Option(paging) match {
//      case Some(paging) => twitter4jObj.getRetweetedToMe(paging)
//      case None => twitter4jObj.getRetweetedToMe()
//    }
    null
  }

  /**
   * {@inheritDoc}
   */
  def getRetweetsOfMe(paging: twitter4j.Paging = null): ResponseList[twitter4j.Status] = {
    Option(paging) match {
      case Some(paging) => twitter4jObj.getRetweetsOfMe(paging)
      case None => twitter4jObj.getRetweetsOfMe()
    }
  }

  /**
   * {@inheritDoc}
   */
  def getRetweetedToUser(
      specificUser: User.SpecificInfo, 
      paging: twitter4j.Paging): ResponseList[twitter4j.Status] = {
    require(specificUser != null)
    
    // TODO メソッド削除
//    specificUser match {
//      case Right(userId) => twitter4jObj.getRetweetedToUser(userId, paging)
//      case Left(screenName) => twitter4jObj.getRetweetedToUser(screenName, paging)
//    }
    null
  }

  /**
   * {@inheritDoc}
   */
  def getRetweetedByUser(
      specificUser: User.SpecificInfo,
      paging: twitter4j.Paging): ResponseList[twitter4j.Status] = {
    require(specificUser != null)
    
    // TODO メソッド削除 or 変更　
//    specificUser match {
//      case Right(userId) => twitter4jObj.getRetweetedByUser(userId, paging)
//      case Left(screenName) => twitter4jObj.getRetweetedByUser(screenName, paging)
//    }
    null
  }
  
  /* UserMethods */
  /**
   * {@inheritDoc}
   */
  def showUser(specificUser: User.SpecificInfo): User = {
    require(specificUser != null)
    
    specificUser match {
      case Right(userId) => twitter4jObj.showUser(userId)
      case Left(screenName) => twitter4jObj.showUser(screenName)
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def lookupUsers(specificUsers: Users.SpecificInfo): ResponseList[twitter4j.User] = {
    require(specificUsers != null)
    
    specificUsers match {
      case Right(ids) => twitter4jObj.lookupUsers(ids)
      case Left(screenNames) => twitter4jObj.lookupUsers(screenNames)
    }
  }

  /**
   * {@inheritDoc}
   */
  def searchUsers(query: String, page: Int): ResponseList[twitter4j.User] = {
    twitter4jObj.searchUsers(query, page)
  }
  
  /**
   * {@inheritDoc}
   */
  def getSuggestedUserCategories: ResponseList[Category] = {
    twitter4jObj.getSuggestedUserCategories()
  }

  /**
   * {@inheritDoc}
   */
  def getUserSuggestions(categorySlug: String): ResponseList[twitter4j.User] = {
    twitter4jObj.getUserSuggestions(categorySlug)
  }

  /**
   * {@inheritDoc}
   */
  def getMemberSuggestions(categorySlug: String): ResponseList[twitter4j.User] = {
    twitter4jObj.getMemberSuggestions(categorySlug)
  }

  // TODO 削除メソッド
//  /**
//   * {@inheritDoc}
//   */
//  def getProfileImage(screenName: String, size: ProfileImage.ImageSize): ProfileImage = {
//    twitter4jObj.getProfileImage(screenName, size)
//  }
}

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
object Twitter {
  /**
   * Create Twitter4S object from twitter4j factory.
   * 
   * @param conf (optional) Configuration object or configuration strings for create factory.
   * @param accessToken (optional) AccessToken or Authorization object for create twitter4j object.
   * @return twitter4s.Twitter
   * @since Twitter4S 1.0.0
   */
  def apply(conf: Configuration.SpecificInfo = null, auth: AuthorizationInformation.SpecificType = null) = {
    val factory4j = getTwitterFactory4j(Option(conf))
    buildTwitter4sObject(getTwitter4jInstance(factory4j, Option(auth)))
  }
  
  /**
   * Create Twitter4S object from twitter4j factory.
   * 
   * @param consumerKey(required) Consumer key object for create factory.
   * @param accessToken(required) Access token object for create twitter4j object.
   * @return twitter4s.Twitter
   * @since Twitter4S 1.0.0
   */
  def apply(consumerKey: ConsumerKey, accessToken: AccessToken) = {
    val twitter4jObj = getTwitter4jInstance(
        getTwitterFactory4j(None),
        None)
    twitter4jObj.setOAuthConsumer(consumerKey.consumerKey, consumerKey.consumerSecret)
    twitter4jObj.setOAuthAccessToken(accessToken)
    buildTwitter4sObject(twitter4jObj)
  }
  
  private def buildTwitter4sObject(twitter4jObj: twitter4j.Twitter) = {
    new Twitter(twitter4jObj) with HelpResourcesImpl
                              with TrendsResourcesImpl
                              with PlaceGeoResourcesImpl
  }
  
  /**
   * Create TwitterFactory object from configurations.
   * 
   * @param conf (optional) Configuration object or configuration strings.
   * @return twitter4j.TwitterFactory
   * @since Twitter4S 1.0.0
   */
  private def getTwitterFactory4j(conf: Option[Configuration.SpecificInfo]) = {
    conf match {
      case None => new TwitterFactory()
      case Some(conf) => conf match {
        case Left(conf) => new TwitterFactory(conf)
        case Right(configTreePath) => new TwitterFactory(configTreePath)
      }
    }
  }
  
  /**
   * Create Twitter4J object from factory.
   * 
   * @param factory4j (required) TwitterFactory is created by configuration.
   * @param auth (optinal) Authorization or AccessToken object.
   * @return twitter4j.Twitter
   * @since Twitter4S 1.0.0
   */
  private def getTwitter4jInstance(factory4j: TwitterFactory, auth: Option[AuthorizationInformation.SpecificType]) = {
    auth match {
      case None => factory4j.getInstance()
      case Some(auth) => auth match {
        case Right(accessToken) => factory4j.getInstance(accessToken)
        case Left(auth) => factory4j.getInstance(auth)
      }
    }
  }
}