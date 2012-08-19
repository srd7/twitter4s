package twitter4s

import java.net.URL
import java.util.Date

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

  def profileImageURL: URL

  def profileImageUrlHttps: URL

  def url: URL

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

  def profileBackgroundImageUrl: String

  def profileBackgroundImageUrlHttps: String

  def isProfileBackgroundTiled: Boolean

  def lang: String

  def statusesCount: Int

  def isGeoEnabled: Boolean

  def isVerified: Boolean

  def isTranslator: Boolean

  def listedCount: Int

  def isFollowRequestSent: Boolean
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