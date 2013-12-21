package twitter4s.api.impl

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
import twitter4s._
import twitter4s.ResponseList
import twitter4j.Paging
import twitter4s.api.TimelinesResources
import scala.util.Either

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 * @since Twitter4S 2.0.0
 */
// TODO import整理
trait TimelinesResourcesImpl extends TimelinesResources {
  self: Twitter =>
  
  /**
   * {@inheritDoc}
   */
  def getHomeTimeline(paging: twitter4j.Paging = null): ResponseList[twitter4j.Status] = {
    Option(paging) match {
      case Some(paging) => twitter4jObj.getHomeTimeline(paging)
      case None => twitter4jObj.getHomeTimeline()
    }
  }
  
  /**
   * {@inheritedDoc}
   */
  def getUserTimeline(
      specificUser: User.SpecificInfo = null,
      paging: twitter4j.Paging = null): ResponseList[twitter4j.Status] = {
    (Option(specificUser), Option(paging)) match {
      case (None, None) => twitter4jObj.getUserTimeline()
      case (None, Some(paging)) => twitter4jObj.getUserTimeline(paging)
      case (Some(speficifUser), None) => specificUser match {
        case Right(userId) => twitter4jObj.getUserTimeline(userId)
        case Left(screenName) => twitter4jObj.getUserTimeline(screenName)
      }
      case (Some(specificUser), Some(paging)) => specificUser match {
        case Right(userId) => twitter4jObj.getUserTimeline(userId, paging)
        case Left(screenName) => twitter4jObj.getUserTimeline(screenName, paging)
      }
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def getMentions(paging: twitter4j.Paging = null): ResponseList[twitter4j.Status] = {
    Option(paging) match {
      case None => twitter4jObj.getMentions()
      case Some(paging) => twitter4jObj.getMentions(paging)
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def getMentionsTimeline(paging: twitter4j.Paging = null): ResponseList[twitter4j.Status] = {
    Option(paging) match {
      case None => twitter4jObj.getMentionsTimeline()
      case Some(paging) => twitter4jObj.getMentionsTimeline(paging)
    }
  }

  /**
   * {@inheritDoc}
   */
  def getRetweetsOfMe(paging: twitter4j.Paging = null): ResponseList[twitter4j.Status] = {
    Option(paging) match {
      case Some(paging) => twitter4jObj.getRetweetsOfMe(paging)
      case None => twitter4jObj.getRetweetsOfMe()
    }
  }
}