package twitter4s

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import twitter4j.TwitterResponse
import twitter4j.conf.ConfigurationBuilder
import Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import twitter4j.RateLimitStatusListener
import twitter4j.RateLimitStatusEvent

@RunWith(classOf[JUnitRunner])
class TwitterTest extends Specification {
  "getAccessLevel" should {
    "unauthenticated access level is None" in {
      val response = unauthenticated.getDailyTrends()
      response.accessLevel must equalTo(TwitterResponse.NONE)
    }
  
    "application has read and write access level is READ_WRITE" in {
      val response = twitter1.verifyCredentials
      response.getAccessLevel() must equalTo(TwitterResponse.READ_WRITE)
    }
  
    "application has all access level is READ_WRITE_DIRECTMESSAGES" in {
      val response = rwPrivateMessage.verifyCredentials
      response.getAccessLevel() must equalTo(TwitterResponse.READ_WRITE_DIRECTMESSAGES)
    }
  }
  
  "screenName" should {
    "get authorized user's screen name" in {
      twitter1.screenName must equalTo(id1.screenName)
    }
  }
  
  "id" should {
    "get authorized user's id" in {
      twitter1.id must equalTo(id1.id)
    }
  }
  
  "getRateLimitStatus" should {
    "get rate limit status" in {
      val rateLimitStatus = twitter1.getRateLimitStatus
      rawJSON(rateLimitStatus) must not equalTo(null)
      rateLimitStatus must equalTo(DataObjectFactory.createRateLimitStatus(rawJSON(rateLimitStatus)))
      rateLimitStatus.getHourlyLimit() must be_>(10)
      rateLimitStatus.getRemainingHits() must be_>(10)
    }
    
    "get comparable rate limit status" in {
      twitter1.getMentions()
      val previousStatus = twitter1.getRateLimitStatus
      
      twitter1.getMentions()
      val afterStatus = twitter1.getRateLimitStatus
      
      previousStatus.getRemainingHits() must be_>(afterStatus.getRemainingHits())
      previousStatus.getHourlyLimit() must equalTo(afterStatus.getHourlyLimit())
    }
  }
  
  // check API spec
//  var accountLimitStatusAcquired:Boolean = false
//  var ipLimitStatusAcquired:Boolean = false
//  
//  "addRateLimitStatusListener" should {
//    "add listener works using APIs" in {
//      twitter1.addRateLimitStatusListener(new RateLimitStatusListener() {
//        def onRateLimitStatus(event: RateLimitStatusEvent) {
//          accountLimitStatusAcquired = event.isAccountRateLimitStatus()
//          ipLimitStatusAcquired = event.isIPRateLimitStatus()
//        }
//        
//        def onRateLimitReached(event: RateLimitStatusEvent) {
//        }
//      })
//      
//      twitter1.getMentions()
//      
//      accountLimitStatusAcquired must beTrue
//      ipLimitStatusAcquired must beFalse
//    }
//  }
}