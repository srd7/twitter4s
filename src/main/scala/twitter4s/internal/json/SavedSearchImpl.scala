package twitter4s.internal.json

import twitter4s.SavedSearch
import java.util.Date

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
  
  def accessLevel = twt4jSavedSearch.getAccessLevel()
  
  def tw4jObj = twt4jSavedSearch
}