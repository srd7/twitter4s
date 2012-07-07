package twitter4s
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
      rawJSON(ids) must not equalTo(null)
      val yusuke = 4933401
      atLeastOnce(ids.getIDs()) { (_:Long) must equalTo(yusuke) }
    }
    
    "get friend ID array specified by user id" in {
      val ids = twitter1.getFriendsIDs(-1, userId = Some(ryunosukey))
      rawJSON(ids) must not equalTo(null)
      ids must equalTo(DataObjectFactory.createIDs(rawJSON(ids)))
      ids.getIDs().length must equalTo(0)
    }
    
    "get friend ID array specified by user screen name" in {
      val ids = twitter1.getFriendsIDs(-1, screenName = Some("yusukey"))
      rawJSON(ids) must not equalTo(null)
      atLeastOnce(ids.getIDs()) { (_:Long) must equalTo(ryunosukey)}
    }
    
    "get friend ID array with cursor specified by user id" in {
      val firstCursor = twitter1.getFriendsIDs(-1, userId = Some(obamaId))
      firstCursor.hasNext() must beTrue
      firstCursor.hasPrevious() must beFalse
      
      val secondCursor = twitter1.getFriendsIDs(firstCursor.getNextCursor(), userId = Some(obamaId))
      secondCursor.hasNext() must beTrue
      secondCursor.hasPrevious() must beTrue
    }
    
    "get friend ID array with cursor specified by user screen name" in {
      val firstCursor = twitter1.getFriendsIDs(-1, screenName = Some(obamaScreenName))
      firstCursor.hasNext() must beTrue
      firstCursor.hasPrevious() must beFalse
      
      val secondCursor = twitter1.getFriendsIDs(firstCursor.getNextCursor(), screenName = Some(obamaScreenName))
      secondCursor.hasNext() must beTrue
      secondCursor.hasPrevious() must beTrue
    }
  }
  
  "getFollowersIDs" should {
    "get followers ID array with cursor specified by user screen name" in {
      val firstCursor = twitter1.getFollowersIDs(-1, screenName = Some(obamaScreenName))
      rawJSON(firstCursor) must not equalTo(null)
      firstCursor must equalTo(DataObjectFactory.createIDs(rawJSON(firstCursor)))
      firstCursor.hasNext must beTrue
      firstCursor.hasPrevious() must beFalse
      
      val secondCursor = twitter1.getFollowersIDs(firstCursor.getNextCursor(), screenName = Some(obamaScreenName))
      secondCursor.hasNext must beTrue
      secondCursor.hasPrevious() must beTrue
    }
    
    "get followers ID array specified by user id" in {
      val ids = twitter1.getFollowersIDs(-1, userId = Some(obamaId))
      rawJSON(ids) must not equalTo(null)
      ids must equalTo(DataObjectFactory.createIDs(rawJSON(ids)))
      ids.hasNext() must beTrue
      ids.hasPrevious() must beFalse
      
      val secondCursor = twitter1.getFollowersIDs(ids.getNextCursor(), userId = Some(obamaId))
      secondCursor.hasNext() must beTrue
      secondCursor.hasPrevious() must beTrue
    }
    
    "get followers ID array specified by cursor" in {
      val ids = twitter1.getFollowersIDs(-1)
      rawJSON(ids) must not equalTo(null)
      atLeastOnce(ids.getIDs()) { (_:Long) must equalTo(id2.id)}
    }
  }
}