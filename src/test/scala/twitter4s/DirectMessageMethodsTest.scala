package twitter4s
import java.util.Date
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import twitter4s.Twitter4sTestHelper._
import org.specs2.runner.JUnitRunner
import twitter4j.json.DataObjectFactory
import twitter4j.TwitterException
import twitter4s.implicits.Twitter4SImplicits._

@RunWith(classOf[JUnitRunner])
class DirectMessageMethodsTest extends Specification {
  "sendDirectMessage" should {
    "send message to other user with userId" in {
      val expectedReturn = new Date().toString() + ":directmessage test"
      val actualReturn = twitter1.sendDirectMessage(userId = Some(id3.id), text = expectedReturn)
      
      actualReturn.getId() must be_>=(0L)
      rawJSON(actualReturn) must not equalTo(null)
      DataObjectFactory.createDirectMessage(rawJSON(actualReturn)) must equalTo(actualReturn)
      actualReturn.getText() must equalTo(expectedReturn)
      actualReturn.getSender().getScreenName() must equalTo(id1.screenName)
      actualReturn.getRecipient().getScreenName() must equalTo(id3.screenName)
    }
  }
  
  "getDirectMessage" should {
    "get direct message list from user" in {
      val actualReturnList = twitter3.getDirectMessages()
      actualReturnList must not equalTo(null)
      actualReturnList(0) must equalTo(DataObjectFactory.createDirectMessage(rawJSON(actualReturnList(0))))
      actualReturnList.size must be_>=(0)
    }
  }
  
  "showDirectMessages" should {
    "throws exception not allowed application" in {
      val actualReturnList = twitter3.getDirectMessages()
      twitter1.showDirectMessage(actualReturnList(0).getId()) must
      throwA[TwitterException].like {case te:TwitterException => te.getStatusCode must equalTo(403)}
    }
    
    "show direct messages allowed application" in {
      val actualReturnList = twitter3.getDirectMessages()
      val actualReturn = twitter3.showDirectMessage(actualReturnList(0).getId())
      rawJSON(actualReturn) must not equalTo(null)
      actualReturn must equalTo(DataObjectFactory.createDirectMessage(rawJSON(actualReturn)))
      actualReturn.getId() must equalTo(actualReturnList(0).getId())
    }
  }
}