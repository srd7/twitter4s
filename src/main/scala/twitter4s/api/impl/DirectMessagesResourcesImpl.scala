package twitter4s.api.impl

import twitter4s.ResponseList
import twitter4j.Paging
import twitter4s.api.DirectMessagesResources
import scala.util.Either
import twitter4s.Twitter
import twitter4s.User
import twitter4s.DirectMessage

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