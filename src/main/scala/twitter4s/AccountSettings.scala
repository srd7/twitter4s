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

import twitter4j.TimeZone
import twitter4j.Location

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait AccountSettings extends TwitterResponse {
  /**
   * is sleep time set.
   * 
   * @return setting enabled(true) or unabled(false)
   * @since Twitter4S 1.0.0
   */
  def isSleepTimeEnabled: Boolean

  /**
   * get sleep start time string.
   * 
   * @return sleep start time.
   * @since Twitter4S 1.0.0
   */
  def sleepStartTime: String

  /**
   * get sleep end time string.
   * 
   * @return sleep end time.
   * @since Twitter4S 1.0.0
   */
  def sleepEndTime: String

  /**
   * get trend location array.
   * 
   * @return trend location array.
   * @since Twitter4S 1.0.0
   */
  def trendLocations: Array[Location]

  /**
   * is geo setting set.
   * 
   * @return setting enabled(true) or unabled(false)
   * @since Twitter4S 1.0.0
   */
  def isGeoEnabled: Boolean

  /**
   * get time zone
   * 
   * @return time zone
   * @since Twitter4S 1.0.0
   */
  def timeZone: TimeZone

  /**
   * get user setting language.
   * 
   * @return language
   * @since Twitter4S 1.0.0
   */
  def language: String

  /**
   * is user acount discoverable by email set.
   * 
   * @return setting enabled(true) or unabled(false)
   * @since Twitter4S 1.0.0
   */
  def isDiscoverableByEmail: Boolean

  /**
   * is user access always use https set.
   */
  def isAlwaysUseHttps: Boolean
}