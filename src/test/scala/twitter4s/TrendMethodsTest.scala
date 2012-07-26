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
  
  // Local Trends Methods
  "getAvailableTrends" should {
    "get trends without localtion parameter" in {
      val locations = twitter1.getAvailableTrends()
      
      rawJSON(locations.tw4jObj) mustNotEqual(null)
      locations(0) must equalTo(DataObjectFactory.createLocation(rawJSON(locations(0))))
      locations.size must be_>(0)
    }
    
    "get trends with location parameter" in {
      val locations = twitter1.getAvailableTrends(geoLocation)
      
      rawJSON(locations.tw4jObj) mustNotEqual(null)
      locations.size must be_>(0)
    }
  }
  
  "getLocationTrends" should {
    "get locations trends" in {
      val locations = twitter1.getAvailableTrends(geoLocation)
      val woeid = locations(0).getWoeid()
      val trends = twitter1.getLocationTrends(woeid)
      
      trends.tw4jObj must equalTo(DataObjectFactory.createTrends(rawJSON(trends.tw4jObj)))
      rawJSON(locations.tw4jObj) mustEqual(null)
      rawJSON(trends.tw4jObj) mustNotEqual(null)
      locations(0) mustEqual(trends.location)
      trends.length must be_>(0)
    }
    
    "throw exception if locations trends not exists" in {
      twitter1.getLocationTrends(2345889/*woeid of Tokyo*/) must 
      throwA[Exception]
    }
  }

}