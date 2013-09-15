package twitter4s

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import Twitter4sTestHelper._
import java.io.{File, FileInputStream}
import twitter4s.api.impl.UsersResourcesImpl
import twitter4s.mocked.FakeValuesUsedByMock

@RunWith(classOf[JUnitRunner])
class UsersResourcesTest extends Specification with TwitterResourcesTestBase {
  type TargetResourcesType = UsersResourcesImpl

  val fakeUsersScreenNameArray = Array("fake_user1", "fake_user2")
  val fakeUsersIdArray = Array(1L, 2L)

  mockedTwitter4j.showUser(anyString) returns FakeValuesUsedByMock.user
  mockedTwitter4j.showUser(anyLong) returns FakeValuesUsedByMock.user
  mockedTwitter4j.lookupUsers(any[Array[String]]) returns FakeValuesUsedByMock.responseList[twitter4j.User]
  mockedTwitter4j.lookupUsers(any[Array[Long]]) returns FakeValuesUsedByMock.responseList[twitter4j.User]
  mockedTwitter4j.searchUsers(anyString, anyInt) returns FakeValuesUsedByMock.responseList[twitter4j.User]
  mockedTwitter4j.verifyCredentials() returns FakeValuesUsedByMock.user
  mockedTwitter4j.updateProfile(anyString, anyString, anyString, anyString) returns FakeValuesUsedByMock.user
  mockedTwitter4j.updateProfileColors(anyString, anyString, anyString, anyString, anyString) returns FakeValuesUsedByMock.user
  mockedTwitter4j.getAccountSettings returns FakeValuesUsedByMock.accountSettings
  mockedTwitter4j.updateAccountSettings(anyInt, any[Boolean], anyString, anyString, anyString, anyString) returns FakeValuesUsedByMock.accountSettings
  mockedTwitter4j.updateProfileImage(any[FileInputStream]) returns FakeValuesUsedByMock.user
  mockedTwitter4j.updateProfileImage(any[File]) returns FakeValuesUsedByMock.user
  mockedTwitter4j.updateProfileBackgroundImage(any[FileInputStream], any[Boolean]) returns FakeValuesUsedByMock.user
  mockedTwitter4j.updateProfileBackgroundImage(any[File], any[Boolean]) returns FakeValuesUsedByMock.user
  mockedTwitter4j.createBlock(anyString) returns FakeValuesUsedByMock.user
  mockedTwitter4j.createBlock(anyLong) returns FakeValuesUsedByMock.user
  mockedTwitter4j.destroyBlock(anyString) returns FakeValuesUsedByMock.user
  mockedTwitter4j.destroyBlock(anyLong) returns FakeValuesUsedByMock.user
  mockedTwitter4j.getBlocksList returns FakeValuesUsedByMock.pagableResponseList[twitter4j.User]
  mockedTwitter4j.getBlocksList(anyLong) returns FakeValuesUsedByMock.pagableResponseList[twitter4j.User]
  mockedTwitter4j.getBlocksIDs returns FakeValuesUsedByMock.ids
  mockedTwitter4j.getBlocksIDs(anyLong) returns FakeValuesUsedByMock.ids
  mockedTwitter4j.getContributees(anyString) returns FakeValuesUsedByMock.responseList[twitter4j.User]
  mockedTwitter4j.getContributees(anyLong) returns FakeValuesUsedByMock.responseList[twitter4j.User]
  mockedTwitter4j.getContributors(anyString) returns FakeValuesUsedByMock.responseList[twitter4j.User]
  mockedTwitter4j.getContributors(anyLong) returns FakeValuesUsedByMock.responseList[twitter4j.User]
  var mockCallStatusRemoveProfileBanner: String = _
  mockedTwitter4j.removeProfileBanner() answers{_ => mockCallStatusRemoveProfileBanner = "called removeProfileBanner"}
  var mockCallStatusUpdateProfileBannerStream: String = _
  mockedTwitter4j.updateProfileBanner(any[FileInputStream]) answers{_ => mockCallStatusUpdateProfileBannerStream = "called updateProfileBanner by image stream"}
  var mockCallStatusUpdateProfileBannerFile: String = _
  mockedTwitter4j.updateProfileBanner(any[File]) answers{_ => mockCallStatusUpdateProfileBannerFile = "called updateProfileBanner by image file"}


  override val twitter = new Twitter(mockedTwitter4j) with UsersResourcesImpl

  "showUser" should {
    "call twitter4j showUser method by screen name" in {
      twitter.showUser(User.isSpecifiedBy("fake_user")).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).showUser("fake_user")
    }

    "call twitter4j showUser method by user id" in {
      twitter.showUser(User.isSpecifiedBy(1L)).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).showUser(1L)
    }
    
    "throw IllegalArgumentException are not set both parameters" in {
      twitter.showUser(null) must throwA[IllegalArgumentException]
    }
  }

  "lookupUsers" should {
    "call twitter4j lookupUsers method by screen name array" in {
      twitter.lookupUsers(Users.areSpecifiedBy(fakeUsersScreenNameArray)).size must equalTo(1)
      there was one(mockedTwitter4j).lookupUsers(fakeUsersScreenNameArray)
    }

    "call twitter4j lookupUsers method by user id array" in {
      twitter.lookupUsers(Users.areSpecifiedBy(fakeUsersIdArray)).size must equalTo(1)
      there was one(mockedTwitter4j).lookupUsers(fakeUsersIdArray)
    }
    
    "throw IllegalArgumentException when specificUsers is null" in {
      twitter.lookupUsers(null) must throwA[IllegalArgumentException]
    }
  }
  
  "searchUser" should {
    "call twitter4j searchUser method" in {
      twitter.searchUsers("search user", 1).size must equalTo(1)
      there was one(mockedTwitter4j).searchUsers("search user", 1)
    }
  }

  "verifyCredentials" should {
    "call twitter4j verifyCredentials method" in {
      twitter.verifyCredentials.screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).verifyCredentials()
    }
  }
  
  "updateProfile" should {
    "call twitter4j updateProfile method" in {
      twitter.updateProfile("twt4s_id", "http://hoge.com", ":Location", "test profile").screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).updateProfile("twt4s_id", "http://hoge.com", ":Location", "test profile")
    }
  }
  
  "updateProfileColors" should {
    "call twitter4j updateProfileColors method" in {
      twitter.updateProfileColors("f00", "f0f", "0ff", "0f0", "f0f").screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).updateProfileColors("f00", "f0f", "0ff", "0f0", "f0f")
    }
  }
  
  "getAccountSettings" should {
    "call twitter4j getAccountSettings method" in {
      twitter.getAccountSettings.accessLevel must equalTo(TwitterResponse.READ_WRITE)
      there was one(mockedTwitter4j).getAccountSettings
    }
  }
  
  "updateAccountSettings" should {
    "call twitter4j updateAccountSettings method" in {
      twitter.updateAccountSettings(1, true, "23", "08", "Helsinki", "it").accessLevel must equalTo(TwitterResponse.READ_WRITE)
      there was one(mockedTwitter4j).updateAccountSettings(1, true, "23", "08", "Helsinki", "it")
    }
  }
  
  "updateProfileImage" should {
    "call twitter4j updateProfileImage method by image stream" in {
      val inputStream = new FileInputStream(getRandomlyChosenFile)
      twitter.updateProfileImage(ImageResource.isAssigned(inputStream)).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).updateProfileImage(inputStream)
    }

    "call twitter4j updateProfileImage method by image file" in {
      val image = getRandomlyChosenFile
      twitter.updateProfileImage(ImageResource.isAssigned(image)).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).updateProfileImage(image)
    }
    
    "throws IllegalArgumentException with no image file and stream" in {
       twitter.updateProfileImage(null) must throwA[IllegalArgumentException]
    }
  }
  
  "updateProfileBackgroundImage" should {
    "call twitter4j updateProfileBackgroundImage method by image stream" in {
      val inputStream = new FileInputStream(getRandomlyChosenFile)
      twitter.updateProfileBackgroundImage(ImageResource.isAssigned(inputStream), true).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).updateProfileBackgroundImage(inputStream, true)
    }

    "call twitter4j updateProfileBackgroundImage method by image file" in {
      val image = getRandomlyChosenFile
      twitter.updateProfileBackgroundImage(ImageResource.isAssigned(image), false).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).updateProfileBackgroundImage(image, false)
    }
    
    "throws IllegalArgumentException with no image file and stream" in {
      twitter.updateProfileBackgroundImage(null, true) must throwA[IllegalArgumentException]
    }
  }
  
  "createBlock" should {
    "call twitter4j createBlock method by screen name" in {
      twitter.createBlock(User.isSpecifiedBy(FakeValuesUsedByMock.userName)).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).createBlock(FakeValuesUsedByMock.userName)
    }

    "call twitter4j createBlock method by user id" in {
      twitter.createBlock(User.isSpecifiedBy(1L)).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).createBlock(1L)
    }
    
    "throw exception when user specific info is set null" in {
      twitter.createBlock(null) must
      throwA[IllegalArgumentException]
    }
  }
  
  "destryoBlock" should {
    "call twitter4j destroyBlock method by screen name" in {
      twitter.destroyBlock(User.isSpecifiedBy(FakeValuesUsedByMock.userName)).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).destroyBlock(FakeValuesUsedByMock.userName)
    }

    "call twitter4j destroyBlock method by user id" in {
      twitter.destroyBlock(User.isSpecifiedBy(2L)).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).destroyBlock(2L)
    }
    
    "throw exception when user specific info is set null" in {
      twitter.destroyBlock(null) must
      throwA[IllegalArgumentException] 
    }
  }
  
  "getBlocksList" should {
    "call twitter4j getBlocksList method without parameter" in {
      val actual = twitter.getBlocksList()
      actual.size must equalTo(50)
      actual.hasNext must beTrue
      there was one(mockedTwitter4j).getBlocksList
    }

    "call twitter4j getBlocksList method by cursor" in {
      val actual = twitter.getBlocksList(3L)
      actual.size must equalTo(50)
      actual.hasPrevious must beTrue
      there was one(mockedTwitter4j).getBlocksList(3L)
    }
  }
  
  "getBlocksIDs" should {
    "call twitter4j getBlocksIDs method without parameter" in {
      twitter.getBlocksIDs().accessLevel must equalTo(TwitterResponse.READ_WRITE_DIRECTMESSAGES)
      there was one(mockedTwitter4j).getBlocksIDs
    }

    "call twitter4j getBlocksIDs method by cursor" in {
      twitter.getBlocksIDs(4L).accessLevel must equalTo(TwitterResponse.READ_WRITE_DIRECTMESSAGES)
      there was one(mockedTwitter4j).getBlocksIDs(4L)
    }
  }

  "getContributees" should {
    "call twitter4j getContributees method by screen name" in {
      twitter.getContributees(User.isSpecifiedBy(FakeValuesUsedByMock.userName)).size must equalTo(1)
      there was one(mockedTwitter4j).getContributees(FakeValuesUsedByMock.userName)
    }

    "call twitter4j getContributees method by user id" in {
      twitter.getContributees(User.isSpecifiedBy(5L)).size must equalTo(1)
      there was one(mockedTwitter4j).getContributees(5L)
    }

    "throw exception when user specific info is set null" in {
      twitter.getContributees(null) must
      throwA[IllegalArgumentException]
    }
  }

  "getContributors" should {
    "call twitter4j getContirbutors method by screen name" in {
      twitter.getContributors(User.isSpecifiedBy(FakeValuesUsedByMock.userName)).size must equalTo(1)
      there was one(mockedTwitter4j).getContributors(FakeValuesUsedByMock.userName)
    }

    "call twitter4j getContributors method by user id" in {
      twitter.getContributors(User.isSpecifiedBy(6L)).size must equalTo(1)
      there was one(mockedTwitter4j).getContributors(6L)
    }

    "throw exception when user specific info is set null" in {
      twitter.getContributors(null) must
      throwA[IllegalArgumentException]
    }
  }

  "removeProfileBanner" should {
    "call twitter4j removeProfileBanner method" in {
      twitter.removeProfileBanner()
      mockCallStatusRemoveProfileBanner must equalTo("called removeProfileBanner")

      there was one(mockedTwitter4j).removeProfileBanner()
    }
  }

  "updateProfileBanner" should {
    "call twitter4j updateProfileBanner method by image stream" in {
      val inputStream = new FileInputStream(getRandomlyChosenFile)
      twitter.updateProfileBanner(ImageResource.isAssigned(inputStream))
      mockCallStatusUpdateProfileBannerStream must equalTo("called updateProfileBanner by image stream")
      there was one(mockedTwitter4j).updateProfileBanner(inputStream)
    }

    "call twitter4j updateProfileBanner method by image file" in {
      val image = getRandomlyChosenFile
      twitter.updateProfileBanner(ImageResource.isAssigned(image))
      mockCallStatusUpdateProfileBannerFile must equalTo("called updateProfileBanner by image file")
      there was one(mockedTwitter4j).updateProfileBanner(image)
    }

    "throw exception when image resources specific info is set null" in {
      twitter.updateProfileBanner(null) must
      throwA[IllegalArgumentException]
    }
  }
}