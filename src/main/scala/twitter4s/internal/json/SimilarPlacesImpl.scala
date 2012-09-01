package twitter4s.internal.json
import scala.collection.JavaConverters.asScalaBufferConverter
import twitter4s.SimilarPlaces
import twitter4j.Place
import twitter4s.AccessLevel

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class SimilarPlacesImpl(tw4jSimilarPlaces: twitter4j.SimilarPlaces) extends SimilarPlaces {
  type Tw4jResponse = twitter4j.SimilarPlaces
  
  /**
   * scala converted list
   */
  def listAsScala = tw4jSimilarPlaces.asScala
  
  /**
   * rate limit status
   */
  def rateLimitStatus = RateLimitStatusImpl(tw4jSimilarPlaces.getRateLimitStatus())
  
  /**
   * feature specific rate limit status
   */
  def featureSpecificRateLimitStatus = RateLimitStatusImpl(tw4jSimilarPlaces.getFeatureSpecificRateLimitStatus())
  
  /**
   * access level
   */
  def accessLevel = AccessLevel(tw4jSimilarPlaces.getAccessLevel())
  
  /**
   * Get object in scala converted list method.
   */
  def apply(idx: Int):Place = listAsScala(idx)
  
  /**
   * Get Twitter4J Object
   */
  def tw4jObj = tw4jSimilarPlaces
  
  def token = tw4jSimilarPlaces.getToken()
}