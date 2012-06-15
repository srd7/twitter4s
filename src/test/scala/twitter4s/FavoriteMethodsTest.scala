package twitter4s
import org.specs2.mutable.Specification
import twitter4j.json.DataObjectFactory
import twitter4s.Twitter4sTestHelper._
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import twitter4j.TwitterException
import twitter4j.Status

@RunWith(classOf[JUnitRunner])
class FavoriteMethodsTest extends Specification {
  
  private def testStatus(target: Status) = {
    rawJSON(target) must not equalTo(null)
    target must equalTo(DataObjectFactory.createStatus(rawJSON(target)))
  }
  
  "createFavorite" should {
    "mark a tweet as favorite" in {
      // get anyone's tweet from timeline
      // and test getHomeTimeline API method
      val anyonesStatus = twitter1.getHomeTimeline()(0)
      testStatus(anyonesStatus)
      
      // mark favorite and destroy
      val status = twitter2.createFavorite(anyonesStatus.getId())
      testStatus(status)
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