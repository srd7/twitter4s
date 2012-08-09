package twitter4s

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
object GeoLocation {
  def apply(latitude: Double, longitude: Double) = {
    new twitter4j.GeoLocation(latitude, longitude)
  }
}