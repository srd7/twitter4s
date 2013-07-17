package twitter4s
import twitter4s._
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import java.util.Date

@RunWith(classOf[JUnitRunner])
class TweetsResourcesTest extends Specification {

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
      val status = twitter2.updateStatus(Status.isWrittenBy(statusString))
      rawJSON(status.tw4jObj) must not equalTo(null)
      status.tw4jObj must equalTo(DataObjectFactory.createStatus(rawJSON(status.tw4jObj)))
      
      // destory test
      val destroyedStatus = twitter2.destroyStatus(status.id)
      rawJSON(destroyedStatus.tw4jObj) must not equalTo(null)
      destroyedStatus.tw4jObj must equalTo(DataObjectFactory.createStatus(rawJSON(destroyedStatus.tw4jObj)))
    }
    
    "update status by StatusUpdate object" in {
      val status = twitter2.updateStatus(Status.isSetBy(StatusUpdate("@" + id1.screenName + " " + new Date().toString())))
      rawJSON(status.tw4jObj) must not equalTo(null)
      status.tw4jObj must equalTo(DataObjectFactory.createStatus(rawJSON(status.tw4jObj)))
    }
    
    "throw IllegalArgumentException when status set null" in {
      twitter2.updateStatus(null) must throwA[IllegalArgumentException]
    }
  }
  
  def testRetweetResponseList(statuses: ResponseList[twitter4j.Status]) = {
    rawJSON(statuses.tw4jObj) must not equalTo(null)
    statuses(0) must equalTo(DataObjectFactory.createStatus(rawJSON(statuses(0))))
    forall(statuses) {(status:twitter4j.Status) => status.getText must startWith("RT ")}
  }
  
  "getRetweetedByMe" should {
    "get retweet by authorized user without page" in {
      // TODO メソッド削除
//      val statuses = twitter1.getRetweetedByMe()
//      testRetweetResponseList(statuses)
    }
    
    "get retweet by authorized user with page" in {
      // TODO メソッド削除
//      val statuses = twitter1.getRetweetedByMe(Paging(1))
//      testRetweetResponseList(statuses)
    }
  }
  
  "getRetweetedByUser" should {
    "get retweet to user specified by id with page" in {
      // TODO メソッド削除
//      val statuses = twitter1.getRetweetedByUser(
//          User.isSpecifiedBy(id1.id), Paging(1))
//      testRetweetResponseList(statuses)
    }
    
    "get retweet to user specified by screen name with page" in {
      // TODO メソッド削除
//      val statuses = twitter1.getRetweetedByUser(
//          User.isSpecifiedBy(id1.screenName), Paging(1))
//      testRetweetResponseList(statuses)
    }
    
    "throw exception when parameter specificUser is null" in {
      // TODO メソッド削除
//      twitter1.getRetweetedByUser(null, Paging(1)) must
//      throwA[IllegalArgumentException]
    }
  }
  
  "getRetweetedToMe" should {
    "get retweet to authorized user without page" in {
      // TODO メソッド削除
//      val statuses = twitter1.getRetweetedToMe()
//      testRetweetResponseList(statuses)
    }
    
    "get retweet to authorized user with page" in {
      // TODO メソッド削除
//      val statuses = twitter1.getRetweetedToMe(Paging(1))
//      testRetweetResponseList(statuses)
    }
  }
  
  "getRtweetedToUser" should {
    "get retweet to user specified by id" in {
      // TODO メソッド削除
//      val statuses = twitter1.getRetweetedToUser(
//          User.isSpecifiedBy(id1.id), Paging(1))
//      testRetweetResponseList(statuses)
    }
    
    "get retweet to user specified by screen name" in {
      // TODO メソッド削除
//      val statuses = twitter1.getRetweetedToUser(
//          User.isSpecifiedBy(id1.screenName), Paging(1))
//      testRetweetResponseList(statuses)
    }
    
    "throw exception when parameter specificUser is null" in {
      // TODO メソッド削除
//      twitter1.getRetweetedToUser(null, Paging(1)) must
//      throwA[IllegalArgumentException]
    }
  }
  
  "getRetweetsOfMe" should {
    "get retweet from authorized user's tweet without page" in {
      val statuses = twitter1.getRetweetsOfMe()
      rawJSON(statuses.tw4jObj) must not equalTo(null)
      statuses(0) must equalTo(DataObjectFactory.createStatus(rawJSON(statuses(0))))
    }
    
    "get retweet from authorized user's tweet with page" in {
      val statuses = twitter1.getRetweetsOfMe(Paging(1))
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
}