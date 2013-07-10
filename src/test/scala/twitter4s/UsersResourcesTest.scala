package twitter4s

import twitter4s._
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4j.json.DataObjectFactory
import Twitter4sTestHelper._
import internal.json.ResponseListImpl
import java.io.FileInputStream
import java.util.Date
import java.io.File

@RunWith(classOf[JUnitRunner])
class UsersResourcesTest extends Specification {

  "showUser" should {
    "get specified user profile with screenName" in {
      val user = twitter1.showUser(
          User.isSpecifiedBy(id1.screenName))
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
//        user.status.inReplyToUserId must equalTo(-1) // TODO リプライされているっぽい
        user.status.inReplyToStatusId must equalTo(-1)
//        user.status.inReplyToScreenName must equalTo(null) // TODO リプライしてる。。。
      }
      user.listedCount must be_>=(0)
      user.isFollowRequestSent must beFalse
    }
    
    "get specified user with no status" in {
      val user = twitter1.showUser(
          User.isSpecifiedBy("tigertest"))
      rawJSON(user.tw4jObj) must not equalTo(null)
      
      val nextUser = twitter1.showUser(
          User.isSpecifiedBy(numberId))
      nextUser.id must equalTo(numberIdId)
      rawJSON(user.tw4jObj) must equalTo(null)
      rawJSON(nextUser.tw4jObj) must not equalTo(null)
      nextUser.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(nextUser.tw4jObj)))
      
      val thirdUser = twitter1.showUser(
          User.isSpecifiedBy(numberIdId))
      thirdUser.id must equalTo(numberIdId)
      rawJSON(thirdUser.tw4jObj) must not equalTo(null)
      thirdUser.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(thirdUser.tw4jObj)))
    }
    
    "throw IllegalArgumentException are not set both parameters" in {
      twitter1.showUser(null) must throwA[IllegalArgumentException]
    }
  }
  
  "lookupUsers" should {
    "get lookup user list specified screen names" in {
      val users = twitter1.lookupUsers(Users.areSpecifiedBy(Array(id1.screenName, id2.screenName)))
      users.size must equalTo(2)
      users.exists(_.getId() == id1.id) must beTrue
      users.exists(_.getId() == id2.id) must beTrue
    }
    
    "get lookup user list specified user ids" in {
      val users = twitter1.lookupUsers(Users.areSpecifiedBy(Array(id1.id, id2.id)))
      users.size must equalTo(2)
      users.exists(_.getId() == id1.id) must beTrue
      users.exists(_.getId() == id2.id) must beTrue
      rawJSON(users(0)) must not equalTo(null)
      users(0) must equalTo(DataObjectFactory.createUser(rawJSON(users(0))))
      rawJSON(users.tw4jObj) must not equalTo(null)
    }
    
    "throw IllegalArgumentException when specificUsers is null" in {
      twitter1.lookupUsers(null) must throwA[IllegalArgumentException]
    }
  }
  
  "searchUser" should {
    "get matched user list specified search text" in {
      val users = twitter1.searchUsers("Doug Williams", 1)
      users.size must be_>=(4)
//      users.featureSpecificRateLimitStatus must not equalTo(null)
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
  

  "verifyCredentials" should {
    "get authorized user's credentials information" in {
      val original = twitter1.verifyCredentials
      rawJSON(original.tw4jObj) must not equalTo(null)
      original.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(original.tw4jObj)))
    }
  }
  
  "updateProfile" should {
    "update user profile(name, url, location, description)" in {
      // make precondition
      val original = twitter3.updateProfile(
          "twt4s_id3",
          "https://github.com/Shinsuke-Abe/twitter4s",
          ":Location",
          "Hi there, I do test a lot!new")
      
      val newName = original.name + "new"
      val newURL = original.url + "new"
      val newLocation = new Date().toString()
      val newDescription = original.description + "new"
      
      // test
      val altered = twitter3.updateProfile(newName, newURL, newLocation, newDescription)
      rawJSON(altered.tw4jObj) must not equalTo(null)
      original.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(original.tw4jObj)))
      altered.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(altered.tw4jObj)))
      altered.name must equalTo(newName)
      altered.url.toString() must equalTo(newURL)
      altered.location must equalTo(newLocation)
      altered.description must equalTo(newDescription)
    }
  }
  
  "updateProfileColors" should {
    def testProfileColors(
        target: User,
        profileBackgroundColor: String,
        profileTextColor: String,
        profileLinkColor: String,
        profileSidebarFillColor: String,
        profileSidebarBorderColor: String) = {
      target.profileBackgroundColor must equalTo(profileBackgroundColor)
      target.profileTextColor must equalTo(profileTextColor)
      target.profileLinkColor must equalTo(profileLinkColor)
      target.profileSidebarFillColor must equalTo(profileSidebarFillColor)
      target.profileSidebarBorderColor must equalTo(profileSidebarBorderColor)
    }
    
    "change colors on user status page with three characters" in {
      val updatedUser = twitter3.updateProfileColors("f00", "f0f", "0ff", "0f0", "f0f")
      rawJSON(updatedUser.tw4jObj) must not equalTo(null)
      updatedUser.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(updatedUser.tw4jObj)))
      testProfileColors(updatedUser, "FF0000", "FF00FF", "00FFFF", "00FF00", "FF00FF")
      updatedUser.isProfileUseBackgroundImage must beTrue
      updatedUser.isShowAllInlineMedia must beFalse
      updatedUser.listedCount must be_<=(0)
    }
    
    "change colors on user status page with six characters" in {
      val updatedUser = twitter3.updateProfileColors("87bc44", "9ae4e8", "000000", "0000ff", "e0ff92")
      rawJSON(updatedUser.tw4jObj) must not equalTo(null)
      updatedUser.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(updatedUser.tw4jObj)))
      testProfileColors(updatedUser, "87BC44", "9AE4E8", "000000", "0000FF", "E0FF92")
    }
  }
  
  "getAccountSettings" should {
    "get authorized user's account settings" in {
      val settings = twitter1.getAccountSettings
      settings.isSleepTimeEnabled must beTrue // this setting's default is false
      settings.isGeoEnabled must beFalse // this setting's default is false
      settings.language must equalTo("ja")
      settings.timeZone.getName() must equalTo("Osaka")
      settings.isAlwaysUseHttps must beTrue
      settings.isDiscoverableByEmail must beTrue
      settings.trendLocations.length must be_>(0)
    }
  }
  
  "updateAccountSettings" should {
    "change authorized user's account settings" in {
      val intermSetting = twitter3.updateAccountSettings(1, true, "23", "08", "Helsinki", "it")
      rawJSON(intermSetting.tw4jObj) must not equalTo(null)
      intermSetting.sleepStartTime must equalTo("23")
      intermSetting.sleepEndTime must equalTo("8")
      intermSetting.isGeoEnabled must beTrue // is default false
      intermSetting.language must equalTo("it")
      intermSetting.isAlwaysUseHttps must beTrue
      intermSetting.isDiscoverableByEmail must beTrue // is default
      intermSetting.timeZone.getName() must equalTo("Helsinki")
      intermSetting.trendLocations.length must be_>(0)
    }
  }
  
  "updateProfileImage" should {
    "change profile image" in {
      val user = twitter2.updateProfileImage(
          ImageResource.isAssigned(new FileInputStream(getRandomlyChosenFile)))
      rawJSON(user.tw4jObj) must not equalTo(null)
    }
    
    "throws IllegalArgumentException with no image file and stream" in {
       twitter2.updateProfileImage(null) must throwA[IllegalArgumentException]
    }
  }
  
  "updateProfileBackgroundImage" should {
    "change background image in authorized user page" in {
      val user = twitter2.updateProfileBackgroundImage(
          ImageResource.isAssigned(getRandomlyChosenFile),
          (5 < System.currentTimeMillis() % 5))
      rawJSON(user.tw4jObj) must not equalTo(null)
      user.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(user.tw4jObj)))
    }
    
    "throws IllegalArgumentException with no image file and stream" in {
      twitter2.updateProfileBackgroundImage(null, true) must throwA[IllegalArgumentException]
    }
  }
  
  val imageFiles = Array(
      "src/test/resources/t4j-reverse.jpeg",
      "src/test/resources/t4j-reverse.png",
      "src/test/resources/t4j-reverse.gif",
      "src/test/resources/t4j.jpeg",
      "src/test/resources/t4j.png",
      "src/test/resources/t4j.gif"
      )
  
  def getRandomlyChosenFile = {
    new File(imageFiles((System.currentTimeMillis() % 6).toInt))
  }
  
  private def testBlockingUsers(target: ResponseList[twitter4j.User]) = {
    rawJSON(target.tw4jObj) must not equalTo(null)
    target(0) must equalTo(DataObjectFactory.createUser(rawJSON(target(0))))
    target.size must equalTo(blockingUsersSize)
    target(0).id must equalTo(blockingUserId)
  }
  
  "createBlock" should {
    "create block and get blocked user" in {
      val user = twitter2.createBlock(
          User.isSpecifiedBy(id3.id))
      rawJSON(user.tw4jObj) must not equalTo(null)
      user.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(user.tw4jObj)))
    }
    
    "throw exception when user specific info is set null" in {
      twitter2.createBlock(null) must
      throwA[IllegalArgumentException]
    }
  }
  
  "destryoBlock" should {
    "destroy block and get unblocked user" in {
      val user = twitter2.destroyBlock(
          User.isSpecifiedBy(id3.screenName))
      rawJSON(user.tw4jObj) must not equalTo(null)
      user.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(user.tw4jObj)))
      
      // remove existsBlock method since Twitter4J 3.0.0
//      // check destroyed block
//      twitter3.existsBlock(
//          User.isSpecifiedBy(id2.id)) must beFalse
//      // check blocked user
//      val blockedUserScreenName = blockingScreenName
//      twitter1.existsBlock(
//          User.isSpecifiedBy(blockedUserScreenName)) must beTrue
    }
    
    "throw exception when user specific info is set null" in {
      twitter2.destroyBlock(null) must
      throwA[IllegalArgumentException] 
    }
  }
  
  "getBlockingUsers" should {
    "get all user list blocking by authorized user" in {
      testBlockingUsers(twitter1.getBlocksList())
    }
    
    // TODO 位置づけが変わった
//    "get user list specified page blocking by authorized user" in {
//      testBlockingUsers(twitter1.getBlocksList(1))
//    }
  }
  
  "getBlockingUsersIDs" should {
    "get user id list blocking by authorized user" in {
      val ids = twitter1.getBlocksIDs()
      rawJSON(ids.tw4jObj) must not equalTo(null)
      ids.ids.size must equalTo(blockingUsersSize)
      ids.ids(0) must equalTo(blockingUserId)
    }
  }
}