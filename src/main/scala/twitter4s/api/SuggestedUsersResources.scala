package twitter4s.api
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
import twitter4j.Category

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait SuggestedUsersResources {
  /**
   * Access to Twitter's suggested user list. This returns the list of suggested user categories. The category can be used in the users/suggestions/category endpoint to get the users in that category.
   * <br />This method calls twitter4j.Twitter.getSuggestedUserCategories.
   * <br />getSuggestedUserCategories calls http://api.twitter.com/1/users/suggestions/:slug.json
   *
   * @return list of suggested user categories.
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/users/suggestions/:slug">GET users/suggestions/:slug | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getSuggestedUserCategories: ResponseList[Category]

  /**
   * Access the users in a given category of the Twitter suggested user list.<br />
   * It is recommended that end clients cache this data for no more than one hour.
   * <br />This method calls twitter4j.Twitter.getUserSuggestions.
   * <br />getUserSuggestions calls http://api.twitter.com/1/users/suggestions/:slug.json
   *
   * @param categorySlug slug
   * @return list of suggested users
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/users/suggestions/slug">GET users/suggestions/slug | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getUserSuggestions(categorySlug: String): ResponseList[twitter4j.User]

  /**
   * Access the users in a given category of the Twitter suggested user list and return their most recent status if they are not a protected user.
   * <br />This method has not been finalized and the interface is subject to change in incompatible ways.
   * <br />This method calls twitter4j.Twitter.getMemberSuggestions.
   * <br />getMemberSuggestions calls http://api.twitter.com/1/users/suggestions/:slug/members.json
   *
   * @param categorySlug slug
   * @return list of suggested users
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="http://groups.google.com/group/twitter-api-announce/msg/34909da7c399169e">#newtwitter and the API - Twitter API Announcements | Google Group</a>
   * @since Twitter4S 1.0.0
   */
  def getMemberSuggestions(categorySlug: String): ResponseList[twitter4j.User]
}