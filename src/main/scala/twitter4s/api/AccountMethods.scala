package twitter4s.api

import twitter4j.User
import twitter4j.RateLimitStatus
import twitter4j.AccountTotals
import twitter4j.AccountSettings

import java.io.File
import java.io.InputStream

trait AccountMethods {

  def verifyCredential: User
  
  def getRateLimitStatus: RateLimitStatus
  
  def updateProfileColors(profileBackgroundColor: String, profileTextColor: String, profileLinkColor: String, profileSidebarFillColor: String, profileSidebarBorderColor: String): User
  
  def updateProfileImage(image: File): User
  
  def updateProfileImage(image: InputStream): User
  
  def updateProfileBackgroundImage(image: File, tile: Boolean): User
  
  def updateProfileBackgroundImage(image: InputStream, tile: Boolean): User
  
  def updateProfile(name: String, url: String, location: String, description: String): User
  
  def getAccountTotals: AccountTotals
  
  def getAccountSettings: AccountSettings
  
  def updateAccountSettings(trendLocationWoeid: Int, sleepTimeEnabled: Boolean, startSleepTime: String, endSleepTime: String, timeZone: String, lang: String): AccountSettings
}