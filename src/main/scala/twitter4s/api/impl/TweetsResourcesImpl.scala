package twitter4s.api.impl

import twitter4j.Paging
import twitter4s.Status
import twitter4s.api.TweetsResources
import scala.util.Either
import twitter4s.Twitter
import twitter4s.ResponseList
import twitter4s.IDs

trait TweetsResourcesImpl extends TweetsResources {
  self: Twitter =>
  
  /**
   * {@inheritDoc}
   */
  def showStatus(id: Long): Status = {
    twitter4jObj.showStatus(id)
  }

  /**
   * {@inheritDoc}
   */
  def updateStatus(status: Status.StatusSpecific): Status = {
    require(status != null)
    
    status match {
      case Left(text) => twitter4jObj.updateStatus(text)
      case Right(latestStatus) => twitter4jObj.updateStatus(latestStatus)
    }
  }

  /**
   * {@inheritDoc}
   */
  def destroyStatus(statusId: Long): Status = {
    twitter4jObj.destroyStatus(statusId)
  }

  /**
   * {@inheritDoc}
   */
  def retweetStatus(statusId: Long): Status = {
    twitter4jObj.retweetStatus(statusId)
  }
  
  /**
   * {@inheritDoc}
   */
  def getRetweets(statusId: Long): ResponseList[twitter4j.Status] = {
    twitter4jObj.getRetweets(statusId)
  }
}