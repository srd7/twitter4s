package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4s.Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import twitter4s.implicits.Twitter4SImplicits._
import twitter4j.ProfileImage

@RunWith(classOf[JUnitRunner])
class UserMethodsTest extends Specification {

  "showUser" should {
    "get specified user profile with screenName" in {
      val user = twitter1.showUser(Some(id1.screenName))
      user.getScreenName must equalTo(id1.screenName)
      user.getLocation must not equalTo(null)
      user.getDescription() must not equalTo(null)
      user.getProfileImageURL() must not equalTo(null)
      user.getURL() must not equalTo(null)
      user.isProtected() must beFalse
      
      user.getFavouritesCount() must be_>=(0)
      user.getFollowersCount() must be_>=(0)
      user.getFriendsCount() must be_>=(0)
      
      user.getCreatedAt() must not equalTo(null)
      user.getTimeZone() must not equalTo(null)
      user.getProfileBackgroundImageUrl() must not equalTo(null)
      
      user.getStatusesCount() must be_>=(0)
      user.getProfileBackgroundColor() must not equalTo(null)
      user.getProfileTextColor() must not equalTo(null)
      user.getProfileLinkColor() must not equalTo(null)
      user.getProfileSidebarBorderColor() must not equalTo(null)
      user.getProfileSidebarFillColor() must not equalTo(null)
      
      if (user.getStatus() != null) {
        user.getStatus().getCreatedAt() must not equalTo(null)
        user.getStatus().getText() must not equalTo(null)
        user.getStatus().getSource() must not equalTo(null)
        user.getStatus().isFavorited() must beFalse
        user.getStatus().getInReplyToUserId() must equalTo(-1)
        user.getStatus().getInReplyToStatusId() must equalTo(-1)
        user.getStatus().getInReplyToScreenName() must equalTo(null)
      }
      user.getListedCount() must be_>=(0)
      user.isFollowRequestSent() must beFalse
    }
    
    "get specified user with no status" in {
      val user = twitter1.showUser(Some("tigertest"))
      rawJSON(user) must not equalTo(null)
      
      val nextUser = twitter1.showUser(Some(numberId))
      nextUser.getId() must equalTo(numberIdId)
      rawJSON(user) must equalTo(null)
      rawJSON(nextUser) must not equalTo(null)
      nextUser must equalTo(DataObjectFactory.createUser(rawJSON(nextUser)))
      
      val thirdUser = twitter1.showUser(userId = Some(numberIdId))
      thirdUser.getId() must equalTo(numberIdId)
      rawJSON(thirdUser) must not equalTo(null)
      thirdUser must equalTo(DataObjectFactory.createUser(rawJSON(thirdUser)))
    }
  }
  
  "lookupUsers" should {
    "get lookup user list specified screen names" in {
      val users = twitter1.lookupUsers(Some(Array(id1.screenName, id2.screenName)))
      users.size must equalTo(2)
      users.exists(_.getId() == id1.id) must beTrue
      users.exists(_.getId() == id2.id) must beTrue
    }
    
    "get lookup user list specified user ids" in {
      val users = twitter1.lookupUsers(ids = Some(Array(id1.id, id2.id)))
      users.size must equalTo(2)
      users.exists(_.getId() == id1.id) must beTrue
      users.exists(_.getId() == id2.id) must beTrue
      rawJSON(users(0)) must not equalTo(null)
      users(0) must equalTo(DataObjectFactory.createUser(rawJSON(users(0))))
      rawJSON(users.twt4jResponseList) must not equalTo(null)
    }
  }
  
  "searchUser" should {
    "get matched user list specified search text" in {
      val users = twitter1.searchUsers("Doug Williams", 1)
      users.size must be_>=(4)
      users.featureSpecificRateLimitStatus must not equalTo(null)
      rawJSON(users(0)) must not equalTo(null)
      users(0) must equalTo(DataObjectFactory.createUser(rawJSON(users(0))))
      rawJSON(users.twt4jResponseList) must not equalTo(null)
    }
  }
  
  "getSuggestedUserCategories" should {
    "get suggest category list" in {
      val categories = twitter1.getSuggestedUserCategories
      categories.size must be_>(0)
      rawJSON(categories.twt4jResponseList) must not equalTo(null)
      categories(0) must equalTo(DataObjectFactory.createCategory(rawJSON(categories(0))))
    }
  }
  
  "getUserSuggestions" should {
    "get suggest user list" in {
      val categories = twitter1.getSuggestedUserCategories
      val users = twitter1.getUserSuggestions(categories(0).getSlug())
      users.size must be_>=(0)
      users(0).getStatus() must equalTo(null)
      rawJSON(users.twt4jResponseList) must not equalTo(null)
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
      rawJSON(users.twt4jResponseList) must not equalTo(null)
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