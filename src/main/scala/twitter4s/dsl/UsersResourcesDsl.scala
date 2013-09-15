package twitter4s.dsl

/*
 * Copyright (C) 2013 Shinsuke Abe
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

import twitter4s.api.UsersResources
import twitter4s.api.impl.UsersResourcesImpl
import twitter4s._
import java.lang.Long

/**
 * @author mao.instantlife at gmail.com
 */
trait UsersResourcesDsl extends Twitter4sDslBase with UsersResources {
  type ResourcesType = UsersResourcesImpl
  override lazy val twitter4sResources = new Twitter(twitter4jResources) with ResourcesType


  // bellow methods implements are a cliche.
  def showUser(specificUser: User.SpecificInfo) = resources.showUser(specificUser)
  def lookupUsers(specificUsers: Users.SpecificInfo) = resources.lookupUsers(specificUsers)
  def searchUsers(query: String, page: Int) = resources.searchUsers(query, page)
  def getContributees(specificUser: User.SpecificInfo) = resources.getContributees(specificUser)
  def getContributors(specificUser: User.SpecificInfo) = resources.getContributors(specificUser)
  def removeProfileBanner() {resources.removeProfileBanner()}
  def updateProfileBanner(imageResource: ImageResource.SpecificResource) {resources.updateProfileBanner(imageResource)}
  def destroyBlock(specificUser: User.SpecificInfo) = resources.destroyBlock(specificUser)
  def getBlocksList(cursor: Long = null) = resources.getBlocksList(cursor)
  def getBlocksIDs(cursor: Long = null) = resources.getBlocksIDs(cursor)
  def createBlock(specificUser: User.SpecificInfo) = resources.createBlock(specificUser)
  def updateProfileColors(
        profileBackgroundColor: String = null,
        profileTextColor: String = null,
        profileLinkColor: String = null,
        profileSidebarFillColor: String = null,
        profileSidebarBorderColor: String = null) = resources.updateProfileColors(profileBackgroundColor, profileTextColor, profileLinkColor, profileSidebarFillColor, profileSidebarBorderColor)
  def updateProfileImage(imageResource: ImageResource.SpecificResource) = resources.updateProfileImage(imageResource)
  def updateProfile(name: String = null, url: String = null, location: String = null, description: String = null) = resources.updateProfile(name, url, location, description)
  def getAccountSettings = resources.getAccountSettings
  def updateAccountSettings(
        trendLocationWoeid: Integer = null,
        sleepTimeEnabled: java.lang.Boolean = null,
        startSleepTime: String = null,
        endSleepTime: String = null,
        timeZone: String = null,
        lang: String = null) = resources.updateAccountSettings(trendLocationWoeid, sleepTimeEnabled, startSleepTime, endSleepTime, timeZone, lang)
  def verifyCredentials = resources.verifyCredentials
  def updateProfileBackgroundImage(
        imageResource: ImageResource.SpecificResource,
        tile: Boolean): User = resources.updateProfileBackgroundImage(imageResource, tile)
}
