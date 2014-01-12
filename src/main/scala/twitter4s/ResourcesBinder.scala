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
  def apply(self: Twitter): ApiResourcesInterface = self match {
    case resource: ApiResourcesInterface => resource
    case _ => bind(self)
  }

  def bind(self: Twitter): ApiResourcesInterface
}

object ListsResourcesBinder extends ResourcesBinder[ListsResources] {
  def bind(self: Twitter): ListsResources = new Twitter(self.twitter4jObj) with ListsResourcesImpl
}

object SavedSearchesBinder extends ResourcesBinder[SavedSearchesResources]{
  def bind(self: Twitter): SavedSearchesResources = new Twitter(self.twitter4jObj) with SavedSearchesResourcesImpl
}

object PlacesGeoResourcesBinder extends ResourcesBinder[PlaceGeoResources]{
  def bind(self: Twitter): PlaceGeoResources = new Twitter(self.twitter4jObj) with PlaceGeoResourcesImpl
}

object TrendsResourcesBinder extends ResourcesBinder[TrendsResources]{
  def bind(self: Twitter): TrendsResources = new Twitter(self.twitter4jObj) with TrendsResourcesImpl
}

object SpamReportingResourcesBinder extends ResourcesBinder[SpamReportingResources]{
  def bind(self: Twitter): SpamReportingResources = new Twitter(self.twitter4jObj) with SpamReportingResourcesImpl
}

object HelpResourcesBinder extends ResourcesBinder[HelpResources]{
  def bind(self: Twitter): HelpResources = new Twitter(self.twitter4jObj) with HelpResourcesImpl
}