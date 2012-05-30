package twitter4s

import org.specs2.mutable._
import twitter4j._
import json.DataObjectFactory
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class TrendMethodsTest extends Specification {
  val geoLocation = new GeoLocation(0, 0)
  
  "getAvailableTrends" should {
    "get trends without localtion parameter" in {
      val locations = Twitter().getAvailableTrends()
      
      DataObjectFactory.getRawJSON(locations) mustNotEqual(null)
      locations.get(0) must equalTo(DataObjectFactory.createLocation(DataObjectFactory.getRawJSON(locations.get(0))))
      (locations.size > 0) must beTrue
    }
    
    "get trends with location parameter" in {
      val locations = Twitter().getAvailableTrends(Some(geoLocation))
      
      DataObjectFactory.getRawJSON(locations) mustNotEqual(null)
      (locations.size() > 0) must beTrue
    }
  }
  
  "getLocationTrends" should {
    "get locations trends" in {
      val locations = Twitter().getAvailableTrends(Some(geoLocation))
      val woeid = locations.get(0).getWoeid()
      val trends = Twitter().getLocationTrends(woeid)
      
      trends must equalTo(DataObjectFactory.createTrends(DataObjectFactory.getRawJSON(trends)))
      DataObjectFactory.getRawJSON(locations) mustEqual(null)
      DataObjectFactory.getRawJSON(trends) mustNotEqual(null)
      locations.get(0) mustEqual(trends.getLocation())
      (trends.getTrends().size > 0) must beTrue
    }
  }

}