package twitter4s

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import Twitter4sTestHelper._
import org.specs2.runner.JUnitRunner
import twitter4s.api.impl.TrendsResourcesImpl
import twitter4s.mocked.FakeValuesUsedByMock

@RunWith(classOf[JUnitRunner])
class TrendsResourcesTest extends Specification with TwitterResourcesTestBase {
  type TargetResourcesType = TrendsResourcesImpl

  val twitter = new Twitter(mockedTwitter4j) with TargetResourcesType

  mockedTwitter4j.getAvailableTrends returns FakeValuesUsedByMock.responseList[twitter4j.Location]
  mockedTwitter4j.getAvailableTrends(any[twitter4j.GeoLocation]) returns FakeValuesUsedByMock.responseList[twitter4j.Location]
  mockedTwitter4j.getLocationTrends(anyInt) returns FakeValuesUsedByMock.trends
  mockedTwitter4j.getPlaceTrends(anyInt) returns FakeValuesUsedByMock.trends
  mockedTwitter4j.getClosestTrends(any[twitter4j.GeoLocation]) returns FakeValuesUsedByMock.responseList[twitter4j.Location]

  val geoLocation = GeoLocation(0, 0)
  
  val twitterTrendsResourceRole1 = new Twitter(twitter4jInstance(User2)) with TrendsResourcesImpl
  
  "getAvailableTrends" should {
    "call twitter4j getAvailableTrends without location" in {
      twitter.getAvailableTrends().size must equalTo(1)
      there was one(mockedTwitter4j).getAvailableTrends
    }

    "call twitter4j getAvailableTrends by location" in {
      twitter.getAvailableTrends(GeoLocation(11.1, 22.2)).size must equalTo(1)
      there was one(mockedTwitter4j).getAvailableTrends(GeoLocation(11.1, 22.2))
    }
  }
  
  "getLocationTrends" should {
    "call twitter4j getLocationTrends method" in {
      twitter.getLocationTrends(3).accessLevel must equalTo(TwitterResponse.READ)
      there was one(mockedTwitter4j).getLocationTrends(3)
    }
  }
  
  "getPlaceTrends" should {
    "call twitter4j getPlaceTrends method" in {
      twitter.getPlaceTrends(4).accessLevel must equalTo(TwitterResponse.READ)
      there was one(mockedTwitter4j).getPlaceTrends(4)
    }
  }
  
  "getClosestTrends" should {
    "call twitter4j getClosestTrends method" in {
      twitter.getClosestTrends(GeoLocation(55.5,66.6)).size must equalTo(1)
      there was one(mockedTwitter4j).getClosestTrends(GeoLocation(55.5,66.6))
    }
  }
}