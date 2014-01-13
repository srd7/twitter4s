package twitter4s

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import twitter4j.api.HelpResources.Language
import twitter4s.mocked.FakeValuesUsedByMock

@RunWith(classOf[JUnitRunner])
class HelpResourcesTest extends Specification with TwitterResourcesTestBase {
  // mocking methods
  mockedTwitter4j.getLanguages returns FakeValuesUsedByMock.responseList[Language]
  mockedTwitter4j.getAPIConfiguration returns FakeValuesUsedByMock.apiConfiguration
  mockedTwitter4j.getTermsOfService returns "call getTermsOfService of mock"
  mockedTwitter4j.getPrivacyPolicy returns "call getPrivacyPolicy of mock"
  
  "getLanguage" should {
    "get Language settings from twitter" in {
      twitter.help.getLanguages.size must equalTo(1)
      there was one(mockedTwitter4j).getLanguages
    }
  }
  
  "getAPIConfiguration" should {
    "get API configuration from twitter" in {
      twitter.help.getAPIConfiguration.accessLevel must equalTo(TwitterResponse.READ_WRITE_DIRECTMESSAGES)
      there was one(mockedTwitter4j).getAPIConfiguration
    }
  }
  
  "getTermsOfService" should {
    "get value from twitter" in {
      twitter.help.getTermsOfService must equalTo("call getTermsOfService of mock")
      there was one(mockedTwitter4j).getTermsOfService
    }
  }
  
  "getPrivacyPolicy" should {
    "get value from twitter" in {
      twitter.help.getPrivacyPolicy must equalTo("call getPrivacyPolicy of mock")
      there was one(mockedTwitter4j).getPrivacyPolicy
    }
  }
}