package twitter4s.internal.json

import twitter4s.DirectMessage
import twitter4s.AccessLevel

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class DirectMessageImpl(twt4jDirectMessage: twitter4j.DirectMessage) extends DirectMessage {
  type Tw4jResponse = twitter4j.DirectMessage
  
  def id = twt4jDirectMessage.getId()
  
  def text = twt4jDirectMessage.getText()
  
  def senderId = twt4jDirectMessage.getSenderId()
  
  def recipientId = twt4jDirectMessage.getRecipientId()
  
  def createdAt = twt4jDirectMessage.getCreatedAt()
  
  def senderScreenName = twt4jDirectMessage.getSenderScreenName()
  
  def recipientScreenName = twt4jDirectMessage.getRecipientScreenName()
  
  def sender = UserImpl(twt4jDirectMessage.getSender())
  
  def recipient = UserImpl(twt4jDirectMessage.getRecipient())
  
  def rateLimitStatus = RateLimitStatusImpl(twt4jDirectMessage.getRateLimitStatus())

  def accessLevel = AccessLevel(twt4jDirectMessage.getAccessLevel())

  def tw4jObj = twt4jDirectMessage
}