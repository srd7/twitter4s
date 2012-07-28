package twitter4s
import twitter4s._
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory

@RunWith(classOf[JUnitRunner])
class NewTwitterMethodsTest extends Specification {
  
  private def testTweetList(target: ResponseList[twitter4j.Status]) = {
    if (target.size > 0) target(0) must equalTo(DataObjectFactory.createStatus(rawJSON(target(0))))
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