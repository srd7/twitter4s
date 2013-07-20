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
import twitter4s.UserList
import twitter4s.PagableResponseList
import twitter4j.Paging
import twitter4s.ResponseList
import twitter4j.Status
import twitter4s.Users

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait ListMethods {
  /**
   * Creates a new list for the authenticated user. Accounts are limited to 20 lists.
   * <br />This method calls twitter4j.Twitter.createUserList.
   * <br />createUserList calls http://api.twitter.com/1/lists/create.json
   *
   * @param listName The name of the list you are creating. Required.
   * @param isPublicList set true if you wish to make a public list
   * @param description The description of the list you are creating. Optional.
   * @return the list that was created
   * @throws twitter4j.TwitterException when Twitter service or network is unavailable, or the authenticated user already has 20 lists(TwitterException.getStatusCode() == 403).
   * @see <a href="https://dev.twitter.com/docs/api/1/post/lists/create ">POST lists/create | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def createUserList(listName: String, isPublicList: Boolean, description: String): UserList

  /**
   * Updates the specified list.
   * <br />This method calls twitter4j.Twitter.updateUserList.
   * <br />updateUserList calls http://api.twitter.com/1/lists/update.json
   *
   * @param listId The id of the list to update.
   * @param newListName What you'd like to change the list's name to.
   * @param isPublicList Whether your list is public or private. Optional. Values can be public or private. Lists are public by default if no mode is specified.
   * @param newDescription What you'd like to change the list description to.
   * @return the updated list
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/lists/update ">POST lists/update | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def updateUserList(listId: Int, newListName: String, isPublicList: Boolean, newDescription: String): UserList

  /**
   * List the lists of the specified user. Private lists will be included if the authenticated users is the same as the user whose lists are being returned.
   * <br />This method calls twitter4j.Twitter.getUserList.
   * <br />getUserList calls http://api.twitter.com/1/lists.json
   * <br />Note1: You must set listOwnerScreenName or listOwnerUserId at least.
   * <br />Note2: Parameter listOwnerUserId is taken priority over listOwnerScreenName.
   *
   * @param listOwnerSpecificUser (required) the user specific information(screen name or ID) of the list owner
   * @param cursor (required) Breaks the results into pages. A single page contains 20 lists. Provide a value of -1 to begin paging. Provide values as returned to in the response body's next_cursor and previous_cursor attributes to page back and forth in the list.
   * @return the list of lists
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when listOwnerSpecificUser is set null.
   * @see <a href="https://dev.twitter.com/docs/api/1/get/lists">GET lists | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getUserLists(
      listOwnerSpecificUser: User.SpecificInfo,
      cursor: Long): PagableResponseList[twitter4j.UserList]

  /**
   * Show the specified list. Private lists will only be shown if the authenticated user owns the specified list.
   * <br />This method calls twitter4j.Twitter.showUserList.
   * <br />showUserList calls http://api.twitter.com/1/lists/show.json
   *
   * @param listId The id of the list to show
   * @return the specified list
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/lists/show">https://dev.twitter.com/docs/api/1/get/lists/show | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def showUserList(listId: Int): UserList

  /**
   * Deletes the specified list. Must be owned by the authenticated user.
   * <br />This method calls twitter4j.Twitter.destroyUserList.
   * <br />destroyUserList calls http://api.twitter.com/1/lists/destroy.json
   *
   * @param listId The id of the list to delete
   * @return the deleted list
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/lists/destroy">POST lists/destroy | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def destroyUserList(listId: Int): UserList

  /**
   * Show tweet timeline for members of the specified list.
   * <br />This method calls twitter4j.Twitter.getUserListStatuses.
   * <br />getUserListStatuses calls http://api.twitter.com/1/user/lists/list_id/statuses.json
   *
   * @param listId The id of the list
   * @param paging controls pagination. Supports since_id, max_id, count and page parameters.
   * @return list of statuses for members of the specified list
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/lists/statuses">GET lists/statuses | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getUserListStatuses(listId: Int, paging: Paging): ResponseList[Status]

  /**
   * List the lists the specified user has been added to.
   * <br />This method calls twitter4j.Twitter.getUserListMemberships.
   * <br />getUserListMemberships calls http://api.twitter.com/1/lists/memberships.json
   * <br />Note: Parameter listMemberId is taken priority over listMemberScreenName.
   * 
   * @param listMemberSpecificUser (optional) the user specific information (screen name or ID) of the list member
   * @param cursor (required) Breaks the results into pages. A single page contains 20 lists. Provide a value of -1 to begin paging. Provide values as returned to in the response body's next_cursor and previous_cursor attributes to page back and forth in the list.
   * @param filterToOwnedLists (optional) Whether to return just lists the authenticating user owns, and the user represented by listMemberScreenName is a member of.
   * @return the list of lists
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalStateException when filerToOwnedLists is true but authorization is not enabled
   * @see <a href="https://dev.twitter.com/docs/api/1/get/lists/memberships">GET lists/memberships | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getUserListMemberships(
      listMemberSpecificUser: User.SpecificInfo = null,
      cursor: Long,
      filterToOwnedLists: java.lang.Boolean = null): PagableResponseList[twitter4j.UserList]

  /**
   * List the lists the specified user follows.
   * <br />This method calls twitter4j.Twitter.getUserListSubscriptions.
   * <br />getUserListSubscriptions calls http://api.twitter.com/1/lists/subscriptions.json
   *
   * @param listOwnerScreenName The screen name of the list owner
   * @param cursor Breaks the results into pages. A single page contains 20 lists. Provide a value of -1 to begin paging. Provide values as returned to in the response body's next_cursor and previous_cursor attributes to page back and forth in the list.
   * @return the list of lists
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/lists/subscriptions">GET lists/subscriptions | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getUserListSubscriptions(cursor: Long, listMemberScreenName: String): PagableResponseList[twitter4j.UserList]

  /**
   * Returns all lists the authenticating or specified user subscribes to, including their own.
   * <br />This method has not been finalized and the interface is subject to change in incompatible ways.
   * <br />This method calls twitter4j.Twitter.getAllUserList.
   * <br />getAllUserList calls http://api.twitter.com/1/lists/all.json
   * <br />Note1: You must set screenName or userId at least.
   * <br />Note2: Parameter userId is taken priority over screenName.
   *
   * @param specificUser (required) the user specific information(screen name or ID) to look up
   * @return list of lists
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when both of screenName and userId are not set.
   * @since Twitter4S 1.0.0
   */
  def getAllUserLists(specificUser: User.SpecificInfo): ResponseList[twitter4j.UserList]
  
  /**
   * Returns the members of the specified list.
   * <br />This method calls twitter4j.Twitter.getUserListMembers.
   * <br />getUserListMembers calls http://api.twitter.com/1/lists/members.json
   *
   * @param listId The id of the list
   * @param cursor Breaks the results into pages. A single page contains 20 lists. Provide a value of -1 to begin paging. Provide values as returned to in the response body's next_cursor and previous_cursor attributes to page back and forth in the list.
   * @return the members of the specified list.
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/lists/members">GET lists/members | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getUserListMembers(listId: Int, cursor: Long): PagableResponseList[twitter4j.User]

  /**
   * Adds a member to a list. The authenticated user must own the list to be able to add members to it. Lists are limited to having 500 members.
   * <br />This method calls twitter4j.Twitter.addUserListMember
   * <br />This method calls http://api.twitter.com/1/lists/members/create.json
   *
   * @param listId The id of the list.
   * @param userId The id of the user to add as a member of the list.
   * @return the updated list
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/lists/members/create">POST lists/members/create | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def addUserListMember(listId: Int, userId: Long): UserList

  /**
   * Adds multiple members to a list, by specifying a comma-separated list of member ids or screen names. The authenticated user must own the list to be able to add members to it. Lists are limited to having 500 members, and you are limited to adding up to 100 members to a list at a time with this method.
   * <br />This method calls twitter4j.Twitter.addUserListMembers.
   * <br />addUserListMembers calls http://api.twitter.com/1/lists/members/create_all.json
   * <br />Note1: You must set userIds or screenNames at least.
   * <br />Note2: Parameter userIds is taken priority over screenNames.
   *
   * @param listId (required) The id of the list.
   * @param specificUsers(required) The array of user specifc informations(ids or screen names) of the user to add as member of the list. up to 100 are allowed in a single request.
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/lists/members/create_all">POST lists/members/create_all | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def addUserListMembers(
      listId: Int,
      specificUsers: Users.SpecificInfo): UserList

  /**
   * Removes the specified member from the list. The authenticated user must be the list's owner to remove members from the list.
   * <br />This method calls twitter4j.Twitter.deleteUserListMember.
   * <br />deleteUserListMember calls http://api.twitter.com/1/lists/members/destroy.json
   *
   * @param listId The id of the list.
   * @param userId The screen name of the member you wish to remove from the list.
   * @return the updated list
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/lists/members/destroy">POST lists/members/destroy | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def deleteUserListMember(listId: Int, userId: Long): UserList

  /**
   * Check if a user is a member of the specified list.
   * <br />This method calls twitter4j.Twitter.showUserListMembership.
   * <br />showUserListMembership calls http://api.twitter.com/1/lists/members/show.json
   *
   * @param listId The id of the list.
   * @param userId The id of the user who you want to know is a member or not of the specified list.
   * @return the updated list
   * @throws TwitterException when Twitter service or network is unavailable
   * , or the user is not a member of the specified list(TwitterException.getStatusCode() returns 404 in that case.)
   * @see <a href="https://dev.twitter.com/docs/api/1/get/lists/members/show">GET lists/members/show | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def showUserListMembership(listId: Int, userId: Long): User
  
  /**
   * Returns the subscribers of the specified list.
   * <br />This method calls twitter4j.Twitter.getUserListSubscribers.
   * <br />getUserListSubscribers calls http://api.twitter.com/1/lists/subscribers.json
   *
   * @param listId The id of the list
   * @param cursor Breaks the results into pages. A single page contains 20 lists. Provide a value of -1 to begin paging. Provide values as returned to in the response    body's next_cursor and previous_cursor attributes to page back and forth in the list.
   * @return the members of the specified list.
   * @throws twitter4j.TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/lists/subscribers">GET lists/subscribers | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getUserListSubscribers(listId: Int, cursor: Long): PagableResponseList[twitter4j.User]

  /**
   * Make the authenticated user follow the specified list.
   * <br />This method calls twitter4j.Twitter.createUserListSubscription.
   * <br />createUserListSubscribers calls http://api.twitter.com/1/list/subscribers/create.json
   *
   * @param listId The id of the list.
   * @return the updated list
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/lists/subscribers/create">POST lists/subscribers/create | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def createUserListSubscription(listId: Int): UserList

  /**
   * Unsubscribes the authenticated user form the specified list.
   * <br />This method calls twitter4j.Twitter.destroyUserListSubscription.
   * <br />destroyUserListSubscription calls http://api.twitter.com/1/subscribers/destroy.json
   *
   * @param listId The id of the list.
   * @return the updated list
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/lists/subscribers/destroy">POST lists/subscribers/destroy | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def destroyUserListSubscription(listId: Int): UserList

  /**
   * Check if the specified user is a subscriber of the specified list.
   * <br />This method calls twitter4j.Twitter.showUserListSubscriptions.
   * <br />showUserListSubscriptions calls http://api.twitter.com/1/lists/subscribers/show.json
   *
   * @param listId The id of the list.
   * @param userId The id of the user who you want to know is a member or not of the specified list.
   * @return the updated list
   * @throws TwitterException when Twitter service or network is unavailable
   * , or the user is not a member of the specified list(TwitterException.getStatusCode() returns 404 in that case.)
   * @see <a href="https://dev.twitter.com/docs/api/1/get/lists/subscribers/show">GET lists/subscribers/show | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def showUserListSubscription(listId: Int, userId: Long): User
}