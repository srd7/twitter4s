package twitter4s

import twitter4s._
import org.junit.runner.RunWith
import Twitter4sTestHelper._
import org.specs2.runner.JUnitRunner
import org.specs2.mutable.Specification
import twitter4j.json.DataObjectFactory
import twitter4s.api.impl.SuggestedUsersResourcesImpl

@RunWith(classOf[JUnitRunner])
class SuggestedUsersResources extends Specification {
  val twitterSuggestedUsersResourceRole =  new Twitter(twitter4jInstance(User1)) with SuggestedUsersResourcesImpl
  
  "getSuggestedUserCategories" should {
    "get suggest category list" in {
      val categories = twitterSuggestedUsersResourceRole.getSuggestedUserCategories
      categories.size must be_>(0)
      rawJSON(categories.tw4jObj) must not equalTo(null)
      categories(0) must equalTo(DataObjectFactory.createCategory(rawJSON(categories(0))))
    }
  }
  
  "getUserSuggestions" should {
    "get suggest user list" in {
      val categories = twitterSuggestedUsersResourceRole.getSuggestedUserCategories
      val users = twitterSuggestedUsersResourceRole.getUserSuggestions(categories(0).getSlug())
      users.size must be_>=(0)
      users(0).getStatus() must equalTo(null)
      rawJSON(users.tw4jObj) must not equalTo(null)
      rawJSON(users(0)) must not equalTo(null)
      users(0) must equalTo(DataObjectFactory.createUser(rawJSON(users(0))))
    }
  }
  
  "getMemberSuggestions" should {
    "get suggerst members list" in {
      val categories = twitterSuggestedUsersResourceRole.getSuggestedUserCategories
      val users = twitterSuggestedUsersResourceRole.getMemberSuggestions(categories(0).getSlug())
      users.size must be_>=(0)
      users(0).getStatus() must not equalTo(null)
      rawJSON(users.tw4jObj) must not equalTo(null)
      rawJSON(users(0)) must not equalTo(null)
      users(0) must equalTo(DataObjectFactory.createUser(rawJSON(users(0))))
    }
  }
}