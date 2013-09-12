package twitter4s.dsl

import org.specs2.mutable._
import org.specs2.mock.Mockito
import twitter4s.Twitter
import twitter4s.auth.{AccessToken, ConsumerKey, RequestToken}
import twitter4j.{Status, URLEntity, RateLimitStatus}
import java.net.URL
import java.util.Date
import twitter4s.api.impl.UsersResourcesImpl

class AuthorizationCallbackTest extends Specification with Twitter4sDslBase with Mockito with Twitter4sDslTestBase {
  // mixin for test
  type ResourcesType = HelpResourcesDsl
  override lazy val twitter4sResources = new Twitter(twitter4jResources) with ResourcesType

  val testRequestToken = RequestToken("testReqToken", "testTokenSeq")
  val expectedAccessToken = AccessToken("userAccessToken", "userTokenSecret")
  implicit val impConsKey = testConsumerKey

  val mockedAuthTwitter = mock[twitter4j.Twitter]
  mockedAuthTwitter.getOAuthAccessToken(testRequestToken, "verifier") returns expectedAccessToken
  mockedAuthTwitter.verifyCredentials() returns (new twitter4j.User {
    def getAccessLevel: Int = ???
    def getBiggerProfileImageURL: String = ???
    def isProtected: Boolean = ???
    def isTranslator: Boolean = ???
    def getProfileLinkColor: String = ???
    def getProfileImageURL: String = ???
    def getProfileBackgroundImageUrl: String = ???
    def getProfileBannerIPadRetinaURL: String = ???
    def getMiniProfileImageURLHttps: String = ???
    def getProfileSidebarFillColor: String = ???
    def getScreenName: String = ???
    def getListedCount: Int = ???
    def getOriginalProfileImageURLHttps: String = ???
    def isProfileBackgroundTiled: Boolean = ???
    def isProfileUseBackgroundImage: Boolean = ???
    def getUtcOffset: Int = ???
    def getProfileSidebarBorderColor: String = ???
    def isContributorsEnabled: Boolean = ???
    def getTimeZone: String = ???
    def getName: String = ???
    def getCreatedAt: Date = ???
    def getDescriptionURLEntities: Array[URLEntity] = ???
    def getURL: String = ???
    def getLang: String = ???
    def getId: Long = 1L
    def getProfileImageURLHttps: String = ???
    def getStatus: Status = ???
    def getMiniProfileImageURL: String = ???
    def getDescription: String = ???
    def getProfileBannerRetinaURL: String = ???
    def getFollowersCount: Int = ???
    def isGeoEnabled: Boolean = ???
    def getURLEntity: URLEntity = ???
    def getProfileBackgroundColor: String = ???
    def isFollowRequestSent: Boolean = ???
    def getProfileBannerMobileURL: String = ???
    def compareTo(o: twitter4j.User): Int = ???
    def getFavouritesCount: Int = ???
    def getProfileBannerURL: String = ???
    def getProfileBackgroundImageUrlHttps: String = ???
    def getProfileBackgroundImageURL: String = ???
    def isVerified: Boolean = ???
    def getProfileImageUrlHttps: URL = ???
    def getRateLimitStatus: RateLimitStatus = ???
    def getLocation: String = ???
    def getFriendsCount: Int = ???
    def getProfileBannerMobileRetinaURL: String = ???
    def getProfileTextColor: String = ???
    def getStatusesCount: Int = ???
    def isShowAllInlineMedia: Boolean = ???
    def getProfileBannerIPadURL: String = ???
    def getOriginalProfileImageURL: String = ???
    def getBiggerProfileImageURLHttps: String = ???
  })

  def assertAuthTwitterCalled = {
    there was atLeastOne(mockedAuthTwitter).getOAuthAccessToken(testRequestToken, "verifier")
    there was atLeastOne(mockedAuthTwitter).verifyCredentials()
  }

  override def twitterForAuth(implicit consumerKey: ConsumerKey) = {
    // for mocked twitter4j object injection to dsl
    val twitter = new Twitter(mockedAuthTwitter) with UsersResourcesImpl
    twitter.setOAuthConsumer(consumerKey)

    twitter
  }

  "userAccessToken" should {
    // method name, getUserAccessToken is better?
    "returned user id and request token" in {
      userAccessToken(testRequestToken, "verifier")(testConsumerKey) must equalTo((1, expectedAccessToken))
      assertAuthTwitterCalled
    }

    "call by implicit consumerKey" in {
      userAccessToken(testRequestToken, "verifier") must equalTo((1, expectedAccessToken))
      assertAuthTwitterCalled
    }
  }

  // mocked storer, for dsl test.
  override val storeExecutor = mock[AccessTokenStoreExecutor]
  storeExecutor.store(1, expectedAccessToken) returns((1, expectedAccessToken))

  "andThenStore" should {
    "implicit conversion with userAccessToken returns" in {
      (userAccessToken(testRequestToken, "verifier") andThenStore) must equalTo((1, expectedAccessToken))
      assertAuthTwitterCalled
      there was one(storeExecutor).store(1, expectedAccessToken)
    }
  }
}
