package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import twitter4j.TwitterException
import twitter4s._

@RunWith(classOf[JUnitRunner])
class FavoritesResourcesTest extends Specification {
  
  "createFavorite" should {
    "mark a tweet as favorite" in {
      // get anyone's tweet from timeline
      // and test getHomeTimeline API method
      val anyonesStatus: Status = twitter1.getHomeTimeline()(0)
      rawJSON(anyonesStatus.tw4jObj) must not equalTo(null)
      anyonesStatus.tw4jObj must equalTo(DataObjectFactory.createStatus(rawJSON(anyonesStatus.tw4jObj)))
      
      // mark favorite and destroy
      val status = twitter2.createFavorite(anyonesStatus.id)
      rawJSON(status.tw4jObj) must not equalTo(null)
      status.tw4jObj must equalTo(DataObjectFactory.createStatus(rawJSON(status.tw4jObj)))
      
      twitter2.getFavorites().size must be_>(0)
      twitter2.getFavorites(User.isSpecifiedBy(id1.screenName)).size must be_>(0)
      twitter2.getFavorites(User.isSpecifiedBy(id1.screenName), Paging(1)).size must be_>(0) 
      twitter2.getFavorites(User.isSpecifiedBy(id1.id)).size must be_>(0)
      twitter2.getFavorites(User.isSpecifiedBy(id1.id), Paging(1)).size must be_>(0)
      try {
        twitter2.destroyFavorite(anyonesStatus.id)
      } catch {
        case te: TwitterException =>
          // sometimes destroying favorite fails with 404
          te.getStatusCode() must equalTo(404)
      }
      true
    }
  }
}