package twitter4s.internal.json

import twitter4s.TwitterAPIConfiguration
import scala.collection.JavaConverters._

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class TwitterAPIConfigurationImpl(tw4jApiConfiguration: twitter4j.TwitterAPIConfiguration) extends TwitterAPIConfiguration {
  type Tw4jResponse = twitter4j.TwitterAPIConfiguration

  def photoSizeLimit = tw4jApiConfiguration.getPhotoSizeLimit()

  def shortURLLength = tw4jApiConfiguration.getShortURLLength()

  def shortURLLengthHttps = tw4jApiConfiguration.getShortURLLengthHttps()

  def charactersReservedPerMedia = tw4jApiConfiguration.getCharactersReservedPerMedia()

  def photoSizes = tw4jApiConfiguration.getPhotoSizes().asScala

  def nonUsernamePaths = tw4jApiConfiguration.getNonUsernamePaths()

  def maxMediaPerUpload = tw4jApiConfiguration.getMaxMediaPerUpload()
  
  def rateLimitStatus = RateLimitStatusImpl(tw4jApiConfiguration.getRateLimitStatus())
  
  def accessLevel = tw4jApiConfiguration.getAccessLevel()
  
  def tw4jObj = tw4jApiConfiguration

}