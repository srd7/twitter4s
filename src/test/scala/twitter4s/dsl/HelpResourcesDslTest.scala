package twitter4s.dsl

import org.specs2.mutable._
import twitter4s.Twitter

class HelpResourcesDslTest extends Specification with Twitter4sDslTestBase with HelpResourcesDsl {
  "attach" should {
    "create twitter4j resources" in {
      attach(testConsumerKey, testAccessToken)

      resources.twitter4jObj must equalTo(Twitter.buildTwitter4jInstance(testConsumerKey, testAccessToken))
    }

    "create twitter4s resources" in {
      attach(testConsumerKey, testAccessToken)

      resources must haveInterface[ResourcesType]
    }
  }
}
