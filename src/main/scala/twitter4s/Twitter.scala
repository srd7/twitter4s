package twitter4s

import twitter4s._
import java.io.File
import java.io.InputStream
import java.util.Date

import twitter4j.api.HelpMethods.Language
import twitter4j.auth.AccessToken
import twitter4j.auth.Authorization
import twitter4j.auth.RequestToken
import twitter4j.conf.Configuration
import twitter4j.AccountSettings
import twitter4j.AccountTotals
import twitter4j.Category
import twitter4j.DirectMessage
import twitter4j.Friendship
import twitter4j.GeoLocation
import twitter4j.GeoQuery
import twitter4j.IDs
import twitter4j.Location
import twitter4j.Paging
import twitter4j.Place
import twitter4j.ProfileImage
import twitter4j.Query
import twitter4j.QueryResult
import twitter4j.RateLimitStatus
import twitter4j.RateLimitStatusListener
import twitter4j.RelatedResults
import twitter4j.Relationship
import twitter4j.SavedSearch
import twitter4j.SimilarPlaces
import twitter4j.Status
import twitter4j.StatusUpdate
import twitter4j.Trends
import twitter4j.TwitterAPIConfiguration
import twitter4j.TwitterFactory
import twitter4j.User
import twitter4j.UserList

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class Twitter(twitter4jObj: twitter4j.Twitter) extends TwitterBase with TwitterAPIs {
  // TODO PagableResponseListやUserListのラッピング
  // TODO 他のリターンオブジェクトとFactoryのラッピング
  // TODO Twitter4Jでカバーしていないパターンに関してはTwitterExceptionをスローする
  // TODO IDとスクリーン名を指定する場合はID優先になるようにパターンマッチを修正する
  
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
  
  def addRateLimitStatusListener(listener: RateLimitStatusListener) {
    // TODO implements
  }
  
  /**
   * {@inheritDoc}
   */
  def authorization: Authorization = {
    twitter4jObj.getAuthorization()
  }
  
  /**
   * {@inheritDoc}
   */
  def configuration: Configuration = {
    twitter4jObj.getConfiguration()
  }
  
  /**
   * {@inheritDoc}
   */
  def shotdown {
    twitter4jObj.shutdown()
  }
  
  /* HelpMethods */
  /**
   * {@inheritDoc}
   */
  def test: Boolean = {
    twitter4jObj.test()
  }

  /**
   * {@inheritDoc}
   */
  def getAPIConfiguration: TwitterAPIConfiguration = {
    twitter4jObj.getAPIConfiguration()
  }

  /**
   * {@inheritDoc}
   */
  def getLanguages: ResponseList[Language] = {
    twitter4jObj.getLanguages()
  }
  
  /* OAuthSupport */
  /**
   * {@inheritDoc}
   */
  def setOAuthConsumer(consumerKey: String, consumerSecret: String) {
    twitter4jObj.setOAuthConsumer(consumerKey, consumerSecret)
  }
  
  def getOAuthRequestToken(callbackURL: Option[String], xAuthAccessType: Option[String]): RequestToken = {
    // TODO implements
    return null
  }
  
  def getOAuthAccessToken(oauthVerifier: Option[String], requestToken: Option[RequestToken], screenName: Option[String], password: Option[String]): AccessToken = {
    // TODO implements
    return null
  }
  
  def setOAuthAccessToken(accessToken: AccessToken) {
    // TODO implements
  }
  
  /* AccountMethods */
  /**
   * {@inheritDoc}
   */
  def verifyCredentials: User = {
    twitter4jObj.verifyCredentials()
  }
  
  def getRateLimitStatus: RateLimitStatus = {
    // TODO implements
    null
  }
  
  /**
   * {@inherited}
   */
  def updateProfileColors(profileBackgroundColor: String, profileTextColor: String, profileLinkColor: String, profileSidebarFillColor: String, profileSidebarBorderColor: String): User = {
    twitter4jObj.updateProfileColors(profileBackgroundColor, profileTextColor, profileLinkColor, profileSidebarFillColor, profileSidebarBorderColor)
  }
  
  /**
   * {@inheritDoc}
   */
  def updateProfileImage(imageFile: Option[File] = None, imageStream: Option[InputStream] = None): User = {
    (imageFile, imageStream) match {
      case (None, Some(imageStream)) => twitter4jObj.updateProfileImage(imageStream)
      case (Some(imageFile), None) => twitter4jObj.updateProfileImage(imageFile)
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def updateProfileBackgroundImage(imageFile: Option[File] = None, imageStream: Option[InputStream] = None, tile: Boolean): User = {
    (imageFile, imageStream) match {
      case(None, Some(imageStream)) => twitter4jObj.updateProfileBackgroundImage(imageStream, tile)
      case(Some(imageFile), None) => twitter4jObj.updateProfileBackgroundImage(imageFile, tile)
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def updateProfile(name: String, url: String, location: String, description: String): User = {
    twitter4jObj.updateProfile(name, url, location, description)
  }
  
  /**
   * {@inheritDoc}
   */
  def getAccountTotals: AccountTotals = {
    twitter4jObj.getAccountTotals()
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
  def updateAccountSettings(trendLocationWoeid: Int, sleepTimeEnabled: Boolean, startSleepTime: String, endSleepTime: String, timeZone: String, lang: String): AccountSettings = {
    twitter4jObj.updateAccountSettings(trendLocationWoeid, sleepTimeEnabled, startSleepTime, endSleepTime, timeZone, lang)
  }
  
  /* BlockMethods */
  /**
   * {@inheritDoc}
   */
  def createBlock(screenName: Option[String] = None, userId: Option[Long] = None): User = {
    (screenName, userId) match {
      case (Some(screenName), None) => twitter4jObj.createBlock(screenName)
      case (None, Some(userId)) => twitter4jObj.createBlock(userId)
      // case _ => // TODO Exception?
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def destroyBlock(screenName: Option[String] = None, userId: Option[Long] = None): User = {
    (screenName, userId) match {
      case (Some(screenName), None) => twitter4jObj.destroyBlock(screenName)
      case (None, Some(userId)) => twitter4jObj.destroyBlock(userId)
      // case _ => // TODO Exception?
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def existsBlock(screenName: Option[String] = None, userId: Option[Long] = None): Boolean = {
    (screenName, userId) match {
      case (Some(screenName), None) => twitter4jObj.existsBlock(screenName)
      case (None, Some(userId)) => twitter4jObj.existsBlock(userId)
      // case _ => // TODO Exception?
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def getBlockingUsers(page: Option[Int] = None): ResponseList[User] = {
    page match {
      case Some(page) => twitter4jObj.getBlockingUsers(page)
      case None => twitter4jObj.getBlockingUsers()
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def getBlockingUsersIDs: IDs = {
    twitter4jObj.getBlockingUsersIDs()
  }
  
  /* DirectMessageMethods */
  /**
   * {@inheritDoc}
   */
  def getDirectMessages(paging: Option[Paging] = None): ResponseList[DirectMessage] = {
    paging match {
      case Some(paging) => twitter4jObj.getDirectMessages(paging)
      case None => twitter4jObj.getDirectMessages()
    }
  }

  def getSentDirectMessages(paging: Option[Paging] = None): ResponseList[DirectMessage] = {
    // TODO implements
    return null
  }
  
  /**
   * {@inheritDoc}
   */
  def sendDirectMessage(screenName: Option[String] = None, userId:Option[Long] = None, text: String): DirectMessage = {
    (screenName, userId) match {
      case (Some(screenName), None) => twitter4jObj.sendDirectMessage(screenName, text)
      case (None, Some(userId)) => twitter4jObj.sendDirectMessage(userId, text)
      // case _ => throw new TwitterException("illegal argument") // TODO exception?
    }
  }

  def destroyDirectMessage(id: Long): DirectMessage = {
    // TODO implements
    return null
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
  def getFavorites(id: Option[String] = None, page: Option[Int] = None, paging: Option[Paging] = None): ResponseList[Status] = {
    (id, page, paging) match {
      case (None, None, None) => twitter4jObj.getFavorites()
      case (None, Some(page), None) => twitter4jObj.getFavorites(page)
      case (Some(id), None, None) => twitter4jObj.getFavorites(id)
      case (Some(id), Some(page), None) => twitter4jObj.getFavorites(id, page)
      case (None, None, Some(paging)) => twitter4jObj.getFavorites(paging)
      case (Some(id), None, Some(paging)) => twitter4jObj.getFavorites(id, paging)
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def createFavorite(id: Long): Status = {
    twitter4jObj.createFavorite(id)
  }
  
  def destroyFavorite(id: Long): Status = {
    // TODO implements
    null
  }
  
  /* FriednsFollowersMethods */
  /**
   * {@inheritDoc}
   */
  def getFriendsIDs(cursor: Long, userId: Option[Long] = None, screenName: Option[String] = None): IDs = {
    (userId, screenName) match {
      case (None, None) => twitter4jObj.getFriendsIDs(cursor)
      case (Some(userId), None) => twitter4jObj.getFriendsIDs(userId, cursor)
      case (None, Some(screenName)) => twitter4jObj.getFriendsIDs(screenName, cursor)
      // case _ => // TODO exception?
    }
  }

  /**
   * {@inheritDoc}
   */
  def getFollowersIDs(cursor: Long, userId: Option[Long] = None, screenName: Option[String] = None): IDs = {
    (userId, screenName) match {
      case (None, Some(screenName)) => twitter4jObj.getFollowersIDs(screenName, cursor)
      case (Some(userId), None) => twitter4jObj.getFollowersIDs(userId, cursor)
      case (None, None) => twitter4jObj.getFollowersIDs(cursor)
      // case _ => // TODO exception?
    }
  }
  
  /* FriendshipMethods */
  /**
   * {@inheritDoc}
   */
  def createFriendship(screenName: Option[String] = None, userId: Option[Long] = None, follow: Option[Boolean] = None): User = {
    (screenName, userId, follow) match {
      case (Some(screenName), None, None) => twitter4jObj.createFriendship(screenName)
      case (Some(screenName), None, Some(follow)) => twitter4jObj.createFriendship(screenName, follow)
      case (None, Some(userId), None) => twitter4jObj.createFriendship(userId)
      case (None, Some(userId), Some(follow)) => twitter4jObj.createFriendship(userId, follow)
      // case _ => // TODO exception?
    }
  }

  /**
   * {@inheritDoc}
   */
  def destroyFriendship(screenName: Option[String] = None, userId: Option[Long] = None): User = {
    (screenName, userId) match {
      case (Some(screenName), None) => twitter4jObj.destroyFriendship(screenName)
      case (None, Some(userId)) => twitter4jObj.destroyFriendship(userId)
      // case _ => // TODO exception?
    }
  }

  /**
   * {@inheritDoc}
   */
  def existsFriendship(userA: String, userB: String): Boolean = {
    twitter4jObj.existsFriendship(userA, userB)
  }

  /**
   * {@inheritDoc}
   */
  def showFriendship(sourceScreenName: Option[String] = None, targetScreenName: Option[String] = None, sourceId: Option[Long] = None, targetId: Option[Long] = None): Relationship = {
    (sourceScreenName, targetScreenName, sourceId, targetId) match {
      case (Some(sourceScreenName), Some(targetScreenName), None, None) => twitter4jObj.showFriendship(sourceScreenName, targetScreenName)
      case (None, None, Some(sourceId), Some(targetId)) => twitter4jObj.showFriendship(sourceId, targetId)
      // case _ => // TODO exception?
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
  def lookupFriendships(screenNames: Option[Array[String]] = None, ids: Option[Array[Long]] = None): ResponseList[Friendship] = {
    (screenNames, ids) match {
      case (Some(screenNames), None) => twitter4jObj.lookupFriendships(screenNames)
      case (None, Some(ids)) => twitter4jObj.lookupFriendships(ids)
      // case _ => // TODO exception?
    }
  }

  /**
   * {@inheritDoc}
   */
  def updateFriendship(enableDeviceNotification: Boolean, retweets: Boolean, screenName: Option[String] = None, userId: Option[Long] = None): Relationship = {
    (screenName, userId) match {
      case (Some(screenName), None) => twitter4jObj.updateFriendship(screenName, enableDeviceNotification, retweets)
      case (None, Some(userId)) => twitter4jObj.updateFriendship(userId, enableDeviceNotification, retweets)
      // case _ => // TODO exception?
    }
  }

  /**
   * {@inheritDoc}
   */
  def getNoRetweetIds: IDs = {
    twitter4jObj.getNoRetweetIds()
  }
  
  /* GeoMethods */
  /**
   * {@inheritDoc}
   */
  def searchPlaces(query: GeoQuery): ResponseList[Place] = {
    twitter4jObj.searchPlaces(query)
  }

  /**
   * {@inhritDoc}
   */
  def getSimilarPlaces(location: GeoLocation, name: String, containedWithin: String, streetAddress: String): SimilarPlaces = {
    twitter4jObj.getSimilarPlaces(location, name, containedWithin, streetAddress)
  }

  /**
   * {@inheritDoc}
   */
  def reverseGeoCode(query: GeoQuery): ResponseList[Place] = {
    twitter4jObj.reverseGeoCode(query)
  }

  /**
   * {@inheritDoc}
   */
  def getGeoDetails(id: String): Place = {
    twitter4jObj.getGeoDetails(id)
  }
  
  def createPlace(name: String, containedWithin: String, token: String, location: GeoLocation, streetAddress: String): Place = {
    // TODO implements
    null
  }
  
  /* LegalResources */
  /**
   * {@inheritDoc}
   */
  def getTermsOfService: String = {
    twitter4jObj.getTermsOfService()
  }

  /**
   * {@inheritDoc}
   */
  def getPrivacyPolicy: String = {
    twitter4jObj.getPrivacyPolicy()
  }
  
  /* ListMemberMethods */
  /**
   * {@inheritDoc}
   */
  def getUserListMembers(listId: Int, cursor: Long): PagableResponseList[User] = {
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
  def addUserListMembers(listId: Int, userIds: Option[Array[Long]] = None, screenNames: Option[Array[String]] = None): UserList = {
    (userIds, screenNames) match {
      case (Some(userIds), None) => twitter4jObj.addUserListMembers(listId, userIds)
      case (None, Some(screenNames)) => twitter4jObj.addUserListMembers(listId, screenNames)
      // case _ => // TODO exception?
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
  def getUserLists(cursor: Long, listOwnerScreenName: Option[String] = None, listOwnerUserId: Option[Long] = None): PagableResponseList[UserList] = {
    (listOwnerScreenName, listOwnerUserId) match {
      case (Some(listOwnerScreenName), None) => twitter4jObj.getUserLists(listOwnerScreenName, cursor)
      case (None, Some(listOwnerUserId)) => twitter4jObj.getUserLists(listOwnerUserId, cursor)
      // case _ => // TODO exception? 
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
  def getUserListStatuses(listId: Int, paging: Paging): ResponseList[Status] = {
    twitter4jObj.getUserListStatuses(listId, paging)
  }

  /**
   * {@inheritDoc}
   */
  def getUserListMemberships(cursor: Long, listMemberId: Option[Long] = None, listMemberScreenName: Option[String] = None, filterToOwnedLists: Option[Boolean] = None): PagableResponseList[UserList] = {
    (listMemberId, listMemberScreenName, filterToOwnedLists) match {
      case (Some(listMemberId), None, None) => twitter4jObj.getUserListMemberships(listMemberId, cursor)
      case (None, Some(listMemberScreenName), None) => twitter4jObj.getUserListMemberships(listMemberScreenName, cursor)
      case (Some(listMemberId), None, Some(filterToOwnedLists)) => twitter4jObj.getUserListMemberships(listMemberId, cursor, filterToOwnedLists)
      case (None, Some(listMemberScreenName), Some(filterToOwnedLists)) => twitter4jObj.getUserListMemberships(listMemberScreenName, cursor, filterToOwnedLists)
      case (None, None, None) => twitter4jObj.getUserListMemberships(cursor)
    }
  }

  /**
   * {@inheritDoc}
   */
  def getUserListSubscriptions(cursor: Long, listMemberScreenName: String): PagableResponseList[UserList] = {
    twitter4jObj.getUserListSubscriptions(listMemberScreenName, cursor)
  }

  /**
   * {@inheritDoc}
   */
  def getAllUserLists(screenName: Option[String] = None, userId: Option[Long] = None): ResponseList[UserList] = {
    (screenName, userId) match {
      case (Some(screenName), None) => twitter4jObj.getAllUserLists(screenName)
      case (None, Some(userId)) => twitter4jObj.getAllUserLists(userId)
    }
  }
  
  /* ListSubscribersMethods */
  /**
   * {@inheritDoc}
   */
  def getUserListSubscribers(listId: Int, cursor: Long): PagableResponseList[User] = {
    twitter4jObj.getUserListSubscribers(listId, cursor)
  }

  def createUserListSubscription(listId: Int): UserList = {
    // TODO implement
    null
  }

  def destroyUserListSubscription(listId: Int): UserList = {
    // TODO implement
    null
  }

  def showUserListSubscription(listId: Int, userId: Long): User = {
    // TODO implement
    null
  }
  
  /* LocalTrendsMethods */
  /**
   * {@inheritDoc}
   */
  def getAvailableTrends(location: Option[GeoLocation] = None): ResponseList[Location] = {
    location match {
      case Some(locationData) => twitter4jObj.getAvailableTrends(locationData)
      case None => twitter4jObj.getAvailableTrends()
    }
  }

  /**
   * {@inheritDoc}
   */
  def getLocationTrends(woeid: Int): Trends = {
    twitter4jObj.getLocationTrends(woeid)
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
  def enableNotification(screenName: Option[String] = None, userId: Option[Long] = None): User = {
    (screenName, userId) match {
      case (Some(screenName), None) => twitter4jObj.enableNotification(screenName)
      case (None, Some(userId)) => twitter4jObj.enableNotification(userId)
      // case _ => exception? 
    }
  }

  /**
   * {@inheritDoc}
   */
  def disableNotification(screenName: Option[String] = None, userId: Option[Long] = None): User = {
    (screenName, userId) match {
      case (Some(screenName), None) => twitter4jObj.disableNotification(screenName)
      case (None, Some(userId)) => twitter4jObj.disableNotification(userId)
      // case _ => TODO exceptioin?
    }
  }
  
  /* SavedSearchedMethods */
  /**
   * {@inheritDoc}
   */
  def getSavedSearches: ResponseList[SavedSearch] = {
    twitter4jObj.getSavedSearches()
  }

  def showSavedSearch(id: Int): SavedSearch = {
    // TODO implements
    null
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
  def search(query: Query): QueryResult = {
    twitter4jObj.search(query)
  }
  
  /* SpamReportingMethods */
  /**
   * {@inheritDoc}
   */
  def reportSpam(userId: Option[Long] = None, screenName: Option[String] = None): User = {
    (userId, screenName) match {
      case (Some(userId), None) => twitter4jObj.reportSpam(userId)
      case (None, Some(screenName)) => twitter4jObj.reportSpam(screenName)
      // case _ => // TODO Exception?
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
  def updateStatus(status: Option[String] = None, latestStatus: Option[StatusUpdate] = None): Status = {
    (status, latestStatus) match {
      case (Some(status), None) => twitter4jObj.updateStatus(status)
      case (None, Some(latestStatus)) => twitter4jObj.updateStatus(latestStatus)
      // case _ => // TODO Exception?
    }
  }

  /**
   * {@inheritDoc}
   */
  def destroyStatus(statusId: Long): Status = {
    twitter4jObj.destroyStatus(statusId)
  }

  def retweetStatus(statusId: Long): Status = {
    // TODO implements
    return null
  }
  
  /**
   * {@inheritDoc}
   */
  def getRetweets(statusId: Long): ResponseList[Status] = {
    twitter4jObj.getRetweets(statusId)
  }
  
  /**
   * {@inheritDoc}
   */
  def getRetweetedBy(statusId: Long, paging: Option[Paging] = None): ResponseList[User] = {
    paging match {
      case Some(paging) => twitter4jObj.getRetweetedBy(statusId, paging)
      case None => twitter4jObj.getRetweetedBy(statusId)
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def getRetweetedByIDs(statusId: Long, paging: Option[Paging] = None): IDs = {
    paging match {
      case Some(paging) => twitter4jObj.getRetweetedByIDs(statusId, paging)
      case None => twitter4jObj.getRetweetedByIDs(statusId)
    }
  }
  
  /* TimelineMethods */
  /**
   * {@inheritDoc}
   */
  def getHomeTimeline(paging: Option[Paging] = None): ResponseList[Status] = {
    paging match {
      case Some(paging) => twitter4jObj.getHomeTimeline(paging)
      case None => twitter4jObj.getHomeTimeline()
    }
  }
  
  /**
   * {@inheritedDoc}
   */
  def getUserTimeline(screenName: Option[String] = None, userId: Option[Long] = None, paging: Option[Paging] = None): ResponseList[Status] = {
    (screenName, userId, paging) match {
      case (None, None, None) => twitter4jObj.getUserTimeline()
      case (Some(screenName), None, None) => twitter4jObj.getUserTimeline(screenName)
      case (None, Some(userId), None) => twitter4jObj.getUserTimeline(userId)
      case (Some(screenName), None, Some(paging)) => twitter4jObj.getUserTimeline(screenName, paging)
      case (None, Some(userId), Some(paging)) => twitter4jObj.getUserTimeline(userId, paging)
      case (None, None, Some(paging)) => twitter4jObj.getUserTimeline(paging)
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def getMentions(paging: Option[Paging] = None): ResponseList[Status] = {
    paging match {
      case None => twitter4jObj.getMentions()
      case Some(paging) => twitter4jObj.getMentions(paging)
    }
  }

  /**
   * {@inheritDoc}
   */
  def getRetweetedByMe(paging: Option[Paging] = None): ResponseList[Status] = {
    paging match {
      case Some(paging) => twitter4jObj.getRetweetedByMe(paging)
      case None => twitter4jObj.getRetweetedByMe()
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def getRetweetedToMe(paging: Option[Paging] = None): ResponseList[Status] = {
    paging match {
      case Some(paging) => twitter4jObj.getRetweetedToMe(paging)
      case None => twitter4jObj.getRetweetedToMe()
    }
  }

  /**
   * {@inheritDoc}
   */
  def getRetweetsOfMe(paging: Option[Paging] = None): ResponseList[Status] = {
    paging match {
      case Some(paging) => twitter4jObj.getRetweetsOfMe(paging)
      case None => twitter4jObj.getRetweetsOfMe()
    }
  }

  /**
   * {@inheritDoc}
   */
  def getRetweetedToUser(paging: Paging, screenName: Option[String] = None, userId: Option[Long] = None): ResponseList[Status] = {
    (screenName, userId) match {
      case (Some(screenName), None) => twitter4jObj.getRetweetedToUser(screenName, paging)
      case (None, Some(userId)) => twitter4jObj.getRetweetedToUser(userId, paging)
      // case _ => // TODO exception?
    }
  }

  /**
   * {@inheritDoc}
   */
  def getRetweetedByUser(paging: Paging, screenName: Option[String] = None, userId: Option[Long] = None): ResponseList[Status] = {
    (screenName, userId) match {
      case (Some(screenName), None) => twitter4jObj.getRetweetedByUser(screenName, paging)
      case (None, Some(userId)) => twitter4jObj.getRetweetedByUser(userId, paging)
      // case _ => // TODO Exception?
    }
  }
  
  /* TrendMethods */
  /**
   * {@inheritDoc}
   */
  def getDailyTrends(date: Option[Date] = None, excludeHashTags: Option[Boolean] = None): ResponseList[Trends] = {
    (date, excludeHashTags) match {
      case (Some(date), Some(excludeHashTags)) => twitter4jObj.getDailyTrends(date, excludeHashTags)
      case (None, None) => twitter4jObj.getDailyTrends()
      //case _ => // TODO Exception?
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def getWeeklyTrends(date: Option[Date] = None, excludeHashTags: Option[Boolean] = None): ResponseList[Trends] = {
    (date, excludeHashTags) match {
      case (Some(date), Some(excludeHashTags)) => twitter4jObj.getWeeklyTrends(date, excludeHashTags)
      case (None, None) => twitter4jObj.getWeeklyTrends()
      // case _ => // TODO Exception?
    }
  }
  
  /* UserMethods */
  /**
   * {@inheritDoc}
   */
  def showUser(screenName: Option[String] = None, userId: Option[Long] = None): User = {
    (screenName, userId) match {
      case (Some(screenName), None) => twitter4jObj.showUser(screenName)
      case (None, Some(userId)) => twitter4jObj.showUser(userId)
      // case _ => // TODO exception?
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def lookupUsers(screenNames: Option[Array[String]] = None, ids: Option[Array[Long]] = None): ResponseList[User] = {
    (screenNames, ids) match {
      case (Some(screenNames), None) => twitter4jObj.lookupUsers(screenNames)
      case (None, Some(ids)) => twitter4jObj.lookupUsers(ids)
      // case _ => // TODO exception?
    }
  }

  /**
   * {@inheritDoc}
   */
  def searchUsers(query: String, page: Int): ResponseList[User] = {
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
  def getUserSuggestions(categorySlug: String): ResponseList[User] = {
    twitter4jObj.getUserSuggestions(categorySlug)
  }

  /**
   * {@inheritDoc}
   */
  def getMemberSuggestions(categorySlug: String): ResponseList[User] = {
    twitter4jObj.getMemberSuggestions(categorySlug)
  }

  /**
   * {@inheritDoc}
   */
  def getProfileImage(screenName: String, size: ProfileImage.ImageSize): ProfileImage = {
    twitter4jObj.getProfileImage(screenName, size)
  }
}

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
object Twitter {
  /**
   * Create Twitter4S object from twitter4j factory.
   * 
   * @param conf (optional) Configuration object for create factory. This parameter is used high priority.
   * @param configTreePath (optional) configuration strings for create factory.
   * @param accessToken (optional) AccessToken object for create twitter4j object. This parameter is used high priority.
   * @param auth (optional) Authorization object for create twitter4j object.
   * @return twitter4s.Twitter
   * @since Twitter4S 1.0.0
   */
  // TODO 引数リストのカリー化
  def apply(conf: Option[Configuration] = None, configTreePath: Option[String] = None, accessToken: Option[AccessToken] = None, auth: Option[Authorization] = None) = {
    val factory4j = getTwitterFactory4j(conf, configTreePath)
    new Twitter(getTwitter4jInstance(factory4j, accessToken, auth))
  }
  
  /**
   * Create TwitterFactory object from configurations.
   * 
   * @param conf (optional) Configuration object. This parameter is used high priority.
   * @param configTreePath (optional) configuration strings.
   * @return twitter4j.TwitterFactory
   * @since Twitter4S 1.0.0
   */
  private def getTwitterFactory4j(conf: Option[Configuration], configTreePath: Option[String]) = {
    (conf, configTreePath) match {
      case (None, None) => new TwitterFactory()
      case (Some(conf), _) => new TwitterFactory(conf)
      case (None, Some(configTreePath)) => new TwitterFactory(configTreePath)
    }
  }
  
  /**
   * Create Twitter4J object from factory.
   * 
   * @param factory4j (required) TwitterFactory is created by configuration.
   * @param accessToken (optional) AccessToken object. This parameter is used high priority.
   * @param auth (optinal) Authorization object.
   * @return twitter4j.Twitter
   * @since Twitter4S 1.0.0
   */
  private def getTwitter4jInstance(factory4j: TwitterFactory, accessToken: Option[AccessToken], auth: Option[Authorization]) = {
    (accessToken, auth) match {
      case (None, None) => factory4j.getInstance()
      case (Some(accessToken), _) => factory4j.getInstance(accessToken)
      case (None, Some(auth)) => factory4j.getInstance(auth)
    }
  }
}