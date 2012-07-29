package twitter4s

import twitter4s._
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4j.json.DataObjectFactory
import twitter4j.ProfileImage
import Twitter4sTestHelper._
import internal.json.ResponseListImpl

@RunWith(classOf[JUnitRunner])
class UserMethodsTest extends Specification {

  "showUser" should {
    "get specified user profile with screenName" in {
      val user = twitter1.showUser(id1.screenName)
      user.screenName must equalTo(id1.screenName)
      user.location must not equalTo(null)
      user.description must not equalTo(null)
      user.profileImageURL must not equalTo(null)
      user.url must not equalTo(null)
      user.isProtected must beFalse
      
      user.favouritesCount must be_>=(0)
      user.followersCount must be_>=(0)
      user.friendsCount must be_>=(0)
      
      user.createdAt must not equalTo(null)
      user.timeZone must not equalTo(null)
      user.profileBackgroundImageUrl must not equalTo(null)
      
      user.statusesCount must be_>=(0)
      user.profileBackgroundColor must not equalTo(null)
      user.profileTextColor must not equalTo(null)
      user.profileLinkColor must not equalTo(null)
      user.profileSidebarBorderColor must not equalTo(null)
      user.profileSidebarFillColor must not equalTo(null)
      
      if (user.status != null) {
        user.status.createdAt must not equalTo(null)
        user.status.text must not equalTo(null)
        user.status.source must not equalTo(null)
        user.status.isFavorited must beFalse
        user.status.inReplyToUserId must equalTo(-1)
        user.status.inReplyToStatusId must equalTo(-1)
        user.status.inReplyToScreenName must equalTo(null)
      }
      user.listedCount must be_>=(0)
      user.isFollowRequestSent must beFalse
    }
    
    "get specified user with no status" in {
      val user = twitter1.showUser("tigertest")
      rawJSON(user.tw4jObj) must not equalTo(null)
      
      val nextUser = twitter1.showUser(numberId)
      nextUser.id must equalTo(numberIdId)
      rawJSON(user.tw4jObj) must equalTo(null)
      rawJSON(nextUser.tw4jObj) must not equalTo(null)
      nextUser.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(nextUser.tw4jObj)))
      
      val thirdUser = twitter1.showUser(userId = numberIdId)
      thirdUser.id must equalTo(numberIdId)
      rawJSON(thirdUser.tw4jObj) must not equalTo(null)
      thirdUser.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(thirdUser.tw4jObj)))
    }
    
    "throw IllegalArgumentException are not set both parameters" in {
      twitter1.showUser() must throwA[IllegalArgumentException]
    }
    
    "is taken priority parameter userId" in {
      val user = twitter1.showUser(screenName = "tigertest", userId = id1.id)
      user.id must equalTo(id1.id)
      user.screenName must not equalTo("tigertest")
    }
  }
  
  "lookupUsers" should {
    "get lookup user list specified screen names" in {
      val users = twitter1.lookupUsers(Array(id1.screenName, id2.screenName))
      users.size must equalTo(2)
      users.exists(_.getId() == id1.id) must beTrue
      users.exists(_.getId() == id2.id) must beTrue
    }
    
    "get lookup user list specified user ids" in {
      val users = twitter1.lookupUsers(ids = Array(id1.id, id2.id))
      users.size must equalTo(2)
      users.exists(_.getId() == id1.id) must beTrue
      users.exists(_.getId() == id2.id) must beTrue
      rawJSON(users(0)) must not equalTo(null)
      users(0) must equalTo(DataObjectFactory.createUser(rawJSON(users(0))))
      rawJSON(users.tw4jObj) must not equalTo(null)
    }
    
    "throw IllegalArgumentException are not set both parameter" in {
      twitter1.lookupUsers() must throwA[IllegalArgumentException]
    }
    
    "is taken priority parameter ids" in {
      val users = twitter1.lookupUsers(
          screenNames = Array(id2.screenName, id3.screenName),
          ids = Array(id1.id, id2.id))
      users.size must equalTo(2)
      users.exists(_.getId() == id1.id) must beTrue
      users.exists(_.getId() == id2.id) must beTrue
      users.exists(_.getId() == id3.id) must beFalse
    }
  }
  
  "searchUser" should {
    "get matched user list specified search text" in {
      val users = twitter1.searchUsers("Doug Williams", 1)
      users.size must be_>=(4)
      users.featureSpecificRateLimitStatus must not equalTo(null)
      rawJSON(users(0)) must not equalTo(null)
      users(0) must equalTo(DataObjectFactory.createUser(rawJSON(users(0))))
      rawJSON(users.tw4jObj) must not equalTo(null)
    }
  }
  
  "getSuggestedUserCategories" should {
    "get suggest category list" in {
      val categories = twitter1.getSuggestedUserCategories
      categories.size must be_>(0)
      rawJSON(categories.tw4jObj) must not equalTo(null)
      categories(0) must equalTo(DataObjectFactory.createCategory(rawJSON(categories(0))))
    }
  }
  
  "getUserSuggestions" should {
    "get suggest user list" in {
      val categories = twitter1.getSuggestedUserCategories
      val users = twitter1.getUserSuggestions(categories(0).getSlug())
      users.size must be_>=(0)
      users(0).getStatus() must equalTo(null)
      rawJSON(users.tw4jObj) must not equalTo(null)
      rawJSON(users(0)) must not equalTo(null)
      users(0) must equalTo(DataObjectFactory.createUser(rawJSON(users(0))))
    }
  }
  
  "getMemberSuggestions" should {
    "get suggerst members list" in {
      val categories = twitter1.getSuggestedUserCategories
      val users = twitter1.getMemberSuggestions(categories(0).getSlug())
      users.size must be_>=(0)
      users(0).getStatus() must not equalTo(null)
      rawJSON(users.tw4jObj) must not equalTo(null)
      rawJSON(users(0)) must not equalTo(null)
      users(0) must equalTo(DataObjectFactory.createUser(rawJSON(users(0))))
    }
  }
  
  "getProfileImage" should {
    "get image url" in {
      val image = twitter1.getProfileImage(id1.screenName, ProfileImage.BIGGER)
      image.getURL must not equalTo(null)
    }
  }
}