package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4s.Twitter4sTestHelper._
import twitter4j.TwitterException
import twitter4j.json.DataObjectFactory
import twitter4j.User

@RunWith(classOf[JUnitRunner])
class NotificationMethodsTest extends Specification {
  
  private def testUser(target: User) = {
    rawJSON(target) must not equalTo(null)
    DataObjectFactory.createUser(rawJSON(target)) must equalTo(target)
  }

  "enableNotification" should {
    "set notificate from specified user to authorized user" in {
      try {
        twitter1.disableNotification(Some(id3.screenName))
      } catch {
        case ex : TwitterException => // do nothing
      }
      
      testUser(twitter1.enableNotification(Some(id3.screenName)))
      testUser(twitter2.disableNotification(Some(id3.screenName)))
    }
  }
}