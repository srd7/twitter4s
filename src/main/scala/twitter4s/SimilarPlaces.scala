package twitter4s

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait SimilarPlaces extends ResponseList[twitter4j.Place] {
  def token: String
}