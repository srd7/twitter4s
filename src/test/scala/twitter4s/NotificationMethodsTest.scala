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
        twitter1.disableNotification(
            User.isSpecifiedBy(id3.screenName))
      } catch {
        case ex : TwitterException => // do nothing
      }
      
      testUser(
          twitter1.enableNotification(
              User.isSpecifiedBy(id3.screenName)))
      testUser(
          twitter2.disableNotification(
              User.isSpecifiedBy(id3.screenName)))
    }
    
    "throw exception when user specified info is null" in {
      twitter1.enableNotification(null) must
      throwA[IllegalArgumentException]
    }
  }
  
  "disableNotification" should {
    "throw exception when user specified info is null" in {
      twitter2.disableNotification(null) must
      throwA[IllegalArgumentException]
    }
  }
}