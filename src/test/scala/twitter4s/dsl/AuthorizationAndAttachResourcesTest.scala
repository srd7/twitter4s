package twitter4s.dsl

import org.specs2.mutable._
import twitter4s.Twitter4sTestHelper._
import twitter4s.auth.ConsumerKey
import twitter4s.Twitter
import twitter4s.api.impl.HelpResourcesImpl

class AuthorizationAndAttachResourcesTest extends Specification with Twitter4sDslBase with Twitter4sDslTestBase {
  // mixin for test
  type ResourcesType = HelpResourcesImpl
  override lazy val twitter4sResources = new Twitter(twitter4jResources) with ResourcesType

  "attach" should {
    "create twitter4j resources with inplicit consumerKey object" in {
      implicit val impConsumerKey = ConsumerKey("implicitConsKey", "implicitConsSec")

      attach(testAccessToken)
      resources.twitter4jObj must equalTo(Twitter.buildTwitter4jInstance(impConsumerKey, testAccessToken))
    }
  }

  "authorizationURL" should {
    val authUrl = "http://api.twitter.com/oauth/authorize?oauth_token="
    val desktopAppConsKey = ConsumerKey(
      prop.getProperty("oauth.consumerKey"),
      prop.getProperty("oauth.consumerSecret"))
    val webAppConsKey = ConsumerKey(
      prop.getProperty("browser.oauth.consumerKey"),
      prop.getProperty("browser.oauth.consumerSecret"))

    "returned auth url and verifier without callback url" in {
      val actual = authorizationURL()(desktopAppConsKey)

      actual._1 must startWith(authUrl + actual._2.getToken)
    }

    "call by implicit consumerKey without callback url" in {
      implicit val impConsumerKey = desktopAppConsKey

      val actual = authorizationURL()
      actual._1 must startWith(authUrl + actual._2.getToken)
    }

    "returned auth url and verifier with callback url" in {
      val actual = authorizationURL("http://hoge.com")(webAppConsKey)
      actual._1 must startWith(authUrl + actual._2.getToken)
    }

    "call by implicit consumerKey with callback url" in {
      implicit val impConsumerKey = webAppConsKey

      val actual = authorizationURL("http://hoge.com")
      actual._1 must startWith(authUrl + actual._2.getToken)
    }
  }
}
