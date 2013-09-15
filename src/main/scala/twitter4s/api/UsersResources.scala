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
import twitter4s._
import twitter4j.Query
import twitter4j.Category

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait UsersResources {
    /**
   * Returns an HTTP 200 OK response code and a representation of the requesting user if authentication was successful; returns a 401 status code and an error message if not. Use this method to test if supplied user credentials are valid.
   * <br />This method calls twitter4j.Twitter.verifyCredentials.
   * <br />verifyCredentials calls http://api.twitter.com/1.1/account/verify_credentials.json
   * 
   * @return user
   * @throws twitter4j.TwitterException when Twitter service or network is unavailable, or if supplied credential is wrong (TwitterException.getStatusCode() == 401)
   * @see <a href="https://dev.twitter.com/docs/api/1.1/get/account/verify_credentials">GET account/verify_credentials | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def verifyCredentials: User
  
  /**
   * Sets one or more hex values that control the color scheme of the authenticating user's profile page on twitter.com. Each parameter's value must be a valid hexidecimal value, and may be either three or six characters (ex: #fff or #ffffff).<br/>
   * <br />This method calls twitter4j.Twitter.updateProfileColors.
   * <br />updateProfileColors calls http://api.twitter.com/1.1/account/update_profile_colors.json
   * 
   * @param profileBackgroundColor optional, can be null
   * @param profileTextColor optional, can be null
   * @param profileLinkColor optional, can be null
   * @param profileSidebarFillColor optional, can be null
   * @param profileSidebarBorderColor optional, can be null
   * @return the updated user
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1.1/post/account/update_profile_colors">POST account/update_profile_colors | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def updateProfileColors(
      profileBackgroundColor: String = null,
      profileTextColor: String = null,
      profileLinkColor: String = null,
      profileSidebarFillColor: String = null,
      profileSidebarBorderColor: String = null): User
  
  /**
   * Updates the authenticating user's profile image.<br/>
   * <br />This method calls twitter4j.Twitter.updateProfileImage.
   * <br />updateProfileImage calls http://api.twitter.com/1.1/account/update_profile_image.json
   * <br />Note: You must set parameter imageFile or imageStream at least.
   * <br />Profile image must be a valid GIF, JPG, or PNG image of less than 700 kilobytes in size. Images with width larger than 500 pixels will be scaled down.
   *
   * @param imageResource (require) Profile image as File or Stream object.
   * @return the updated user
   * @throws TwitterException when Twitter service or network is unavailable,
   * or when the specified file is not found (FileNotFoundException will be nested),
   * or when the specified file object in not representing a file (IOException will be nested).
   * @see <a href="https://dev.twitter.com/docs/api/1.1/post/account/update_profile_image">POST account/update_profile_image | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def updateProfileImage(imageResource: ImageResource.SpecificResource): User
  
  /**
   * Updates the authenticating user's profile background image.<br/>
   * <br />This method calls twitter4.Twitter.updateProfileBackgroundImage.
   * <br />updateProfileBackgroundImage method calls http://api.twitter.com/1.1/account/update_profile_background_image.json
   * <br />Note: You must set prameter imageFile or imageStream at least.
   * <br />Background image must be a valid GIF, JPG, or PNG image of less than 800 kilobytes in size. Images with width larger than 2048 pixels will be forceably scaled down.
   *
   * @param imageResource (require) Background image as File or Stream object.
   * @param tile (required) If set to true the background image will be displayed tiled. The image will not be tiled otherwise.
   * @return the updated user
   * @throws TwitterException when Twitter service or network is unavailable,
   * or when the specified file is not found (FileNotFoundException will be nested),
   * or when the specified file object in not representing a file (IOException will be nested)
   * @see <a href="https://dev.twitter.com/docs/api/1.1/post/account/update_profile_background_image">POST account/update_profile_background_image | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def updateProfileBackgroundImage(
      imageResource: ImageResource.SpecificResource,
      tile: Boolean): User
  
  /**
   * Sets values that users are able to set under the "Account" tab of their settings page. Only the parameters specified(non-null) will be updated.<br/>
   * <br />This method calls twitter4j.Twitter.updateProfile.
   * <br />updateProfile calls http://api.twitter.com/1.1/account/update_profile.json
   *
   * @param name Optional. Maximum of 20 characters.
   * @param url Optional. Maximum of 100 characters. Will be prepended with "http://" if not present.
   * @param location Optional. Maximum of 30 characters. The contents are not normalized or geocoded in any way.
   * @param description Optional. Maximum of 160 characters.
   * @return the updated user
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1.1/post/account/update_profile">POST account/update_profile | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def updateProfile(
      name: String = null,
      url: String = null,
      location: String = null,
      description: String = null): User
  
  /**
   * Returns the current trend, geo, language, timezone and sleep time information for the authenticating user.
   * <br />This method has not been finalized and the interface is subject to change in incompatible ways.
   * <br />This method calls twitter4j.Twitter.getAccountSettings.
   * <br />getAccountSettings calls http://api.twitter.com/1.1/account/settings.json
   *
   * @return the current trend, geo and sleep time information for the authenticating user.
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1.1/get/account/settings">GET account/settings | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getAccountSettings: AccountSettings
  
  /**
   * Updates the current trend, geo, language, timezone and sleep time information for the authenticating user.
   * <br />This method has not been finalized and the interface is subject to change in incompatible ways.
   * <br />This method calls twitter4j.Twitter.updateAccountSettings.
   * <br />updateAccountSettings calls http://api.twitter.com/1.1/account/settings.json
   *
   * @param trendLocationWoeid Optional. The Yahoo! Where On Earth ID to use as the user's default trend location.
   * @param sleepTimeEnabled Optional. Whether sleep time is enabled for the user
   * @param startSleepTime Optional. The hour that sleep time should begin if it is enabled.
   * @param endSleepTime Optional. The hour that sleep time should end if it is enabled.
   * @param timeZone Optional. The timezone dates and times should be displayed in for the user.
   * @param lang Optional. The language which Twitter should render in for this user. (two letter ISO 639-1)
   * @return the current trend, geo and sleep time information for the authenticating user.
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1.1/post/account/settings">POST account/settings | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def updateAccountSettings(
      trendLocationWoeid: java.lang.Integer = null,
      sleepTimeEnabled: java.lang.Boolean = null,
      startSleepTime: String = null,
      endSleepTime: String = null,
      timeZone: String = null,
      lang: String = null): AccountSettings
  
  /**
   * Blocks the user specified in the ID parameter as the authenticating user. Returns the blocked user in the requested format when successful.
   * <br />This method calls twitter4j.Twitter.createBlock(screenName) or createBlock(userId)
   * <br />createBlock calls http://api.twitter.com/1.1/blocks/create/[id].json
   * <br />Note1: You must set parameter screenName or userId at least.
   * <br />Note2: Parameter userId is taken priority over screenName.
   *
   * @param specificUser the user spcecific information of the user to block
   * @return the blocked user
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when specificUser is set null.
   * @see <a href="https://dev.twitter.com/docs/api/1.1/post/blocks/create">POST blocks/create | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def createBlock(specificUser: User.SpecificInfo): User

  /**
   * Un-blocks the user specified in the ID parameter as the authenticating user. Returns the un-blocked user in the requested format when successful.
   * <br />This method calls twitter4j.Twitter.destroyBlock(screenName) or destroyBlock(userId)
   * <br />destroyBlock calls http://api.twitter.com/1.1/blocks/destroy/[id].json
   * <br />Note1: You must set parameter screenName or userId at least.
   * <br />Note2: Parameter userId is taken priority over screenName.
   *
   * @param specificUser the user spcecific information of the user to block
   * @return the unblocked user
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when specificUser is set null.
   * @see <a href="https://dev.twitter.com/docs/api/1.1/post/blocks/destroy">POST blocks/destroy | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def destroyBlock(specificUser: User.SpecificInfo): User

  /**
   * Returns a list of user objects that the authenticating user is blocking.
   * <br />This method calls twitter4j.Twitter.getBlocksList() or getBlocksList(page)
   * <br />getBlockingUsers calls http://api.twitter.com/1.1/blocks/blocking.json
   *
   * @param page (optional) the number of page
   * @return a list of user objects that the authenticating user
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1.1/get/blocks/blocking">GET blocks/blocking | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getBlocksList(cursor: java.lang.Long = null): PagableResponseList[twitter4j.User]

  /**
   * Returns an array of numeric user ids the authenticating user is blocking.
   * <br />This method calls twitter4j.Twitter.getBlockingUsersIDs
   * <br />getBlockingUsersIDs calls http://api.twitter.com/1.1/blocks/ids
   *
   * @return Returns an array of numeric user ids the authenticating user is blocking.
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1.1/get/blocks/ids">GET blocks/blocking/ids | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getBlocksIDs(cursor: java.lang.Long = null): IDs
  
  /**
   * Returns extended information of a given user, specified by ID or screen name as per the required id parameter. The author's most recent status will be returned inline.
   * <br />This method calls twitter4j.Twitter.showUser.
   * <br />showUser calls http://api.twitter.com/1.1/users/show.json
   * <br />Note1: You must set screenName or userId at least.
   * <br />Note2: Parameter userId is taken priority over screenName.
   *
   * @param specificUser (required) the user specific information(screen name or ID) for whom to request the detail
   * @return users
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when specificUser is set null.
   * @see <a href="https://dev.twitter.com/docs/api/1.1/get/users/show">GET users/show | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def showUser(specificUser: User.SpecificInfo): User

  /**
   * Return up to 100 users worth of extended information, specified by either ID, screen name, or combination of the two. The author's most recent status (if the authenticating user has permission) will be returned inline.
   * <br />This method calls twitter4j.Twitter.lookupUsers.
   * <br />lookupUsers calls http://api.twitter.com/1.1/users/lookup.json
   * <br />Note1: You must set screenNames or ids at least.
   * <br />Note2: Parameter ids is taken priority over screenNames.
   *
   * @param specificUsers (optional) user specific information(screen names or ids) to return
   * @return users
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1.1/get/users/lookup">GET users/lookup | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def lookupUsers(specificUsers: Users.SpecificInfo): ResponseList[twitter4j.User]

  /**
   * Run a search for users similar to the Find People button on Twitter.com; the same results returned by people search on Twitter.com will be returned by using this API.<br />
   * Usage note: It is only possible to retrieve the first 1000 matches from this API.
   * <br />This method calls twitter4j.Twitter.searchUsers.
   * <br />searchUsers calls http://api.twitter.com/1.1/users/search.json
   *
   * @param query The query to run against people search.
   * @param page Specifies the page of results to retrieve. Number of statuses per page is fixed to 20.
   * @return the list of Users matches the provided
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1.1/get/users/search">GET users/search | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def searchUsers(query: String, page: Int): ResponseList[twitter4j.User]
  
  /**
	* Returns an array of users that the specified user can contribute to.
	* <br />This method calls twitter4j.Twitter.getContributees.
	*
	* @param specificUser The user id or screen name of the user for whom to return results for
	* @return list of contributors
	* @throws TwitterException when Twitter service or network is unavailable
	* @see <a href="https://dev.twitter.com/docs/api/1.1/get/users/contributees">GET users/contributors | Twitter Developers</a>
	* @since Twitter4S 2.0.0
	*/
  def getContributees(specificUser: User.SpecificInfo): ResponseList[twitter4j.User]
  
  /**
   * Returns an array of users who can contribute to the specified account.
   * <br />This method calls twitter4j.Twitter.getContributors.
   *
   * @param specificUser The user id or screen name of the user for whom to return results for
   * @return list of contributors
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1.1/get/users/contributors">GET users/contributors | Twitter Developers</a>
   * @since Twitter4S 2.0.0
   */
  def getContributors(specificUser: User.SpecificInfo): ResponseList[twitter4j.User]

  /**
	* Removes the uploaded profile banner for the authenticating user. Returns HTTP 200 upon success.
	* <br />This method calls https://api.twitter.com/1.1/account/remove_profile_banner.json
	* <br />This method calls twitter4j.Twitter.removeProfileBanner
	*
	* @see <a href="https://dev.twitter.com/docs/api/1.1/post/account/remove_profile_banner">POST account/remove_profile_banner | Twitter Developers</a>
	* @since Twitter4S 2.0.0
	*/
  def removeProfileBanner()
  
  /**
	* Uploads a profile banner on behalf of the authenticating user. For best results, upload an <5MB image that is exactly 1252px by 626px. Images will be resized for a number of display options. Users with an uploaded profile banner will have a profile_banner_url node in their <a href="https://dev.twitter.com/docs/platform-objects/users">Users</a> objects. More information about sizing variations can be found in <a href="https://dev.twitter.com/docs/user-profile-images-and-banners">User Profile Images and Banners</a>.<br>
	* Profile banner images are processed asynchronously. The profile_banner_url and its variant sizes will not necessary be available directly after upload.<br>
	* <br />This method calls https://api.twitter.com/1.1/account/update_profile_banner.json
	* <br />This method calls twitter4j.Twitter.updateProfileBanner
	*
	* @param imageResource (File or FileStream) For best results, upload an <5MB image that is exactly 1252px by 626px.
	* @throws TwitterException when Twitter service or network is unavailable,
	* or when the specified file is not found (FileNotFoundException will be nested),
	* or when the specified file object in not representing a file (IOException will be nested)
	* @see <a href="https://dev.twitter.com/docs/api/1.1/post/account/update_profile_banner">POST account/update_profile_banner | Twitter Developers</a>
	* @since Twitter4S 2.0.0
	*/
  def updateProfileBanner(imageResource: ImageResource.SpecificResource)
}