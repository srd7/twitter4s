package twitter4s.auth
import twitter4s._
import Twitter4sTestHelper._
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class OAuthTest extends Specification {
  "Twitter object deterministic" should {
    "be equal in same consumerKey" in {
      val twitterObject1 = Twitter()
      val twitterObject2 = Twitter()
      
      val consumerKey = ConsumerKey(prop.getProperty("oauth.consumerKey"), prop.getProperty("oauth.consumerSecret"))
      
      twitterObject1.setOAuthConsumer(consumerKey)
      twitterObject2.setOAuthConsumer(consumerKey)
      
      twitterObject1 must equalTo(twitterObject2)
    }
  }
}