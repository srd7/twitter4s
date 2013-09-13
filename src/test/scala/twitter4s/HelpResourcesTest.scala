package twitter4s

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import api.impl.HelpResourcesImpl
import org.specs2.mock.Mockito
import twitter4j.api.HelpResources.Language
import java.util
import twitter4j.MediaEntity.Size
import twitter4s.mocked.FakeValuesUsedByMock

@RunWith(classOf[JUnitRunner])
class HelpResourcesTest extends Specification with Mockito {
  val twitter4jMock = mock[twitter4j.Twitter]
  twitter4jMock.getLanguages returns FakeValuesUsedByMock.responseList[Language]
  twitter4jMock.getAPIConfiguration returns (new twitter4j.TwitterAPIConfiguration {
    def getAccessLevel: Int = 3 // Read Write DM
    def getMaxMediaPerUpload: Int = ???
    def getPhotoSizeLimit: Int = ???
    def getShortURLLengthHttps: Int = ???
    def getRateLimitStatus: twitter4j.RateLimitStatus = ???
    def getNonUsernamePaths: Array[String] = ???
    def getShortURLLength: Int = ???
    def getPhotoSizes: util.Map[Integer, Size] = ???
    def getCharactersReservedPerMedia: Int = ???
  })
  twitter4jMock.getTermsOfService returns "call getTermsOfService of mock"
  twitter4jMock.getPrivacyPolicy returns "call getPrivacyPolicy of mock"

  val twitterHelpResourceRole = new Twitter(twitter4jMock) with HelpResourcesImpl
  
  "getLanguage" should {
    "get Language settings from twitter" in {
      val languages = twitterHelpResourceRole.getLanguages
      
      languages.size must equalTo(1)
      there was one(twitter4jMock).getLanguages
    }
  }
  
  "getAPIConfiguration" should {
    "get API configuration from twitter" in {
      val conf = twitterHelpResourceRole.getAPIConfiguration

      conf.accessLevel must equalTo(TwitterResponse.READ_WRITE_DIRECTMESSAGES)
      there was one(twitter4jMock).getAPIConfiguration
    }
  }
  
  "getTermsOfService" should {
    "get value from twitter" in {
      twitterHelpResourceRole.getTermsOfService must equalTo("call getTermsOfService of mock")
      there was one(twitter4jMock).getTermsOfService
    }
  }
  
  "getPrivacyPolicy" should {
    "get value from twitter" in {
      twitterHelpResourceRole.getPrivacyPolicy must equalTo("call getPrivacyPolicy of mock")
      there was one(twitter4jMock).getPrivacyPolicy
    }
  }
}