package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import Twitter4sTestHelper.prop
import Twitter4sTestHelper.rawJSON
import Twitter4sTestHelper.rwPrivateMessage
import Twitter4sTestHelper.twitter1
import auth.ConsumerKey
import twitter4j.auth.AccessToken
import twitter4j.json.DataObjectFactory
import org.specs2.runner.JUnitRunner
import org.specs2.mock.Mockito
import twitter4s.mocked.FakeValuesUsedByMock

@RunWith(classOf[JUnitRunner])
class TwitterTest extends Specification with Mockito {
  // TODO TwitterResourcesTestBaseに集約(TargetResourcesTypeの削除が終わったら)
  val mockedTwitter4j = mock[twitter4j.Twitter]

  mockedTwitter4j.getScreenName returns(FakeValuesUsedByMock.userName)
  mockedTwitter4j.getId returns(10L)

  val twitter = new Twitter(mockedTwitter4j)

  "getAccessLevel" should {
    "application has read and write access level is READ_WRITE" in {
      twitter1.verifyCredentials.accessLevel must equalTo(TwitterResponse.READ_WRITE)
    }
  
    "application has all access level is READ_WRITE_DIRECTMESSAGES" in {
      rwPrivateMessage.verifyCredentials.accessLevel must equalTo(TwitterResponse.READ_WRITE_DIRECTMESSAGES)
    }
  }
  
  "screenName" should {
    "call twitter4j getScreenName method" in {
      twitter.screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).getScreenName
    }
  }
  
  "id" should {
    "get authorized user's id" in {
      twitter.id must equalTo(10L)
      there was one(mockedTwitter4j).getId
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