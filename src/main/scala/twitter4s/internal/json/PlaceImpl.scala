package twitter4s.internal.json

import twitter4s.Place
import twitter4s.AccessLevel

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class PlaceImpl(tw4jPlace: twitter4j.Place) extends Place {
  type Tw4jResponse = twitter4j.Place

  def name = tw4jPlace.getName()

  def streetAddress = tw4jPlace.getStreetAddress()

  def countryCode = tw4jPlace.getCountryCode()

  def id = tw4jPlace.getId()

  def country = tw4jPlace.getCountry()

  def placeType = tw4jPlace.getPlaceType()

  def url = tw4jPlace.getURL()
  
  def fullName = tw4jPlace.getFullName()

  def boundingBoxType = tw4jPlace.getBoundingBoxType()

  def boundingBoxCoordinates = tw4jPlace.getBoundingBoxCoordinates()

  def geometryType = tw4jPlace.getGeometryType()

  def geometryCoordinates = tw4jPlace.getGeometryCoordinates()

  def containedWithIn = tw4jPlace.getContainedWithIn().map(PlaceImpl(_))
  
  def rateLimitStatus = RateLimitStatusImpl(tw4jPlace.getRateLimitStatus())
  
  def accessLevel = AccessLevel(tw4jPlace.getAccessLevel())
  
  def tw4jObj = tw4jPlace

  def compare(that: Place): Int = this.id.compare(that.id)

}