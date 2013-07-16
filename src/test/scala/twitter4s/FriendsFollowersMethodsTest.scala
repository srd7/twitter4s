package twitter4s

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import twitter4s._
import twitter4j.TwitterException

@RunWith(classOf[JUnitRunner])
class FriendsFollowersMethodsTest extends Specification {
  val obamaScreenName = "barackobama"
  val obamaId = 813286

  "getFriedsIDs" should {
    val ryunosukey = 48528137
    "get friend ID array specified cursor" in {
      val ids = twitter1.getFriendsIDs(-1)
      rawJSON(ids.tw4jObj) must not equalTo(null)
      val yusuke = 4933401
      atLeastOnce(ids.ids) { (_:Long) must equalTo(yusuke) }
    }
    
    "get friend ID array specified by user id" in {
      val ids = twitter1.getFriendsIDs(
          -1,
          User.isSpecifiedBy(ryunosukey))
      rawJSON(ids.tw4jObj) must not equalTo(null)
      ids.tw4jObj must equalTo(DataObjectFactory.createIDs(rawJSON(ids.tw4jObj)))
      ids.length must equalTo(0)
    }
    
    "get friend ID array specified by user screen name" in {
      val ids = twitter1.getFriendsIDs(
          -1,
          User.isSpecifiedBy("yusuke"))
      rawJSON(ids.tw4jObj) must not equalTo(null)
      atLeastOnce(ids.ids) { (_:Long) must equalTo(ryunosukey)}
    }
    
    "get friend ID array with cursor specified by user id" in {
      val firstCursor = twitter1.getFriendsIDs(
          -1,
          User.isSpecifiedBy(obamaId))
      firstCursor.hasNext must beTrue
      firstCursor.hasPrevious must beFalse
      
      val secondCursor = twitter1.getFriendsIDs(
          firstCursor.nextCursor,
          User.isSpecifiedBy(obamaId))
      secondCursor.hasNext must beTrue
      secondCursor.hasPrevious must beTrue
    }
    
    "get friend ID array with cursor specified by user screen name" in {
      val firstCursor = twitter1.getFriendsIDs(
          -1,
          User.isSpecifiedBy(obamaScreenName))
      firstCursor.hasNext must beTrue
      firstCursor.hasPrevious must beFalse
      
      val secondCursor = twitter1.getFriendsIDs(
          firstCursor.nextCursor,
          User.isSpecifiedBy(obamaScreenName))
      secondCursor.hasNext must beTrue
      secondCursor.hasPrevious must beTrue
    }
  }
  
  "getFollowersIDs" should {
    "get followers ID array with cursor specified by user screen name" in {
      val firstCursor = twitter1.getFollowersIDs(
          -1,
          User.isSpecifiedBy(obamaScreenName))
      rawJSON(firstCursor.tw4jObj) must not equalTo(null)
      firstCursor.tw4jObj must equalTo(DataObjectFactory.createIDs(rawJSON(firstCursor.tw4jObj)))
      firstCursor.hasNext must beTrue
      firstCursor.hasPrevious must beFalse
      
      val secondCursor = twitter1.getFollowersIDs(
          firstCursor.nextCursor,
          User.isSpecifiedBy(obamaScreenName))
      secondCursor.hasNext must beTrue
      secondCursor.hasPrevious must beTrue
    }
    
    "get followers ID array specified by user id" in {
      val ids = twitter1.getFollowersIDs(
          -1,
          User.isSpecifiedBy(obamaId))
      rawJSON(ids.tw4jObj) must not equalTo(null)
      ids.tw4jObj must equalTo(DataObjectFactory.createIDs(rawJSON(ids.tw4jObj)))
      ids.hasNext must beTrue
      ids.hasPrevious must beFalse
      
      val secondCursor = twitter1.getFollowersIDs(
          ids.nextCursor,
          User.isSpecifiedBy(obamaId))
      secondCursor.hasNext must beTrue
      secondCursor.hasPrevious must beTrue
    }
    
    "get followers ID array specified by cursor" in {
      val ids = twitter2.getFollowersIDs(-1)
      rawJSON(ids.tw4jObj) must not equalTo(null)
      atLeastOnce(ids.ids) { (_:Long) must equalTo(id1.id)}
    }
  }
  
  
  "destroyFriendship" should {
    "destroy specified user's friendship" in {
      if(!twitter1.existsFriendship(id1.screenName, id2.screenName))
        twitter1.createFriendship(
            User.isSpecifiedBy(id1.id), follow = true)
      val user = twitter2.destroyFriendship(
          User.isSpecifiedBy(id1.screenName))
      rawJSON(user.tw4jObj) must not equalTo(null)
      user.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(user.tw4jObj)))
    }
    
    "destroy specified user was destroyed" in {
      val user = twitter2.destroyFriendship(
          User.isSpecifiedBy(id1.id))
      rawJSON(user.tw4jObj) must not equalTo(null)
      user.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(user.tw4jObj)))
      
      // Test code in Twitter4J, check TwitterException(status code = 403).
      // Checking ratelimitstatus?
    }
    
    "throw exception with no target user specific information" in {
      twitter2.destroyFriendship(null) must
      throwA[IllegalArgumentException]
    }
  }
  
  "createFriendship" should {
    "create specified user's friendship" in {
      val user = twitter2.createFriendship(
          User.isSpecifiedBy(id1.screenName), follow = true)
      rawJSON(user.tw4jObj) must not equalTo(null)
      user.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(user.tw4jObj)))
      user.screenName must equalTo(id1.screenName)
    }
    
    "throw exception create friendship with myself" in {
      twitter2.createFriendship(
          User.isSpecifiedBy(id2.id)) must
      throwA[TwitterException].like {case te: TwitterException => te.getStatusCode() must equalTo(403)}
    }
    
    "throw exception create friendship with not-exists usre" in {
      twitter2.createFriendship(
          User.isSpecifiedBy("dosentexists--")) must
      throwA[TwitterException].like {case te: TwitterException => te.getStatusCode() must equalTo(404)}
    }
    
    "throw exception when specificUser is set null" in {
      twitter2.createFriendship(null, null) must
      throwA[IllegalArgumentException]
    }
  }
  
  "showFriendship" should {
    "get friendship status between user one way follow" in {
      val rel = twitter1.showFriendship(
          User.isSpecifiedBy(id1.screenName),
          User.isSpecifiedBy(followsOneWay))
      rawJSON(rel.tw4jObj) must not equalTo(null)
      rel.tw4jObj must equalTo(DataObjectFactory.createRelationship(rawJSON(rel.tw4jObj)))
      rel.isSourceFollowedByTarget must beTrue
      rel.isSourceFollowingTarget must beFalse
      rel.isTargetFollowingSource must beTrue
      rel.isTargetFollowedBySource must beFalse
      
      val rel2 = twitter1.showFriendship(
          User.isSpecifiedBy(bestFriend1.id),
          User.isSpecifiedBy(bestFriend2.id))
      rawJSON(rel.tw4jObj) must equalTo(null)
    }
    
    "get friendship status between user best friends" in {
      val rel = twitter1.showFriendship(
          User.isSpecifiedBy(bestFriend1.id),
          User.isSpecifiedBy(bestFriend2.id))
      rawJSON(rel.tw4jObj) must not equalTo(null)
      rel.tw4jObj must equalTo(DataObjectFactory.createRelationship(rawJSON(rel.tw4jObj)))
      rel.isSourceFollowedByTarget must beTrue
      rel.isSourceFollowingTarget must beTrue
      rel.isTargetFollowedBySource must beTrue
      rel.isTargetFollowingSource must beTrue
    }
    
    "throw exception with no source user specific information" in {
      twitter1.showFriendship(null, User.isSpecifiedBy(bestFriend2.id)) must
      throwA[IllegalArgumentException]
    }
    
    "throw exception with no target user specific information" in {
      twitter1.showFriendship(User.isSpecifiedBy(bestFriend1.id), null) must
      throwA[IllegalArgumentException]
    }
    
    "throw exception with no parameter" in {
      twitter1.showFriendship(null, null) must
      throwA[IllegalArgumentException]
    }
    
    "throw exception different user specific information between source and target" in {
      twitter1.showFriendship(
          User.isSpecifiedBy(bestFriend1.screenName),
          User.isSpecifiedBy(bestFriend2.id)) must
      throwA[IllegalArgumentException]
    }
  }
  
  "lookupFriendships" should {
    "get friendship status list with specified users by screen name" in {
      val friendshipList = twitter1.lookupFriendships(
          Users.areSpecifiedBy(Array("barakobama", id2.screenName, id3.screenName)))
      friendshipList.size must equalTo(3)
      friendshipList(0).getScreenName() must equalTo("barakobama")
      friendshipList(0).isFollowedBy() must beFalse
      friendshipList(0).isFollowing() must beFalse
      friendshipList(2).getScreenName() must equalTo(id3.screenName)
      friendshipList(2).isFollowedBy() must beTrue
      friendshipList(2).isFollowing() must beTrue
    }
    
    "get friendship status list with specified users by id" in {
      val friendshipList = twitter1.lookupFriendships(
          Users.areSpecifiedBy(Array(id2.id, id3.id)))
      friendshipList.size must equalTo(2)
    }
  }
  
  "updateFriendship" should {
    "update friendship to specified user by screen name" in {
      val relationship = twitter1.updateFriendship(
          User.isSpecifiedBy(id3.screenName),
          true,
          true)
      relationship.targetUserScreenName must equalTo(id3.screenName)
    }
    
    "update friendship to specified user by id" in {
      val relationship = twitter1.updateFriendship(
          User.isSpecifiedBy(id3.id),
          true,
          true)
      relationship.targetUserScreenName must equalTo(id3.screenName)
    }
    
    "throw exception when user specific information is null" in {
      twitter1.updateFriendship(null, true, true) must
      throwA[IllegalArgumentException]
    }
  }
  
  "getIncomingFriendships" should {
    "get incoming friendship" in {
      val ids = twitter3.getIncomingFriendships(-1)
      rawJSON(ids.tw4jObj) must not equalTo(null)
      ids.tw4jObj must equalTo(DataObjectFactory.createIDs(rawJSON(ids.tw4jObj)))
      ids.length must be_>=(0)
    }
  }
  
  "getOutcomingFriendships" should {
    "get outcoming friendship" in {
      val ids = twitter2.getOutgoingFriendships(-1)
      rawJSON(ids.tw4jObj) must not equalTo(null)
      ids.tw4jObj must equalTo(DataObjectFactory.createIDs(rawJSON(ids.tw4jObj)))
      ids.length must be_>=(0)
    }
  }
  
  "getNoRetweetIDs" should {
    "get not retweet users id" in {
      val ids = twitter2.getNoRetweetIds
      rawJSON(ids.tw4jObj) must not equalTo(null)
      ids.tw4jObj must equalTo(DataObjectFactory.createIDs(rawJSON(ids.tw4jObj)))
    }
  }
}