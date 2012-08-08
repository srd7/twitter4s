package twitter4s

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait RelatedResults extends TwitterResponse {
  def tweetsFromUser: ResponseList[twitter4j.Status]
  
  def tweetsWithConversation: ResponseList[twitter4j.Status]
  
  def tweetsWithReply: ResponseList[twitter4j.Status]
}