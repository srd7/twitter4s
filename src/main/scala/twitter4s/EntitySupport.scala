package twitter4s
import twitter4j.URLEntity
import twitter4j.MediaEntity
import twitter4j.HashtagEntity

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait EntitySupport {
  def urlEntities: Array[URLEntity]
  
  def hashtagEntities: Array[HashtagEntity]
  
  def mediaEntities: Array[MediaEntity]
}