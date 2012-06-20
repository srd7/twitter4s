package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4s.Twitter4sTestHelper._
import twitter4j.GeoQuery
import twitter4j.GeoLocation
import twitter4j.json.DataObjectFactory
import twitter4j.Place
import twitter4j.TwitterException
import twitter4j.StatusUpdate
import java.util.Date
import twitter4s.implicits.Twitter4SImplicits._

@RunWith(classOf[JUnitRunner])
class GeoMethodsTest extends Specification {
  
  private def testPlaces(target: ResponseList[Place]) = {
    rawJSON(target.twt4jResponseList) must not equalTo(null)
    target(0) must equalTo(DataObjectFactory.createPlace(rawJSON(target(0))))
    target.size must be_>(0)
  }
  
  "reverseGeoCode" should {
    "get no places if location is (0,0)" in {
      val query = new GeoQuery(new GeoLocation(0, 0))
      val places = twitter1.reverseGeoCode(query)
      places.size must equalTo(0)
    }
    
    "get place list if location is exists" in {
      val query = new GeoQuery(new GeoLocation(37.78215, -122.40060))
      testPlaces(twitter1.reverseGeoCode(query))
    }
  }
  
  "searchPlaces" should {
    "get place list with location" in {
      val query = new GeoQuery(new GeoLocation(37.78215, -122.40060))
      testPlaces(twitter1.searchPlaces(query))
    }
  }
  
  "getSimilarPlaces" should {
    "get similar place list with parameter location" in {
      val places = twitter1.getSimilarPlaces(new GeoLocation(37.78215, -122.40060), "SoMa", null, null)
      rawJSON(places) must not equalTo(null)
      places.get(0) must equalTo(DataObjectFactory.createPlace(rawJSON(places.get(0))))
      places.size() must be_>(0)
    }
  }
  
  "getGeoDetails" should {
    "get detail location information" in {
      try {
        val place = unauthenticated.getGeoDetails("5a110d312052166f")
        rawJSON(place) must not equalTo(null)
        place must equalTo(DataObjectFactory.createPlace(rawJSON(place)))
        place.getFullName() must equalTo("San Francisco, CA")
        place.getContainedWithIn()(0).getFullName() must equalTo("California, US")
      } catch {
        // is being rate limited
        case te: TwitterException => te.getStatusCode must equalTo(404)
      }
    }
  }
  
  "updateStatus(StatusMethods)" should {
    "update user status with place information" in {
      val sanFrancisco = "5a110d312052166f"
      val latestStatus = new StatusUpdate(new Date() + " status with place")
      val status = twitter3.updateStatus(latestStatus = Some(latestStatus.placeId(sanFrancisco)))
      rawJSON(status) must not equalTo(null)
      status must equalTo(DataObjectFactory.createStatus(rawJSON(status)))
      status.getPlace().getId() must equalTo(sanFrancisco)
      status.getContributors() must equalTo(null)
    }
    
    "update user status with geo information" in {
      val latitude = 12.3456d
      val longitude = -34.5678d
      val latestStatus = new StatusUpdate(new Date() + " status with geo")
      
      val statusWithGeo = twitter3.updateStatus(latestStatus = Some(latestStatus.location(new GeoLocation(latitude, longitude))))
      rawJSON(statusWithGeo) must not equalTo(null)
      statusWithGeo must equalTo(DataObjectFactory.createStatus(rawJSON(statusWithGeo)))
      statusWithGeo.getUser().isGeoEnabled() must beTrue
      statusWithGeo.getGeoLocation().getLatitude() must equalTo(latitude)
      statusWithGeo.getGeoLocation().getLongitude() must equalTo(longitude)
      twitter1.verifyCredentials.isGeoEnabled() must beFalse
    }
  }
}