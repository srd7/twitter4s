package twitter4s

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
object GeoQuery {
  def apply(latitude: Double, longitude: Double) = {
    new twitter4j.GeoQuery(GeoLocation(latitude, longitude))
  }
  
  def apply(location: twitter4j.GeoLocation) = {
    new twitter4j.GeoQuery(location)
  }
  
  def apply(ip: String) = {
    new twitter4j.GeoQuery(ip)
  }
}