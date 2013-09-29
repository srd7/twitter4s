package twitter4s.dsl

import org.specs2.mutable._
import twitter4s.api.impl.HelpResourcesImpl
import org.specs2.mock.Mockito
import twitter4s.Twitter
import twitter4s.auth.ConsumerKey
import twitter4j.auth.AccessToken

class AttachResourcesTest extends Specification with Twitter4sDslBase with Twitter4sDslTestBase with Mockito {
  twitter4jResources = mock[twitter4j.Twitter]
  var callForSetOAuthConsumer: String = _
  twitter4jResources.setOAuthConsumer(anyString, anyString) answers
    { _ => callForSetOAuthConsumer = "call for set auth consumer"}
  var callForSetOAuthAccessToken: String = _
  twitter4jResources.setOAuthAccessToken(any[AccessToken]) answers
    { _ => callForSetOAuthAccessToken = "call for set access token"}

  // mixin for test
  type ResourcesType = HelpResourcesImpl
  override lazy val twitter4sResources = new Twitter(twitter4jResources) with ResourcesType

  "attach" should {
    "create twitter4j resources with inplicit consumerKey object" in {
      implicit val impConsumerKey = ConsumerKey("implicitConsKey", "implicitConsSec")

      attach(testAccessToken)

      callForSetOAuthConsumer must equalTo("call for set auth consumer")
      there was one(twitter4jResources).setOAuthConsumer(impConsumerKey.consumerKey, impConsumerKey.consumerSecret)

      callForSetOAuthAccessToken must equalTo("call for set access token")
      there was one(twitter4jResources).setOAuthAccessToken(testAccessToken)
    }
  }
}
