package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4s.Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import java.util.Date
import twitter4j.StatusUpdate
import twitter4s.implicits.Twitter4SImplicits._
import twitter4j.Status
import twitter4j.Paging

@RunWith(classOf[JUnitRunner])
class StatusMethodsTest extends Specification {

  "showStatus" should {
    "get status specified by tweet id" in {
      val status = twitter2.showStatus(1000)
      rawJSON(status) must not equalTo(null)
      status must equalTo(DataObjectFactory.createStatus(rawJSON(status)))
      status.getUser().getId() must equalTo(52)
      status.getRateLimitStatus() must not equalTo(null)
    }
  }
  
  "updateStatus" should {
    "update status by status string" in {
      val statusString = new Date().toString + "test http://t.co/VEDROet #twitter4stest"
      val status = twitter2.updateStatus(status = Some(statusString))
      rawJSON(status) must not equalTo(null)
      status must equalTo(DataObjectFactory.createStatus(rawJSON(status)))
      
      // destory test
      val destroyedStatus = twitter2.destroyStatus(status.getId())
      rawJSON(destroyedStatus) must not equalTo(null)
      destroyedStatus must equalTo(DataObjectFactory.createStatus(rawJSON(destroyedStatus)))
    }
    
    "update status by StatusUpdate object" in {
      val status = twitter2.updateStatus(latestStatus = Some(new StatusUpdate("@" + id1.screenName + " " + new Date().toString())))
      rawJSON(status) must not equalTo(null)
      status must equalTo(DataObjectFactory.createStatus(rawJSON(status)))
    }
  }
  
  def testRetweetResponseList(statuses: ResponseList[Status]) = {
    rawJSON(statuses.twt4jResponseList) must not equalTo(null)
    statuses(0) must equalTo(DataObjectFactory.createStatus(rawJSON(statuses(0))))
    forall(statuses) {(status:Status) => status.getText must startWith("RT ")}
  }
  
  "getRetweetedByMe" should {
    "get retweet by authorized user without page" in {
      val statuses = twitter1.getRetweetedByMe()
      testRetweetResponseList(statuses)
    }
    
    "get retweet by authorized user with page" in {
      val statuses = twitter1.getRetweetedByMe(Some(new Paging(1)))
      testRetweetResponseList(statuses)
    }
  }
  
  "getRetweetedByUser" should {
    "get retweet to user specified by id with page" in {
      val statuses = twitter1.getRetweetedByUser(new Paging(1), userId = Some(id1.id))
      testRetweetResponseList(statuses)
    }
    
    "get retweet to user specified by screen name with page" in {
      val statuses = twitter1.getRetweetedByUser(new Paging(1), screenName = Some(id1.screenName))
      testRetweetResponseList(statuses)
    }
  }
  
  "getRetweetedToMe" should {
    "get retweet to authorized user without page" in {
      val statuses = twitter1.getRetweetedToMe()
      testRetweetResponseList(statuses)
    }
    
    "get retweet to authorized user with page" in {
      val statuses = twitter1.getRetweetedToMe(Some(new Paging(1)))
      testRetweetResponseList(statuses)
    }
  }
  
  "getRtweetedToUser" should {
    "get retweet to user specified by id" in {
      val statuses = twitter1.getRetweetedToUser(new Paging(1), userId = Some(id1.id))
      testRetweetResponseList(statuses)
    }
    
    "get retweet to user specified by screen name" in { 
      val statuses = twitter1.getRetweetedToUser(new Paging(1), screenName = Some(id1.screenName))
      testRetweetResponseList(statuses)
    }
  }
  
  "getRetweetsOfMe" should {
    "get retweet from authorized user's tweet without page" in {
      val statuses = twitter1.getRetweetsOfMe()
      rawJSON(statuses.twt4jResponseList) must not equalTo(null)
      statuses(0) must equalTo(DataObjectFactory.createStatus(rawJSON(statuses(0))))
    }
    
    "get retweet from authorized user's tweet with page" in {
      val statuses = twitter1.getRetweetsOfMe(Some(new Paging(1)))
      rawJSON(statuses.twt4jResponseList) must not equalTo(null)
      statuses(0) must equalTo(DataObjectFactory.createStatus(rawJSON(statuses(0))))
    }
  }
  
  "getRetweets" should {
    "get retweet specified by tweet id" in {
      val statuses = twitter1.getRetweets(18594701629L)
      testRetweetResponseList(statuses)
      statuses.size must be_>(20)
    }
  }
  
  "getRetweetedBy" should {
    "get users list retweeted specified tweet with page 1 per 100" in {
      val users = unauthenticated.getRetweetedBy(47621163517624320L, Some(new Paging(1, 100)))
      users.size must be_>(50)
    }
    
    "get users list retweeted specified tweet with page 2 per 100" in {
      val users = unauthenticated.getRetweetedBy(47621163517624320L, Some(new Paging(2, 100)))
      users.size must be_>(10)
    }
    
    "get users list retweeted specified tweet without page" in {
      val users = unauthenticated.getRetweetedBy(47621163517624320L)
      users.size must be_>(50)
    }
  }
  
  "getRetweetedByIDs" should {
    "get ids list retweeted specified tweet with page 1 per 100" in {
      val ids = twitter1.getRetweetedByIDs(47621163517624320L, Some(new Paging(1, 100)))
      ids.getIDs.length must be_>(50)
    }
    
    "get ids list retweeted specidied tweet with page 2 per 100" in {
      val ids = twitter1.getRetweetedByIDs(47621163517624320L, Some(new Paging(2, 100)))
      ids.getIDs.length must be_>(10)
    }
    
    "get ids list retweeted specified tweet without page" in {
      val ids = twitter1.getRetweetedByIDs(47621163517624320L)
      ids.getIDs.length must be_>(50)
    }
  }
}