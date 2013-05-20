package twitter4s

import twitter4s._
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import org.specs2.runner.JUnitRunner
import twitter4s.conf.PropertyConfiguration
import twitter4s.api.impl.TrendsResourcesImpl

@RunWith(classOf[JUnitRunner])
class TrendsResourcesTest extends Specification {
  val geoLocation = GeoLocation(0, 0)
  
  val twitterTrendsResourceRole1 = new Twitter(twitter4jInstance(User2)) with TrendsResourcesImpl
  
  "getAvailableTrends" should {
    "get trends without localtion parameter" in {
      val locations = twitterTrendsResourceRole1.getAvailableTrends()
      
      rawJSON(locations.tw4jObj) mustNotEqual(null)
      locations(0) must equalTo(DataObjectFactory.createLocation(rawJSON(locations(0))))
      locations.size must be_>(0)
    }
    
    "get trends with location parameter" in {
      val locations = twitterTrendsResourceRole1.getAvailableTrends(geoLocation)
      
      rawJSON(locations.tw4jObj) mustNotEqual(null)
      locations.size must be_>(0)
    }
  }
  
  "getLocationTrends" should {
    "get locations trends" in {
      val locations = twitterTrendsResourceRole1.getAvailableTrends(geoLocation)
      val woeid = locations(0).getWoeid()
      val trends = twitter1.getLocationTrends(woeid)
      
      trends.tw4jObj must equalTo(DataObjectFactory.createTrends(rawJSON(trends.tw4jObj)))
      rawJSON(locations.tw4jObj) mustEqual(null)
      rawJSON(trends.tw4jObj) mustNotEqual(null)
      locations(0) mustEqual(trends.location)
      trends.length must be_>(0)
    }
    
    "throw exception if locations trends not exists" in {
      twitterTrendsResourceRole1.getLocationTrends(2345889/*woeid of Tokyo*/) must 
      throwA[Exception]
    }
  }
  
  "property tw4jObj of Trends class" should {
    "is instance of twitter4j.Trends" in {
      val locations = twitterTrendsResourceRole1.getAvailableTrends(geoLocation)
      val woeid = locations(0).getWoeid()
      val trends = twitter1.getLocationTrends(woeid).tw4jObj
      
      trends must beAnInstanceOf[twitter4j.Trends]
    }
  }
  
  "getPlaceTrends" should {
    "get trends with place id" in {
      val trends = twitterTrendsResourceRole1.getPlaceTrends(1)
      trends.location.getName mustEqual("世界中")
    } 
  }
  
  "getClosestTrends" should {
    "get location trends with geo location" in {
      val locations = twitterTrendsResourceRole1.getClosestTrends(GeoLocation(35.677248D, 139.72911D))
      locations(0).getName mustEqual("東京")
    }
  }
}