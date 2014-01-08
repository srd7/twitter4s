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
//import twitter4j.Annotations

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
// TODO Twtを統合する
trait Status extends TwitterResponse with Twt with EntitySupport {
  def isTruncated: Boolean
  
  def inReplyToUserId: Long
  
  def inReplyToScreenName: String
  
  def isFavorited: Boolean
  
  def user: User
  
  def isRetweet: Boolean
  
  def retweetedStatus: Status
  
  def contributors: Array[Long]
  
  def retweetCount: Long
  
  def isRetweetedByMe: Boolean

  /**
   * @since Twitter4S 2.0.0
   */
  def currentUserRetweetId: Long
  
  def isPossiblySensitive: Boolean

  // TODO 追加
//  /**
//   * Returns the iso language code set by the Twitter API (best-effort).
//   *
//   * @return two-letter iso language code
//   */
//  def isoLanguageCode: String
//  /**
//   * Test if the status is retweeted
//   *
//   * @return true if retweeted
//   * @since Twitter4J 3.0.4
//   */
//  boolean isRetweeted();
//
//  /**
//   * Indicates approximately how many times this Tweet has been "favorited" by Twitter users.
//   *
//   * @return the favorite count
//   * @since Twitter4J 3.0.4
//   */
//  int getFavoriteCount();
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