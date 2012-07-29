package twitter4s
import twitter4s._
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import java.util.Date
import twitter4j.StatusUpdate

@RunWith(classOf[JUnitRunner])
class StatusMethodsTest extends Specification {

  "showStatus" should {
    "get status specified by tweet id" in {
      val status = twitter2.showStatus(1000)
      rawJSON(status.tw4jObj) must not equalTo(null)
      status.tw4jObj must equalTo(DataObjectFactory.createStatus(rawJSON(status.tw4jObj)))
      status.user.id must equalTo(52)
      status.rateLimitStatus must not equalTo(null)
    }
  }
  
  "updateStatus" should {
    "update status by status string" in {
      val statusString = new Date().toString + "test http://t.co/VEDROet #twitter4stest"
      val status = twitter2.updateStatus(status = statusString)
      rawJSON(status.tw4jObj) must not equalTo(null)
      status.tw4jObj must equalTo(DataObjectFactory.createStatus(rawJSON(status.tw4jObj)))
      
      // destory test
      val destroyedStatus = twitter2.destroyStatus(status.id)
      rawJSON(destroyedStatus.tw4jObj) must not equalTo(null)
      destroyedStatus.tw4jObj must equalTo(DataObjectFactory.createStatus(rawJSON(destroyedStatus.tw4jObj)))
    }
    
    "update status by StatusUpdate object" in {
      val status = twitter2.updateStatus(latestStatus = new StatusUpdate("@" + id1.screenName + " " + new Date().toString()))
      rawJSON(status.tw4jObj) must not equalTo(null)
      status.tw4jObj must equalTo(DataObjectFactory.createStatus(rawJSON(status.tw4jObj)))
    }
    
    "throw IllegalArgumentException both of parameter not set" in {
      twitter2.updateStatus() must throwA[IllegalArgumentException]
    }
    
    "throw IllegalArgumentException both of parameter set" in {
      val statusString = new Date().toString + "test http://t.co/VEDROet #twitter4stest"
      twitter2.updateStatus(
          status = statusString,
          latestStatus = new StatusUpdate("@" + id1.screenName + " " + new Date().toString())) must
      throwA[IllegalArgumentException]
    }
  }
  
  def testRetweetResponseList(statuses: ResponseList[twitter4j.Status]) = {
    rawJSON(statuses.tw4jObj) must not equalTo(null)
    statuses(0) must equalTo(DataObjectFactory.createStatus(rawJSON(statuses(0))))
    forall(statuses) {(status:twitter4j.Status) => status.getText must startWith("RT ")}
  }
  
  "getRetweetedByMe" should {
    "get retweet by authorized user without page" in {
      val statuses = twitter1.getRetweetedByMe()
      testRetweetResponseList(statuses)
    }
    
    "get retweet by authorized user with page" in {
      val statuses = twitter1.getRetweetedByMe(Some(Paging(1)))
      testRetweetResponseList(statuses)
    }
  }
  
  "getRetweetedByUser" should {
    "get retweet to user specified by id with page" in {
      val statuses = twitter1.getRetweetedByUser(Paging(1), userId = Some(id1.id))
      testRetweetResponseList(statuses)
    }
    
    "get retweet to user specified by screen name with page" in {
      val statuses = twitter1.getRetweetedByUser(Paging(1), screenName = Some(id1.screenName))
      testRetweetResponseList(statuses)
    }
  }
  
  "getRetweetedToMe" should {
    "get retweet to authorized user without page" in {
      val statuses = twitter1.getRetweetedToMe()
      testRetweetResponseList(statuses)
    }
    
    "get retweet to authorized user with page" in {
      val statuses = twitter1.getRetweetedToMe(Some(Paging(1)))
      testRetweetResponseList(statuses)
    }
  }
  
  "getRtweetedToUser" should {
    "get retweet to user specified by id" in {
      val statuses = twitter1.getRetweetedToUser(Paging(1), userId = Some(id1.id))
      testRetweetResponseList(statuses)
    }
    
    "get retweet to user specified by screen name" in { 
      val statuses = twitter1.getRetweetedToUser(Paging(1), screenName = Some(id1.screenName))
      testRetweetResponseList(statuses)
    }
  }
  
  "getRetweetsOfMe" should {
    "get retweet from authorized user's tweet without page" in {
      val statuses = twitter1.getRetweetsOfMe()
      rawJSON(statuses.tw4jObj) must not equalTo(null)
      statuses(0) must equalTo(DataObjectFactory.createStatus(rawJSON(statuses(0))))
    }
    
    "get retweet from authorized user's tweet with page" in {
      val statuses = twitter1.getRetweetsOfMe(Some(Paging(1)))
      rawJSON(statuses.tw4jObj) must not equalTo(null)
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
      val users = unauthenticated.getRetweetedBy(47621163517624320L, Paging(page = 1, count = 100))
      users.size must be_>(50)
    }
    
    "get users list retweeted specified tweet with page 2 per 100" in {
      val users = unauthenticated.getRetweetedBy(47621163517624320L, Paging(page = 2, count = 100))
      users.size must be_>(10)
    }
    
    "get users list retweeted specified tweet without page" in {
      val users = unauthenticated.getRetweetedBy(47621163517624320L)
      users.size must be_>(50)
    }
  }
  
  "getRetweetedByIDs" should {
    "get ids list retweeted specified tweet with page 1 per 100" in {
      val ids = twitter1.getRetweetedByIDs(47621163517624320L, Paging(page = 1, count = 100))
      ids.length must be_>(50)
    }
    
    "get ids list retweeted specidied tweet with page 2 per 100" in {
      val ids = twitter1.getRetweetedByIDs(47621163517624320L, Paging(page = 2, count = 100))
      ids.length must be_>(10)
    }
    
    "get ids list retweeted specified tweet without page" in {
      val ids = twitter1.getRetweetedByIDs(47621163517624320L)
      ids.length must be_>(50)
    }
  }
}