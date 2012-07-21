package twitter4s.api

import twitter4j.IDs

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait FriendsFollowersMethods {
  /**
   * Returns an array of numeric IDs for every user the authenticating user is following.
   * <br />This method calls twitter4j.Twitter.getFriendsIDs.
   * <br />getFriendsIDs calls http://api.twitter.com/1/friends/ids.json
   * <br />Note: Parameter userId is taken priority over screenName.
   *
   * @param cursor (required) Causes the list of connections to be broken into pages of no more than 5000 IDs at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are filterd out after connections are queried. <br/>
   * To begin paging provide a value of -1 as the cursor. The response from the API will include a previous_cursor and next_cursor to allow paging back and forth.
   * @param userId (optional) Specifies the ID of the user for whom to return the friends list.
   * @param screenName (optional) Specifies the screen name of the user for whom to return the friends list.
   * @return an array of numeric IDs for every user the authenticating user is following
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/friends/ids">GET friends/ids | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getFriendsIDs(cursor: Long, userId: Option[Long] = None, screenName: Option[String] = None): IDs

  /**
   * Returns an array of numeric IDs for every user the specified user is followed by.
   * <br />This method calls twitter4j.Twitter.getFollowersIDs.
   * <br />getFollowersIDs calls http://api.twitter.com/1/followers/ids.json
   * <br />Note: Parameter userId is taken priority over screenName.
   *
   * @param cursor (required) Causes the list of connections to be broken into pages of no more than 5000 IDs at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are filterd out after connections are queried. <br/>
   * To begin paging provide a value of -1 as the cursor. The response from the API will include a previous_cursor and next_cursor to allow paging back and forth.
   * @param userId (optional) Specifies the ID of the user for whom to return the followers list.
   * @param screenName (optional) Specifies the screen name of the user for whom to return the followers list.
   * @return The ID or screen_name of the user to retrieve the friends ID list for.
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/followers/ids">GET followers/ids | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getFollowersIDs(cursor: Long, userId: Option[Long] = None, screenName: Option[String] = None): IDs
}