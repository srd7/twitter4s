package twitter4s
import twitter4s._
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory

@RunWith(classOf[JUnitRunner])
class BlockMethodsTest extends Specification {
  
  private def testBlockingUsers(target: ResponseList[twitter4j.User]) = {
    rawJSON(target.tw4jObj) must not equalTo(null)
    target(0) must equalTo(DataObjectFactory.createUser(rawJSON(target(0))))
    target.size must equalTo(blockingUsersSize)
    target(0).getId() must equalTo(blockingUserId)
  }
  
  "createBlock" should {
    "create block and get blocked user" in {
      val user = twitter2.createBlock(id3.screenName)
      rawJSON(user.tw4jObj) must not equalTo(null)
      user.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(user.tw4jObj)))
    }
    
    "throw exception both of screenName and userId are not set" in {
      twitter2.createBlock() must
      throwA[IllegalArgumentException]
    }
  }
  
  "destryoBlock" should {
    "destroy block and get unblocked user" in {
      val user = twitter2.destroyBlock(id3.screenName)
      rawJSON(user.tw4jObj) must not equalTo(null)
      user.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(user.tw4jObj)))
      
      // check destroyed block
      twitter3.existsBlock(id2.screenName) must beFalse
      // check blocked user
      val blockedUserScreenName = blockingScreenName
      twitter1.existsBlock(blockedUserScreenName) must beTrue
    }
    
    "throw exception both of screenName and userId are not set" in {
      twitter2.destroyBlock() must
      throwA[IllegalArgumentException] 
    }
  }
  
  "existBlock" should {
    "thorw exception both of screenName and userId are not set" in {
      twitter2.existsBlock() must
      throwA[IllegalArgumentException]
    }
  }
  
  "getBlockingUsers" should {
    "get all user list blocking by authorized user" in {
      testBlockingUsers(twitter1.getBlockingUsers())
    }
    
    "get user list specified page blocking by authorized user" in {
      testBlockingUsers(twitter1.getBlockingUsers(1))
    }
  }
  
  "getBlockingUsersIDs" should {
    "get user id list blocking by authorized user" in {
      val ids = twitter1.getBlockingUsersIDs
      rawJSON(ids.tw4jObj) must not equalTo(null)
      ids.ids.size must equalTo(blockingUsersSize)
      ids.ids(0) must equalTo(blockingUserId)
    }
  }
}