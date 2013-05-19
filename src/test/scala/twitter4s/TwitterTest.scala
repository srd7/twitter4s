package twitter4s
import twitter4s._
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import Twitter4sTestHelper.id1
import Twitter4sTestHelper.prop
import Twitter4sTestHelper.rawJSON
import Twitter4sTestHelper.rwPrivateMessage
import Twitter4sTestHelper.twitter1
import Twitter4sTestHelper.unauthenticated
import auth.ConsumerKey
import twitter4j.auth.AccessToken
import twitter4j.json.DataObjectFactory
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TwitterTest extends Specification {
  "getAccessLevel" should {
    "unauthenticated access level is None" in {
      val response = unauthenticated.getDailyTrends()
      response.accessLevel must equalTo(TwitterResponse.NONE)
    }
  
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
    
    def testTwitterObjectAuthorize(target: Twitter) = {
      val user = target.verifyCredentials
      rawJSON(user.tw4jObj) must not equalTo(null)
      user.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(user.tw4jObj)))
    }
    
    "create object with consumerKey and consumerSecret" in {
      val twitterObj = Twitter(consumerKey, accessToken)
      // execute authorized api.
      testTwitterObjectAuthorize(twitterObj)
    }
    
    "create object without settings and after set oauth information" in {
      val twitterObj = Twitter()
      twitterObj.setOAuthConsumer(prop.getProperty("oauth.consumerKey"), prop.getProperty("oauth.consumerSecret"))
      twitterObj.setOAuthAccessToken(accessToken)
      // execute authorized api.
      testTwitterObjectAuthorize(twitterObj)
    }
    
    "create object without settings and after set oauth information used by ConsumerKey class" in {
      val twitterObj = Twitter()
      twitterObj.setOAuthConsumer(consumerKey)
      twitterObj.setOAuthAccessToken(accessToken)
      // execute authorized api.
      testTwitterObjectAuthorize(twitterObj)
    }
  }

  "getRateLimitStatus" should {
    // TODO RateLimitStatusはtwitter4j 3.0.0でHelpResourceに変更してメソッドインタフェースが変わってる
    "get rate limit status" in {
//      val rateLimitStatus = twitter1.getRateLimitStatus
//      rawJSON(rateLimitStatus.tw4jObj) must not equalTo(null)
//      rateLimitStatus.tw4jObj must equalTo(DataObjectFactory.createRateLimitStatus(rawJSON(rateLimitStatus.tw4jObj)))
//      rateLimitStatus.hourlyLimit must be_>(10)
//      rateLimitStatus.remainingHits must be_>(10)
      true
    }
    
    "get comparable rate limit status" in {
      // TODO RateLimitStatusはtwitter4j 3.0.0でHelpResourceに変更してメソッドインタフェースが変わってる
//      twitter1.getMentions()
//      val previousStatus = twitter1.getRateLimitStatus
//      
//      twitter1.getMentions()
//      val afterStatus = twitter1.getRateLimitStatus
//      
//      previousStatus.remainingHits must be_>(afterStatus.remainingHits)
//      previousStatus.hourlyLimit must equalTo(afterStatus.hourlyLimit)
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