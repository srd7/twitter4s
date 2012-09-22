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
import twitter4s.AccountSettings
import twitter4s.AccessLevel

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class AccountSettingsImpl(twt4jAccountSettings: twitter4j.AccountSettings) extends AccountSettings {
  type Tw4jResponse = twitter4j.AccountSettings
  
  def isSleepTimeEnabled = twt4jAccountSettings.isSleepTimeEnabled()

  def sleepStartTime = twt4jAccountSettings.getSleepStartTime()

  def sleepEndTime = twt4jAccountSettings.getSleepEndTime()

  def trendLocations = twt4jAccountSettings.getTrendLocations()

  def isGeoEnabled = twt4jAccountSettings.isGeoEnabled()

  def timeZone = twt4jAccountSettings.getTimeZone()

  def language = twt4jAccountSettings.getLanguage()

  def isDiscoverableByEmail = twt4jAccountSettings.isDiscoverableByEmail()

  def isAlwaysUseHttps = twt4jAccountSettings.isAlwaysUseHttps()
  
  def rateLimitStatus = RateLimitStatusImpl(twt4jAccountSettings.getRateLimitStatus())
  
  def accessLevel = AccessLevel(twt4jAccountSettings.getAccessLevel())
  
  def tw4jObj = twt4jAccountSettings
}