package twitter4s

import org.specs2.mutable.Specification
import twitter4s.Twitter4sTestHelper._
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import scala.collection.mutable.Buffer
import twitter4j.Trends
import twitter4j.RateLimitStatus
import twitter4j.TwitterResponse

@RunWith(classOf[JUnitRunner])
class ResponseListTest extends Specification {

  "apply" should {
    "get twitter4j object list as scala" in {
      val responseList = twitter1.getDailyTrends()
      responseList() must beAnInstanceOf[Buffer[Trends]]
      responseList().size must be_>(0)
    }
  }
  
  "apply with index parameter" should {
    "get twitter4j object in list" in {
      val responseList = twitter1.getDailyTrends()
      responseList(0) must beAnInstanceOf[Trends]
      responseList(0).getTrends().size must be_>(0)
    }
  }
  
  "ResponseList class" should {
    "has rateLimitStatus" in {
      val responseList = twitter1.getDailyTrends()
      responseList.rateLimitStatus must beAnInstanceOf[RateLimitStatus]
    }
    
    "has featureSpecificRateLimitStatus" in {
      val responseList = twitter1.getDailyTrends()
      if (responseList.featureSpecificRateLimitStatus != null) responseList.featureSpecificRateLimitStatus must beAnInstanceOf[RateLimitStatus]
      true
    }
    
    "has accessLevel" in {
      val responseList = twitter1.getDailyTrends()
      responseList.accessLevel must equalTo(TwitterResponse.READ_WRITE)
    }
  }
}