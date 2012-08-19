package twitter4s.api

import twitter4s.User
import twitter4s.ResponseList
import twitter4j.Query
import twitter4j.Category
import twitter4j.ProfileImage

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait UserMethods {
  /**
   * Returns extended information of a given user, specified by ID or screen name as per the required id parameter. The author's most recent status will be returned inline.
   * <br />This method calls twitter4j.Twitter.showUser.
   * <br />showUser calls http://api.twitter.com/1/users/show.json
   * <br />Note1: You must set screenName or userId at least.
   * <br />Note2: Parameter userId is taken priority over screenName.
   *
   * @param specificUser (required) the user specific information(screen name or ID) for whom to request the detail
   * @return users
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when specificUser is set null.
   * @see <a href="https://dev.twitter.com/docs/api/1/get/users/show">GET users/show | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def showUser(specificUser: User.SpecificInfo): User

  /**
   * Return up to 100 users worth of extended information, specified by either ID, screen name, or combination of the two. The author's most recent status (if the authenticating user has permission) will be returned inline.
   * <br />This method calls twitter4j.Twitter.lookupUsers.
   * <br />lookupUsers calls http://api.twitter.com/1/users/lookup.json
   * <br />Note1: You must set screenNames or ids at least.
   * <br />Note2: Parameter ids is taken priority over screenNames.
   *
   * @param screenNames (optional) Specifies the screen names of the users to return.
   * @param ids (optional) Specifies the screen names of the users to return.
   * @return users
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when both of screenNames and ids are not set.
   * @see <a href="https://dev.twitter.com/docs/api/1/get/users/lookup">GET users/lookup | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def lookupUsers(screenNames: Array[String] = null, ids: Array[Long] = null): ResponseList[twitter4j.User]

  /**
   * Run a search for users similar to the Find People button on Twitter.com; the same results returned by people search on Twitter.com will be returned by using this API.<br />
   * Usage note: It is only possible to retrieve the first 1000 matches from this API.
   * <br />This method calls twitter4j.Twitter.searchUsers.
   * <br />searchUsers calls http://api.twitter.com/1/users/search.json
   *
   * @param query The query to run against people search.
   * @param page Specifies the page of results to retrieve. Number of statuses per page is fixed to 20.
   * @return the list of Users matches the provided
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/users/search">GET users/search | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def searchUsers(query: String, page: Int): ResponseList[twitter4j.User]

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

  /**
   * Access the profile image in various sizes for the user with the indicated screen_name. If no size is provided the normal image is returned. This resource does not return JSON or XML, but instead returns a 302 redirect to the actual image resource.
   * This method should only be used by application developers to lookup or check the profile image URL for a user. This method must not be used as the image source URL presented to users of your application.
   * <br />This method calls twitter4j.Twitter.getProfileImage.
   * <br />getProfileImage calls http://api.twitter.com/1/users/profile_image/:screen_name.json
   *
   * @param screenName The screen name of the user for whom to return results for.
   * @param size Specifies the size of image to fetch. Not specifying a size will give the default, normal size of 48px by 48px. Valid options include: bigger - 73px by 73px normal - 48px by 48px mini - 24px by 24px
   * @return profile image
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/users/profile_image/:screen_name">GET users/profile_image/:screen_name | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getProfileImage(screenName: String, size: ProfileImage.ImageSize): ProfileImage
}