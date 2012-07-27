package twitter4s
import twitter4j.TimeZone
import twitter4j.Location

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait AccountSettings extends TwitterResponse {
  /**
   * is sleep time set.
   * 
   * @return setting enabled(true) or unabled(false)
   * @since Twitter4S 1.0.0
   */
  def isSleepTimeEnabled: Boolean

  /**
   * get sleep start time string.
   * 
   * @return sleep start time.
   * @since Twitter4S 1.0.0
   */
  def sleepStartTime: String

  /**
   * get sleep end time string.
   * 
   * @return sleep end time.
   * @since Twitter4S 1.0.0
   */
  def sleepEndTime: String

  /**
   * get trend location array.
   * 
   * @return trend location array.
   * @since Twitter4S 1.0.0
   */
  def trendLocations: Array[Location]

  /**
   * is geo setting set.
   * 
   * @return setting enabled(true) or unabled(false)
   * @since Twitter4S 1.0.0
   */
  def isGeoEnabled: Boolean

  /**
   * get time zone
   * 
   * @return time zone
   * @since Twitter4S 1.0.0
   */
  def timeZone: TimeZone

  /**
   * get user setting language.
   * 
   * @return language
   * @since Twitter4S 1.0.0
   */
  def language: String

  /**
   * is user acount discoverable by email set.
   * 
   * @return setting enabled(true) or unabled(false)
   * @since Twitter4S 1.0.0
   */
  def isDiscoverableByEmail: Boolean

  /**
   * is user access always use https set.
   */
  def isAlwaysUseHttps: Boolean
}