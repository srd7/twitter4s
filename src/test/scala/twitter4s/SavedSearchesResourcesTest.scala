package twitter4s

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import api.impl.SavedSearchesResourcesImpl
import twitter4s.mocked.FakeValuesUsedByMock

@RunWith(classOf[JUnitRunner])
class SavedSearchesResourcesTest extends Specification with TwitterResourcesTestBase {

  type TargetResourcesType = SavedSearchesResourcesImpl

  mockedTwitter4j.getSavedSearches returns FakeValuesUsedByMock.responseList[twitter4j.SavedSearch]
  mockedTwitter4j.createSavedSearch(anyString) returns FakeValuesUsedByMock.savedSearch
  mockedTwitter4j.showSavedSearch(anyInt) returns FakeValuesUsedByMock.savedSearch
  mockedTwitter4j.destroySavedSearch(anyInt) returns FakeValuesUsedByMock.savedSearch

  val twitter = new Twitter(mockedTwitter4j) with TargetResourcesType

  "getSavedSearches" should {
    "call twitter4j getSavedSearches method" in {
      twitter.savedSearches.getSavedSearches.size must equalTo(1)
      there was one(mockedTwitter4j).getSavedSearches
    }
  }
  
  "createSavedSearch" should {
    "call twitter4j createSavedSearch method" in {
      twitter.savedSearches.createSavedSearch("search string").query must equalTo(FakeValuesUsedByMock.searchQueryString)
      there was one(mockedTwitter4j).createSavedSearch("search string")
    }
  }

  "showSavedSearch" should {
    "call twitter4j showSavedSearch method" in {
      twitter.savedSearches.showSavedSearch(1).query must equalTo(FakeValuesUsedByMock.searchQueryString)
      there was one(mockedTwitter4j).showSavedSearch(1)
    }
  }

  "destroySavedSearch" should {
    "call twitter4j destroySavedSearch method" in {
      twitter.savedSearches.destroySavedSearch(2).query must equalTo(FakeValuesUsedByMock.searchQueryString)
      there was one(mockedTwitter4j).destroySavedSearch(2)
    }
  }
}