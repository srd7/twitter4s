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
  type Tw4jResponse = twitter4j.Trends
  
  def trends = twt4jTrends.getTrends()

  def location = twt4jTrends.getLocation()

  def asOf = twt4jTrends.getAsOf()

  def trendAt = twt4jTrends.getTrendAt()

  def rateLimitStatus = RateLimitStatusImpl(twt4jTrends.getRateLimitStatus())

  def accessLevel = twt4jTrends.getAccessLevel()

  def tw4jObj = twt4jTrends

  def apply(idx: Int) = trends(idx)
}