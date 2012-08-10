package twitter4s

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait Place extends TwitterResponse with Ordered[Place] {
  def name: String
  
  def streetAddress: String
  
  def countryCode: String
  
  def id: String
  
  def country: String
  
  def placeType: String
  
  def url: String
  
  def fullName: String
  
  def boundingBoxType: String
  
  def boundingBoxCoordinates: Array[Array[twitter4j.GeoLocation]]
  
  def geometryType: String
  
  def geometryCoordinates: Array[Array[twitter4j.GeoLocation]]
  
  def containedWithIn: Array[Place]
}