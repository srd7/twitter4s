package twitter4s.api.impl

import twitter4s._
import twitter4s.ResponseList
import twitter4j.GeoQuery
import twitter4s.api.PlaceGeoResources

trait PlaceGeoResourcesImpl extends PlaceGeoResources {
  self: Twitter =>
  
  /**
   * {@inheritDoc}
   */
  def searchPlaces(query: twitter4j.GeoQuery): ResponseList[twitter4j.Place] = {
    twitter4jObj.searchPlaces(query)
  }

  /**
   * {@inhritDoc}
   */
  def getSimilarPlaces(location: twitter4j.GeoLocation, name: String, containedWithin: String, streetAddress: String): SimilarPlaces = {
    twitter4jObj.getSimilarPlaces(location, name, containedWithin, streetAddress)
  }

  /**
   * {@inheritDoc}
   */
  def reverseGeoCode(query: twitter4j.GeoQuery): ResponseList[twitter4j.Place] = {
    twitter4jObj.reverseGeoCode(query)
  }

  /**
   * {@inheritDoc}
   */
  def getGeoDetails(id: String): Place = {
    twitter4jObj.getGeoDetails(id)
  }
  
  /**
   * {@inheritDoc}
   */
  def createPlace(name: String, containedWithin: String, token: String, location: twitter4j.GeoLocation, streetAddress: String): Place = {
    twitter4jObj.createPlace(name, containedWithin, token, location, streetAddress)
  }
}