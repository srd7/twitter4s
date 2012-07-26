package twitter4s
import twitter4j.Trend
import twitter4j.RateLimitStatus
import twitter4j.Location
import java.util.Date

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait Trends extends TwitterResponse {
  type Tw4jResponse = twitter4j.Trends
  
  /**
   * Array of Trend object from twitter api response.
   * 
   * @return array of trend
   * @since Twitter4S 1.0.0
   */
  def trends: Array[Trend]

  /**
   * Location object from twitter api response.
   * 
   * @return location object.
   * @since Twitter4S 1.0.0
   */
  def location: Location

  def asOf: Date

  def trendAt: Date

  def apply(idx: Int):Trend
}