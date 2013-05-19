package twitter4s

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import Twitter4sTestHelper._

@RunWith(classOf[JUnitRunner])
class ResponseListTest extends Specification {
  "apply with index parameter" should {
    "get twitter4j object in list" in {
      // TODO getDailyTrendsメソッド削除
//      val responseList = twitter1.getDailyTrends()
//      responseList(0) must beAnInstanceOf[twitter4j.Trends]
//      responseList(0).getTrends().size must be_>(0)
      true
    }
  }
  
  "ResponseList class" should {
    "has rateLimitStatus" in {
      // TODO getDailyTrendsメソッド削除
//      val responseList = twitter1.getDailyTrends()
//      responseList.rateLimitStatus must beAnInstanceOf[RateLimitStatus]
      true
    }
    
    "has featureSpecificRateLimitStatus" in {
      // TODO getDailyTrendsメソッド削除
//      val responseList = twitter1.getDailyTrends()
//      if (responseList.featureSpecificRateLimitStatus != null) responseList.featureSpecificRateLimitStatus must beAnInstanceOf[RateLimitStatus]
      true
    }
    
    "has accessLevel" in {
      // TODO getDailyTrendsメソッド削除
//      val responseList = twitter1.getDailyTrends()
//      responseList.accessLevel must equalTo(twitter4j.TwitterResponse.READ_WRITE)
      true
    }
    
    "has tw4jObj is instance of twitter4j.ResponseList" in {
      // TODO getDailyTrendsメソッド削除
//      val responseList = twitter1.getDailyTrends().tw4jObj
//      responseList must beAnInstanceOf[twitter4j.ResponseList[twitter4j.Trends]]
      true
    }
  }
}