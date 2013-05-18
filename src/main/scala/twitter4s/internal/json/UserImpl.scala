package twitter4s.internal.json
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
import java.net.URL
import java.util.Date

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

  // TODO twitter4jはStringに変更
  def profileImageURL = new java.net.URL(twt4jUser.getProfileImageURL())

  // TODO StringとURLでオーバーロード
  def profileImageUrlHttps = twt4jUser.getProfileImageUrlHttps()

  // TODO twitter4jはStringに変更
  def url = new java.net.URL(twt4jUser.getURL())

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

  def profileBackgroundImageUrl = twt4jUser.getProfileBackgroundImageUrl()

  def profileBackgroundImageUrlHttps = twt4jUser.getProfileBackgroundImageUrlHttps()

  def isProfileBackgroundTiled = twt4jUser.isProfileBackgroundTiled()

  def lang = twt4jUser.getLang()

  def statusesCount = twt4jUser.getStatusesCount()

  def isGeoEnabled = twt4jUser.isGeoEnabled()

  def isVerified = twt4jUser.isVerified()

  def isTranslator = twt4jUser.isTranslator()

  def listedCount = twt4jUser.getListedCount()

  def isFollowRequestSent = twt4jUser.isFollowRequestSent()
  
  def rateLimitStatus = RateLimitStatusImpl(twt4jUser.getRateLimitStatus())
  
  def accessLevel = AccessLevel(twt4jUser.getAccessLevel())
  
  def tw4jObj = twt4jUser
}