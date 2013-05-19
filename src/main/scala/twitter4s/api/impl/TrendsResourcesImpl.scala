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
import twitter4s._
import twitter4s.ResponseList
import twitter4j.GeoLocation
import twitter4s.api.TrendsResources
import twitter4j.Location

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 * @since Twitter4S 2.0.0
 */
trait TrendsResourcesImpl extends TrendsResources {
  self: Twitter =>
  
  /**
   * {@inheritDoc}
   */
  def getAvailableTrends(location: twitter4j.GeoLocation = null): ResponseList[Location] = {
    Option(location) match {
      case Some(location) => twitter4jObj.getAvailableTrends(location)
      case None => twitter4jObj.getAvailableTrends()
    }
  }

  /**
   * {@inheritDoc}
   */
  def getLocationTrends(woeid: Int): Trends = {
    twitter4jObj.getLocationTrends(woeid)
  }
  
  /**
   * {@inheritDoc}
   */
  def getPlaceTrends(woeid: Int): Trends = {
    twitter4jObj.getPlaceTrends(woeid)
  }
}