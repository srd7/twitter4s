package twitter4s
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