package twitter4s.dsl

import org.specs2.mutable._
import twitter4s.auth.{AccessToken, ConsumerKey}
import twitter4s.Twitter
import twitter4s.api.impl.HelpResourcesImpl

class HelpResourcesDslTest extends Specification with HelpResourcesDsl {

  val testConsumerKey = ConsumerKey("consumerKey", "consumerSecret")
  val testAccessToken = AccessToken("accessToken", "tokenSecret")

  "attach" should {
    "create twitter4j resources" in {
      attach(testConsumerKey, testAccessToken)

      resources.twitter4jObj must equalTo(Twitter.buildTwitter4jInstance(testConsumerKey, testAccessToken))
    }

    "create twitter4s resources" in {
      attach(testConsumerKey, testAccessToken)

      resources must haveInterface[HelpResourcesImpl]
    }
  }
}
