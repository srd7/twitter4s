package twitter4s
import twitter4j.Place

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait SimilarPlaces extends ResponseList[Place] {
  def token: String
}