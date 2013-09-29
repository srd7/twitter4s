package twitter4s.dsl

import org.specs2.mutable._
import twitter4s.{TwitterResponse, Twitter, ResponseList, TwitterAPIConfiguration}
import twitter4j.api.HelpResources.Language
import org.specs2.mock.Mockito
import twitter4s.mocked.FakeValuesUsedByMock
import twitter4s.api.HelpResources
import twitter4s.api.impl.HelpResourcesImpl
import java.util
import twitter4j.MediaEntity.Size

class HelpResourcesDslTest extends Specification with Twitter4sDslTestBase with HelpResourcesDsl with Mockito {
  twitter4jResources = mock[twitter4j.Twitter]

  var callForSetOAuthConsumer: String = _
  twitter4jResources.setOAuthConsumer(testConsumerKey.consumerKey, testConsumerKey.consumerSecret) answers
    { _ => callForSetOAuthConsumer = "call for set auth consumer"}
  var callForSetOAuthAccessToken: String = _
  twitter4jResources.setOAuthAccessToken(testAccessToken) answers
    { _ => callForSetOAuthAccessToken = "call for set access token"}
  twitter4jResources.getLanguages returns FakeValuesUsedByMock.responseList[twitter4j.api.HelpResources.Language]
  twitter4jResources.getAPIConfiguration returns FakeValuesUsedByMock.apiConfiguration



  "attach" should {
    "create twitter4s resources" in {
      attach(testAccessToken)(testConsumerKey)

      resources must haveInterface[ResourcesType]

      // bellow specs to base trait test
      callForSetOAuthConsumer must equalTo("call for set auth consumer")
      there was one(twitter4jResources).setOAuthConsumer(testConsumerKey.consumerKey, testConsumerKey.consumerSecret)

      callForSetOAuthAccessToken must equalTo("call for set access token")
      there was one(twitter4jResources).setOAuthAccessToken(testAccessToken)
    }
  }

  "get" should {
    "with Language context" in {
      "returns ResourceContextBuilder" in {
        get(Languages) must haveClass[ResourceContextBuilder[ParameterNothing.type, ResponseList[Language]]]
      }

      "have conditions is ParameterNothing" in {
        get(Languages).conditions must equalTo(ParameterNothing)
      }

      "call getLanguage method when do execute" in {
        get(Languages).execute.size must equalTo(1)
        there was one(twitter4jResources).getLanguages
      }
    }

    "with APIConfiguration Context" in {
      "returns ResourceContextBuilder" in {
        get(APIConfiguration) must haveClass[ResourceContextBuilder[ParameterNothing.type, TwitterAPIConfiguration]]
      }

      "have condition is ParameterNothing" in {
        get(APIConfiguration).conditions must equalTo(ParameterNothing)
      }

      "call getAPIConfiguration method when do execute" in {
        get(APIConfiguration).execute.accessLevel must equalTo(TwitterResponse.READ_WRITE_DIRECTMESSAGES)
        there was one(twitter4jResources).getAPIConfiguration
      }
    }

    "with TermsOfService Context" in {
      "returns ResourceContextBuilder" in {
        get(TermsOfService) must haveClass[ResourceContextBuilder[ParameterNothing.type, String]]
      }

      "have condition is ParameterNothing" in {
        get(TermsOfService).conditions must equalTo(ParameterNothing)
      }
    }
  }

//  get(languages) where {
//    User isSpecifiedBy "hogehoge" and
//      Paging 5
//  }
}
