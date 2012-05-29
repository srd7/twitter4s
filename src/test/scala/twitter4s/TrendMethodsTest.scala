package twitter4s

import org.specs2.mutable._
import twitter4j._
import json.DataObjectFactory
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class TrendMethodsTest extends Specification {
  
  "LocalTrendMethods" should {
    "get trends without localtion parameter" in {
      val locations = Twitter().getAvailableTrends()
      
      DataObjectFactory.getRawJSON(locations) mustNotEqual(null)
      locations.get(0) must equalTo(DataObjectFactory.createLocation(DataObjectFactory.getRawJSON(locations.get(0))))
      (locations.size > 0) must beTrue
    }
  }

}