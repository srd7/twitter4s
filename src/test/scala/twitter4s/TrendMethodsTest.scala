package twitter4s

import twitter4s._
import org.junit.runner.RunWith
import org.specs2.mutable._
import org.specs2.runner.JUnitRunner

import twitter4j.json.DataObjectFactory
import twitter4j._
import Twitter4sTestHelper._

@RunWith(classOf[JUnitRunner])
class TrendMethodsTest extends Specification {
  val geoLocation = new GeoLocation(0, 0)
  
  // Available trend at location from twitter1
  // Location trend of woeid from twitter1

  // twitter1.from(Avalable trend at location)
  // new TrendHelper(Available).trend.at(location).from(Twitter)
  // new TrendHelper(Location).trend.of(woeid).from(Twitter)
  
  // from((Available, Option(GeoLocation)), Twitter) => ResponseList[Location]
  // from: (Location, woeid) => Trends

  // new TrendHelper(Available)
  // new Trend(Location)
  
  // Local Trends Methods
  "getAvailableTrends" should {
    "get trends without localtion parameter" in {
      val locations = twitter1.getAvailableTrends()
      
      rawJSON(locations.tw4jObj) mustNotEqual(null)
      locations(0) must equalTo(DataObjectFactory.createLocation(rawJSON(locations(0))))
      (locations.size > 0) must beTrue
    }
    
    "get trends with location parameter" in {
      val locations = twitter1.getAvailableTrends(geoLocation)
      
      rawJSON(locations.tw4jObj) mustNotEqual(null)
      (locations.size > 0) must beTrue
    }
  }
  
  "getLocationTrends" should {
    "get locations trends" in {
      val locations = twitter1.getAvailableTrends(geoLocation)
      val woeid = locations(0).getWoeid()
      val trends = twitter1.getLocationTrends(woeid)
      
      trends must equalTo(DataObjectFactory.createTrends(rawJSON(trends)))
      rawJSON(locations) mustEqual(null)
      rawJSON(trends) mustNotEqual(null)
      locations(0) mustEqual(trends.getLocation())
      (trends.getTrends().size > 0) must beTrue
    }
    
    "throw exception if locations trends not exists" in {
      twitter1.getLocationTrends(2345889/*woeid of Tokyo*/) must 
      throwA[Exception]
    }
  }

}