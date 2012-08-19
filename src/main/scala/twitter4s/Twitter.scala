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
import twitter4j.Category
import twitter4j.Friendship
import twitter4j.Location
import twitter4j.ProfileImage
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
   * {@inheritDoc}
   */
  def getRateLimitStatus: RateLimitStatus = {
    twitter4jObj.getRateLimitStatus()
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
  def updateProfileImage(
      imageFile: File = null,
      imageStream: InputStream = null): User = {
    (Option(imageFile), Option(imageStream)) match {
      case (None, Some(imageStream)) => twitter4jObj.updateProfileImage(imageStream)
      case (Some(imageFile), None) => twitter4jObj.updateProfileImage(imageFile)
      case _ => throw new IllegalArgumentException("Parameter can set either imageFile or imageStream.")
    }
  }
  
  /**
   * {@inheritDoc}
   */
  // TODO ファイルをEitherにする
  def updateProfileBackgroundImage(
      imageFile: File = null,
      imageStream: InputStream = null,
      tile: Boolean): User = {
    (Option(imageFile), Option(imageStream)) match {
      case(None, Some(imageStream)) => twitter4jObj.updateProfileBackgroundImage(imageStream, tile)
      case(Some(imageFile), None) => twitter4jObj.updateProfileBackgroundImage(imageFile, tile)
      case _ => throw new IllegalArgumentException("Parameter can set either imageFile or imageStream.")
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
    
    specificUser match {
      case Right(userId) => twitter4jObj.existsBlock(userId)
      case Left(screenName) => twitter4jObj.existsBlock(screenName)
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def getBlockingUsers(page: java.lang.Integer = null): ResponseList[twitter4j.User] = {
    Option(page) match {
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
  // TODO pageとpagingはどちらかのみにする
  def getFavorites(
      id: String = null,
      page: java.lang.Integer = null,
      paging: twitter4j.Paging = null): ResponseList[twitter4j.Status] = {
    (Option(id), Option(page), Option(paging)) match {
      case (None, None, None) => twitter4jObj.getFavorites()
      case (None, Some(page), None) => twitter4jObj.getFavorites(page)
      case (Some(id), None, None) => twitter4jObj.getFavorites(id)
      case (Some(id), Some(page), None) => twitter4jObj.getFavorites(id, page)
      case (None, None, Some(paging)) => twitter4jObj.getFavorites(paging)
      case (Some(id), None, Some(paging)) => twitter4jObj.getFavorites(id, paging)
      case (_, Some(page), Some(paging)) => throw new IllegalArgumentException("parameter page or paging must be set either one.")
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
    twitter4jObj.existsFriendship(userA, userB)
  }

  /**
   * {@inheritDoc}
   */
  // TODO ユーザ指定変更
  def showFriendship(
      sourceScreenName: String = null,
      targetScreenName: String = null,
      sourceId: java.lang.Long = null,
      targetId: java.lang.Long = null): Relationship = {
    (Option(sourceScreenName), Option(targetScreenName), Option(sourceId), Option(targetId)) match {
      case (_, _, Some(sourceId), Some(targetId)) => twitter4jObj.showFriendship(sourceId, targetId)
      case (Some(sourceScreenName), Some(targetScreenName), None, None) => twitter4jObj.showFriendship(sourceScreenName, targetScreenName)
      case _ => throw new IllegalArgumentException("Parameter combination can not create. See API method comment.")
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
  // TODO ユーザ指定情報の配列化
  def lookupFriendships(screenNames: Array[String] = null, ids: Array[Long] = null): ResponseList[Friendship] = {
    (Option(screenNames), Option(ids)) match {
      case (_, Some(ids)) => twitter4jObj.lookupFriendships(ids)
      case (Some(screenNames), None) => twitter4jObj.lookupFriendships(screenNames)
      case _ => throw new IllegalArgumentException("Parameter must be set screenNames or ids at least.")
    }
  }

  /**
   * {@inheritDoc}
   */
  // TODO ユーザ指定変更
  def updateFriendship(
      enableDeviceNotification: Boolean,
      retweets: Boolean,
      screenName: String = null,
      userId: java.lang.Long = null): Relationship = {
    (Option(screenName), Option(userId)) match {
      case (_, Some(userId)) => twitter4jObj.updateFriendship(userId, enableDeviceNotification, retweets)
      case (Some(screenName), None) => twitter4jObj.updateFriendship(screenName, enableDeviceNotification, retweets)
      case _ => throw new IllegalArgumentException("Parameter must be set screenName or id userId at least.")
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
  def searchPlaces(query: twitter4j.GeoQuery): ResponseList[twitter4j.Place] = {
    twitter4jObj.searchPlaces(query)
  }

  /**
   * {@inhritDoc}
   */
  def getSimilarPlaces(location: twitter4j.GeoLocation, name: String, containedWithin: String, streetAddress: String): SimilarPlaces = {
    twitter4jObj.getSimilarPlaces(location, name, containedWithin, streetAddress)
  }

  /**
   * {@inheritDoc}
   */
  def reverseGeoCode(query: twitter4j.GeoQuery): ResponseList[twitter4j.Place] = {
    twitter4jObj.reverseGeoCode(query)
  }

  /**
   * {@inheritDoc}
   */
  def getGeoDetails(id: String): Place = {
    twitter4jObj.getGeoDetails(id)
  }
  
  /**
   * {@inheritDoc}
   */
  def createPlace(name: String, containedWithin: String, token: String, location: twitter4j.GeoLocation, streetAddress: String): Place = {
    twitter4jObj.createPlace(name, containedWithin, token, location, streetAddress)
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
  // TODO ユーザ指定情報の配列化
  def addUserListMembers(
      listId: Int,
      userIds: Array[Long] = null,
      screenNames: Array[String] = null): UserList = {
    (Option(userIds), Option(screenNames)) match {
      case (_, Some(screenNames)) => twitter4jObj.addUserListMembers(listId, screenNames)
      case (Some(userIds), None) => twitter4jObj.addUserListMembers(listId, userIds)
      case _ => throw new IllegalArgumentException("Parameter userIds and screenNames must be set at least.")
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
  // TODO ユーザ指定変更
  def getUserLists(cursor: Long,
      listOwnerScreenName: String = null,
      listOwnerUserId: java.lang.Long = null): PagableResponseList[twitter4j.UserList] = {
    (Option(listOwnerScreenName), Option(listOwnerUserId)) match {
      case (_, Some(listOwnerUserId)) => twitter4jObj.getUserLists(listOwnerUserId, cursor)
      case (Some(listOwnerScreenName), None) => twitter4jObj.getUserLists(listOwnerScreenName, cursor)
      case _ => throw new IllegalArgumentException("Parameter listOwnerScreenName or listOwnerUserId must be set at least.")
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
  // TODO ユーザ指定変更
  def getUserListMemberships(
      cursor: Long,
      listMemberId: java.lang.Long = null,
      listMemberScreenName: String = null,
      filterToOwnedLists: java.lang.Boolean = null): PagableResponseList[twitter4j.UserList] = {
    (Option(listMemberId), Option(listMemberScreenName), Option(filterToOwnedLists)) match {
      case (Some(listMemberId), _, None) => twitter4jObj.getUserListMemberships(listMemberId, cursor)
      case (None, Some(listMemberScreenName), None) => twitter4jObj.getUserListMemberships(listMemberScreenName, cursor)
      case (Some(listMemberId), _, Some(filterToOwnedLists)) => twitter4jObj.getUserListMemberships(listMemberId, cursor, filterToOwnedLists)
      case (None, Some(listMemberScreenName), Some(filterToOwnedLists)) => twitter4jObj.getUserListMemberships(listMemberScreenName, cursor, filterToOwnedLists)
      case (None, None, None) => twitter4jObj.getUserListMemberships(cursor)
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
  // TODO ユーザ指定変更
  def getAllUserLists(screenName: String = null, userId: java.lang.Long = null): ResponseList[twitter4j.UserList] = {
    (Option(screenName), Option(userId)) match {
      case (_, Some(userId)) => twitter4jObj.getAllUserLists(userId)
      case (Some(screenName), None) => twitter4jObj.getAllUserLists(screenName)
      case _ => throw new IllegalArgumentException("Parameter screenName or userId must be set at least.")
    }
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
  
  /* LocalTrendsMethods */
  /**
   * {@inheritDoc}
   */
  def getAvailableTrends(location: twitter4j.GeoLocation = null): ResponseList[Location] = {
    Option(location) match {
      case Some(location) => twitter4jObj.getAvailableTrends(location)
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
  def enableNotification(specificUser: User.SpecificInfo): User = {
    require(specificUser != null)
    
    specificUser match {
      case Right(userId) => twitter4jObj.enableNotification(userId)
      case Left(screenName) => twitter4jObj.enableNotification(screenName)
    }
  }

  /**
   * {@inheritDoc}
   */
  def disableNotification(specificUser: User.SpecificInfo): User = {
    require(specificUser != null)
    
    specificUser match {
      case Right(userId) => twitter4jObj.disableNotification(userId)
      case Left(screenName) => twitter4jObj.disableNotification(screenName)
    }
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
  def updateStatus(status: String = null, latestStatus: twitter4j.StatusUpdate = null): Status = {
    (Option(status), Option(latestStatus)) match {
      case (Some(status), None) => twitter4jObj.updateStatus(status)
      case (None, Some(latestStatus)) => twitter4jObj.updateStatus(latestStatus)
      case _ => throw new IllegalArgumentException("Parameter must set either status or latestStatus.")
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
    Option(paging) match {
      case Some(paging) => twitter4jObj.getRetweetedBy(statusId, paging)
      case None => twitter4jObj.getRetweetedBy(statusId)
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def getRetweetedByIDs(statusId: Long, paging: twitter4j.Paging = null): IDs = {
    Option(paging) match {
      case Some(paging) => twitter4jObj.getRetweetedByIDs(statusId, paging)
      case None => twitter4jObj.getRetweetedByIDs(statusId)
    }
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
    Option(paging) match {
      case Some(paging) => twitter4jObj.getRetweetedByMe(paging)
      case None => twitter4jObj.getRetweetedByMe()
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def getRetweetedToMe(paging: twitter4j.Paging = null): ResponseList[twitter4j.Status] = {
    Option(paging) match {
      case Some(paging) => twitter4jObj.getRetweetedToMe(paging)
      case None => twitter4jObj.getRetweetedToMe()
    }
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
    
    specificUser match {
      case Right(userId) => twitter4jObj.getRetweetedToUser(userId, paging)
      case Left(screenName) => twitter4jObj.getRetweetedToUser(screenName, paging)
    }
  }

  /**
   * {@inheritDoc}
   */
  def getRetweetedByUser(
      specificUser: User.SpecificInfo,
      paging: twitter4j.Paging): ResponseList[twitter4j.Status] = {
    require(specificUser != null)
    
    specificUser match {
      case Right(userId) => twitter4jObj.getRetweetedByUser(userId, paging)
      case Left(screenName) => twitter4jObj.getRetweetedByUser(screenName, paging)
    }
  }
  
  /* TrendMethods */
  /**
   * {@inheritDoc}
   */
  def getDailyTrends(date: Date = null, excludeHashTags: java.lang.Boolean = null): ResponseList[twitter4j.Trends] = {
    (Option(date), Option(excludeHashTags)) match {
      case (Some(date), Some(excludeHashTags)) => twitter4jObj.getDailyTrends(date, excludeHashTags)
      case (None, None) => twitter4jObj.getDailyTrends()
      case _ => throw new IllegalArgumentException("Parameter must set nothing or both of date and excludeHashTags.")
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def getWeeklyTrends(date: Date = null, excludeHashTags: java.lang.Boolean = null): ResponseList[twitter4j.Trends] = {
    (Option(date), Option(excludeHashTags)) match {
      case (Some(date), Some(excludeHashTags)) => twitter4jObj.getWeeklyTrends(date, excludeHashTags)
      case (None, None) => twitter4jObj.getWeeklyTrends()
      case _ => throw new IllegalArgumentException("Parameter must set nothing or both of date and excludeHashTags.")
    }
  }
  
  /* UserMethods */
  /**
   * {@inheritDoc}
   */
  // TODO ユーザ指定変更
  def showUser(screenName: String = null, userId: java.lang.Long = null): User = {
    (Option(screenName), Option(userId)) match {
      case (_, Some(userId)) => twitter4jObj.showUser(userId)
      case (Some(screenName), None) => twitter4jObj.showUser(screenName)
      case _ => throw new IllegalArgumentException("Parameter screenName or userId must be set at least.")
    }
  }
  
  /**
   * {@inheritDoc}
   */
  // TODO ユーザ指定情報の配列化
  def lookupUsers(screenNames: Array[String] = null, ids: Array[Long] = null): ResponseList[twitter4j.User] = {
    (Option(screenNames), Option(ids)) match {
      case (_, Some(ids)) => twitter4jObj.lookupUsers(ids)
      case (Some(screenNames), None) => twitter4jObj.lookupUsers(screenNames)
      case _ => throw new IllegalArgumentException("Parameter screenNames or ids must be set at least.")
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
   * 
   */
  def apply(consumerKey: ConsumerKey, accessToken: AccessToken) = {
    val twitter4jObj = getTwitter4jInstance(
        getTwitterFactory4j(None, None),
        None,
        None)
    twitter4jObj.setOAuthConsumer(consumerKey.consumerKey, consumerKey.consumerSecret)
    twitter4jObj.setOAuthAccessToken(accessToken)
    new Twitter(twitter4jObj)
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