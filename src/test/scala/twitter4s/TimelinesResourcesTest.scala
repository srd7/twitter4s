package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import twitter4s.api.impl.TimelinesResourcesImpl
import org.specs2.mock.Mockito
import twitter4s.mocked.FakeValuesUsedByMock

@RunWith(classOf[JUnitRunner])
class TimelinesResourcesTest extends Specification with Mockito {
  val fakeResponseList = FakeValuesUsedByMock.responseList[twitter4j.Status]

  val twitter4jMock = mock[twitter4j.Twitter]
  twitter4jMock.getHomeTimeline returns fakeResponseList
  twitter4jMock.getHomeTimeline(Paging(1)) returns fakeResponseList
  twitter4jMock.getUserTimeline returns fakeResponseList
  twitter4jMock.getUserTimeline(Paging(1).count(30)) returns fakeResponseList
  twitter4jMock.getUserTimeline(1000) returns fakeResponseList
  twitter4jMock.getUserTimeline(1001, Paging(sinceId = 999383469L)) returns fakeResponseList
  twitter4jMock.getUserTimeline("1000") returns fakeResponseList
  twitter4jMock.getUserTimeline("1001", Paging(1)) returns fakeResponseList
  twitter4jMock.getMentions returns fakeResponseList
  twitter4jMock.getMentions(Paging(1)) returns fakeResponseList
  twitter4jMock.getMentionsTimeline returns fakeResponseList
  twitter4jMock.getMentionsTimeline(Paging(1)) returns fakeResponseList
  twitter4jMock.getRetweetsOfMe returns fakeResponseList
  twitter4jMock.getRetweetsOfMe(Paging(9)) returns fakeResponseList

  val twitter4sObj = new Twitter(twitter4jMock) with TimelinesResourcesImpl
  
  val twitterTimelineResourceRole = new Twitter(twitter4jInstance(User1)) with TimelinesResourcesImpl
  
  def testStatuses(target: ResponseList[twitter4j.Status]) = {
    rawJSON(target.tw4jObj) must not equalTo(null)
    target(0) must equalTo(DataObjectFactory.createStatus(rawJSON(target(0))))
    target.size must be_>(0)
  }

  def assertReturnedFakeResponseExactly(statuses: ResponseList[twitter4j.Status]) = {
    statuses.accessLevel must equalTo(TwitterResponse.READ_WRITE_DIRECTMESSAGES)
    statuses.size must equalTo(1)
  }
  
  "getHomeTimeline" should {
    "get authenticated user's home timeline" in {
      assertReturnedFakeResponseExactly(twitter4sObj.getHomeTimeline())
      there was one(twitter4jMock).getHomeTimeline
    }
    
    "get authenticated user's home timeline with page" in {
      assertReturnedFakeResponseExactly(twitter4sObj.getHomeTimeline(Paging(1)))
      there was one(twitter4jMock).getHomeTimeline(Paging(1))
    }
  }
  
  "getUserTimeline" should {
    "get user timeline no parameters" in {
      assertReturnedFakeResponseExactly(twitter4sObj.getUserTimeline())
      there was one(twitter4jMock).getUserTimeline
    }
    
    "get user timeline with screen name" in {
      assertReturnedFakeResponseExactly(twitter4sObj.getUserTimeline(User.isSpecifiedBy("1000")))
      there was one(twitter4jMock).getUserTimeline("1000")
    }

    "get user timeline with screen name and page number" in {
      assertReturnedFakeResponseExactly(twitter4sObj.getUserTimeline(User.isSpecifiedBy("1001"), Paging(1)))
      there was one(twitter4jMock).getUserTimeline("1001", Paging(1))
    }
    
    "get user timeline with user id" in {
      assertReturnedFakeResponseExactly(twitter4sObj.getUserTimeline(User.isSpecifiedBy(1000)))
      there was one(twitter4jMock).getUserTimeline(1000)
    }

    "get user timeline with user id and specified sinceid" in {
      assertReturnedFakeResponseExactly(twitter4sObj.getUserTimeline(User.isSpecifiedBy(1001), Paging(sinceId = 999383469L)))
      there was one(twitter4jMock).getUserTimeline(1001, Paging(sinceId = 999383469L))
    }
    
    "get authed user's timeline specified page count" in {
      assertReturnedFakeResponseExactly(twitter4sObj.getUserTimeline(paging = Paging(1).count(30)))
      there was one(twitter4jMock).getUserTimeline(Paging(1).count(30))
    }
  }
  
  "getMentions" should {
    // Note: getMentions is deprecated method since twitter4j 3.0.0
    // The following specs are work now.

    "get mentions timeline without pages" in {
      assertReturnedFakeResponseExactly(twitter4sObj.getMentions())
      there was one(twitter4jMock).getMentions
    }

    "get mentions timeline with page" in {
      assertReturnedFakeResponseExactly(twitter4sObj.getMentions(Paging(1)))
      there was one(twitter4jMock).getMentions(Paging(1))
    }
  }
  
  "getMentionsTimeline" should {
    "get specified user's mention list without parameters" in {
      assertReturnedFakeResponseExactly(twitter4sObj.getMentionsTimeline())
      there was one(twitter4jMock).getMentionsTimeline
    }
    
    "get specified user's mention list with page parameter" in {
      assertReturnedFakeResponseExactly(twitter4sObj.getMentionsTimeline(Paging(1)))
      there was one(twitter4jMock).getMentionsTimeline(Paging(1))
    }
  }
  
  "getRetweetsOfMe" should {
    "get retweets statuses of user without parameters" in {
      assertReturnedFakeResponseExactly(twitter4sObj.getRetweetsOfMe())
      there was one(twitter4jMock).getRetweetsOfMe
    }

    "get retweets statuses of user with page parameter" in {
      assertReturnedFakeResponseExactly(twitter4sObj.getRetweetsOfMe(Paging(9)))
      there was one(twitter4jMock).getRetweetsOfMe(Paging(9))
    }
  }
}