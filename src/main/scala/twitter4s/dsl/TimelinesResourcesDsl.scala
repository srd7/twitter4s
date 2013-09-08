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

import twitter4s.{User, ResponseList, Twitter}
import twitter4s.api.impl.TimelinesResourcesImpl
import twitter4s.api.TimelinesResources
import twitter4j.{Status, Paging}

/**
 * @author mao.instantlife at gmail.com
 */
trait TimelinesResourcesDsl extends Twitter4sDslBase with TimelinesResources {
  type ResourcesType = TimelinesResourcesImpl

  override lazy val twitter4sResources = new Twitter(twitter4jResources) with ResourcesType

  // bellow methods implements are a cliche.
  def getHomeTimeline(paging: Paging = null): ResponseList[Status] = resources.getHomeTimeline(paging)
  def getUserTimeline(specificUser: User.SpecificInfo = null, paging: Paging = null): ResponseList[Status] = resources.getUserTimeline(specificUser, paging)
  def getMentions(paging: Paging = null): ResponseList[Status] = resources.getMentions(paging)
  def getRetweetsOfMe(paging: Paging = null): ResponseList[Status] = resources.getRetweetsOfMe()
  def getMentionsTimeline(paging: Paging = null): ResponseList[Status] = resources.getMentionsTimeline(paging)
}
