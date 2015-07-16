package twitter4s.api.impl
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
import twitter4s._
import twitter4s.api.UsersResources
import scala.Some

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 * @since Twitter4S 2.0.0
 */
trait UsersResourcesImpl extends UsersResources {
  self: Twitter =>

  /**
   * {@inheritDoc}
   */
  def verifyCredentials: User = {
    twitter4jObj.verifyCredentials()
  }

  /**
   * {@inherited}
   */
  def updateProfileColors(
      profileBackgroundColor: String = null,
      profileTextColor: String = null,
      profileLinkColor: String = null,
      profileSidebarFillColor: String = null,
      profileSidebarBorderColor: String = null): User = {
    twitter4jObj.updateProfileColors(profileBackgroundColor, profileTextColor, profileLinkColor, profileSidebarFillColor, profileSidebarBorderColor)
  }

  /**
   * {@inheritDoc}
   */
  def updateProfileImage(imageResource: ImageResource.SpecificResource): User = {
    require(imageResource != null)

    imageResource match {
      case Left(imageStream) => twitter4jObj.updateProfileImage(imageStream)
      case Right(imageFile) => twitter4jObj.updateProfileImage(imageFile)
    }
  }

  /**
   * {@inheritDoc}
   */
  def updateProfileBackgroundImage(
      imageResource: ImageResource.SpecificResource,
      tile: Boolean): User = {
    require(imageResource != null)

    imageResource match {
      case Left(imageStream) => twitter4jObj.updateProfileBackgroundImage(imageStream, tile)
      case Right(imageFile) => twitter4jObj.updateProfileBackgroundImage(imageFile, tile)
    }
  }

  /**
   * {@inheritDoc}
   */
  def updateProfile(
      name: String = null,
      url: String = null,
      location: String = null,
      description: String = null): User = {
    twitter4jObj.updateProfile(name, url, location, description)
  }

  /**
   * {@inheritDoc}
   */
  def getAccountSettings: AccountSettings = {
    twitter4jObj.getAccountSettings()
  }

  /**
   * {@inheritDoc}
   */
  def updateAccountSettings(
      trendLocationWoeid: java.lang.Integer = null,
      sleepTimeEnabled: java.lang.Boolean = null,
      startSleepTime: String = null,
      endSleepTime: String = null,
      timeZone: String = null,
      lang: String = null): AccountSettings = {
    twitter4jObj.updateAccountSettings(trendLocationWoeid, sleepTimeEnabled, startSleepTime, endSleepTime, timeZone, lang)
  }

  /**
   * {@inheritDoc}
   */
  def createBlock(specificUser: User.SpecificInfo): User = {
    require(specificUser != null)

    specificUser match {
      case Right(userId) => twitter4jObj.createBlock(userId)
      case Left(screenName) => twitter4jObj.createBlock(screenName)
    }
  }

  /**
   * {@inheritDoc}
   */
  def destroyBlock(specificUser: User.SpecificInfo): User = {
    require(specificUser != null)

    specificUser match {
      case Right(userId) => twitter4jObj.destroyBlock(userId)
      case Left(screenName) => twitter4jObj.destroyBlock(screenName)
    }
  }

  /**
   * {@inheritDoc}
   */
  def getBlocksList(cursor: java.lang.Long = null): PagableResponseList[twitter4j.User] = {
    Option(cursor) match {
      case Some(cursor) => twitter4jObj.getBlocksList(cursor)
      case None => twitter4jObj.getBlocksList()
    }
  }

  /**
   * {@inheritDoc}
   */
  def getBlocksIDs(cursor: java.lang.Long = null): IDs = {
    Option(cursor) match {
      case Some(cursor) => twitter4jObj.getBlocksIDs(cursor)
      case None => twitter4jObj.getBlocksIDs()
    }
  }

    /**
   * {@inheritDoc}
   */
  def showUser(specificUser: User.SpecificInfo): User = {
    require(specificUser != null)

    specificUser match {
      case Right(userId) => twitter4jObj.showUser(userId)
      case Left(screenName) => twitter4jObj.showUser(screenName)
    }
  }

  /**
   * {@inheritDoc}
   */
  def lookupUsers(specificUsers: Users.SpecificInfo): ResponseList[twitter4j.User] = {
    require(specificUsers != null)

    specificUsers match {
      case Right(ids) => twitter4jObj.lookupUsers(ids: _*)
      case Left(screenNames) => twitter4jObj.lookupUsers(screenNames: _*)
    }
  }

  /**
   * {@inheritDoc}
   */
  def searchUsers(query: String, page: Int): ResponseList[twitter4j.User] = {
    twitter4jObj.searchUsers(query, page)
  }

  /**
   * {@inheritDoc}
   */
  def getContributees(specificUser: User.SpecificInfo): ResponseList[twitter4j.User] = {
    require(specificUser != null)

    specificUser match {
      case Right(id) => twitter4jObj.getContributees(id)
      case Left(screenName) => twitter4jObj.getContributees(screenName)
    }
  }

  /**
   * {@inheritDoc}
   */
  def getContributors(specificUser: User.SpecificInfo): ResponseList[twitter4j.User] = {
    require(specificUser != null)

    specificUser match {
      case Right(id) => twitter4jObj.getContributors(id)
      case Left(screenName) => twitter4jObj.getContributors(screenName)
    }
  }

  /**
   * {@inheritDoc}
   */
  def removeProfileBanner() {
    twitter4jObj.removeProfileBanner()
  }

  /**
   * {@inheritDoc}
   */
  def updateProfileBanner(imageResource: ImageResource.SpecificResource) {
    require(imageResource != null)

    imageResource match {
      case Right(imageFile) => twitter4jObj.updateProfileBanner(imageFile)
      case Left(imageStream) => twitter4jObj.updateProfileBanner(imageStream)
    }
  }
}
