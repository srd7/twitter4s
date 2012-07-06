package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4s.Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import twitter4j.Paging
import twitter4j.Status
import twitter4s.implicits.Twitter4SImplicits._

@RunWith(classOf[JUnitRunner])
class TimelineMethodsTest extends Specification {
  
  def testStatuses(target: ResponseList[Status]) = {
    rawJSON(target.tw4jObj) must not equalTo(null)
    target(0) must equalTo(DataObjectFactory.createStatus(rawJSON(target(0))))
    target.size must be_>(0)
  }
  
  "getHomeTimeline" should {
    "get authenticated user's home timeline" in {
      val statuses = twitter1.getHomeTimeline()
      statuses.size must be_>(0)
      twitter1.configuration.isJSONStoreEnabled must beTrue
      rawJSON(statuses.tw4jObj) must not equalTo(null)
      statuses(0) must equalTo(DataObjectFactory.createStatus(rawJSON(statuses(0))))
    }
  }
  
  "getUserTimeline" should {

    
    "get user timeline no parameters" in {
      val statuses = twitter1.getUserTimeline()
      statuses.size must be_>(0)
    }
    
    "get spcified user screen name timeline unauthenticated" in {
      val statuses = unauthenticated.getUserTimeline(Some("1000"))
      testStatuses(statuses)
      statuses(0).getUser().getId() must equalTo(9737332L)
    }
    
    "get specified user id timeline unauthenticated" in {
      val statuses = unauthenticated.getUserTimeline(userId = Some(1000L))
      testStatuses(statuses)
      statuses(0).getUser().getId() must equalTo(1000L)
    }
    
    "get specified user screen name and page count timeline unauthenticated" in {
      val statuses = unauthenticated.getUserTimeline(screenName = Some(id1.screenName), paging = Some(new Paging().count(10)))
      testStatuses(statuses)
    }
    
    "get specified user id and sinceid timeline unauthenticated" in {
      val statuses = unauthenticated.getUserTimeline(userId = Some(id1.id), paging = Some(new Paging(999383469L)))
      testStatuses(statuses)
    }
    
    "get authed user's timeline specified sinceid" in {
      val statuses = twitter1.getUserTimeline(paging = Some(new Paging(999383469L)))
      testStatuses(statuses)
    }
    
    "get authed user's timeline specified page count" in {
      val statuses = twitter1.getUserTimeline(paging = Some(new Paging(1).count(30)))
      testStatuses(statuses)
    }
    
    "get users timeline with deffer page setting but same tweet count" in {
      val statuses1 = twitter1.getUserTimeline(paging = Some(new Paging(1).count(30)))
      val statuses2 = twitter1.getUserTimeline(paging = Some(new Paging(2).count(15)))
      statuses1(statuses1.size - 1) must equalTo(statuses2(statuses2.size - 1))
    }
  }
  
  "getMentions" should {
    "get specified user's mention list without parameter" in {
      val statuses = twitter1.getMentions()
      testStatuses(statuses)
    }
    
    "get specified user's mention list with page parameter" in {
      val statuses = twitter1.getMentions(Some(new Paging(1)))
      testStatuses(statuses)
    }
    
    "get specified user's mention list with page and sinceId parameter" in {
      val statuses = twitter1.getMentions(Some(new Paging(1, 1L)))
      testStatuses(statuses)
    }
    
    "get specified user's mention list with sinceId parameter" in {
      val statuses = twitter1.getMentions(Some(new Paging(1L)))
      testStatuses(statuses)
    }
  }
}