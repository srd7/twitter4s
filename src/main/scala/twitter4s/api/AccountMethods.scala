package twitter4s.api

import twitter4j.User
import twitter4j.RateLimitStatus
import twitter4j.AccountTotals
import twitter4j.AccountSettings

import java.io.File
import java.io.InputStream

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait AccountMethods {

  /**
   * Returns an HTTP 200 OK response code and a representation of the requesting user if authentication was successful; returns a 401 status code and an error message if not. Use this method to test if supplied user credentials are valid.
   * <br/>This method calls twitter4j.Twitter.verifyCredentials.
   * <br/>twitter4j.Twitter.verifyCredentials calls http://api.twitter.com/1/account/verify_credentials.json
   * 
   * @return user
   * @throws twitter4j.TwitterException when Twitter service or network is unavailable, or if supplied credential is wrong (TwitterException.getStatusCode() == 401)
   * @see <a href="https://dev.twitter.com/docs/api/1/get/account/verify_credentials">GET account/verify_credentials | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def verifyCredentials: User
  
  def getRateLimitStatus: RateLimitStatus
  
  def updateProfileColors(profileBackgroundColor: String, profileTextColor: String, profileLinkColor: String, profileSidebarFillColor: String, profileSidebarBorderColor: String): User
  
  def updateProfileImage(imageFile: Option[File] = None, imageStream: Option[InputStream] = None): User
  
  def updateProfileBackgroundImage(imageFile: Option[File] = None, imageStream: Option[InputStream] = None, tile: Boolean): User
  
  def updateProfile(name: String, url: String, location: String, description: String): User
  
  def getAccountTotals: AccountTotals
  
  def getAccountSettings: AccountSettings
  
  def updateAccountSettings(trendLocationWoeid: Int, sleepTimeEnabled: Boolean, startSleepTime: String, endSleepTime: String, timeZone: String, lang: String): AccountSettings
}