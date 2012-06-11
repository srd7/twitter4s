package twitter4s
import java.util.Date
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import twitter4s.Twitter4sTestHelper._
import org.specs2.runner.JUnitRunner
import twitter4j.json.DataObjectFactory

@RunWith(classOf[JUnitRunner])
class DirectMessageMethodsTest extends Specification {
  "sendDirectMessage" should {
    "send message to other user with userId" in {
      val expectedReturn = new Date().toString() + ":directmessage test"
      val actualReturn = twitter1.sendDirectMessage(userId = Some(id3.id), text = expectedReturn)
      
      actualReturn.getId() must be_>=(0L)
      DataObjectFactory.getRawJSON(actualReturn) must not equalTo(null)
      DataObjectFactory.createDirectMessage(DataObjectFactory.getRawJSON(actualReturn)) must equalTo(actualReturn)
      actualReturn.getText() must equalTo(expectedReturn)
      actualReturn.getSender().getScreenName() must equalTo(id1.screenName)
      actualReturn.getRecipient().getScreenName() must equalTo(id3.screenName)
    }
  }
  
  "getDirectMessage" should {
    "get direct message list from user" in {
      val actualReturnList = twitter3.getDirectMessages()
      actualReturnList must not equalTo(null)
      actualReturnList.get(0) must equalTo(DataObjectFactory.createDirectMessage(DataObjectFactory.getRawJSON(actualReturnList.get(0))))
      actualReturnList.size() must be_>=(0)
    }
  }
}