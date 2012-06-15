package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4s.Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import twitter4j.TwitterException

@RunWith(classOf[JUnitRunner])
class SavedSearchesMethodsTest extends Specification {

  "getSavedSearches" should {
    "get saved search list" in {
      val list = twitter3.getSavedSearches
      rawJSON(list.twt4jResponseList) must not equalTo(null)
    }
  }
  
  "createSavedSearch" should {
    "add seach string to saved searches list" in {
      // clear saved search list for test
      twitter3.getSavedSearches().foreach(search => twitter3.destroySavedSearch(search.getId()))
      
      val queryStr = "my search"
      
      val ss1 = twitter3.createSavedSearch(queryStr)
      rawJSON(ss1) must not equalTo(null)
      ss1 must equalTo(DataObjectFactory.createSavedSearch(rawJSON(ss1)))
      ss1.getQuery() must equalTo(queryStr)
      ss1.getPosition() must equalTo(-1)
      
      // check added seach
      val list = twitter3.getSavedSearches
      rawJSON(list.twt4jResponseList) must not equalTo(null)
      list(0) must equalTo(DataObjectFactory.createSavedSearch(rawJSON(list(0))))
      
      // check destroy added search
      try {
	    val ss2 = twitter3.destroySavedSearch(ss1.getId())
	    ss2 must equalTo(ss1)
      } catch {
        case te: TwitterException => te.getStatusCode must beOneOf(404, 500)
      }
    }
  }
}