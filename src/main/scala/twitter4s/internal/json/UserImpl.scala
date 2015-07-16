package twitter4s.internal.json

import java.util.Date

import twitter4s._

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class UserImpl(twt4jUser: twitter4j.User) extends User {
  type Tw4jResponse = twitter4j.User

  def id = twt4jUser.getId()

  def name = twt4jUser.getName()

  def screenName = twt4jUser.getScreenName()

  def location = twt4jUser.getLocation()

  def description = twt4jUser.getDescription()

  def isContributorsEnabled = twt4jUser.isContributorsEnabled()

  def profileImageURL = twt4jUser.getProfileImageURL()

  def biggerProfileImageURL = twt4jUser.getBiggerProfileImageURL()

  def miniProfileImageURL = twt4jUser.getMiniProfileImageURL()

  def originalProfileImageURL = twt4jUser.getOriginalProfileImageURL()

  def biggerProfileImageURLHttps = twt4jUser.getBiggerProfileImageURLHttps()

  def miniProfileImageURLHttps = twt4jUser.getMiniProfileImageURLHttps()

  def originalProdileImageURLHttps = twt4jUser.getOriginalProfileImageURLHttps()

  def profileBannerURL = twt4jUser.getProfileBannerURL()

  def profileBannerRetinaURL = twt4jUser.getProfileBannerRetinaURL()

  def profileBannerIPadURL = twt4jUser.getProfileBannerIPadURL()

  def profileBannerIPadRetinaURL = twt4jUser.getProfileBannerIPadRetinaURL()

  def profileBannerMobileURL = twt4jUser.getProfileBannerMobileURL()

  def profileBannerMobileRetinaURL = twt4jUser.getProfileBannerMobileRetinaURL()

  def url = twt4jUser.getURL()

  def isProtected = twt4jUser.isProtected()

  def followersCount = twt4jUser.getFollowersCount()

  def status = StatusImpl(twt4jUser.getStatus())

  def profileBackgroundColor = twt4jUser.getProfileBackgroundColor()

  def profileTextColor = twt4jUser.getProfileTextColor()

  def profileLinkColor = twt4jUser.getProfileLinkColor()

  def profileSidebarFillColor = twt4jUser.getProfileSidebarFillColor()

  def profileSidebarBorderColor = twt4jUser.getProfileSidebarBorderColor()

  def isProfileUseBackgroundImage = twt4jUser.isProfileUseBackgroundImage()

  def isShowAllInlineMedia = twt4jUser.isShowAllInlineMedia()

  def friendsCount = twt4jUser.getFriendsCount()

  def createdAt = twt4jUser.getCreatedAt()

  def favouritesCount = twt4jUser.getFavouritesCount()

  def utcOffset = twt4jUser.getUtcOffset()

  def timeZone = twt4jUser.getTimeZone()

  def profileBackgroundImageUrlHttps = twt4jUser.getProfileBackgroundImageUrlHttps()

  def isProfileBackgroundTiled = twt4jUser.isProfileBackgroundTiled()

  def lang = twt4jUser.getLang()

  def statusesCount = twt4jUser.getStatusesCount()

  def isGeoEnabled = twt4jUser.isGeoEnabled()

  def isVerified = twt4jUser.isVerified()

  def isTranslator = twt4jUser.isTranslator()

  def listedCount = twt4jUser.getListedCount()

  def isFollowRequestSent = twt4jUser.isFollowRequestSent()

  def descriptionURLEntities = twt4jUser.getDescriptionURLEntities()

  def urlEntity = twt4jUser.getURLEntity()

  def rateLimitStatus = RateLimitStatusImpl(twt4jUser.getRateLimitStatus())

  def accessLevel = AccessLevel(twt4jUser.getAccessLevel())

  def tw4jObj = twt4jUser
}
