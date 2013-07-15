package twitter4s

import twitter4s._
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import twitter4j.TwitterException
import api.impl.SavedSearchesResourcesImpl

@RunWith(classOf[JUnitRunner])
class SavedSearchesResourcesTest extends Specification {
  val twitterSavedSearchesRole = new Twitter(twitter4jInstance(User3)) with SavedSearchesResourcesImpl

  "getSavedSearches" should {
    "get saved search list" in {
      val list = twitterSavedSearchesRole.getSavedSearches
      rawJSON(list.tw4jObj) must not equalTo(null)
    }
  }
  
  "createSavedSearch" should {
    "add seach string to saved searches list" in {
      // clear saved search list for test
      twitterSavedSearchesRole.getSavedSearches.foreach(search => twitterSavedSearchesRole.destroySavedSearch(search.getId()))
      
      val queryStr = "my search"
      
      val ss1 = twitterSavedSearchesRole.createSavedSearch(queryStr)
      rawJSON(ss1.tw4jObj) must not equalTo(null)
      ss1.tw4jObj must equalTo(DataObjectFactory.createSavedSearch(rawJSON(ss1.tw4jObj)))
      ss1.query must equalTo(queryStr)
      ss1.position must equalTo(-1)
      
      // check added seach
      val list = twitterSavedSearchesRole.getSavedSearches
      rawJSON(list.tw4jObj) must not equalTo(null)
      list(0) must equalTo(DataObjectFactory.createSavedSearch(rawJSON(list(0))))
      
      // check destroy added search
      try {
	    val ss2 = twitterSavedSearchesRole.destroySavedSearch(ss1.id)
	    ss2 must equalTo(ss1)
      } catch {
        case te: TwitterException => te.getStatusCode must beOneOf(404, 500)
      }
    }
  }
}