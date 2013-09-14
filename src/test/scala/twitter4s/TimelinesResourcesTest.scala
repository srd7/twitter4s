package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import twitter4s.api.impl.TimelinesResourcesImpl
import twitter4s.mocked.FakeValuesUsedByMock

@RunWith(classOf[JUnitRunner])
class TimelinesResourcesTest extends Specification with TwitterResourcesTestBase {
  type TargetResourcesType = TimelinesResourcesImpl
  val fakeResponseList = FakeValuesUsedByMock.responseList[twitter4j.Status]

  mockedTwitter4j.getHomeTimeline returns fakeResponseList
  mockedTwitter4j.getHomeTimeline(Paging(1)) returns fakeResponseList
  mockedTwitter4j.getUserTimeline returns fakeResponseList
  mockedTwitter4j.getUserTimeline(Paging(1).count(30)) returns fakeResponseList
  mockedTwitter4j.getUserTimeline(1000) returns fakeResponseList
  mockedTwitter4j.getUserTimeline(1001, Paging(sinceId = 999383469L)) returns fakeResponseList
  mockedTwitter4j.getUserTimeline("1000") returns fakeResponseList
  mockedTwitter4j.getUserTimeline("1001", Paging(1)) returns fakeResponseList
  mockedTwitter4j.getMentions returns fakeResponseList
  mockedTwitter4j.getMentions(Paging(1)) returns fakeResponseList
  mockedTwitter4j.getMentionsTimeline returns fakeResponseList
  mockedTwitter4j.getMentionsTimeline(Paging(1)) returns fakeResponseList
  mockedTwitter4j.getRetweetsOfMe returns fakeResponseList
  mockedTwitter4j.getRetweetsOfMe(Paging(9)) returns fakeResponseList

  override val twitter = new Twitter(mockedTwitter4j) with TargetResourcesType

  def assertReturnedFakeResponseExactly(statuses: ResponseList[twitter4j.Status]) = {
    statuses.accessLevel must equalTo(TwitterResponse.READ_WRITE_DIRECTMESSAGES)
    statuses.size must equalTo(1)
  }
  
  "getHomeTimeline" should {
    "get authenticated user's home timeline" in {
      assertReturnedFakeResponseExactly(twitter.getHomeTimeline())
      there was one(mockedTwitter4j).getHomeTimeline
    }
    
    "get authenticated user's home timeline with page" in {
      assertReturnedFakeResponseExactly(twitter.getHomeTimeline(Paging(1)))
      there was one(mockedTwitter4j).getHomeTimeline(Paging(1))
    }
  }
  
  "getUserTimeline" should {
    "get user timeline no parameters" in {
      assertReturnedFakeResponseExactly(twitter.getUserTimeline())
      there was one(mockedTwitter4j).getUserTimeline
    }
    
    "get user timeline with screen name" in {
      assertReturnedFakeResponseExactly(twitter.getUserTimeline(User.isSpecifiedBy("1000")))
      there was one(mockedTwitter4j).getUserTimeline("1000")
    }

    "get user timeline with screen name and page number" in {
      assertReturnedFakeResponseExactly(twitter.getUserTimeline(User.isSpecifiedBy("1001"), Paging(1)))
      there was one(mockedTwitter4j).getUserTimeline("1001", Paging(1))
    }
    
    "get user timeline with user id" in {
      assertReturnedFakeResponseExactly(twitter.getUserTimeline(User.isSpecifiedBy(1000)))
      there was one(mockedTwitter4j).getUserTimeline(1000)
    }

    "get user timeline with user id and specified sinceid" in {
      assertReturnedFakeResponseExactly(twitter.getUserTimeline(User.isSpecifiedBy(1001), Paging(sinceId = 999383469L)))
      there was one(mockedTwitter4j).getUserTimeline(1001, Paging(sinceId = 999383469L))
    }
    
    "get authed user's timeline specified page count" in {
      assertReturnedFakeResponseExactly(twitter.getUserTimeline(paging = Paging(1).count(30)))
      there was one(mockedTwitter4j).getUserTimeline(Paging(1).count(30))
    }
  }
  
  "getMentions" should {
    // Note: getMentions is deprecated method since twitter4j 3.0.0
    // The following specs are work now.

    "get mentions timeline without pages" in {
      assertReturnedFakeResponseExactly(twitter.getMentions())
      there was one(mockedTwitter4j).getMentions
    }

    "get mentions timeline with page" in {
      assertReturnedFakeResponseExactly(twitter.getMentions(Paging(1)))
      there was one(mockedTwitter4j).getMentions(Paging(1))
    }
  }
  
  "getMentionsTimeline" should {
    "get specified user's mention list without parameters" in {
      assertReturnedFakeResponseExactly(twitter.getMentionsTimeline())
      there was one(mockedTwitter4j).getMentionsTimeline
    }
    
    "get specified user's mention list with page parameter" in {
      assertReturnedFakeResponseExactly(twitter.getMentionsTimeline(Paging(1)))
      there was one(mockedTwitter4j).getMentionsTimeline(Paging(1))
    }
  }
  
  "getRetweetsOfMe" should {
    "get retweets statuses of user without parameters" in {
      assertReturnedFakeResponseExactly(twitter.getRetweetsOfMe())
      there was one(mockedTwitter4j).getRetweetsOfMe
    }

    "get retweets statuses of user with page parameter" in {
      assertReturnedFakeResponseExactly(twitter.getRetweetsOfMe(Paging(9)))
      there was one(mockedTwitter4j).getRetweetsOfMe(Paging(9))
    }
  }
}