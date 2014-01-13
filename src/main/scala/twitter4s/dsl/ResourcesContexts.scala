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
import twitter4s.{UserList, User, Twitter}

/**
 * @author mao.instantlife at gmail.com
 */

/**
 * string context to specific to tweet
 * @param tweet tweet string
 */
case class TweetContext(tweet: String) {
  def update(implicit twitter: Twitter) = {
    twitter.twitter4jObj.updateStatus(tweet)
  }
}

/**
 * string context to specific to user
 * @param user user specific information(screen name or user id)
 */
case class UserContext(user: User.SpecificInfo)

/**
 * string context to specific to user list
 * @param list user list specific information(list slug or list id)
 */
case class ListContext(list: Either[String, Int]) {
  def specifiedInfo(implicit twitter: Twitter) = list match {
    case Left(listSlug) => UserList.isSpecifiedBy(User.isSpecifiedBy(twitter.id), listSlug)
    case Right(listId) => UserList.isSpecifiedBy(listId)
  }
}

/**
 * string context to specific to direct message
 * @param message message string
 */
case class MessageContext(message: String)
