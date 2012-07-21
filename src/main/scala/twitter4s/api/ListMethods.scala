package twitter4s.api

import twitter4j.UserList
import twitter4s.PagableResponseList
import twitter4j.Paging
import twitter4s.ResponseList
import twitter4j.Status

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
   * @param cursor (required) Breaks the results into pages. A single page contains 20 lists. Provide a value of -1 to begin paging. Provide values as returned to in the response body's next_cursor and previous_cursor attributes to page back and forth in the list.
   * @param listOwnerScreenName (optional) The screen name of the list owner
   * @param listOwnerUserId (optional) The id of the list owner
   * @return the list of lists
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when both of listOwnerScreenName and listOwnerUserId are not set.
   * @see <a href="https://dev.twitter.com/docs/api/1/get/lists">GET lists | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getUserLists(cursor: Long, listOwnerScreenName: Option[String] = None, listOwnerUserId: Option[Long] = None): PagableResponseList[UserList]

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
   * @param cursor (required) Breaks the results into pages. A single page contains 20 lists. Provide a value of -1 to begin paging. Provide values as returned to in the response body's next_cursor and previous_cursor attributes to page back and forth in the list.
   * @param listMemberScreenName (optional) The screen name of the list member
   * @param listMemberId (optional) The id of the list member
   * @param filterToOwnedLists (optional) Whether to return just lists the authenticating user owns, and the user represented by listMemberScreenName is a member of.
   * @return the list of lists
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalStateException when filerToOwnedLists is true but authorization is not enabled
   * @see <a href="https://dev.twitter.com/docs/api/1/get/lists/memberships">GET lists/memberships | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getUserListMemberships(
      cursor: Long,
      listMemberId: Option[Long] = None,
      listMemberScreenName: Option[String] = None,
      filterToOwnedLists: Option[Boolean] = None): PagableResponseList[UserList]

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
  def getUserListSubscriptions(cursor: Long, listMemberScreenName: String): PagableResponseList[UserList]

  /**
   * Returns all lists the authenticating or specified user subscribes to, including their own.
   * <br />This method has not been finalized and the interface is subject to change in incompatible ways.
   * <br />This method calls twitter4j.Twitter.getAllUserList.
   * <br />getAllUserList calls http://api.twitter.com/1/lists/all.json
   * <br />Note1: You must set screenName or userId at least.
   * <br />Note2: Parameter userId is taken priority over screenName.
   *
   * @param screenName (optional) screen name to look up
   * @param userId (optional) user id to look up
   * @return list of lists
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when both of screenName and userId are not set.
   * @since Twitter4S 1.0.0
   */
  def getAllUserLists(screenName: Option[String] = None, userId: Option[Long] = None): ResponseList[UserList]
}