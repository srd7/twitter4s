package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4s.Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import twitter4s.implicits.Twitter4SImplicits._

@RunWith(classOf[JUnitRunner])
class NewTwitterMethodsTest extends Specification {
  "getRelatedResult" should {
    "get tweet list array related to specified tweet" in {
      val relatedResult = twitter1.getRelatedResults(999383469l)
      relatedResult must not equalTo(null)
      
      val fromUserTweet = relatedResult.getTweetsFromUser()
      if (fromUserTweet().size > 0)
        fromUserTweet(0) must equalTo(DataObjectFactory.createStatus(DataObjectFactory.getRawJSON(fromUserTweet(0))))
      
      val tweetWithConversation = relatedResult.getTweetsWithConversation()
      if (tweetWithConversation().size > 0)
        tweetWithConversation(0) must equalTo(DataObjectFactory.createStatus(DataObjectFactory.getRawJSON(tweetWithConversation(0))))
      
      val tweetWithReply = relatedResult.getTweetsWithReply()
      if (tweetWithReply().size > 0)
        tweetWithReply(0) must equalTo(DataObjectFactory.createStatus(DataObjectFactory.getRawJSON(tweetWithReply(0))))
      true
    }
  }
}