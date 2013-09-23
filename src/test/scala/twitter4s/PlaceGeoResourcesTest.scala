package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4s.api.impl.PlaceGeoResourcesImpl
import twitter4s.mocked.FakeValuesUsedByMock
import java.util

@RunWith(classOf[JUnitRunner])
class PlaceGeoResourcesTest extends Specification with TwitterResourcesTestBase {
  type TargetResourcesType = PlaceGeoResourcesImpl

  mockedTwitter4j.reverseGeoCode(any[twitter4j.GeoQuery]) returns FakeValuesUsedByMock.responseList[twitter4j.Place]
  mockedTwitter4j.searchPlaces(any[twitter4j.GeoQuery]) returns FakeValuesUsedByMock.responseList[twitter4j.Place]
  mockedTwitter4j.getSimilarPlaces(any[twitter4j.GeoLocation], anyString, anyString, anyString) returns (new twitter4j.SimilarPlaces {
    def getAccessLevel: Int = ???
    def removeAll(c: util.Collection[_]): Boolean = ???
    def subList(fromIndex: Int, toIndex: Int): util.List[twitter4j.Place] = ???
    def set(index: Int, element: twitter4j.Place): twitter4j.Place = ???
    def indexOf(o: scala.Any): Int = ???
    def get(index: Int): twitter4j.Place = ???
    def retainAll(c: util.Collection[_]): Boolean = ???
    def lastIndexOf(o: scala.Any): Int = ???
    def clear() {}
    def toArray[T](a: Array[T]): Array[T] = ???
    def toArray: Array[AnyRef] = ???
    def listIterator(index: Int): util.ListIterator[twitter4j.Place] = ???
    def listIterator(): util.ListIterator[twitter4j.Place] = ???
    def size(): Int = ???
    def getToken: String = "fake token"
    def remove(index: Int): twitter4j.Place = ???
    def remove(o: scala.Any): Boolean = ???
    def contains(o: scala.Any): Boolean = ???
    def getRateLimitStatus: twitter4j.RateLimitStatus = ???
    def iterator(): util.Iterator[twitter4j.Place] = ???
    def addAll(index: Int, c: util.Collection[_ <: twitter4j.Place]): Boolean = ???
    def addAll(c: util.Collection[_ <: twitter4j.Place]): Boolean = ???
    def isEmpty: Boolean = ???
    def containsAll(c: util.Collection[_]): Boolean = ???
    def add(index: Int, element: twitter4j.Place) {}
    def add(e: twitter4j.Place): Boolean = ???
    def toArray[T](a: Array[T with Object]): Array[T with Object] = ???
  })
  mockedTwitter4j.getGeoDetails(anyString) returns FakeValuesUsedByMock.place
  mockedTwitter4j.createPlace(anyString, anyString, anyString, any[twitter4j.GeoLocation], anyString) returns FakeValuesUsedByMock.place

  val twitter = new Twitter(mockedTwitter4j) with TargetResourcesType
  
  "reverseGeoCode" should {
    "call twitter4j reverseGeoCode method" in {
      twitter.reverseGeoCode(GeoQuery("tokyo")).size must equalTo(1)
      there was one(mockedTwitter4j).reverseGeoCode(GeoQuery("tokyo"))
    }
  }
  
  "searchPlaces" should {
    "call twitter4j searchPlaces method" in {
      twitter.searchPlaces(GeoQuery("okayama")).size must equalTo(1)
      there was one(mockedTwitter4j).searchPlaces(GeoQuery("okayama"))
    }
  }
  
  "getSimilarPlaces" should {
    "call twitter4j getSimilarPlaces method" in {
      twitter.getSimilarPlaces(
        GeoLocation(11.1, 22.2),
        "location name",
        "contained within",
        "street address").token must equalTo("fake token")
      there was one(mockedTwitter4j).getSimilarPlaces(GeoLocation(11.1, 22.2), "location name", "contained within", "street address")
    }
  }
  
  "getGeoDetails" should {
    "call twitter4j getGeoDetails method" in {
      twitter.getGeoDetails("fake_place_id").fullName must equalTo(FakeValuesUsedByMock.placeName)
      there was one(mockedTwitter4j).getGeoDetails("fake_place_id")
    }
  }

  "createPlace" should {
    "call twitter4j createPlace method" in {
      twitter.createPlace(
        "place name",
        "contained within",
        "token",
        GeoLocation(33.3,44.4),
        "street address").fullName must equalTo(FakeValuesUsedByMock.placeName)
      there was one(mockedTwitter4j).createPlace("place name", "contained within", "token", GeoLocation(33.3, 44.4), "street address")
    }
  }
}