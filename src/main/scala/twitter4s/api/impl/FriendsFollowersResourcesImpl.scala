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
import twitter4s.IDs
import twitter4s.api.FriendsFollowersResources
import twitter4s.Twitter
import twitter4s.Users
import twitter4s.Relationship
import twitter4s.ResponseList
import twitter4j.Friendship
import twitter4s.User
import twitter4s.PagableResponseList

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 * @since Twitter4S 2.0.0
 */
trait FriendsFollowersResourcesImpl extends FriendsFollowersResources {
  self: Twitter =>
    
  /**
   * {@inheritDoc}
   */
  def getFriendsIDs(cursor: Long, specificUser: User.SpecificInfo = null): IDs = {
    Option(specificUser) match {
      case Some(specificUser) => specificUser match {
        case Right(userId) => twitter4jObj.getFriendsIDs(userId, cursor)
        case Left(screenName) => twitter4jObj.getFriendsIDs(screenName, cursor)
      }
      case None => twitter4jObj.getFriendsIDs(cursor)
    }
  }

  /**
   * {@inheritDoc}
   */
  def getFollowersIDs(cursor: Long, specificUser: User.SpecificInfo = null): IDs = {
    Option(specificUser) match {
      case Some(specificUser) => specificUser match {
        case Right(userId) => twitter4jObj.getFollowersIDs(userId, cursor)
        case Left(screenName) => twitter4jObj.getFollowersIDs(screenName, cursor)
      }
      case None => twitter4jObj.getFollowersIDs(cursor)
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def createFriendship(
      specificUser: User.SpecificInfo,
      follow: java.lang.Boolean = null): User = {
    require(specificUser != null)
    
    Option(follow) match {
      case None => specificUser match {
        case Right(userId) => twitter4jObj.createFriendship(userId)
        case Left(screenName) => twitter4jObj.createFriendship(screenName)
      }
      case Some(follow) => specificUser match {
        case Right(userId) => twitter4jObj.createFriendship(userId, follow)
        case Left(screenName) => twitter4jObj.createFriendship(screenName, follow)
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  def destroyFriendship(specificUser: User.SpecificInfo): User = {
    require(specificUser != null)
    
    specificUser match {
      case Right(userId) => twitter4jObj.destroyFriendship(userId)
      case Left(screenName) => twitter4jObj.destroyFriendship(screenName)
    }
  }

  /**
   * {@inheritDoc}
   */
  def showFriendship(
      sourceSpecificUser: User.SpecificInfo,
      targetSpecificUser: User.SpecificInfo): Relationship = {
    require(sourceSpecificUser != null && targetSpecificUser != null)
    require((sourceSpecificUser.isRight && targetSpecificUser.isRight) ||
        (sourceSpecificUser.isLeft && targetSpecificUser.isLeft))
    
    (sourceSpecificUser, targetSpecificUser) match {
      case (Right(sourceId), Right(targetId)) => twitter4jObj.showFriendship(sourceId, targetId)
      case (Left(sourceScreenName), Left(targetScreenName)) => twitter4jObj.showFriendship(sourceScreenName, targetScreenName)
      case _ => throw new IllegalArgumentException("No such parameter pattern in twitter4j")
    }
  }

  /**
   * {@inheritDoc}
   */
  def getIncomingFriendships(cursor: Long): IDs = {
    twitter4jObj.getIncomingFriendships(cursor)
  }

  /**
   * {@inheritDoc}
   */
  def getOutgoingFriendships(cursor: Long): IDs = {
    twitter4jObj.getOutgoingFriendships(cursor)
  }

  /**
   * {@inheritDoc}
   */
  def lookupFriendships(specificUsers: Users.SpecificInfo): ResponseList[Friendship] = {
    require(specificUsers != null)
    
    specificUsers match {
      case Right(ids) => twitter4jObj.lookupFriendships(ids)
      case Left(screenNames) => twitter4jObj.lookupFriendships(screenNames)
    }
  }

  /**
   * {@inheritDoc}
   */
  def updateFriendship(
      specificUser: User.SpecificInfo,
      enableDeviceNotification: Boolean,
      retweets: Boolean): Relationship = {
    require(specificUser != null)
    
    specificUser match {
      case Right(userId) => twitter4jObj.updateFriendship(userId, enableDeviceNotification, retweets)
      case Left(screenName) => twitter4jObj.updateFriendship(screenName, enableDeviceNotification, retweets)
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def getFriendsList(specificUser: User.SpecificInfo, cursor: Long): PagableResponseList[twitter4j.User] = {
    require(specificUser != null)
    
    specificUser match {
      case Left(screenName) => twitter4jObj.getFriendsList(screenName, cursor)
      case Right(userId) => twitter4jObj.getFriendsList(userId, cursor)
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def getFollowersList(specificUser: User.SpecificInfo, cursor: Long): PagableResponseList[twitter4j.User] = {
    require(specificUser != null)
    
    specificUser match {
      case Left(screenName) => twitter4jObj.getFollowersList(screenName, cursor)
      case Right(userId) => twitter4jObj.getFollowersList(userId, cursor)
    }
  }
}