package twitter4s.dsl

import org.specs2.mutable._
import twitter4s.Twitter4sTestHelper._
import twitter4s.auth.ConsumerKey
import twitter4s.Twitter
import twitter4s.api.impl.HelpResourcesImpl

class Twitter4sDslBaseTest extends Specification with Twitter4sDslBase {
  // mixin for test
  type ResourcesType = HelpResourcesImpl
  override lazy val twitter4sResources = new Twitter(twitter4jResources) with ResourcesType

  "authorizationURL" should {
    "returned auth url without callback url" in {
      val testConsumerKey = ConsumerKey(
        prop.getProperty("oauth.consumerKey"),
        prop.getProperty("oauth.consumerSecret"))
      val actual = authorizationURL(testConsumerKey)

      actual must startWith("http://api.twitter.com/oauth/authorize?oauth_token")
    }

    "returned auth url with callback url" in {
      val testConsumerKey = ConsumerKey(
        prop.getProperty("browser.oauth.consumerKey"),
        prop.getProperty("browser.oauth.consumerSecret"))
      val actual = authorizationURL(testConsumerKey, "http://hoge.com")

      print(actual)
      actual must not startWith("http://api.twitter.com/oauth/authorize?")
    }
  }
}
