package twitter4s
import java.util.Date

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait DirectMessage extends TwitterResponse {
  def id: Long
  
  def text: String
  
  def senderId: Long
  
  def recipientId: Long
  
  def createdAt: Date
  
  def senderScreenName: String
  
  def recipientScreenName: String
  
  def sender: User
  
  def recipient: User
}