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
import twitter4s.IDs
import twitter4s.User
import twitter4s.Users
import twitter4s.Relationship
import twitter4s.ResponseList
import twitter4j.Friendship

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait FriendsFollowersResources {
  /**
   * Returns an array of numeric IDs for every user the authenticating user is following.
   * <br />This method calls twitter4j.Twitter.getFriendsIDs.
   * <br />getFriendsIDs calls http://api.twitter.com/1/friends/ids.json
   * <br />Note: Parameter userId is taken priority over screenName.
   *
   * @param cursor (required) Causes the list of connections to be broken into pages of no more than 5000 IDs at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are filterd out after connections are queried. <br/>
   * To begin paging provide a value of -1 as the cursor. The response from the API will include a previous_cursor and next_cursor to allow paging back and forth.
   * @param specificUser (optional) Specifies the user (screen name or ID) for whom to return the friends list.
   * @return an array of numeric IDs for every user the authenticating user is following
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/friends/ids">GET friends/ids | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getFriendsIDs(cursor: Long, specificUser: User.SpecificInfo = null): IDs

  /**
   * Returns an array of numeric IDs for every user the specified user is followed by.
   * <br />This method calls twitter4j.Twitter.getFollowersIDs.
   * <br />getFollowersIDs calls http://api.twitter.com/1/followers/ids.json
   * <br />Note: Parameter userId is taken priority over screenName.
   *
   * @param cursor (required) Causes the list of connections to be broken into pages of no more than 5000 IDs at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are filterd out after connections are queried. <br/>
   * To begin paging provide a value of -1 as the cursor. The response from the API will include a previous_cursor and next_cursor to allow paging back and forth.
   * @param specificUser (optional) Specifies the user (screen name or ID) for whom to return the followers list.
   * @return The ID or screen_name of the user to retrieve the friends ID list for.
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/followers/ids">GET followers/ids | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getFollowersIDs(cursor: Long, specificUser: User.SpecificInfo = null): IDs
  
    /**
   * Allows the authenticating users to follow the user specified in the ID parameter.<br />
   * Returns the befriended user in the requested format when successful. Returns a string describing the failure condition when unsuccessful. If you are already friends with the user an HTTP 403 will be returned.
   * <br />This method calls twitter4j.Twitter.createFriendship.
   * <br />createFriendship calls http://api.twitter.com/1/friendships/create/[id].json
   * <br />Note1: You must set parameter screenName or userId at least.
   * <br />Note2: Parameter userId is taken priority over screenName.
   *
   * @param specificUser (required) the user specific information(screen name or ID) to be befriended.
   * @param follow (optional) Enable notifications for the target user in addition to becoming friends.
   * @return the befriended user
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when specificUser is set null.
   * @see <a href="https://dev.twitter.com/docs/api/1/post/friendships/create">POST friendships/create | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def createFriendship(
      specificUser: User.SpecificInfo,
      follow: java.lang.Boolean = null): User

  /**
   * Allows the authenticating users to unfollow the user specified in the ID parameter.<br />
   * Returns the unfollowed user in the requested format when successful. Returns a string describing the failure condition when unsuccessful.
   * <br />This method calls twitter4j.Twitter.destroyFriendship.
   * <br />destroyFriendship calls http://api.twitter.com/1/friendships/destroy/[id].json
   * <br />Note1: You must set parameter screenName or userId at least.
   * <br />Note2: Parameter userId is taken priority over screenName.
   *
   * @param specificUser the user specific information (screen name or ID) for whom to request a list of friends
   * @return User
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when specificUser is set null.
   * @see <a href="https://dev.twitter.com/docs/api/1/post/friendships/destroy">POST friendships/destroy | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def destroyFriendship(specificUser: User.SpecificInfo): User

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
   * @param sourceSpecificUser (required) the source user specific information(screen name or ID)
   * @param targetSpecificUser (required) the target user specific information(screen name or ID)
   * @return Relationship
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when same information combination on source and target can not create.
   * @see <a href="https://dev.twitter.com/docs/api/1/get/friendships/show">GET friendships/show | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def showFriendship(
      sourceSpecificUser: User.SpecificInfo,
      targetSpecificUser: User.SpecificInfo): Relationship

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
   * @param specificUsers (required) array of user specific informations(screen names or ids) to lookup
   * @return list of Relationships
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="http://groups.google.com/group/twitter-api-announce/msg/34909da7c399169e">#newtwitter and the API - Twitter API Announcements | Google Group</a>
   * @since Twitter4S 1.0.0
   */
  def lookupFriendships(specificUsers: Users.SpecificInfo): ResponseList[Friendship]

  /**
   * Allows you to enable or disable retweets and device notifications from the specified user.
   * <br />This method has not been finalized and the interface is subject to change in incompatible ways.
   * <br />This method calls twitter4j.Twitter.lookupFriendships.
   * <br />lookupFriendships calls http://api.twitter.com/1/friendships/update.json
   * <br />Note1: You must set screenName or id at least.
   * <br />Note2: Parameter id is taken priority over screenName.
   *
   * @param specificUser (required) user specific information(screen name or ID) to update
   * @param enableDeviceNotification (required) set true to enable device notification
   * @param retweets (required) set true to enable retweets
   * @return Relationship
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when specificUser is set null.
   * @see <a href="http://groups.google.com/group/twitter-api-announce/msg/34909da7c399169e">#newtwitter and the API - Twitter API Announcements | Google Group</a>
   * @since Twitter4S 1.0.0
   */
  def updateFriendship(
      specificUser: User.SpecificInfo,
      enableDeviceNotification: Boolean,
      retweets: Boolean): Relationship

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