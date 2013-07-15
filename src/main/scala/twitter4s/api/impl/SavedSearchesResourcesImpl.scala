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
import twitter4s.ResponseList
import twitter4s.api.SavedSearchesResources
import twitter4s.Twitter
import twitter4s.SavedSearch

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 * @since Twitter4S 2.0.0
 */
trait SavedSearchesResourcesImpl extends SavedSearchesResources {
  self: Twitter =>
  
  /**
   * {@inheritDoc}
   */
  def getSavedSearches: ResponseList[twitter4j.SavedSearch] = {
    twitter4jObj.getSavedSearches()
  }

  /**
   * {@inheritDoc}
   */
  def showSavedSearch(id: Int): SavedSearch = {
    twitter4jObj.showSavedSearch(id)
  }

  /**
   * {@inheritDoc}
   */
  def createSavedSearch(query: String): SavedSearch = {
    twitter4jObj.createSavedSearch(query)
  }

  /**
   * {@inheritDoc}
   */
  def destroySavedSearch(id: Int): SavedSearch = {
    twitter4jObj.destroySavedSearch(id)
  }
}