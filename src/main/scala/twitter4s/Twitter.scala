package twitter4s

import java.io.File
import java.io.InputStream
import java.util.Date
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

case class Twitter(twitter4jObj: twitter4j.Twitter) extends TwitterBase with TwitterAPIs {
  /* TwitterBase method */
  def getScreenName: String = {
    // TODO implements
    return null
  }
  
  def getId: Long = {
    // TODO imeplements
    return 0
  }
  
  def addRateLimitStatusListener(listener: RateLimitStatusListener) {
    // TODO implements
  }
  
  /**
   * {@inheritDoc}
   */
  def getAuthorization: Authorization = {
    twitter4jObj.getAuthorization()
  }
  
  def getConfiguration: Configuration = {
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
    return twitter4jObj.test()
  }
	
  def getAPIConfiguration: TwitterAPIConfiguration = {
    return twitter4jObj.getAPIConfiguration()
  }

  /**
   * {@inheritDoc}
   */
  def getLanguages: ResponseList[Language] = {
    ResponseList(twitter4jObj.getLanguages())
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
    ResponseList(paging match {
      case Some(paging) => twitter4jObj.getDirectMessages(paging)
      case None => twitter4jObj.getDirectMessages()
    })
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
  
  /* LocalTrendsMethods */
  /**
   * {@inheritDoc}
   */
  def getAvailableTrends(location: Option[GeoLocation] = None): ResponseList[Location] = {
    ResponseList(location match {
      case Some(locationData) => twitter4jObj.getAvailableTrends(locationData)
      case None => twitter4jObj.getAvailableTrends()
    })
  }

  /**
   * {@inheritDoc}
   */
  def getLocationTrends(woeid: Int): Trends = {
    twitter4jObj.getLocationTrends(woeid)
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
  
  /* TrendMethods */
  /**
   * {@inheritDoc}
   */
  def getDailyTrends(date: Option[Date] = None, excludeHashTags: Option[Boolean] = None): ResponseList[Trends] = {
    ResponseList((date, excludeHashTags) match {
      case (Some(date), Some(excludeHashTags)) => twitter4jObj.getDailyTrends(date, excludeHashTags)
      case (None, None) => twitter4jObj.getDailyTrends()
      //case _ => //Exception?
    })
  }
  
  /**
   * {@inheritDoc}
   */
  def getWeeklyTrends(date: Option[Date] = None, excludeHashTags: Option[Boolean] = None): ResponseList[Trends] = {
    ResponseList((date, excludeHashTags) match {
      case (Some(date), Some(excludeHashTags)) => twitter4jObj.getWeeklyTrends(date, excludeHashTags)
      case (None, None) => twitter4jObj.getWeeklyTrends()
      // case _ => //Exception?
    })
  }
}

object Twitter {
  def apply(conf: Option[Configuration] = None, configTreePath: Option[String] = None, accessToken: Option[AccessToken] = None, auth: Option[Authorization] = None) = {
    val factory4j = getTwitterFactory4j(conf, configTreePath)
    new Twitter(getTwitter4jInstance(factory4j, accessToken, auth))
  }
  
  private def getTwitterFactory4j(conf: Option[Configuration], configTreePath: Option[String]) = {
    (conf, configTreePath) match {
      case (None, None) => new TwitterFactory()
      case (Some(config), None) => new TwitterFactory(config)
      case (None, Some(confTree)) => new TwitterFactory(confTree)
      //case _ => // Exception?
    }
  }
  
  private def getTwitter4jInstance(factory4j: TwitterFactory, accessToken: Option[AccessToken], auth: Option[Authorization]) = {
    (accessToken, auth) match {
      case (None, None) => factory4j.getInstance()
      case (Some(token), None) => factory4j.getInstance(token)
      case (None, Some(authInfo)) => factory4j.getInstance(authInfo)
      //case _ => // Exception?
    }
  }
}