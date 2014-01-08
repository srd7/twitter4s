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
import twitter4s.api.FavoritesResources
import twitter4s.Twitter
import twitter4s.Status
import twitter4j.Paging
import twitter4s.User

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 * @since Twitter4S 2.0.0
 */
trait FavoritesResourcesImpl extends FavoritesResources {
  self: Twitter =>
    
  /**
   * {@inhritDoc}
   */
  def getFavorites(
      specificUser: User.SpecificInfo = null,
      paging: Paging = null): ResponseList[twitter4j.Status] = {
    (Option(specificUser), Option(paging)) match {
      case (None, None) => twitter4jObj.getFavorites()
      case (Some(specificUser), None) => specificUser match {
        case Right(userId) => twitter4jObj.getFavorites(userId)
        case Left(screenName) => twitter4jObj.getFavorites(screenName)
      }
      case (Some(specificUser), Some(paging)) => specificUser match {
        case Right(userId) => twitter4jObj.getFavorites(userId, paging)
        case Left(screenName) => twitter4jObj.getFavorites(screenName, paging)
      }
      case (None, Some(paging)) => twitter4jObj.getFavorites(paging)
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def createFavorite(id: Long): Status = {
    twitter4jObj.createFavorite(id)
  }
  
  /**
   * {@inhritDoc}
   */
  def destroyFavorite(id: Long): Status = {
    twitter4jObj.destroyFavorite(id)
  }
}