package twitter4s
import twitter4s._
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import twitter4s.api.impl.TimelinesResourcesImpl

@RunWith(classOf[JUnitRunner])
class TimelineMethodsTest extends Specification {
  
  val twitterTimelineResourceRole = new Twitter(twitter4jInstance(User1)) with TimelinesResourcesImpl
  
  def testStatuses(target: ResponseList[twitter4j.Status]) = {
    rawJSON(target.tw4jObj) must not equalTo(null)
    target(0) must equalTo(DataObjectFactory.createStatus(rawJSON(target(0))))
    target.size must be_>(0)
  }
  
  "getHomeTimeline" should {
    "get authenticated user's home timeline" in {
      val statuses = twitterTimelineResourceRole.getHomeTimeline()
      statuses.size must be_>(0)
      twitterTimelineResourceRole.configuration.isJSONStoreEnabled must beTrue
      rawJSON(statuses.tw4jObj) must not equalTo(null)
      statuses(0) must equalTo(DataObjectFactory.createStatus(rawJSON(statuses(0))))
    }
    
    "get authenticated user's home timeline with page" in {
      val statuses = twitterTimelineResourceRole.getHomeTimeline(Paging(1))
      rawJSON(statuses.tw4jObj) must not equalTo(null)
    }
  }
  
  "getUserTimeline" should {
    "get user timeline no parameters" in {
      val statuses = twitterTimelineResourceRole.getUserTimeline()
      statuses.size must be_>(0)
    }
    
    "get user timeline with screen name" in {
      val statuses = twitterTimelineResourceRole.getUserTimeline(User.isSpecifiedBy("1000"))
      testStatuses(statuses)
      statuses(0).getUser().id must equalTo(9737332)
    }
    
    "get user timeline with user id" in {
      val statuses = twitterTimelineResourceRole.getUserTimeline(User.isSpecifiedBy(1000))
      testStatuses(statuses)
      statuses(0).getUser.id must equalTo(1000)
    }
    
    "get authed user's timeline specified sinceid" in {
      val statuses = twitterTimelineResourceRole.getUserTimeline(paging = Paging(sinceId = 999383469L))
      testStatuses(statuses)
    }
    
    "get authed user's timeline specified page count" in {
      val statuses = twitterTimelineResourceRole.getUserTimeline(paging = Paging(1).count(30))
      testStatuses(statuses)
    }
    
    "get users timeline with deffer page setting but same tweet count" in {
      val statuses1 = twitterTimelineResourceRole.getUserTimeline(paging = Paging(1).count(30))
      val statuses2 = twitterTimelineResourceRole.getUserTimeline(paging = Paging(2).count(15))
      statuses1(statuses1.size - 1) must equalTo(statuses2(statuses2.size - 1))
    }
  }
  
  "getMentions" should {
    // Note: getMentions is deprecated method from twitter4j 3.0.0
    // The following specs are work.
    // But comment out for rate limit status on run specs.
    /*
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
    */
  }
  
  "getMentionsTimeline" should {
    "get specified user's mention list without parameters" in {
      val statuses = twitterTimelineResourceRole.getMentionsTimeline()
      testStatuses(statuses)
    }
    
    "get specified user's mention list with page parameter" in {
      val statuses = twitterTimelineResourceRole.getMentionsTimeline(Paging(1))
      testStatuses(statuses)
    }
    
    "get specified user's mention list with page and sinceId parameter" in {
      val statuses = twitterTimelineResourceRole.getMentionsTimeline(Paging(1, sinceId = 1L))
      testStatuses(statuses)
    }
    
    "get specified user's mention list with sinceId parameter" in {
      val statuses = twitterTimelineResourceRole.getMentionsTimeline(Paging(sinceId = 1L))
      testStatuses(statuses)
    }
  }
  
  "getRetweetsOfMe" should {
    "get retweets statuses of user" in {
      val statuses = twitterTimelineResourceRole.getRetweetsOfMe()
      statuses.size must be_>(0)
    }
  }
}