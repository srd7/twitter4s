package twitter4s.internal.json
/*
 * Copyright (C) 2012 Shinsuke Abe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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