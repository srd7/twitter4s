package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4s.Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import twitter4j.User

@RunWith(classOf[JUnitRunner])
class BlockMethodsTest extends Specification {
  
  private def testBlockingUsers(target: ResponseList[User]) = {
    rawJSON(target.twt4jResponseList) must not equalTo(null)
    target(0) must equalTo(DataObjectFactory.createUser(rawJSON(target(0))))
    target().size must equalTo(blockingUsersSize)
    target(0).getId() must equalTo(blockingUserId)
  }
  
  "createBlock" should {
    "create block and get blocked user" in {
      val user = twitter2.createBlock(Some(id1.screenName))
      rawJSON(user) must not equalTo(null)
      user must equalTo(DataObjectFactory.createUser(rawJSON(user)))
    }
  }
  
  "destryoBlock" should {
    "destroy block and get unblocked user" in {
      val user = twitter2.destroyBlock(Some(id1.screenName))
      rawJSON(user) must not equalTo(null)
      user must equalTo(DataObjectFactory.createUser(rawJSON(user)))
      
      // check destroyed block
      twitter1.existsBlock(Some(id2.screenName)) must beFalse
      // check blocked user
      val blockedUserScreenName = blockingScreenName
      twitter1.existsBlock(Some(blockedUserScreenName)) must beTrue
    }
  }
  
  "getBlockingUsers" should {
    "get all user list blocking by authorized user" in {
      testBlockingUsers(twitter1.getBlockingUsers())
    }
    
    "get user list specified page blocking by authorized user" in {
      testBlockingUsers(twitter1.getBlockingUsers(Some(1)))
    }
  }
  
  "getBlockingUsersIDs" should {
    "get user id list blocking by authorized user" in {
      val ids = twitter1.getBlockingUsersIDs
      rawJSON(ids) must not equalTo(null)
      ids.getIDs().size must equalTo(blockingUsersSize)
      ids.getIDs()(0) must equalTo(blockingUserId)
    }
  }
}