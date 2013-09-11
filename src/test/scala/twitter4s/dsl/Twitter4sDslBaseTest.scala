package twitter4s.dsl

import org.specs2.mutable._
import twitter4s.Twitter4sTestHelper._
import twitter4s.auth.ConsumerKey
import twitter4s.Twitter
import twitter4s.api.impl.HelpResourcesImpl

class Twitter4sDslBaseTest extends Specification with Twitter4sDslBase with Twitter4sDslTestBase {
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

    "returned auth url without callback url" in {
      authorizationURL()(desktopAppConsKey) must startWith(authUrl)
    }

    "call by implicit consumerKey without callback url" in {
      implicit val impConsumerKey = desktopAppConsKey

      authorizationURL() must startWith(authUrl)
    }

    "returned auth url with callback url" in {
      authorizationURL("http://hoge.com")(webAppConsKey) must startWith(authUrl)
    }

    "call by implicit consumerKey with callback url" in {
      implicit val impConsumerKey = webAppConsKey

      authorizationURL("http://hoge.com") must startWith(authUrl)
    }
  }
}
