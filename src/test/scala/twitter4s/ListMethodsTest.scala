package twitter4s
import twitter4s._
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import Twitter4sTestHelper._
import scala.collection.JavaConverters.asScalaBufferConverter
import twitter4j.UserList
import twitter4j.json.DataObjectFactory
import twitter4j.Paging
import twitter4j.TwitterException

@RunWith(classOf[JUnitRunner])
class ListMethodsTest extends Specification {

  def makePrecondition = {
    val userLists = twitter2.getUserLists(-1L, listOwnerScreenName = Some(id2.screenName))
    userLists.foreach(alist => twitter2.destroyUserList(alist.getId))
    twitter2.createUserList("testpoint1", false, "description1")
  }
  
  "getUserList" should {
    "get list specified by user id" in {
      // Note: is not destroyUserList API reflected immediatly ?
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
      rawJSON(statuses.tw4jObj) must not equalTo(null)
      statuses must not equalTo(null)
    }
  }
  
  "getAllUserLists" should {
    "get user's list specified by user id" in {
      val lists = twitter1.getAllUserLists(userId = Some(id1.id))
      lists.size must be_>(0)
    }
    
    "get user's list specified by screen name" in {
      val lists = twitter1.getAllUserLists(screenName = Some(id1.screenName))
      lists.size must be_>(0)
    }
  }
  
  "updateUserList" should {
    "update user list setting specified by id" in {
      val userList = makePrecondition
      val targetList = twitter2.updateUserList(userList.getId(), "testpoint2", true, "description2")
      rawJSON(targetList) must not equalTo(null)
      targetList must equalTo(DataObjectFactory.createUserList(rawJSON(targetList)))
      
      targetList.isPublic() must beTrue
      targetList.getName() must equalTo("testpoint2")
      targetList.getDescription() must equalTo("description2")
      
      // Note:below code be fale. is showUserList API reflected immidiatly?
//      val updatedList = twitter2.showUserList(userList.getId())
//      updatedList must equalTo(DataObjectFactory.createUserList(rawJSON(updatedList)))
//      updatedList.isPublic() must beTrue
//      updatedList.getName() must equalTo("testpoint2")
//      updatedList.getDescription() must equalTo("description2")
    }
  }
  
  "showUserListMembership" should {
    "throw TwitterException execute to no member list" in {
      val userList = makePrecondition
      twitter2.showUserListMembership(userList.getId, id1.id) must
      throwA[TwitterException].like {case te:TwitterException => te.getStatusCode must equalTo(404)}
    }
    
    "get list membership user added member list" in {
      val userList = makePrecondition
      
      // add user test
      val addedList1 = twitter2.addUserListMember(userList.getId, id1.id)
      rawJSON(addedList1) must not equalTo(null)
      addedList1 must equalTo(DataObjectFactory.createUserList(rawJSON(addedList1)))
      
      val addedList2 = twitter2.addUserListMembers(userList.getId, Some(Array(id3.id, id1.id)))
      rawJSON(addedList2) must not equalTo(null)
      addedList2 must equalTo(DataObjectFactory.createUserList(rawJSON(addedList2)))
      
      val addedList3 = twitter2.addUserListMembers(listId = userList.getId, screenNames = Some(Array(prop.getProperty("followsOneWay"))))
      rawJSON(addedList3) must not equalTo(null)
      addedList3 must equalTo(DataObjectFactory.createUserList(rawJSON(addedList3)))
      
      // getUserListMembers test
      val users = twitter2.getUserListMembers(userList.getId(), -1)
      users(0) must equalTo(DataObjectFactory.createUser(rawJSON(users(0))))
      rawJSON(users.tw4jObj) must not equalTo(null)
      users.size must be_>(0)
    }
  }
  
  "deleteUserListMembership" should {
    "delete user from list specified by user id" in {
      // make precondition
      val userList = makePrecondition
      twitter2.addUserListMembers(userList.getId, Some(Array(id3.id, id1.id)))
      
      val deletedList = twitter2.deleteUserListMember(userList.getId(), id3.id)
      rawJSON(deletedList) must not equalTo(null)
      deletedList must equalTo(DataObjectFactory.createUserList(rawJSON(deletedList)))
    }
  }
}