package twitter4s

import org.specs2.mutable._
import twitter4s.auth.{AccessToken, ConsumerKey}

class TwitterObjectTest extends Specification {

  val testConsumerKey = ConsumerKey("consumerKey", "consumerSecret")
  val testAccessToken = AccessToken("accessToken", "tokenSecret")

  "buildTwitter4jInstance" should {
    "get any instance" in {
      Twitter.buildTwitter4jInstance(
        testConsumerKey,
        testAccessToken) must not equalTo(null)
    }

    "get instance has twitter4J class" in {
      Twitter.buildTwitter4jInstance(
        testConsumerKey,
        testAccessToken) must haveInterface[twitter4j.Twitter]
    }
  }

  "apply" should {
    "get any instance" in {
      Twitter(testConsumerKey, testAccessToken) must not equalTo(null)
    }

//    "get instance has twitter4S class" in {
//      Twitter(testConsumerKey, testAccessToken) must haveInterface[twitter4s.Twitter]
//    }
  }
}
