package twitter4s

import twitter4s._
import java.io.File
import java.io.FileInputStream
import java.util.Date
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import Twitter4sTestHelper.rawJSON
import Twitter4sTestHelper.twitter1
import Twitter4sTestHelper.twitter2
import Twitter4sTestHelper.twitter3
import twitter4j.json.DataObjectFactory
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class AccountMethodsTest extends Specification {

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
      testProfileColors(updatedUser, "f00", "f0f", "0ff", "0f0", "f0f")
      updatedUser.isProfileUseBackgroundImage must beTrue
      updatedUser.isShowAllInlineMedia must beFalse
      updatedUser.listedCount must be_<=(0)
    }
    
    "change colors on user status page with six characters" in {
      val updatedUser = twitter3.updateProfileColors("87bc44", "9ae4e8", "000000", "0000ff", "e0ff92")
      rawJSON(updatedUser.tw4jObj) must not equalTo(null)
      updatedUser.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(updatedUser.tw4jObj)))
      testProfileColors(updatedUser, "87bc44", "9ae4e8", "000000", "0000ff", "e0ff92")
    }
  }
  
  "getAccountTotals" should {
    "get user's (favorite, followers, friends, udpates) total number" in {
      val totals = twitter1.getAccountTotals
      totals.favorites must be_>(0)
      totals.followers must be_>(0)
      totals.friends must be_>(0)
      totals.updates must be_>(0)
      totals.tw4jObj must equalTo(DataObjectFactory.createAccountTotals(rawJSON(totals.tw4jObj)))
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
      val user = twitter2.updateProfileImage(imageStream = new FileInputStream(getRandomlyChosenFile))
      rawJSON(user.tw4jObj) must not equalTo(null)
    }
    
    "throws IllegalArgumentException with no image file and stream" in {
       twitter2.updateProfileImage() must throwA[IllegalArgumentException]
    }
  }
  
  "updateProfileBackgroundImage" should {
    "change background image in authorized user page" in {
      val user = twitter2.updateProfileBackgroundImage(
          imageFile = getRandomlyChosenFile,
          tile = (5 < System.currentTimeMillis() % 5))
      rawJSON(user.tw4jObj) must not equalTo(null)
      user.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(user.tw4jObj)))
    }
    
    "throws IllegalArgumentException with no image file and stream" in {
      twitter2.updateProfileBackgroundImage(tile = true) must throwA[IllegalArgumentException]
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
}