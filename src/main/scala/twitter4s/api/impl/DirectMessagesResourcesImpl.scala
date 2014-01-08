package twitter4s.api.impl

/*
 * Copyright (C) 2013 Shinsuke Abe
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
import twitter4s.ResponseList
import twitter4s.api.DirectMessagesResources
import twitter4s.Twitter
import twitter4s.User
import twitter4s.DirectMessage

/**
 * @author mao.instantlife at gmail.com
 */
trait DirectMessagesResourcesImpl extends DirectMessagesResources {
  self: Twitter =>
    
  /**
   * {@inheritDoc}
   */
  def getDirectMessages(paging: twitter4j.Paging = null): ResponseList[twitter4j.DirectMessage] = {
    Option(paging) match {
      case Some(paging) => twitter4jObj.getDirectMessages(paging)
      case None => twitter4jObj.getDirectMessages()
    }
  }

  /**
   * {@inheritDoc}
   */
  def getSentDirectMessages(paging: twitter4j.Paging = null): ResponseList[twitter4j.DirectMessage] = {
    Option(paging) match {
      case Some(paging) => twitter4jObj.getSentDirectMessages(paging)
      case None => twitter4jObj.getSentDirectMessages()
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def sendDirectMessage(specificUser: User.SpecificInfo, text: String): DirectMessage = {
    require(specificUser != null)
    
    specificUser match {
      case Right(userId) => twitter4jObj.sendDirectMessage(userId, text)
      case Left(screenName) => twitter4jObj.sendDirectMessage(screenName, text)
    }
  }

  /**
   * {@inheritDoc}
   */
  def destroyDirectMessage(id: Long): DirectMessage = {
    twitter4jObj.destroyDirectMessage(id)
  }

  /**
   * {@inheritDoc}
   */
  def showDirectMessage(id: Long): DirectMessage = {
    twitter4jObj.showDirectMessage(id)
  }
}