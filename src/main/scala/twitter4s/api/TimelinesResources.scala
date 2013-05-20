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
import twitter4j.Status

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait TimelinesResources {
  /**
   * Returns the 20 most recent statuses, including retweets, posted by the authenticating user and that user's friends. This is the equivalent of /timeline/home on the Web.<br />
   * Usage note: This home_timeline call is identical to statuses/friends_timeline, except that home_timeline also contains retweets, while statuses/friends_timeline does not for backwards compatibility reasons. In a future version of the API, statuses/friends_timeline will be deprected and replaced by home_timeline.
   * <br />This method calls twitter4j.Twitter.getHomeTimeline.
   * <br />getHomeTimeline calls http://api.twitter.com/1/statuses/home_timeline
   *
   * @param paging (optional) controls pagination. Supports since_id, max_id, count and page parameters.
   * @return list of the home Timeline
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/statuses/home_timeline">GET statuses/home_timeline | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getHomeTimeline(paging: Paging = null): ResponseList[Status]

  /**
   * Returns the 20 most recent statuses posted from the authenticating user. It's also possible to request another user's timeline via the id parameter.<br />
   * This is the equivalent of the Web / page for your own user, or the profile page for a third party.<br />
   * For backwards compatibility reasons, retweets are stripped out of the user_timeline when calling in XML or JSON (they appear with 'RT' in RSS and Atom). If you'd like them included, you can merge them in from statuses retweeted_by_me.<br />
   * <br />This method calls 
   * <br />This method calls http://api.twitter.com/1/statuses/user_timeline.json
   *
   * @param specificUser (optional) specifies information of the user(screen name or ID) for whom to return the user_timeline
   * @param paging (optional) controls pagination. Supports since_id, max_id, count and page parameters.
   * @return list of the user Timeline
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/statuses/user_timeline">GET statuses/user_timeline | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getUserTimeline(
      specificUser: User.SpecificInfo = null,
      paging: Paging = null): ResponseList[Status]

  /**
   * Returns the 20 most recent mentions (status containing @username) for the authenticating user.
   * <br />This method calls twitter4j.Twitter.getMentions.
   * <br />getMentions calls http://api.twitter.com/1/statuses/mentions
   *
   * @param paging (optional) controls pagination. Supports since_id, max_id, count and page parameters.
   * @return the 20 most recent replies
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/statuses/mentions">GET statuses/mentions | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getMentions(paging: Paging = null): ResponseList[Status]

  /**
   * Returns the 20 most recent retweets posted by the authenticating user's friends.
   * <br />This method calls twitter4j.Twitter.getRetweetedToMe.
   * <br />This method calls http://api.twitter.com/1/statuses/retweeted_to_me
   *
   * @param paging (optional) controls pagination. Supports since_id, max_id, count and page parameters.
   * @return the 20 most recent retweets posted by the authenticating user's friends.
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/statuses/retweeted_to_me">GET statuses/retweeted_to_me | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getRetweetedToMe(paging: Paging = null): ResponseList[Status]

  /**
   * Returns the 20 most recent tweets of the authenticated user that have been retweeted by others.
   * <br />This method calls twitter4j.Twitter.getRetweetsOfMe
   * <br />This method calls http://api.twitter.com/1/statuses/retweets_of_me
   *
   * @param paging (optional) controls pagination. Supports since_id, max_id, count and page parameters.
   * @return the 20 most recent tweets of the authenticated user that have been retweeted by others.
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/statuses/retweets_of_me">GET statuses/retweets_of_me | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getRetweetsOfMe(paging: Paging = null): ResponseList[Status]

  /**
   * Returns the 20 most recent retweets posted by users the specified user follows. This method is identical to statuses/retweeted_to_me except you can choose the user to view.
   * <br />This method has not been finalized and the interface is subject to change in incompatible ways.
   * <br />This method calls twitter4j.Twitter.getRetweetedToUser.
   * <br />getRetweetedToUser calls http://api.twitter.com/1/statuses/retweeted_to_user
   * <br />Note1: You must set screenName or userId at least.
   * <br />Note2: Parameter userId is taken priority over screenName.
   *
   * @param specificUser (required) the user to view.
   * @param paging (required) controls pagination. Supports since_id, max_id, count and page parameters.
   * @return the 20 most recent retweets posted by the authenticating user's friends.
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when specificUser is set null.
   * @see <a href="http://groups.google.com/group/twitter-api-announce/msg/34909da7c399169e">#newtwitter and the API - Twitter API Announcements | Google Group</a>
   * @since Twitter4J 1.0.0
   */
  def getRetweetedToUser(
      specificUser: User.SpecificInfo,
      paging: Paging): ResponseList[Status]

  /**
   * Returns the 20 most recent retweets posted by the specified user. This method is identical to statuses/retweeted_by_me except you can choose the user to view.
   * <br />This method has not been finalized and the interface is subject to change in incompatible ways.
   * <br />This method calls twitter4j.Twitter.getRetweetedByUser.
   * <br />getRetweetedByUser calls http://api.twitter.com/1/statuses/retweeted_by_user
   * <br />Note1: You must set userId or screenName at least.
   * <br />Note2: Parameter userId is taken priority over screenName.
   *
   * @param specificUser (required) the user to view.
   * @param paging (required) controls pagination. Supports since_id, max_id, count and page parameters.
   * @return the 20 most recent retweets posted by the authenticating user
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when specificUser is set null.
   * @see <a href="http://groups.google.com/group/twitter-api-announce/msg/34909da7c399169e">#newtwitter and the API - Twitter API Announcements | Google Group</a>
   * @since Twitter4S 1.0.0
   */
  def getRetweetedByUser(
      specificUser: User.SpecificInfo,
      paging: Paging): ResponseList[Status]
}