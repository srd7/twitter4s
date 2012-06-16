package twitter4s

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import twitter4j.auth.NullAuthorization
import twitter4s.Twitter4sTestHelper._
import twitter4j.auth.OAuthAuthorization

@RunWith(classOf[JUnitRunner])
class AuthorizationTest extends Specification {

  "getAuthorization" should {
    "get anoymous instance is NullAuthorization" in {
      val twitter = Twitter()
      val auth = twitter.authorization
      auth must beAnInstanceOf[NullAuthorization]
    }
    
    "get instance with illegal consumer information is OAuthAuthorization" in {
      val consumerKey = prop.getProperty("browser.oauth.consumerKey")
      val consumerSecret = prop.getProperty("browser.oauth.consumerSecret")
      
      val twitter = Twitter()
      twitter.setOAuthConsumer(consumerKey, consumerSecret)
      twitter.setOAuthConsumer(consumerKey, consumerSecret) must
      throwA[IllegalStateException]
      
      val auth = twitter.authorization
      auth must beAnInstanceOf[OAuthAuthorization]
    }
  }
}