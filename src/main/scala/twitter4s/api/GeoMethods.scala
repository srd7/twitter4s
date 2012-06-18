package twitter4s.api

import twitter4j.GeoQuery
import twitter4s.ResponseList
import twitter4j.Place
import twitter4j.GeoLocation
import twitter4j.SimilarPlaces

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait GeoMethods {
  /**
   * Search for places that can be attached to a statuses/update. Given a latitude and a longitude pair, an IP address, or a name, this request will return a list of all the valid places that can be used as the place_id when updating a status.
   * <br/>Conceptually, a query can be made from the user's location, retrieve a list of places, have the user validate the location he or she is at, and then send the ID of this location with a call to statuses/update.
   * <br/>This is the recommended method to use find places that can be attached to statuses/update. Unlike geo/reverse_geocode which provides raw data access, this endpoint can potentially re-order places with regards to the user who is authenticated. This approach is also preferred for interactive place matching with the user.
   * <br/>This method calls twitter4j.Twitter.searchPlaces(query)
   * <br/>searchPlaces calls http://api.twitter.com/1/geo/search.json
   *
   * @param query search query
   * @return places (cities and neighborhoods) that can be attached to a statuses/update
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/geo/search">GET geo/search | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def searchPlaces(query: GeoQuery): ResponseList[Place]
  
  /**
   * Locates places near the given coordinates which are similar in name.
   * <br/>Conceptually you would use this method to get a list of known places to choose from first. Then, if the desired place doesn't exist, make a request to post/geo/place to create a new one.
   * <br/>The token contained in the response is the token needed to be able to create a new place.
   * <br/>This method calls twitter4j.Twitter.getSimilarPlaces(location, name, containedWithin, streetAddress)
   * <br/>getSimilarPlaces calls http://api.twitter.com/1/geo/similar_places.json
   *
   * @param location The latitude and longitude to search around.
   * @param name The name a place is known as.
   * @param containedWithin optional: the place_id which you would like to restrict the search results to. Setting this value means only places within the given place_id will be found.
   * @param streetAddress optional: This parameter searches for places which have this given street address. There are other well-known, and application specific attributes available. Custom attributes are also permitted. Learn more about Place Attributes.
   * @return places (cities and neighborhoods) that can be attached to a statuses/update
   * @throws TwitterException when Twitter service or network is unavailable
   * @since Twitter4S 1.0.0
   */
  def getSimilarPlaces(location: GeoLocation, name: String, containedWithin: String, streetAddress: String): SimilarPlaces
  
  /**
   * Search for places (cities and neighborhoods) that can be attached to a statuses/update. Given a latitude and a longitude, return a list of all the valid places that can be used as a place_id when updating a status. Conceptually, a query can be made from the user's location, retrieve a list of places, have the user validate the location he or she is at, and then send the ID of this location up with a call to statuses/update.<br/>
   * There are multiple granularities of places that can be returned -- "neighborhoods", "cities", etc. At this time, only United States data is available through this method.<br/>
   * This API call is meant to be an informative call and will deliver generalized results about geography.
   * <br/>This method calls twitter4j.Twitter.reverseGeoCode(query)
   * <br/>This method calls http://api.twitter.com/1/geo/reverse_geocode.json
   *
   * @param query search query
   * @return places (cities and neighborhoods) that can be attached to a statuses/update
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/geo/reverse_geocode">GET geo/reverse_geocode | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def reverseGeoCode(query: GeoQuery): ResponseList[Place]
  
  /**
   * Find out more details of a place that was returned from the {@link twitter4j.api.GeoMethods#reverseGeoCode(twitter4j.GeoQuery)} method.
   * <br/>This method calls twitter4j.Twitter.getGeoDetails(id)
   * <br/>This method calls http://api.twitter.com/1/geo/id/:id.json
   *
   * @param id The ID of the location to query about.
   * @return details of the specified place
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/geo/id/:place_id">GET geo/id/:place_id | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getGeoDetails(id: String): Place
  
  // TODO コメント
  def createPlace(name: String, containedWithin: String, token: String, location: GeoLocation, streetAddress: String): Place
}