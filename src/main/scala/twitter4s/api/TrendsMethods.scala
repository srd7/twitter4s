package twitter4s.api

import java.util.Date
import twitter4s.ResponseList
import twitter4j.Trends

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait TrendsMethods {
  /**
   * Returns the top 20 trending topics for each hour in a given day.
   * <br /> This method calls twitter4j.Twitter.getDailyTrends.
   * <br /> getDailyTrends method calls http://search.twitter.com/trends/daily.json
   * 
   * @param date (optional) Permits specifying a start date for the report.
   * @param excludeHashTags (optional) Setting this to true will remove all hashtags from the trends list.
   * @return the result
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when only one parameter either date or excludeHashTags is set.
   * @see <a href="https://dev.twitter.com/docs/api/1/get/trends/daily">GET trends/daily | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getDailyTrends(date: Date = null, excludeHashTags: java.lang.Boolean = null): ResponseList[Trends]
  
  /**
   * Returns the top 30 trending topics for each day in a given week.
   * <br />This method calls twitter4j.Twitter.getWeeklyTrends.
   * <br />getWeeklyTrends method calls http://search.twitter.com/trends/weekly.json
   * 
   * @param date (optional) Permits specifying a start date for the report.
   * @param excludeHashTags (optional) Setting this to true will remove all hashtags from the trends list.
   * @return the result
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when only one parameter either date or excludeHashTags is set.
   * @see <a href="https://dev.twitter.com/docs/api/1/get/trends/weekly">GET trends/weekly | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getWeeklyTrends(date: Date = null, excludeHashTags: java.lang.Boolean = null): ResponseList[Trends]
}