package twitter4s
import twitter4j.MediaEntity

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait TwitterAPIConfiguration extends TwitterResponse {
  def photoSizeLimit: Int
  
  def shortURLLength: Int
  
  def shortURLLengthHttps: Int
  
  def charactersReservedPerMedia: Int
  
  def photoSizes: scala.collection.mutable.Map[Integer, MediaEntity.Size]
  
  def nonUsernamePaths: Array[String]
  
  def maxMediaPerUpload: Int
}