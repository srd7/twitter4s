package twitter4s

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4j.auth.AccessToken
import Twitter4sTestHelper._
import auth.ConsumerKey
import twitter4j.TwitterResponse
import twitter4s._
import twitter4j.json.DataObjectFactory

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
  
  "Twitter object" should {
    val consumerKey = new ConsumerKey(prop.getProperty("oauth.consumerKey"), prop.getProperty("oauth.consumerSecret"))
    val accessToken = new AccessToken(prop.getProperty("id1.oauth.accessToken"), prop.getProperty("id1.oauth.accessTokenSecret"))
    
    def testTwitterObjectAuthorize(target: Twitter) = {
      val user = target.verifyCredentials
      rawJSON(user) must not equalTo(null)
      user must equalTo(DataObjectFactory.createUser(rawJSON(user)))
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
}