package twitter4s

import java.io.File
import java.io.InputStream
import java.util.Date
import implicits.Twitter4SImplicits._
import twitter4j.api.HelpMethods.Language
import twitter4j.auth._
import twitter4j.conf._
import twitter4j.TwitterException
import twitter4j.TwitterAPIConfiguration
import twitter4j.IDs
import twitter4j.AccountTotals
import twitter4j.StatusUpdate
import twitter4j.Trends
import twitter4j.GeoLocation
import twitter4j.Paging
import twitter4j.DirectMessage
import twitter4j.TwitterFactory
import twitter4j.QueryResult
import twitter4j.AccountSettings
import twitter4j.RateLimitStatus
import twitter4j.RateLimitStatusListener
import twitter4j.Location
import twitter4j.Query
import twitter4j.User
import twitter4j.Status
import twitter4j.RelatedResults
import twitter4j.SavedSearch

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class Twitter(twitter4jObj: twitter4j.Twitter) extends TwitterBase with TwitterAPIs {
  // TODO PagableResponseListやUserListのラッピング
  // TODO 他のリターンオブジェクトとFactoryのラッピング
  // TODO 名前付き引数でセットするAPIで全てセットされた場合の挙動をAPIのドキュメントを確認して決める
  
  /* TwitterBase method */
  def screenName: String = {
    // TODO implements
    return null
  }
  
  def id: Long = {
    // TODO implements
    return 0
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
  
  def configuration: Configuration = {
    // TODO implements
    return null
  }
  
  def shotdown {
    // TODO implements
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
  
  def updateProfileColors(profileBackgroundColor: String, profileTextColor: String, profileLinkColor: String, profileSidebarFillColor: String, profileSidebarBorderColor: String): User = {
    // TODO implements
    null
  }
  
  def updateProfileImage(imageFile: Option[File] = None, imageStream: Option[InputStream] = None): User = {
    // TODO implements
    null
  }
  
  def updateProfileBackgroundImage(imageFile: Option[File] = None, imageStream: Option[InputStream] = None, tile: Boolean): User = {
    // TODO implements
    null
  }
  
  def updateProfile(name: String, url: String, location: String, description: String): User = {
    // TODO implements
    null
  }
  
  def getAccountTotals: AccountTotals = {
    // TODO implements
    null
  }
  
  def getAccountSettings: AccountSettings = {
    // TODO implements
    null
  }
  
  def updateAccountSettings(trendLocationWoeid: Int, sleepTimeEnabled: Boolean, startSleepTime: String, endSleepTime: String, timeZone: String, lang: String): AccountSettings = {
    // TODO implements
    null
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

  def getSentDirectMessages(paging: Option[Paging] = None): twitter4s.ResponseList[DirectMessage] = {
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
      case _ => throw new TwitterException("illegal argument")
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
      // case _ => exceptioin?
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
  
  /* StatusMethods */
  def showStatus(id: Long): Status = {
    // TODO implements
    return null
  }

  def updateStatus(status: Option[String] = None, latestStatus: Option[StatusUpdate] = None): Status = {
    // TODO implements
    return null
  }

  def destroyStatus(statusId: Long): Status = {
    // TODO implements
    return null
  }

  def retweetStatus(statusId: Long): Status = {
    // TODO implements
    return null
  }
  
  def getRetweets(statusId: Long): Status = {
    // TODO implements
    return null
  }
  
  def getRetweetedBy(statusId: Long, paging: Option[Paging] = None): ResponseList[User] = {
    // TODO implements
    return null
  }
  
  def getRetweetedByIDs(statusId: Long, paging: Option[Paging] = None): IDs = {
    // TODO implements
    return null
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
  
  def getUserTimeline(screenName: Option[String] = None, userId: Option[Long] = None, paging: Option[Paging] = None): ResponseList[Status] = {
    // TODO implements
    null
  }
  
  def getMentions(paging: Option[Paging] = None): ResponseList[Status] = {
    // TODO implements
    null
  }

  def getRetweetedByMe(paging: Option[Paging] = None): ResponseList[Status] = {
    // TODO implements
    null
  }
  
  def getRetweetedToMe(paging: Option[Paging] = None): ResponseList[Status] = {
    // TODO implements
    null
  }

  def getRetweetedOfMe(paging: Option[Paging] = None): ResponseList[Status] = {
    // TODO implements
    null
  }

  def getRetweetedToUser(paging: Paging, screenName: Option[String] = None, userId: Option[Long] = None): ResponseList[Status] = {
    // TODO implements
    null
  }

  def getRetweetedByUser(pagind: Paging, screenName: Option[String] = None, userId: Option[Long] = None): ResponseList[Status] = {
    // TODO implements
    null
  }
  
  /* TrendMethods */
  /**
   * {@inheritDoc}
   */
  def getDailyTrends(date: Option[Date] = None, excludeHashTags: Option[Boolean] = None): ResponseList[Trends] = {
    (date, excludeHashTags) match {
      case (Some(date), Some(excludeHashTags)) => twitter4jObj.getDailyTrends(date, excludeHashTags)
      case (None, None) => twitter4jObj.getDailyTrends()
      //case _ => //Exception?
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def getWeeklyTrends(date: Option[Date] = None, excludeHashTags: Option[Boolean] = None): ResponseList[Trends] = {
    (date, excludeHashTags) match {
      case (Some(date), Some(excludeHashTags)) => twitter4jObj.getWeeklyTrends(date, excludeHashTags)
      case (None, None) => twitter4jObj.getWeeklyTrends()
      // case _ => //Exception?
    }
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