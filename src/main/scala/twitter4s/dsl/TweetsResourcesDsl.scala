package twitter4s.dsl

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
import twitter4s.api.TweetsResources
import twitter4s.api.impl.TweetsResourcesImpl
import twitter4s.{ResponseList, Twitter}
import twitter4j.Status

/**
 * @author mao.instantlife at gmail.com
 */
trait TweetsResourcesDsl extends Twitter4sDslBase with TweetsResources {
  type ResourcesType = TweetsResourcesImpl

  override lazy val twitter4sResources = new Twitter(twitter4jResources) with ResourcesType

  // bellow methods implements are a cliche.
  def showStatus(id: Long): twitter4s.Status = resources.showStatus(id)
  def updateStatus(status: twitter4s.Status.StatusSpecific): twitter4s.Status = resources.updateStatus(status)
  def destroyStatus(statusId: Long): twitter4s.Status = resources.destroyStatus(statusId)
  def retweetStatus(statusId: Long): twitter4s.Status = resources.retweetStatus(statusId)
  def getRetweets(statusId: Long): ResponseList[Status] = resources.getRetweets(statusId)
}
