package twitter4s.dsl

/*
 * Copyright (C) 2014 Shinsuke Abe
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

// context operators classes

/**
 * @author mao.instantlife at gmail.com
 */
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
