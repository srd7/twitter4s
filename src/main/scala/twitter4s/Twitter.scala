package twitter4s

import twitter4j._
import twitter4j.conf._
import twitter4j.auth._
import java.util.Date
import java.io.File
import java.io.InputStream

case class Twitter(twitter4jObj: twitter4j.Twitter) extends TwitterAPIs {
  
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