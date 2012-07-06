package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4s.Twitter4sTestHelper._
import scala.collection.JavaConverters.asScalaBufferConverter
import twitter4j.UserList
import twitter4s.implicits.Twitter4SImplicits._
import twitter4j.json.DataObjectFactory
import twitter4j.Paging

@RunWith(classOf[JUnitRunner])
class ListMethodsTest extends Specification {

  def makePrecondition = {
    val userLists = twitter2.getUserLists(-1L, listOwnerScreenName = Some(id2.screenName))
    userLists.foreach(alist => twitter2.destroyUserList(alist.getId))
    twitter2.createUserList("testpoint1", false, "description1")
  }
  
  "getUserList" should {
    "get list specified by user id" in {
      val userList = makePrecondition
      rawJSON(userList) must not equalTo(null) 
      userList must equalTo(DataObjectFactory.createUserList(rawJSON(userList)))
      userList must not equalTo(null)
      userList.getName() must equalTo("testpoint1")
      userList.getDescription() must equalTo("description1")
      
      val userLists = twitter1.getUserLists(-1L, listOwnerScreenName = Some(id1.screenName))
      userLists.size must not equalTo(0)
      
      // showUserList test
      val showUserList = twitter2.showUserList(userList.getId())
      showUserList must not equalTo(null)
      rawJSON(showUserList) must not equalTo(null)
      showUserList must equalTo(DataObjectFactory.createUserList(rawJSON(showUserList)))
    }
  }
  
  "getUserListStatuses" should {
    "get status from user list specified by list id" in {
      val userLists = twitter1.getUserLists(-1L, listOwnerUserId = Some(id1.id))
      val statuses = twitter1.getUserListStatuses(userLists(0).getId, new Paging())
      statuses(0) must equalTo(DataObjectFactory.createStatus(rawJSON(statuses(0))))
    }
  }
}