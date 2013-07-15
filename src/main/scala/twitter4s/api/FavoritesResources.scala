package twitter4s.api
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
import twitter4s.Page
import twitter4s.ResponseList
import twitter4s.Status

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait FavoritesResources {
  /**
   * Returns the 20 most recent favorite statuses for the authenticating user or user specified by the ID parameter in the requested format.
   * <br />This method calls twitter4j.Twitter.getFavorites by one of below parameters combinations.
   * <br /> 
   * <ul>
   * <li>getFavorites()</li>
   * <li>getFavorites(page)</li>
   * <li>getFavorites(id)</li>
   * <li>getFavorites(id, page)</li>
   * <li>getFavorites(paging)</li>
   * <li>getFavorites(id, paging)</li>
   * </ul>
   * <br />getFavorites method calls http://api.twitter.com/1/favorites.json
   * 
   * @param id (optional) the ID or screen name of the user for whom to request a list of favorite statuses
   * @param page (optional) page specific information (number or controls pagination. Supports sinceId and page parameters.)
   * @return List<Status>
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when both of parameters page and paging are set.
   * @see <a href="https://dev.twitter.com/docs/api/1/get/favorites">GET favorites | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getFavorites(
      id: String = null,
      page: Page.PageSpecific = null): ResponseList[twitter4j.Status]

  /**
   * Favorites the status specified in the ID parameter as the authenticating user. Returns the favorite status when successful.
   * <br />This method calls twitter4j.Twitter.createFavorite
   * <br />createFavorite calls http://api.twitter.com/1/favorites/create/[id].json
   *
   * @param id the ID of the status to favorite
   * @return Status
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/favorites/create/:id">POST favorites/create/:id | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def createFavorite(id: Long): Status

  /**
   * Un-favorites the status specified in the ID parameter as the authenticating user. Returns the un-favorited status in the requested format when successful.
   * <br />This method calls twitter4j.Twitter.destroyFavorite
   * <br />This method calls http://api.twitter.com/1/favorites/destroy/[id].json
   *
   * @param id the ID of the status to un-favorite
   * @return Status
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/favorites/destroy/:id">POST favorites/destroy/:id | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def destroyFavorite(id: Long): Status
}