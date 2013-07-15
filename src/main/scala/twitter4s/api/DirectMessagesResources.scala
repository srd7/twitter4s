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
import twitter4j.Paging
import twitter4s.ResponseList
import twitter4s.DirectMessage

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait DirectMessagesResources {
  /**
   * Returns a list of the direct messages sent to the authenticating user.
   * <br />This method calls twitter4j.Twitter.getDirectMessages
   * <br />getDirectMessages calls http://api.twitter.com/1/direct_messages
   *
   * @param paging (optional) controls pagination. Supports since_id, max_id, count and page parameters.
   * @return List
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/direct_messages">GET direct_messages | Twitter Developers</a>
   */
  def getDirectMessages(paging: Paging = null): ResponseList[twitter4j.DirectMessage]

  /**
   * Returns a list of the direct messages sent by the authenticating user.
   * <br />This method calls twitter4j.Twitter.getSentDirectMessages
   * <br />getSentDirectMessages calls http://api.twitter.com/1/direct_messages/sent
   *
   * @param paging (optional) controls pagination. Supports since_id, max_id, count and page parameters.
   * @return List
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/direct_messages/sent">GET direct_messages/sent | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getSentDirectMessages(paging: Paging = null): ResponseList[twitter4j.DirectMessage]

  /**
   * Sends a new direct message to the specified user from the authenticating user. Requires both the user and text parameters below.
   * The text will be trimmed if the length of the text is exceeding 140 characters.
   * <br />This method calls twitter4j.Twitter.sendDirectMessage
   * <br />sendDirectMessage calls http://api.twitter.com/1/direct_messages/new
   * <br />Note1: You must set parameter screenName or userId at least.
   * <br />Note2: Parameter userId is taken priority over screenName.
   *
   * @param specificUser (required) the user specific information(screen name or ID) to whom send the direct message
   * @param text (required) The text of your direct message.
   * @return DirectMessage
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when specificUser is set null.
   * @see <a href="https://dev.twitter.com/docs/api/1/post/direct_messages/new">POST direct_messages/new | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def sendDirectMessage(specificUser: User.SpecificInfo, text: String): DirectMessage

  /**
   * Destroys the direct message specified in the required ID parameter. The authenticating user must be the recipient of the specified direct message.
   * <br />This method calls twitter4j.Twitter.destroyDirectMessage.
   * <br />destroyDirectMessage calls http://api.twitter.com/1/direct_messages/destroy
   *
   * @param id the ID of the direct message to destroy
   * @return the deleted direct message
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/direct_messages/destroy/:id">POST direct_messages/destroy/:id | Twitter Developers</a>
   * @since Twitter4J 1.0.0
   */
  def destroyDirectMessage(id: Long): DirectMessage

  /**
   * Returns a single direct message, specified by an id parameter.
   * <br />This method has not been finalized and the interface is subject to change in incompatible ways.
   * <br />This method calls twitter4j.Twitter.showDirectMessage.
   * <br />showDirectMessage calls http://api.twitter.com/1/direct_messages/show/:id.json
   *
   * @param id message id
   * @return DirectMessage
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="http://groups.google.com/group/twitter-api-announce/msg/34909da7c399169e">#newtwitter and the API - Twitter API Announcements | Google Group</a>
   * @since Twitter4S 1.0.0
   */
  def showDirectMessage(id: Long): DirectMessage
}