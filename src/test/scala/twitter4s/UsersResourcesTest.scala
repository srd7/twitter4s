package twitter4s

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4j.json.DataObjectFactory
import Twitter4sTestHelper._
import java.io.{File, FileInputStream}
import twitter4s.api.impl.UsersResourcesImpl
import twitter4j._
import twitter4s.mocked.FakeValuesUsedByMock

@RunWith(classOf[JUnitRunner])
class UsersResourcesTest extends Specification with TwitterResourcesTestBase {
  type TargetResourcesType = UsersResourcesImpl
  
  val twitter1UserResourceRole = new Twitter(twitter4jInstance(User1)) with UsersResourcesImpl
  val twitter2UserResourceRole = new Twitter(twitter4jInstance(User2)) with UsersResourcesImpl
  val twitter3UserResourceRole = new Twitter(twitter4jInstance(User3)) with UsersResourcesImpl

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
  
  private def testBlockingUsers(target: ResponseList[twitter4j.User]) = {
    rawJSON(target.tw4jObj) must not equalTo(null)
    target(0) must equalTo(DataObjectFactory.createUser(rawJSON(target(0))))
    target.size must equalTo(blockingUsersSize)
    target(0).id must equalTo(blockingUserId)
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
    "destroy block and get unblocked user" in {
      val user = twitter2UserResourceRole.destroyBlock(
          User.isSpecifiedBy(id3.screenName))
      rawJSON(user.tw4jObj) must not equalTo(null)
      user.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(user.tw4jObj)))
    }
    
    "throw exception when user specific info is set null" in {
      twitter2UserResourceRole.destroyBlock(null) must
      throwA[IllegalArgumentException] 
    }
  }
  
  "getBlockingUsers" should {
    "get all user list blocking by authorized user" in {
      testBlockingUsers(twitter1UserResourceRole.getBlocksList())
    }
  }
  
  "getBlockingUsersIDs" should {
    "get user id list blocking by authorized user" in {
      val ids = twitter1UserResourceRole.getBlocksIDs()
      rawJSON(ids.tw4jObj) must not equalTo(null)
      ids.ids.size must equalTo(blockingUsersSize)
      ids.ids(0) must equalTo(blockingUserId)
    }
  }
  
  // TODO
  // I don't know how to use contributor function.
  // The following specs is pending until I understand it.
//  "getContributees" should {
//    "get user list by authorized user name" in {
//      val users = twitter1.getContributors("twitter")
//      users.size must be_>(0)
//    }
//  }
  
  // TODO
  // The profile banner methods has no return value.
  // I have no ideas suitable specs for these methods.
  // removeProfileBanner
  // updateProfileBanner
}