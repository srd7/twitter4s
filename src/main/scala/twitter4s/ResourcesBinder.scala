package twitter4s

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
import twitter4s.api.impl._
import twitter4s.api._

/**
 * @author mao.instantlife at gmail.com
 */
trait ResourcesBinder[ApiResourcesInterface] {
  def apply(self: Twitter): ApiResourcesInterface = bind(self)

  val bind: (Twitter) => ApiResourcesInterface
}

object TimelinesResourcesBinder extends ResourcesBinder[TimelinesResources]{
  val bind = (self: Twitter) => new Twitter(self.twitter4jObj) with TimelinesResourcesImpl
}

object TweetsResourcesBinder extends ResourcesBinder[TweetsResources]{
  val bind = (self: Twitter) => new Twitter(self.twitter4jObj) with TweetsResourcesImpl
}

object SearchResourcesBinder extends ResourcesBinder[SearchResources]{
  val bind = (self: Twitter) => new Twitter(self.twitter4jObj) with SearchResourcesImpl
}

object DirectMessagesResourcesBinder extends ResourcesBinder[DirectMessagesResources]{
  val bind = (self: Twitter) => new Twitter(self.twitter4jObj) with DirectMessagesResourcesImpl
}

object FriendsFollowersResourcesBinder extends ResourcesBinder[FriendsFollowersResources]{
  val bind = (self: Twitter) => new Twitter(self.twitter4jObj) with FriendsFollowersResourcesImpl
}

object UsersResourcesBinder extends ResourcesBinder[UsersResourcesImpl]{
  val bind = (self: Twitter) => new Twitter(self.twitter4jObj) with UsersResourcesImpl
}

object SuggestedUsersResourcesBinder extends ResourcesBinder[SuggestedUsersResources]{
  val bind = (self: Twitter) => new Twitter(self.twitter4jObj) with SuggestedUsersResourcesImpl
}

object FavoritesResourcesBinder extends ResourcesBinder[FavoritesResources]{
  val bind = (self: Twitter) => new Twitter(self.twitter4jObj) with FavoritesResourcesImpl
}

object ListsResourcesBinder extends ResourcesBinder[ListsResources] {
  val bind = (self: Twitter) => new Twitter(self.twitter4jObj) with ListsResourcesImpl
}

object SavedSearchesResourcesBinder extends ResourcesBinder[SavedSearchesResources]{
  val bind = (self: Twitter) => new Twitter(self.twitter4jObj) with SavedSearchesResourcesImpl
}

object PlacesGeoResourcesBinder extends ResourcesBinder[PlaceGeoResources]{
  val bind = (self: Twitter) => new Twitter(self.twitter4jObj) with PlaceGeoResourcesImpl
}

object TrendsResourcesBinder extends ResourcesBinder[TrendsResources]{
  val bind = (self: Twitter) => new Twitter(self.twitter4jObj) with TrendsResourcesImpl
}

object SpamReportingResourcesBinder extends ResourcesBinder[SpamReportingResources]{
  val bind = (self: Twitter) => new Twitter(self.twitter4jObj) with SpamReportingResourcesImpl
}

object HelpResourcesBinder extends ResourcesBinder[HelpResources]{
  val bind = (self: Twitter) => new Twitter(self.twitter4jObj) with HelpResourcesImpl
}
