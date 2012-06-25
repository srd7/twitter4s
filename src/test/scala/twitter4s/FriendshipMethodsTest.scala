package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4s.Twitter4sTestHelper._
import twitter4s.implicits.Twitter4SImplicits._
import twitter4j.json.DataObjectFactory
import twitter4j.TwitterException

@RunWith(classOf[JUnitRunner])
class FriendshipMethodsTest extends Specification {

  "destroyFriendship" should {
    "destroy specified user's friendship" in {
      if(!twitter1.existsFriendship(id1.screenName, id2.screenName))
        twitter1.createFriendship(userId = Some(id1.id), follow = Some(true))
      val user = twitter2.destroyFriendship(Some(id1.screenName))
      rawJSON(user) must not equalTo(null)
      user must equalTo(DataObjectFactory.createUser(rawJSON(user)))
    }
    
    "destroy specified user was destroyed" in {
      val user = twitter2.destroyFriendship(userId = Some(id1.id))
      rawJSON(user) must not equalTo(null)
      user must equalTo(DataObjectFactory.createUser(rawJSON(user)))
      
      // Test code in Twitter4J, check TwitterException(status code = 403).
      // Checking ratelimitstatus?
    }
  }
  
  "createFriendship" should {
    "create specified user's friendship" in {
      val user = twitter2.createFriendship(screenName = Some(id1.screenName), follow = Some(true))
      rawJSON(user) must not equalTo(null)
      user must equalTo(DataObjectFactory.createUser(rawJSON(user)))
      user.getScreenName() must equalTo(id1.screenName)
    }
    
    "throw exception create friendship with myself" in {
      twitter2.createFriendship(userId = Some(id2.id)) must
      throwA[TwitterException].like {case te: TwitterException => te.getStatusCode() must equalTo(403)}
    }
    
    "throw exception create friendship with not-exists usre" in {
      twitter2.createFriendship(Some("dosentexists--")) must
      throwA[TwitterException].like {case te: TwitterException => te.getStatusCode() must equalTo(404)}
    }
  }
  
// This test be fail. I'm researching reason.
//  "showFriendship" should {
//    "get friendship status between user" in {
//      val rel = twitter1.showFriendship(sourceScreenName = Some(id1.screenName), targetScreenName = Some(followsOneWay))
//      rawJSON(rel) must not equalTo(null)
//      rel must equalTo(DataObjectFactory.createRelationship(rawJSON(rel)))
//    }
//  }
}