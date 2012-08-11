package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import Twitter4sTestHelper._
import twitter4j.TwitterException
import twitter4j.json.DataObjectFactory

@RunWith(classOf[JUnitRunner])
class NotificationMethodsTest extends Specification {
  
  private def testUser(target: User) = {
    rawJSON(target.tw4jObj) must not equalTo(null)
    DataObjectFactory.createUser(rawJSON(target.tw4jObj)) must equalTo(target.tw4jObj)
  }

  "enableNotification" should {
    "set notificate from specified user to authorized user" in {
      try {
        twitter1.disableNotification(id3.screenName)
      } catch {
        case ex : TwitterException => // do nothing
      }
      
      testUser(twitter1.enableNotification(id3.screenName))
      testUser(twitter2.disableNotification(id3.screenName))
    }
    
    "throw exception both of screenName and userId are not set" in {
      twitter1.enableNotification() must
      throwA[IllegalArgumentException]
    }
  }
  
  "disableNotification" should {
    "throw exception both of screenName and userId are not set" in {
      twitter2.disableNotification() must
      throwA[IllegalArgumentException]
    }
  }
}