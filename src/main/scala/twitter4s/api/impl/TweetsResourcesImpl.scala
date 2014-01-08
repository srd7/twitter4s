package twitter4s.api.impl

/*
 * Copyright (C) 2014 Shinsuke Abe
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
import twitter4s.Status
import twitter4s.api.TweetsResources
import twitter4s.Twitter
import twitter4s.ResponseList

/**
 * @author mao.instantlife at gmail.com
 */
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