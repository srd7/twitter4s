package twitter4s
import twitter4s._
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import Twitter4sTestHelper.id1
import Twitter4sTestHelper.prop
import Twitter4sTestHelper.rawJSON
import Twitter4sTestHelper.rwPrivateMessage
import Twitter4sTestHelper.twitter1
import auth.ConsumerKey
import twitter4j.auth.AccessToken
import twitter4j.json.DataObjectFactory
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TwitterTest extends Specification {
  "getAccessLevel" should {
    "application has read and write access level is READ_WRITE" in {
      val response = twitter1.verifyCredentials
      response.accessLevel must equalTo(TwitterResponse.READ_WRITE)
    }
  
    "application has all access level is READ_WRITE_DIRECTMESSAGES" in {
      val response = rwPrivateMessage.verifyCredentials
      response.accessLevel must equalTo(TwitterResponse.READ_WRITE_DIRECTMESSAGES)
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
  
  "Twitter object" should {
    val consumerKey = new ConsumerKey(prop.getProperty("oauth.consumerKey"), prop.getProperty("oauth.consumerSecret"))
    val accessToken = new AccessToken(prop.getProperty("id1.oauth.accessToken"), prop.getProperty("id1.oauth.accessTokenSecret"))
    
    "create object with consumerKey and consumerSecret" in {
      val twitterObj = Twitter(consumerKey, accessToken)
      // execute authorized api.
      val user = twitterObj.verifyCredentials
      rawJSON(user.tw4jObj) must not equalTo(null)
      user.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(user.tw4jObj)))
    }
    
    "create object without settings and after set oauth information" in {
      val twitterObj = Twitter()
      twitterObj.setOAuthConsumer(prop.getProperty("oauth.consumerKey"), prop.getProperty("oauth.consumerSecret"))
      twitterObj.setOAuthAccessToken(accessToken)
      // execute authorized api.
      val user = twitterObj.verifyCredentials
      rawJSON(user.tw4jObj) must not equalTo(null)
      user.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(user.tw4jObj)))
    }
    
    "create object without settings and after set oauth information used by ConsumerKey class" in {
      val twitterObj = Twitter()
      twitterObj.setOAuthConsumer(consumerKey)
      twitterObj.setOAuthAccessToken(accessToken)
      // execute authorized api.
      val user = twitterObj.verifyCredentials
      rawJSON(user.tw4jObj) must not equalTo(null)
      user.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(user.tw4jObj)))
    }
  }

  // TODO mock化
  "getRateLimitStatus" should {
    "get rate limit status" in {
      val rateLimitStatus = twitter1.getRateLimitStatus()
      DataObjectFactory.getRawJSON(rateLimitStatus) must not equalTo(null)
      rateLimitStatus must equalTo(DataObjectFactory.createRateLimitStatus(rawJSON(rateLimitStatus)))
      
      val status = rateLimitStatus.values.iterator.next
      status.limit must be_>(10)
      status.remaining must be_>(10)
      status.secondsUntilReset must be_>(0)
    }
    
    "get comparable rate limit status" in {
      twitter1.getMentions()
      val previousRateLimitStatus = twitter1.getRateLimitStatus()
      val previousStatus = previousRateLimitStatus.values.iterator.next
      
      twitter1.getMentions()
      val afterRateLimitStatus = twitter1.getRateLimitStatus()
      val afterStatus = afterRateLimitStatus.values.iterator.next
      
      previousStatus.remaining must be_>(afterStatus.remaining)
      previousStatus.limit must equalTo(afterStatus.limit)
      true
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