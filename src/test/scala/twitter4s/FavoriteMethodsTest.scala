package twitter4s
import org.specs2.mutable.Specification
import twitter4j.json.DataObjectFactory
import twitter4s.Twitter4sTestHelper._
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import twitter4j.TwitterException

@RunWith(classOf[JUnitRunner])
class FavoriteMethodsTest extends Specification {
  "createFavorite" should {
    "mark a tweet as favorite" in {
      // get anyone's tweet from timeline
      // and test getHomeTimeline API method
      val anyonesStatus = twitter1.getHomeTimeline()(0)
      DataObjectFactory.getRawJSON(anyonesStatus) must not equalTo(null)
      anyonesStatus must equalTo(DataObjectFactory.createStatus(DataObjectFactory.getRawJSON(anyonesStatus)))
      
      // mark favorite and destroy
      val status = twitter2.createFavorite(anyonesStatus.getId())
      DataObjectFactory.getRawJSON(status) must not equalTo(null)
      status must equalTo(DataObjectFactory.createStatus(DataObjectFactory.getRawJSON(status)))
      twitter2.getFavorites()().size must be_>(0)
      try {
        twitter2.destroyFavorite(anyonesStatus.getId())
      } catch {
        case te: TwitterException =>
          // sometimes destroying favorite fails with 404
          te.getStatusCode() must equalTo(404)
      }
      true
    }
  }
}