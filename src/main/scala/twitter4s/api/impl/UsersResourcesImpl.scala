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
import twitter4s.User
import twitter4s.api.UsersResources
import scala.util.Either
import twitter4s.Users
import twitter4s.ImageResource
import twitter4s.IDs
import twitter4s.AccountTotals
import twitter4s.Twitter
import twitter4s.ResponseList
import twitter4s.AccountSettings
import twitter4j.Category

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
  def existsBlock(specificUser: User.SpecificInfo): Boolean = {
    require(specificUser != null)
    
    // TODO 削除メソッド
//    specificUser match {
//      case Right(userId) => twitter4jObj.existsBlock(userId)
//      case Left(screenName) => twitter4jObj.existsBlock(screenName)
//    }
    false
  }
  
  /**
   * {@inheritDoc}
   */
  def getBlocksList(page: java.lang.Integer = null): ResponseList[twitter4j.User] = {
    // TODO 削除メソッド or メソッド変更
//    Option(page) match {
//      case Some(page) => twitter4jObj.getBlockingUsers(page)
//      case None => twitter4jObj.getBlockingUsers()
//    }
    null
  }
  
  /**
   * {@inheritDoc}
   */
  def getBlocksIDs: IDs = {
    // TODO 削除メソッド or メソッド変更
//    twitter4jObj.getBlockingUsersIDs()
    null
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
      case Right(ids) => twitter4jObj.lookupUsers(ids)
      case Left(screenNames) => twitter4jObj.lookupUsers(screenNames)
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
  def getSuggestedUserCategories: ResponseList[Category] = {
    twitter4jObj.getSuggestedUserCategories()
  }

  /**
   * {@inheritDoc}
   */
  def getUserSuggestions(categorySlug: String): ResponseList[twitter4j.User] = {
    twitter4jObj.getUserSuggestions(categorySlug)
  }

  /**
   * {@inheritDoc}
   */
  def getMemberSuggestions(categorySlug: String): ResponseList[twitter4j.User] = {
    twitter4jObj.getMemberSuggestions(categorySlug)
  }

  // TODO 削除メソッド
//  /**
//   * {@inheritDoc}
//   */
//  def getProfileImage(screenName: String, size: ProfileImage.ImageSize): ProfileImage = {
//    twitter4jObj.getProfileImage(screenName, size)
//  }
}