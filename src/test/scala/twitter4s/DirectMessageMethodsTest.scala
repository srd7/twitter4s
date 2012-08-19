package twitter4s
import twitter4s._
import java.util.Date
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import Twitter4sTestHelper._
import org.specs2.runner.JUnitRunner
import twitter4j.json.DataObjectFactory
import twitter4j.TwitterException

@RunWith(classOf[JUnitRunner])
class DirectMessageMethodsTest extends Specification {
  "sendDirectMessage" should {
    "send message to other user with userId" in {
      val expectedReturn = new Date().toString() + ":directmessage test"
      val actualReturn = twitter1.sendDirectMessage(
          User.isSpecifiedBy(id3.id), text = expectedReturn)
      
      actualReturn.id must be_>=(0L)
      rawJSON(actualReturn.tw4jObj) must not equalTo(null)
      DataObjectFactory.createDirectMessage(rawJSON(actualReturn.tw4jObj)) must equalTo(actualReturn.tw4jObj)
      actualReturn.text must equalTo(expectedReturn)
      actualReturn.sender.screenName must equalTo(id1.screenName)
      actualReturn.recipient.screenName must equalTo(id3.screenName)
    }
    
    "throw exception both of parameter screenName and userId are not set" in {
      twitter1.sendDirectMessage(null, text = "unsuccess send") must
      throwA[IllegalArgumentException]
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
      rawJSON(actualReturn.tw4jObj) must not equalTo(null)
      actualReturn.tw4jObj must equalTo(DataObjectFactory.createDirectMessage(rawJSON(actualReturn.tw4jObj)))
      actualReturn.id must equalTo(actualReturnList(0).getId())
    }
  }
}