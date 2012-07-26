package twitter4s

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import Twitter4sTestHelper._
import twitter4j.RateLimitStatus
import twitter4j.Trends
import twitter4j.TwitterResponse

@RunWith(classOf[JUnitRunner])
class ResponseListTest extends Specification {
  // TODO ラップオブジェクト対応
  "apply with index parameter" should {
    "get twitter4j object in list" in {
      val responseList = twitter1.getDailyTrends()
      responseList(0) must beAnInstanceOf[twitter4j.Trends]
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
    
    "has tw4jObj is instance of twitter4j.ResponseList" in {
      val responseList = twitter1.getDailyTrends().tw4jObj
      responseList must beAnInstanceOf[twitter4j.ResponseList[twitter4j.Trends]]
    }
  }
}