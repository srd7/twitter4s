package twitter4s

import java.util.Date
import twitter4j.URLEntity

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait User extends TwitterResponse {
  /**
   * get user id from twitter response.
   * 
   * @return user id.
   * @since Twitter4S 1.0.0
   */
  def id: Long

  /**
   * get user name from twitter response.
   * 
   * @return user name
   * @since Twitter4S 1.0.0
   */
  def name: String

  /**
   * get screen name from twitter response.
   * 
   * @return screen name
   * @since Twitter4S 1.0.0
   */
  def screenName: String

  /**
   * get user location from twitter response.
   * 
   * @return user location
   * @since Twitter4S 1.0.0
   */
  def location: String

  /**
   * get user description from twitter response.
   * 
   * @return user description
   * @since Twitter4S 1.0.0
   */
  def description: String

  def isContributorsEnabled: Boolean

  /**
   * Returns the profile image url of the user
   */
  def profileImageURL: String
  def biggerProfileImageURL: String
  def miniProfileImageURL: String
  def originalProfileImageURL: String

  /**
   * Returns the profile image url of the user, served over SSL
   * 
   * @deprecated use {@link #profileImageURL} instead
   */
  def profileImageURLHttps: String
  def biggerProfileImageURLHttps: String
  def miniProfileImageURLHttps: String
  def originalProdileImageURLHttps: String

  def url: String

  /**
   * is user account protected ?
   */
  def isProtected: Boolean

  def followersCount: Int

  def status: Status

  def profileBackgroundColor: String

  def profileTextColor: String

  def profileLinkColor: String

  def profileSidebarFillColor: String

  def profileSidebarBorderColor: String

  def isProfileUseBackgroundImage: Boolean

  def isShowAllInlineMedia: Boolean

  def friendsCount: Int

  def createdAt: Date

  def favouritesCount: Int

  def utcOffset: Int

  def timeZone: String

  /**
   * @deprecated use {@link #profileImageURL} instead
   */
  def profileBackgroundImageUrl: String

  def profileBackgroundImageUrlHttps: String

  /**
   * @since Twitter4S 2.0.0
   */
  def profileBannerURL: String
  def profileBannerRetinaURL: String
  def profileBannerIPadURL: String
  def profileBannerIPadRetinaURL: String
  def profileBannerMobileURL: String
  def profileBannerMobileRetinaURL: String
  
  def isProfileBackgroundTiled: Boolean

  def lang: String

  def statusesCount: Int

  def isGeoEnabled: Boolean

  def isVerified: Boolean

  def isTranslator: Boolean

  def listedCount: Int

  def isFollowRequestSent: Boolean
  
  /**
   * @since Twitter4S 2.0.0
   */
  def descriptionURLEntities: Array[URLEntity]
  
  def urlEntity: URLEntity
}

object User {
  // type alias
  type SpecificInfo = Either[String, Long]
  
  /**
   * generate information that user specified by screen name.
   * 
   * @param screenName user specific data.
   * @return Left(screenName) is specified information.
   */
  def isSpecifiedBy(screenName: String): SpecificInfo = {
    require(screenName != null)
    
    Left(screenName)
  }
  
  /**
   * generate information that user specified by user id.
   * 
   * @param id user specific data.
   * @return Right(id) is specified information.
   */
  def isSpecifiedBy(id: Long): SpecificInfo = {
    Right(id)
  }
}

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
object Users {
  // type alias
  type SpecificInfo = Either[Array[String], Array[Long]]
  
  def areSpecifiedBy(screenNames: Array[String]): SpecificInfo = {
    require(screenNames != null && !screenNames.isEmpty)
    
    Left(screenNames)
  }
  
  def areSpecifiedBy(ids: Array[Long]): SpecificInfo = {
    require(ids != null && !ids.isEmpty)
    
    Right(ids)
  }
}