package twitter4s.api

import twitter4s.User
import twitter4s.Relationship
import twitter4s.IDs
import twitter4s.ResponseList
import twitter4j.Friendship

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait FriendshipMethods {
  /**
   * Allows the authenticating users to follow the user specified in the ID parameter.<br />
   * Returns the befriended user in the requested format when successful. Returns a string describing the failure condition when unsuccessful. If you are already friends with the user an HTTP 403 will be returned.
   * <br />This method calls twitter4j.Twitter.createFriendship.
   * <br />createFriendship calls http://api.twitter.com/1/friendships/create/[id].json
   * <br />Note1: You must set parameter screenName or userId at least.
   * <br />Note2: Parameter userId is taken priority over screenName.
   *
   * @param screenName (optional) the screen name of the user to be befriended
   * @param userId (optional) the ID of the user to be befriended
   * @param follow Enable notifications for the target user in addition to becoming friends.
   * @return the befriended user
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when both of parameter screenName and userId are not set.
   * @see <a href="https://dev.twitter.com/docs/api/1/post/friendships/create">POST friendships/create | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def createFriendship(
      screenName: String = null,
      userId: java.lang.Long = null,
      follow: java.lang.Boolean = null): User

  /**
   * Allows the authenticating users to unfollow the user specified in the ID parameter.<br />
   * Returns the unfollowed user in the requested format when successful. Returns a string describing the failure condition when unsuccessful.
   * <br />This method calls twitter4j.Twitter.destroyFriendship.
   * <br />destroyFriendship calls http://api.twitter.com/1/friendships/destroy/[id].json
   * <br />Note1: You must set parameter screenName or userId at least.
   * <br />Note2: Parameter userId is taken priority over screenName.
   *
   * @param screenName the screen name of the user for whom to request a list of friends
   * @param userId the ID of the user for whom to request a list of friends
   * @return User
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when both of parameter screenName and userId are not set.
   * @see <a href="https://dev.twitter.com/docs/api/1/post/friendships/destroy">POST friendships/destroy | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def destroyFriendship(screenName: String = null, userId: java.lang.Long = null): User

  /**
   * Tests for the existence of friendship between two users. Will return true if user_a follows user_b, otherwise will return false.
   * <br />This method calls twitter4j.Twitter.existsFriendship.
   * <br />existsFriendship calls http://api.twitter.com/1/friendships/exists.json
   *
   * @param userA The ID or screen_name of the first user to test friendship for.
   * @param userB The ID or screen_name of the second user to test friendship for.
   * @return if a friendship exists between two users.
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/friendships/exists">GET friendships/exists | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def existsFriendship(userA: String, userB: String): Boolean

  /**
   * Returns detailed information about the relationship between two users.
   * <br />This method calls twitter4j.Twitter.showFriendship
   * <br />showFriendship calls http://api.twitter.com/1/friendships/show.json
   * <br />Note1: You must set source and target ScreenName or source and target Id at least combination
   * <br />Note2: Id is taken priority over ScreenName.
   *
   * @param sourceScreenName (optional) the screen name of the source user
   * @param targetScreenName (optional) the screen name of the target user
   * @param sourceId (optional) the ID of the source user
   * @param targetId (optional) the ID of the target user
   * @return Relationship
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when all parameters are not set or source and target combination can not create.
   * @see <a href="https://dev.twitter.com/docs/api/1/get/friendships/show">GET friendships/show | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def showFriendship(
      sourceScreenName: Option[String] = None,
      targetScreenName: Option[String] = None,
      sourceId: Option[Long] = None,
      targetId: Option[Long] = None): Relationship

  /**
   * Returns an array of numeric IDs for every user who has a pending request to follow the authenticating user.
   * <br />This method calls twitter4j.Twitter.getIncomingFriendships.
   * <br />getIncomingFriendships calls http://api.twitter.com/1/friendships/incoming.json
   *
   * @param cursor Breaks the results into pages. A single page contains 5000 identifiers. Provide a value of -1 to begin paging.
   * @return an array of numeric IDs for every user who has a pending request to follow the authenticating user.
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/friendships/incoming">GET friendships/incoming | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getIncomingFriendships(cursor: Long): IDs

  /**
   * Returns an array of numeric IDs for every protected user for whom the authenticating user has a pending follow request.
   * <br />This method calls twitter4j.Twitter.getOutgoingFriendships.
   * <br />This method calls http://api.twitter.com/1/friendships/outgoing.json
   *
   * @param cursor Breaks the results into pages. A single page contains 5000 identifiers. Provide a value of -1 to begin paging.
   * @return an array of numeric IDs for every protected user for whom the authenticating user has a pending follow request.
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/friendships/outgoing">GET friendships/outgoing | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getOutgoingFriendships(cursor: Long): IDs

  /**
   * Returns the relationship of the authenticating user to the specified users.
   * <br />This method has not been finalized and the interface is subject to change in incompatible ways.
   * <br />This method calls twitter4j.Twitter.lookupFriendships.
   * <br />lookupFriendships calls http://api.twitter.com/1/friendships/lookup.json
   * <br />Note1: You must set screenNames or ids at least.
   * <br />Note2: Parameter ids is taken priority over screenNames.
   *
   * @param screenNames (optional) array of the screen names to lookup
   * @param ids (optional) array of the ids to lookup
   * @return list of Relationships
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when both of screenNames and ids are not set.
   * @see <a href="http://groups.google.com/group/twitter-api-announce/msg/34909da7c399169e">#newtwitter and the API - Twitter API Announcements | Google Group</a>
   * @since Twitter4S 1.0.0
   */
  def lookupFriendships(screenNames: Option[Array[String]] = None, ids: Option[Array[Long]] = None): ResponseList[Friendship]

  /**
   * Allows you to enable or disable retweets and device notifications from the specified user.
   * <br />This method has not been finalized and the interface is subject to change in incompatible ways.
   * <br />This method calls twitter4j.Twitter.lookupFriendships.
   * <br />lookupFriendships calls http://api.twitter.com/1/friendships/update.json
   * <br />Note1: You must set screenName or id at least.
   * <br />Note2: Parameter id is taken priority over screenName.
   *
   * @param enableDeviceNotification (required) set true to enable device notification
   * @param retweets (required) set true to enable retweets
   * @param screenName (optional) screen name to update
   * @param id (optional) user id to update
   * @return Relationship
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when both of screenName and id are not set.
   * @see <a href="http://groups.google.com/group/twitter-api-announce/msg/34909da7c399169e">#newtwitter and the API - Twitter API Announcements | Google Group</a>
   * @since Twitter4S 1.0.0
   */
  def updateFriendship(
      enableDeviceNotification: Boolean,
      retweets: Boolean,
      screenName: Option[String] = None,
      userId: Option[Long] = None): Relationship

  /**
   * Returns the list of user_ids for which the authenticating user has said they do not want to receive retweets from when successful.
   * <br />Now the return value had no data for paging.
   * <br />This method calls twitter4j.Twitter.getNoRetweetIds.
   * <br />getNoRetweetIds calls http://api.twitter.com/1/friendships/no_retweet_ids.json
   *
   * @return IDs
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="http://groups.google.com/group/twitter-api-announce/browse_thread/thread/6f734611ac57e281">Some changes and updates to the API and Tweet Button - Twitter API Announcements | Google Groups</a>
   * @since Twitter4S 1.0.0
   */
  def getNoRetweetIds: IDs
}