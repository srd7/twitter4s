package twitter4s
import java.io.File
import java.io.FileInputStream
import java.util.Date

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import twitter4j.json.DataObjectFactory
import twitter4j.User
import Twitter4sTestHelper._

@RunWith(classOf[JUnitRunner])
class AccountMethodsTest extends Specification {

  "verifyCredentials" should {
    "get authorized user's credentials information" in {
      val original = twitter1.verifyCredentials
      rawJSON(original) must not equalTo(null)
      original must equalTo(DataObjectFactory.createUser(rawJSON(original)))
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
      
      val newName = original.getName + "new"
      val newURL = original.getURL() + "new"
      val newLocation = new Date().toString()
      val newDescription = original.getDescription() + "new"
      
      // test
      val altered = twitter3.updateProfile(newName, newURL, newLocation, newDescription)
      rawJSON(altered) must not equalTo(null)
      original must equalTo(DataObjectFactory.createUser(rawJSON(original)))
      altered must equalTo(DataObjectFactory.createUser(rawJSON(altered)))
      altered.getName() must equalTo(newName)
      altered.getURL().toString() must equalTo(newURL)
      altered.getLocation() must equalTo(newLocation)
      altered.getDescription() must equalTo(newDescription)
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
      target.getProfileBackgroundColor() must equalTo(profileBackgroundColor)
      target.getProfileTextColor() must equalTo(profileTextColor)
      target.getProfileLinkColor() must equalTo(profileLinkColor)
      target.getProfileSidebarFillColor() must equalTo(profileSidebarFillColor)
      target.getProfileSidebarBorderColor() must equalTo(profileSidebarBorderColor)
    }
    
    "change colors on user status page with three characters" in {
      val updatedUser = twitter3.updateProfileColors("f00", "f0f", "0ff", "0f0", "f0f")
      rawJSON(updatedUser) must not equalTo(null)
      updatedUser must equalTo(DataObjectFactory.createUser(rawJSON(updatedUser)))
      testProfileColors(updatedUser, "f00", "f0f", "0ff", "0f0", "f0f")
      updatedUser.isProfileUseBackgroundImage() must beTrue
      updatedUser.isShowAllInlineMedia() must beFalse
      updatedUser.getListedCount() must be_<=(0)
    }
    
    "change colors on user status page with six characters" in {
      val updatedUser = twitter3.updateProfileColors("87bc44", "9ae4e8", "000000", "0000ff", "e0ff92")
      rawJSON(updatedUser) must not equalTo(null)
      updatedUser must equalTo(DataObjectFactory.createUser(rawJSON(updatedUser)))
      testProfileColors(updatedUser, "87bc44", "9ae4e8", "000000", "0000ff", "e0ff92")
    }
  }
  
  "getAccountTotals" should {
    "get user's (favorite, followers, friends, udpates) total number" in {
      val totals = twitter1.getAccountTotals
      totals.getFavorites must be_>(0)
      totals.getFollowers() must be_>(0)
      totals.getFriends() must be_>(0)
      totals.getUpdates() must be_>(0)
      totals must equalTo(DataObjectFactory.createAccountTotals(rawJSON(totals)))
    }
  }
  
  "getAccountSettings" should {
    "get authorized user's account settings" in {
      val settings = twitter1.getAccountSettings
      settings.isSleepTimeEnabled must beTrue // this setting's default is false
      settings.isGeoEnabled() must beFalse // this setting's default is false
      settings.getLanguage() must equalTo("ja")
      settings.getTimeZone().getName() must equalTo("Osaka")
      settings.isAlwaysUseHttps() must beTrue
      settings.isDiscoverableByEmail() must beTrue
      settings.getTrendLocations().length must be_>(0)
    }
  }
  
  "updateAccountSettings" should {
    "change authorized user's account settings" in {
      val intermSetting = twitter3.updateAccountSettings(1, true, "23", "08", "Helsinki", "it")
      rawJSON(intermSetting) must not equalTo(null)
      intermSetting.getSleepStartTime() must equalTo("23")
      intermSetting.getSleepEndTime() must equalTo("8")
      intermSetting.isGeoEnabled() must beTrue // is default false
      intermSetting.getLanguage() must equalTo("it")
      intermSetting.isAlwaysUseHttps() must beTrue
      intermSetting.isDiscoverableByEmail() must beTrue // is default
      intermSetting.getTimeZone().getName() must equalTo("Helsinki")
      intermSetting.getTrendLocations().length must be_>(0)
    }
  }
  
  "updateProfileImage" should {
    "change profile image" in {
      val user = twitter2.updateProfileImage(imageStream = Some(new FileInputStream(getRandomlyChosenFile)))
      rawJSON(user) must not equalTo(null)
    }
  }
  
  "updateProfileBackgroundImage" should {
    "change background image in authorized user page" in {
      val user = twitter2.updateProfileBackgroundImage(imageFile = Some(getRandomlyChosenFile), tile = (5 < System.currentTimeMillis() % 5))
      rawJSON(user) must not equalTo(null)
      user must equalTo(DataObjectFactory.createUser(rawJSON(user)))
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