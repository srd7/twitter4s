package twitter4s.internal.json
import twitter4s.Trends
import twitter4j.Trend
import twitter4j.RateLimitStatus
import twitter4j.Location
import java.util.Date

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class TrendsImpl(twt4jTrends: twitter4j.Trends) extends Trends {
  def trends: Array[Trend] = twt4jTrends.getTrends()

  def location: Location = twt4jTrends.getLocation()

  def asOf: Date = twt4jTrends.getAsOf()

  def trendAt: Date = twt4jTrends.getTrendAt()

  def rateLimitStatus: RateLimitStatus = twt4jTrends.getRateLimitStatus()

  def accessLevel: Int = twt4jTrends.getAccessLevel()

  def tw4jObj: Any = twt4jTrends

  def apply(idx: Int): Trend = trends(idx)
}