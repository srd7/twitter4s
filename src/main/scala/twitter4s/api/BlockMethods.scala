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
import twitter4s.User
import twitter4s.ResponseList
import twitter4s.IDs

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait BlockMethods {
  /**
   * Blocks the user specified in the ID parameter as the authenticating user. Returns the blocked user in the requested format when successful.
   * <br />This method calls twitter4j.Twitter.createBlock(screenName) or createBlock(userId)
   * <br />createBlock calls http://api.twitter.com/1/blocks/create/[id].json
   * <br />Note1: You must set parameter screenName or userId at least.
   * <br />Note2: Parameter userId is taken priority over screenName.
   *
   * @param specificUser the user spcecific information of the user to block
   * @return the blocked user
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when specificUser is set null.
   * @see <a href="https://dev.twitter.com/docs/api/1/post/blocks/create">POST blocks/create | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def createBlock(specificUser: User.SpecificInfo): User

  /**
   * Un-blocks the user specified in the ID parameter as the authenticating user. Returns the un-blocked user in the requested format when successful.
   * <br />This method calls twitter4j.Twitter.destroyBlock(screenName) or destroyBlock(userId)
   * <br />destroyBlock calls http://api.twitter.com/1/blocks/destroy/[id].json
   * <br />Note1: You must set parameter screenName or userId at least.
   * <br />Note2: Parameter userId is taken priority over screenName.
   *
   * @param specificUser the user spcecific information of the user to block
   * @return the unblocked user
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when specificUser is set null.
   * @see <a href="https://dev.twitter.com/docs/api/1/post/blocks/destroy">POST blocks/destroy | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def destroyBlock(specificUser: User.SpecificInfo): User

  /**
   * Returns if the authenticating user is blocking a target user. Will return the blocked user's object if a block exists, and error with a HTTP 404 response code otherwise.
   * <br />This method calls twitter4j.Twitter.existsBlock(screenName) or existsBlock(userId)
   * <br />existsBlock calls http://api.twitter.com/1/blocks/exists/[id].json
   * <br />Note1: You must set parameter screenName or userId at least.
   * <br />Note2: Parameter userId is taken priority over screenName.
   *
   * @param specificUser the user spcecific information of the potentially blocked user.
   * @return if the authenticating user is blocking a target user
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when specificUser is set null.
   * @see <a href="https://dev.twitter.com/docs/api/1/get/blocks/exists">GET blocks/exists | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def existsBlock(specificUser: User.SpecificInfo): Boolean

  /**
   * Returns a list of user objects that the authenticating user is blocking.
   * <br />This method calls twitter4j.Twitter.getBlockingUsers() or getBlockingUsers(page)
   * <br />getBlockingUsers calls http://api.twitter.com/1/blocks/blocking.json
   *
   * @param page (optional) the number of page
   * @return a list of user objects that the authenticating user
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/blocks/blocking">GET blocks/blocking | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getBlockingUsers(page: java.lang.Integer = null): ResponseList[twitter4j.User]

  /**
   * Returns an array of numeric user ids the authenticating user is blocking.
   * <br />This method calls twitter4j.Twitter.getBlockingUsersIDs
   * <br />getBlockingUsersIDs calls http://api.twitter.com/1/blocks/blocking/ids
   *
   * @return Returns an array of numeric user ids the authenticating user is blocking.
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/blocks/blocking/ids">GET blocks/blocking/ids | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getBlockingUsersIDs: IDs
}