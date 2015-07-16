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

import java.util.Date

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait Status extends TwitterResponse with EntitySupport {
  def createdAt: Date

  def id: Long

  def text: String

  def source: String

  def inReplyToStatusId: Long

  def place: Place

  def geoLocation: twitter4j.GeoLocation

  def isTruncated: Boolean

  def inReplyToUserId: Long

  def inReplyToScreenName: String

  def isFavorited: Boolean

  def user: User

  def isRetweet: Boolean

  def retweetedStatus: Status

  def contributors: Array[Long]

  def retweetCount: Int

  def isRetweetedByMe: Boolean

  /**
   * @since Twitter4S 2.0.0
   */
  def currentUserRetweetId: Long

  def isPossiblySensitive: Boolean

  /**
   * Returns the lang of the status text if available.
   *
   * @since Twitter4S 2.1.0
   */
  def lang: String

  /**
   * Test if the status is retweeted
   *
   * @since Twitter4S 2.1.0
   */
  def isRetweeted: Boolean

  /**
   * Indicates approximately how many times this Tweet has been "favorited" by Twitter users.
   *
   * @since Twitter4S 2.1.0
   */
  def favoriteCount: Int
}

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
object Status {
  type StatusSpecific = Either[String, twitter4j.StatusUpdate]

  def isWrittenBy(text: String) = {
    require(text != null)

    Left(text)
  }

  def isSetBy(latestStatus: twitter4j.StatusUpdate) = {
    require(latestStatus != null)

    Right(latestStatus)
  }
}
