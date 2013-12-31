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
import twitter4s.Twitter
import twitter4s.dsl.dsl.{ListContext, UserContext}

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait ContextGetter[ContextType, ReturnType] {
  def get(self: ContextType)(implicit twitter:Twitter): ReturnType
}

object UserContextGetter extends ContextGetter[UserContext, twitter4s.User] {
  def get(self: UserContext)(implicit twitter:Twitter) = self.user match {
    case Right(userId) => twitter.twitter4jObj.showUser(userId)
    case Left(screenName) => twitter.twitter4jObj.showUser(screenName)
  }
}

object ListContextGetter extends ContextGetter[ListContext, twitter4s.UserList] {
  def get(self: ListContext)(implicit twitter: Twitter) = self.list match {
    case Right(listId) => twitter.twitter4jObj.showUserList(listId)
    case Left(listSlug) => twitter.twitter4jObj.showUserList(twitter.id, listSlug)
  }
}
