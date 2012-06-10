package twitter4s

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import twitter4s.Twitter4sTestHelper._

@RunWith(classOf[JUnitRunner])
class HelpMethodsTest extends Specification {
  "test" should {
    "return true" in {
      unauthenticated.test must beTrue
    }
  }
  
  "getLanguage" should {
    "get Language settings from twitter" in {
      val languages = twitter1.getLanguages
      
      languages.size() must be_>(5)
      
      val language = languages.get(0)
      
      language.getCode() must not equalTo(null)
      language.getName() must not equalTo(null)
      language.getStatus() must not equalTo(null)
    }
  }
  
  "getAPIConfiguration" should {
    "get API configuration from twitter" in {
      val conf = twitter1.getAPIConfiguration
      
      conf.getPhotoSizeLimit must equalTo(3145728)
      conf.getCharactersReservedPerMedia must equalTo(21)
      conf.getShortURLLength() must equalTo(20)
      conf.getShortURLLengthHttps() must equalTo(21)
      conf.getPhotoSizes().size() must equalTo(4)
      conf.getNonUsernamePaths().length must be_>(20)
      conf.getMaxMediaPerUpload() must equalTo(1)
    }
  }
}