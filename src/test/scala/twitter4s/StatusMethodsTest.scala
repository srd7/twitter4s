package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4s.Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import java.util.Date
import twitter4j.StatusUpdate

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
    }
    
    "update status by StatusUpdate object" in {
      val status = twitter2.updateStatus(latestStatus = Some(new StatusUpdate("@" + id1.screenName + " " + new Date().toString())))
      rawJSON(status) must not equalTo(null)
      status must equalTo(DataObjectFactory.createStatus(rawJSON(status)))
    }
  }
  
  // status dupilicate?
//  "destroyStatus" should {
//    "destroy status specified by id" in {
//      // set precondition
//      val statusString = new Date().toString + "test http://t.co/VEDROet #twitter4stest"
//      val status = twitter2.updateStatus(status = Some(statusString))
//      
//      // test
//      val destroyedStatus = twitter2.destroyStatus(status.getId())
//      rawJSON(destroyedStatus) must not equalTo(null)
//      destroyedStatus must equalTo(DataObjectFactory.createStatus(rawJSON(destroyedStatus)))
//    }
//  }
}