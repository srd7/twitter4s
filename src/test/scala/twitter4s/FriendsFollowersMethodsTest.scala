package twitter4s
import twitter4s._
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory

@RunWith(classOf[JUnitRunner])
class FriendsFollowersMethodsTest extends Specification {
  val obamaScreenName = "barackobama"
  val obamaId = 813286

  "getFriedsIDs" should {
    val ryunosukey = 48528137
    "get friend ID array specified cursor" in {
      val ids = twitter1.getFriendsIDs(-1)
      rawJSON(ids.tw4jObj) must not equalTo(null)
      val yusuke = 4933401
      atLeastOnce(ids.ids) { (_:Long) must equalTo(yusuke) }
    }
    
    "get friend ID array specified by user id" in {
      val ids = twitter1.getFriendsIDs(-1, userId = ryunosukey)
      rawJSON(ids.tw4jObj) must not equalTo(null)
      ids.tw4jObj must equalTo(DataObjectFactory.createIDs(rawJSON(ids.tw4jObj)))
      ids.length must equalTo(0)
    }
    
    "get friend ID array specified by user screen name" in {
      val ids = twitter1.getFriendsIDs(-1, screenName = "yusuke")
      rawJSON(ids.tw4jObj) must not equalTo(null)
      atLeastOnce(ids.ids) { (_:Long) must equalTo(ryunosukey)}
    }
    
    "get friend ID array with cursor specified by user id" in {
      val firstCursor = twitter1.getFriendsIDs(-1, userId = obamaId)
      firstCursor.hasNext must beTrue
      firstCursor.hasPrevious must beFalse
      
      val secondCursor = twitter1.getFriendsIDs(firstCursor.nextCursor, userId = obamaId)
      secondCursor.hasNext must beTrue
      secondCursor.hasPrevious must beTrue
    }
    
    "get friend ID array with cursor specified by user screen name" in {
      val firstCursor = twitter1.getFriendsIDs(-1, screenName = obamaScreenName)
      firstCursor.hasNext must beTrue
      firstCursor.hasPrevious must beFalse
      
      val secondCursor = twitter1.getFriendsIDs(firstCursor.nextCursor, screenName = obamaScreenName)
      secondCursor.hasNext must beTrue
      secondCursor.hasPrevious must beTrue
    }
  }
  
  "getFollowersIDs" should {
    "get followers ID array with cursor specified by user screen name" in {
      val firstCursor = twitter1.getFollowersIDs(-1, screenName = obamaScreenName)
      rawJSON(firstCursor.tw4jObj) must not equalTo(null)
      firstCursor.tw4jObj must equalTo(DataObjectFactory.createIDs(rawJSON(firstCursor.tw4jObj)))
      firstCursor.hasNext must beTrue
      firstCursor.hasPrevious must beFalse
      
      val secondCursor = twitter1.getFollowersIDs(firstCursor.nextCursor, screenName = obamaScreenName)
      secondCursor.hasNext must beTrue
      secondCursor.hasPrevious must beTrue
    }
    
    "get followers ID array specified by user id" in {
      val ids = twitter1.getFollowersIDs(-1, userId = obamaId)
      rawJSON(ids.tw4jObj) must not equalTo(null)
      ids.tw4jObj must equalTo(DataObjectFactory.createIDs(rawJSON(ids.tw4jObj)))
      ids.hasNext must beTrue
      ids.hasPrevious must beFalse
      
      val secondCursor = twitter1.getFollowersIDs(ids.nextCursor, userId = obamaId)
      secondCursor.hasNext must beTrue
      secondCursor.hasPrevious must beTrue
    }
    
    "get followers ID array specified by cursor" in {
      val ids = twitter2.getFollowersIDs(-1)
      rawJSON(ids.tw4jObj) must not equalTo(null)
      atLeastOnce(ids.ids) { (_:Long) must equalTo(id1.id)}
    }
  }
}