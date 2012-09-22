package twitter4s.internal.json
/*
 * Copyright (C) 2012 Shinsuke Abe
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
import twitter4s.SavedSearch
import java.util.Date
import twitter4s.AccessLevel

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class SavedSearchImpl(twt4jSavedSearch: twitter4j.SavedSearch) extends SavedSearch {
  type Tw4jResponse = twitter4j.SavedSearch

  def createdAt = twt4jSavedSearch.getCreatedAt()

  def query = twt4jSavedSearch.getQuery()

  def position = twt4jSavedSearch.getPosition()

  def name = twt4jSavedSearch.getName()

  def id = twt4jSavedSearch.getId()

  def compare(that: SavedSearch) = this.id - that.id
  
  def rateLimitStatus = RateLimitStatusImpl(twt4jSavedSearch.getRateLimitStatus())
  
  def accessLevel = AccessLevel(twt4jSavedSearch.getAccessLevel())
  
  def tw4jObj = twt4jSavedSearch
}