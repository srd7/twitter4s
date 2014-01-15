package twitter4s

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

import twitter4j.auth.AccessToken
import twitter4s.auth.ConsumerKey

/**
 * @author mao.instantlife at gmail.com
 */
package object dsl {
  val idPrefix = "id:"

  implicit class TwitterDSLString(val sc: StringContext) extends AnyVal {
    def tweet(args: Any*) = TweetContext(sc.s(args: _*))

    def user(args: Any*) = UserContext(
      sc.s(args: _*) match {
        case str if str.toLowerCase.startsWith(idPrefix) => User.isSpecifiedBy(str.drop(idPrefix.length).toLong)
        case str => User.isSpecifiedBy(str)
      })

    def list(args: Any*) = ListContext(
      sc.s(args: _*) match {
        case str if str.toLowerCase.startsWith(idPrefix) => Right(str.drop(idPrefix.length).toInt)
        case str => Left(str)
      })

    def message(args: Any*) = MessageContext(sc.s(args: _*))
  }

  // context getter binding
  implicit val userContextGetter = UserContextGetter
  implicit val listContextGetter = ListContextGetter

  /**
   * Binding access token and execute DSL script.
   *
   * @param accessToken access token for binding on DSL script.
   * @param dslScript executing DSL script
   * @param consumerKey comsumer key
   * @return Unit
   */
  def withToken(accessToken: AccessToken)(dslScript: Twitter => Unit)(implicit consumerKey: ConsumerKey) {
    dslScript(Twitter(consumerKey, accessToken))
  }

  /**
   * Add user for any resource.
   *
   * @param user user string context
   * @return UserAdder instance
   */
  def add(user: UserContext) = UserAdder(user)

  /**
   * Remove user from any resource.
   *
   * @param user user string context
   * @return UserRemover instance
   */
  def remove(user: UserContext) = UserRemover(user)

  /**
   * Get resource with specified context string from Twitter API.
   *
   * @param self Twitter4S DSL context instance
   * @param twitter Twitter4S instance
   * @param getter ContextGetter instance
   * @tparam C Twitter4S DSL context type
   * @tparam R Return value type
   * @return Context resources from API
   */
  def get[C, R](self: C)(implicit twitter: Twitter, getter: ContextGetter[C, R]):R = getter.get(self)

  /**
   * Send message to any user.
   *
   * @param messageContext message string context
   * @return MessageSender instance
   */
  def send(messageContext: MessageContext) = MessageSender(messageContext)

  /**
   * Follow any user.
   *
   * @param userContext user string context
   * @param twitter Twitter4S instance
   * @return User instance
   */
  def follow(userContext: UserContext)(implicit twitter: Twitter): twitter4s.User =
    twitter.friendsFollowers.createFriendship(userContext.user)

  /**
   * Un follow any user.
   *
   * @param userContext user string context
   * @param twitter Twtter4S instance
   * @return User instance
   */
  def unfollow(userContext: UserContext)(implicit twitter: Twitter): twitter4s.User =
    twitter.friendsFollowers.destroyFriendship(userContext.user)

  // TODO to another source file(below case classes)
  case class UserAdder(userContext: UserContext) {
    def to(listContext: ListContext)(implicit twitter: Twitter): twitter4s.UserList =
      twitter.list.createUserListMember(listContext.specifiedInfo, get(userContext).id)
  }

  case class UserRemover(userContext: UserContext) {
    def from(listContext: ListContext)(implicit twitter: Twitter): twitter4s.UserList =
      twitter.list.destroyUserListMember(listContext.specifiedInfo, get(userContext).id)
  }

  case class MessageSender(messageContext: MessageContext) {
    def to(userContext: UserContext)(implicit twitter: Twitter): twitter4s.DirectMessage =
      twitter.directMessages.sendDirectMessage(userContext.user, messageContext.message)
  }
}
