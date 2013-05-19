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
import twitter4s.RateLimitStatus
import twitter4s.AccountTotals
import twitter4s.AccountSettings
import java.io.File
import java.io.InputStream
import twitter4s.ImageResource

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait AccountMethods {

  /**
   * Returns an HTTP 200 OK response code and a representation of the requesting user if authentication was successful; returns a 401 status code and an error message if not. Use this method to test if supplied user credentials are valid.
   * <br />This method calls twitter4j.Twitter.verifyCredentials.
   * <br />verifyCredentials calls http://api.twitter.com/1/account/verify_credentials.json
   * 
   * @return user
   * @throws twitter4j.TwitterException when Twitter service or network is unavailable, or if supplied credential is wrong (TwitterException.getStatusCode() == 401)
   * @see <a href="https://dev.twitter.com/docs/api/1/get/account/verify_credentials">GET account/verify_credentials | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def verifyCredentials: User
  
  /**
   * Sets one or more hex values that control the color scheme of the authenticating user's profile page on twitter.com. Each parameter's value must be a valid hexidecimal value, and may be either three or six characters (ex: #fff or #ffffff).<br/>
   * <br />This method calls twitter4j.Twitter.updateProfileColors.
   * <br />updateProfileColors calls http://api.twitter.com/1/account/update_profile_colors.json
   * 
   * @param profileBackgroundColor optional, can be null
   * @param profileTextColor optional, can be null
   * @param profileLinkColor optional, can be null
   * @param profileSidebarFillColor optional, can be null
   * @param profileSidebarBorderColor optional, can be null
   * @return the updated user
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/account/update_profile_colors">POST account/update_profile_colors | Twitter Developers</a>
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
   * <br />updateProfileImage calls http://api.twitter.com/1/account/update_profile_image.json
   * <br />Note: You must set parameter imageFile or imageStream at least.
   * <br />Profile image must be a valid GIF, JPG, or PNG image of less than 700 kilobytes in size. Images with width larger than 500 pixels will be scaled down.
   *
   * @param imageResource (require) Profile image as File or Stream object.
   * @return the updated user
   * @throws TwitterException when Twitter service or network is unavailable,
   * or when the specified file is not found (FileNotFoundException will be nested),
   * or when the specified file object in not representing a file (IOException will be nested).
   * @see <a href="https://dev.twitter.com/docs/api/1/post/account/update_profile_image">POST account/update_profile_image | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def updateProfileImage(imageResource: ImageResource.SpecificResource): User
  
  /**
   * Updates the authenticating user's profile background image.<br/>
   * <br />This method calls twitter4.Twitter.updateProfileBackgroundImage.
   * <br />updateProfileBackgroundImage method calls http://api.twitter.com/1/account/update_profile_background_image.json
   * <br />Note: You must set prameter imageFile or imageStream at least.
   * <br />Background image must be a valid GIF, JPG, or PNG image of less than 800 kilobytes in size. Images with width larger than 2048 pixels will be forceably scaled down.
   *
   * @param imageResource (require) Background image as File or Stream object.
   * @param tile (required) If set to true the background image will be displayed tiled. The image will not be tiled otherwise.
   * @return the updated user
   * @throws TwitterException when Twitter service or network is unavailable,
   * or when the specified file is not found (FileNotFoundException will be nested),
   * or when the specified file object in not representing a file (IOException will be nested)
   * @see <a href="https://dev.twitter.com/docs/api/1/post/account/update_profile_background_image">POST account/update_profile_background_image | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def updateProfileBackgroundImage(
      imageResource: ImageResource.SpecificResource,
      tile: Boolean): User
  
  /**
   * Sets values that users are able to set under the "Account" tab of their settings page. Only the parameters specified(non-null) will be updated.<br/>
   * <br />This method calls twitter4j.Twitter.updateProfile.
   * <br />updateProfile calls http://api.twitter.com/1/account/update_profile.json
   *
   * @param name Optional. Maximum of 20 characters.
   * @param url Optional. Maximum of 100 characters. Will be prepended with "http://" if not present.
   * @param location Optional. Maximum of 30 characters. The contents are not normalized or geocoded in any way.
   * @param description Optional. Maximum of 160 characters.
   * @return the updated user
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/account/update_profile">POST account/update_profile | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def updateProfile(
      name: String = null,
      url: String = null,
      location: String = null,
      description: String = null): User
  
  /**
   * Returns the current count of friends, followers, updates (statuses) and favorites of the authenticating user.
   * <br />This method has not been finalized and the interface is subject to change in incompatible ways.
   * <br />This method calls twitter4j.Twitter.getAccountTotals.
   * <br />getAccountTotals calls http://api.twitter.com/1/account/totals.json
   *
   * @return the current count of friends, followers, updates (statuses) and favorites of the authenticating user
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/account/totals">GET account/totals | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getAccountTotals: AccountTotals
  
  /**
   * Returns the current trend, geo, language, timezone and sleep time information for the authenticating user.
   * <br />This method has not been finalized and the interface is subject to change in incompatible ways.
   * <br />This method calls twitter4j.Twitter.getAccountSettings.
   * <br />getAccountSettings calls http://api.twitter.com/1/account/settings.json
   *
   * @return the current trend, geo and sleep time information for the authenticating user.
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/account/settings">GET account/settings | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getAccountSettings: AccountSettings
  
  /**
   * Updates the current trend, geo, language, timezone and sleep time information for the authenticating user.
   * <br />This method has not been finalized and the interface is subject to change in incompatible ways.
   * <br />This method calls twitter4j.Twitter.updateAccountSettings.
   * <br />updateAccountSettings calls http://api.twitter.com/1/account/settings.json
   *
   * @param trendLocationWoeid Optional. The Yahoo! Where On Earth ID to use as the user's default trend location.
   * @param sleepTimeEnabled Optional. Whether sleep time is enabled for the user
   * @param startSleepTime Optional. The hour that sleep time should begin if it is enabled.
   * @param endSleepTime Optional. The hour that sleep time should end if it is enabled.
   * @param timeZone Optional. The timezone dates and times should be displayed in for the user.
   * @param lang Optional. The language which Twitter should render in for this user. (two letter ISO 639-1)
   * @return the current trend, geo and sleep time information for the authenticating user.
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/account/settings">POST account/settings | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def updateAccountSettings(
      trendLocationWoeid: java.lang.Integer = null,
      sleepTimeEnabled: java.lang.Boolean = null,
      startSleepTime: String = null,
      endSleepTime: String = null,
      timeZone: String = null,
      lang: String = null): AccountSettings
}