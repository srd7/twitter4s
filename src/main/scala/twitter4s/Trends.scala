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
  
  def trends: Array[Trend]

  def location: Location

  def asOf: Date

  def trendAt: Date

  def apply(idx: Int):Trend
}