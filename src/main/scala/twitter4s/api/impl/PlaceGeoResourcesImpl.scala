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
import twitter4j.GeoQuery
import twitter4s.api.PlaceGeoResources

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 * @since Twitter4S 2.0.0
 */
// TODO import整理
trait PlaceGeoResourcesImpl extends PlaceGeoResources {
  self: Twitter =>
  
  /**
   * {@inheritDoc}
   */
  def searchPlaces(query: twitter4j.GeoQuery): ResponseList[twitter4j.Place] = {
    twitter4jObj.searchPlaces(query)
  }

  /**
   * {@inhritDoc}
   */
  def getSimilarPlaces(location: twitter4j.GeoLocation, name: String, containedWithin: String, streetAddress: String): SimilarPlaces = {
    twitter4jObj.getSimilarPlaces(location, name, containedWithin, streetAddress)
  }

  /**
   * {@inheritDoc}
   */
  def reverseGeoCode(query: twitter4j.GeoQuery): ResponseList[twitter4j.Place] = {
    twitter4jObj.reverseGeoCode(query)
  }

  /**
   * {@inheritDoc}
   */
  def getGeoDetails(id: String): Place = {
    twitter4jObj.getGeoDetails(id)
  }
  
  /**
   * {@inheritDoc}
   */
  def createPlace(name: String, containedWithin: String, token: String, location: twitter4j.GeoLocation, streetAddress: String): Place = {
    twitter4jObj.createPlace(name, containedWithin, token, location, streetAddress)
  }
}