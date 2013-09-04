package twitter4s

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import api.impl.HelpResourcesImpl
import org.specs2.mock.Mockito
import twitter4j.api.HelpResources.Language
import java.util
import twitter4j.MediaEntity.Size

@RunWith(classOf[JUnitRunner])
class HelpResourcesTest extends Specification with Mockito {
  val twitter4jMock = mock[twitter4j.Twitter]
  twitter4jMock.getLanguages returns (new twitter4j.ResponseList[Language] {
    def getAccessLevel: Int = ???
    def removeAll(c: util.Collection[_]): Boolean = ???
    def subList(fromIndex: Int, toIndex: Int): util.List[Language] = ???
    def set(index: Int, element: Language): Language = ???
    def indexOf(o: scala.Any): Int = ???
    def get(index: Int): Language = ???
    def retainAll(c: util.Collection[_]): Boolean = ???
    def lastIndexOf(o: scala.Any): Int = ???
    def clear() {}
    def toArray[T](a: Array[T]): Array[T] = ???
    def toArray: Array[AnyRef] = ???
    def listIterator(index: Int): util.ListIterator[Language] = ???
    def listIterator(): util.ListIterator[Language] = ???
    def size(): Int = 9
    def remove(index: Int): Language = ???
    def remove(o: scala.Any): Boolean = ???
    def contains(o: scala.Any): Boolean = ???
    def getRateLimitStatus: twitter4j.RateLimitStatus = ???
    def iterator(): util.Iterator[Language] = ???
    def addAll(index: Int, c: util.Collection[_ <: Language]): Boolean = ???
    def addAll(c: util.Collection[_ <: Language]): Boolean = ???
    def isEmpty: Boolean = ???
    def containsAll(c: util.Collection[_]): Boolean = ???
    def add(index: Int, element: Language) {}
    def add(e: Language): Boolean = ???
    def toArray[T](x: Array[T with Object]): Array[T with Object] = ???
  })
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
      
      languages.size must equalTo(9)
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