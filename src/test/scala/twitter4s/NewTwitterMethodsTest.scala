package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4s.Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import twitter4s.implicits.Twitter4SImplicits._
import twitter4j.Status

@RunWith(classOf[JUnitRunner])
class NewTwitterMethodsTest extends Specification {
  
  private def testTweetList(target: ResponseList[Status]) = {
    if (target().size > 0) target(0) must equalTo(DataObjectFactory.createStatus(rawJSON(target(0))))
    true
  }
  
  "getRelatedResult" should {
    "get tweet list array related to specified tweet" in {
      val relatedResult = twitter1.getRelatedResults(999383469l)
      relatedResult must not equalTo(null)
      
      testTweetList(relatedResult.getTweetsFromUser())
      testTweetList(relatedResult.getTweetsWithConversation())
      testTweetList(relatedResult.getTweetsWithReply())
    }
  }
}