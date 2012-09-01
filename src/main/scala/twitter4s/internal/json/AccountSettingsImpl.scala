package twitter4s.internal.json
import twitter4s.AccountSettings
import twitter4s.AccessLevel

case class AccountSettingsImpl(twt4jAccountSettings: twitter4j.AccountSettings) extends AccountSettings {
  type Tw4jResponse = twitter4j.AccountSettings
  
  def isSleepTimeEnabled = twt4jAccountSettings.isSleepTimeEnabled()

  def sleepStartTime = twt4jAccountSettings.getSleepStartTime()

  def sleepEndTime = twt4jAccountSettings.getSleepEndTime()

  def trendLocations = twt4jAccountSettings.getTrendLocations()

  def isGeoEnabled = twt4jAccountSettings.isGeoEnabled()

  def timeZone = twt4jAccountSettings.getTimeZone()

  def language = twt4jAccountSettings.getLanguage()

  def isDiscoverableByEmail = twt4jAccountSettings.isDiscoverableByEmail()

  def isAlwaysUseHttps = twt4jAccountSettings.isAlwaysUseHttps()
  
  def rateLimitStatus = RateLimitStatusImpl(twt4jAccountSettings.getRateLimitStatus())
  
  def accessLevel = AccessLevel(twt4jAccountSettings.getAccessLevel())
  
  def tw4jObj = twt4jAccountSettings
}