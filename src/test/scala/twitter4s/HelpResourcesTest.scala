package twitter4s

import twitter4s._
import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import Twitter4sTestHelper._
import conf.PropertyConfiguration
import api.impl.HelpResourcesImpl

@RunWith(classOf[JUnitRunner])
class HelpResourcesTest extends Specification {
  val twitterHelpResourceRole = new Twitter(twitter4jInstance(User1)) with HelpResourcesImpl
  
  "getLanguage" should {
    "get Language settings from twitter" in {
      val languages = twitterHelpResourceRole.getLanguages
      
      languages.size must be_>(5)
      
      val language = languages(0)
      
      language.getCode() must not equalTo(null)
      language.getName() must not equalTo(null)
      language.getStatus() must not equalTo(null)
    }
  }
  
  "getAPIConfiguration" should {
    "get API configuration from twitter" in {
      val conf = twitterHelpResourceRole.getAPIConfiguration
      
      conf.photoSizeLimit must equalTo(3145728)
      conf.charactersReservedPerMedia must equalTo(23)
      conf.shortURLLength must equalTo(22)
      conf.shortURLLengthHttps must equalTo(23)
      conf.photoSizes.size must equalTo(4)
      conf.nonUsernamePaths.length must be_>(20)
      conf.maxMediaPerUpload must equalTo(1)
    }
  }
  
  "getTermsOfService" should {
    "get value from twitter" in {
      twitterHelpResourceRole.getTermsOfService must not equalTo(null)
    }
  }
  
  "getPrivacyPolicy" should {
    "get value from twitter" in {
      twitterHelpResourceRole.getPrivacyPolicy must not equalTo(null)
    }
  }
}