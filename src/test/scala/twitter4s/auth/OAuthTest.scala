package twitter4s.auth
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import twitter4s.Twitter4sTestHelper.prop
import twitter4s.conf.ConfigurationBuilder
import twitter4s.Authorization
import twitter4s.AuthorizationInformation
import twitter4s.Twitter
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
  
  "OAuth" should {
    "verify credential when build authorization config from properties file" in {
      val configBuilder = ConfigurationBuilder()
      configBuilder
      	.setOAuthAccessToken(prop.getProperty("id1.oauth.accessToken"))
      	.setOAuthAccessTokenSecret(prop.getProperty("id1.oauth.accessTokenSecret"))
      	.setOAuthConsumerKey(prop.getProperty("oauth.consumerKey"))
      	.setOAuthConsumerSecret(prop.getProperty("oauth.consumerSecret"))
      val twitter = Twitter(
          auth = AuthorizationInformation.isSpecifiedBy(
              Authorization(configBuilder.build())))
      twitter.verifyCredentials must not equalTo(null)
    }
  }
}