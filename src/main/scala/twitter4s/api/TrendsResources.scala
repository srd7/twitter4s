package twitter4s.api
/*
 * Copyright (C) 2012 Shinsuke Abe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import twitter4s.ResponseList
import twitter4j.Location
import twitter4j.GeoLocation
import twitter4s.Trends

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait TrendsResources {
  /**
   * Returns the locations that Twitter has trending topic information for.
   * <br />This method calls twitter4j.Twitter.getAvailableTrends.
   * <br />getAvailableTrends method calls http://api.twitter.com/1.1/trends/available.json
   *
   * @return the locations
   * @throws twitter4j.TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1.1/get/trends/available">GET trends/available | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getAvailableTrends(): ResponseList[Location]

  /**
   * Returns the top 10 trending topics for a specific WOEID, if trending information is available for it.<br>
   * The response is an array of "trend" objects that encode the name of the trending topic, the query parameter that can be used to search for the topic on <a href="http://search.twitter.com/">Twitter Search</a>, and the Twitter Search URL.<br>
   * This information is cached for 5 minutes. Requesting more frequently than that will not return any more data, and will count against your rate limit usage.<br>
   * <br />This method calls twitter4j.Twitter.getPlaceTrends
   * <br />This method calls http://api.twitter.com/1.1/trends/place.json
   *
   * @param woeid The WOEID of the location to be querying for
   * @return trends
   * @throws twitter4j.TwitterException when Twitter service or network is unavailable
   * @since Twitter4S 2.0.0
   */
  def getPlaceTrends(woeid: Int): Trends

  /**
   * Returns the locations that Twitter has trending topic information for, closest to a specified location.<br>
   * The response is an array of "locations" that encode the location's WOEID and some other human-readable information such as a canonical name and country the location belongs in.<br>
   * A WOEID is a <a href="http://developer.yahoo.com/geo/geoplanet/">Yahoo! Where On Earth ID</a>.
   * <br />This method calls twitter4j.Twitter.getClosestTrends.
   * <br />This method calls http://api.twitter.com/1.1/trends/closest.json
   *
   * @param location the available trend locations will be sorted by distance to the lat and long passed in. The sort is nearest to furthest.
   * @return the locations
   * @throws twitter4j.TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1.1/get/trends/closest">GET trends/closest | Twitter Developers</a>
   * @since Twitter4S 2.0.0
   */
  def getClosestTrends(location: GeoLocation): ResponseList[Location]
}
