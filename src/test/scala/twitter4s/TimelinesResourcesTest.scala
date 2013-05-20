package twitter4s
import twitter4s._
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory

@RunWith(classOf[JUnitRunner])
class TimelineMethodsTest extends Specification {
  
  def testStatuses(target: ResponseList[twitter4j.Status]) = {
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
    
    "get authenticated user's home timeline with page" in {
      val statuses = twitter1.getHomeTimeline(Paging(1))
      rawJSON(statuses.tw4jObj) must not equalTo(null)
    }
  }
  
  "getUserTimeline" should {
    "get user timeline no parameters" in {
      val statuses = twitter1.getUserTimeline()
      statuses.size must be_>(0)
    }
    // TODO unauthenticateユーザはAPI 1.1から使えない
//    
//    "get spcified user screen name timeline unauthenticated" in {
//      val statuses = unauthenticated.getUserTimeline(
//          User.isSpecifiedBy("1000"))
//      testStatuses(statuses)
//      statuses(0).getUser().getId() must equalTo(9737332L)
//    }
//    
//    "get specified user id timeline unauthenticated" in {
//      val statuses = unauthenticated.getUserTimeline(
//          User.isSpecifiedBy(1000L))
//      testStatuses(statuses)
//      statuses(0).getUser().getId() must equalTo(1000L)
//    }
//    
//    "get specified user screen name and page count timeline unauthenticated" in {
//      val statuses = unauthenticated.getUserTimeline(
//          User.isSpecifiedBy(id1.screenName), paging = Paging().count(10))
//      testStatuses(statuses)
//    }
//    
//    "get specified user id and sinceid timeline unauthenticated" in {
//      val statuses = unauthenticated.getUserTimeline(
//          User.isSpecifiedBy(id1.id), paging = Paging(sinceId = 999383469L))
//      testStatuses(statuses)
//    }
    
    "get authed user's timeline specified sinceid" in {
      val statuses = twitter1.getUserTimeline(paging = Paging(sinceId = 999383469L))
      testStatuses(statuses)
    }
    
    "get authed user's timeline specified page count" in {
      val statuses = twitter1.getUserTimeline(paging = Paging(1).count(30))
      testStatuses(statuses)
    }
    
    "get users timeline with deffer page setting but same tweet count" in {
      val statuses1 = twitter1.getUserTimeline(paging = Paging(1).count(30))
      val statuses2 = twitter1.getUserTimeline(paging = Paging(2).count(15))
      statuses1(statuses1.size - 1) must equalTo(statuses2(statuses2.size - 1))
    }
  }
  
  "getMentions" should {
    "get specified user's mention list without parameter" in {
      val statuses = twitter1.getMentions()
      testStatuses(statuses)
    }
    
    "get specified user's mention list with page parameter" in {
      val statuses = twitter1.getMentions(Paging(1))
      testStatuses(statuses)
    }
    
    "get specified user's mention list with page and sinceId parameter" in {
      val statuses = twitter1.getMentions(Paging(1, sinceId = 1L))
      testStatuses(statuses)
    }
    
    "get specified user's mention list with sinceId parameter" in {
      val statuses = twitter1.getMentions(Paging(sinceId = 1L))
      testStatuses(statuses)
    }
  }
}