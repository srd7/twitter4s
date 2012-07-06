package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4s.Twitter4sTestHelper._
import scala.collection.JavaConverters.asScalaBufferConverter
import twitter4j.UserList
import twitter4s.implicits.Twitter4SImplicits._

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
    }
  }
}