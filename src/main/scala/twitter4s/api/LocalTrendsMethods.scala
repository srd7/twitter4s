package twitter4s.api

import twitter4s.ResponseList
import twitter4j.Location
import twitter4j.GeoLocation
import twitter4j.Trends

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait LocalTrendsMethods {
  /**
   * Returns the locations that Twitter has trending topic information for.The response is an array of &quot;locations&quot; that encode the location's WOEID (a <a href="http://developer.yahoo.com/geo/geoplanet/">Yahoo! Where On Earth ID</a>) and some other human-readable information such as a canonical name and country the location belongs in.
   * <br/>This method calls twitter4j.Twitter.getAvailableTrends.
   * <br/>getAvailableTrends method calls http://api.twitter.com/1/trends/available.json
   * 
   * @param location (optional) the available trend locations will be sorted by distance to the lat and long passed in. The sort is nearest to furthest.
   * @return the locations
   * @throws twitter4j.TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/trends/available">GET trends/available | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getAvailableTrends(location: Option[GeoLocation] = None): ResponseList[Location]
  
  /**
   * Returns the top 10 trending topics for a specific location Twitter has trending topic information for. The response is an array of "trend" objects that encode the name of the trending topic, the query parameter that can be used to search for the topic on Search, and the direct URL that can be issued against Search. This information is cached for five minutes, and therefore users are discouraged from querying these endpoints faster than once every five minutes. Global trends information is also available from this API by using a WOEID of 1.
   * <br/>This method calls twitter4j.Twitter.getLocationTrends.
   * <br/>twitter4j.Twitter.getLocationTrends method calls http://api.twitter.com/1/trends/:woeid.json
   * 
   * @param woeid The WOEID of the location to be querying for
   * @return trends
   * @throws twitter4j.TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/trends/:woeid">GET trends/:woeid | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getLocationTrends(woeid: Int): Trends
}