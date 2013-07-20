package twitter4s
import twitter4s._
import scala.collection.JavaConverters.asScalaBufferConverter

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import twitter4j.TwitterException

@RunWith(classOf[JUnitRunner])
class ListResourcesTest extends Specification {

  def makePrecondition = {
    val userLists = twitter2.getUserLists(
        User.isSpecifiedBy(id2.screenName), -1L)
    userLists.foreach(alist => try {twitter2.destroyUserList(alist.getId)} catch {case e:TwitterException =>})
    twitter2.createUserList("testpoint1", false, "description1")
  }
  
  "getUserList" should {
    "get list specified by user id" in {
      // Note: is not destroyUserList API reflected immediatly ?
      val userList = makePrecondition
      rawJSON(userList.tw4jObj) must not equalTo(null) 
      userList.tw4jObj must equalTo(DataObjectFactory.createUserList(rawJSON(userList.tw4jObj)))
      //userList.tw4jObj must not equalTo(null)
      userList.name must equalTo("testpoint1")
      userList.description must equalTo("description1")
      
      val userLists = twitter1.getUserLists(
          User.isSpecifiedBy(id1.screenName), -1L)
      userLists.size must not equalTo(0)
      
      // showUserList test
      val showUserList = twitter2.showUserList(userList.id)
      showUserList must not equalTo(null)
      rawJSON(showUserList.tw4jObj) must not equalTo(null)
      showUserList.tw4jObj must equalTo(DataObjectFactory.createUserList(rawJSON(showUserList.tw4jObj)))
    }
    
    "throw exception both of parameter listOwnerScreenName and listOwnerUserId are not set" in {
      twitter1.getUserLists(null, -1L) must
      throwA[IllegalArgumentException]
    }
  }
  
  "getUserListStatuses" should {
    "get status from user list specified by list id" in {
      val userLists = twitter1.getUserLists(
          User.isSpecifiedBy(id1.id), -1L)
      val statuses = twitter1.getUserListStatuses(userLists(0).getId, Paging())
      statuses(0) must equalTo(DataObjectFactory.createStatus(rawJSON(statuses(0))))
      rawJSON(statuses.tw4jObj) must not equalTo(null)
      statuses must not equalTo(null)
    }
  }
  
  "getAllUserLists" should {
    "get user's list specified by user id" in {
      val lists = twitter1.getAllUserLists(
          User.isSpecifiedBy(id1.id))
      lists.size must be_>(0)
    }
    
    "get user's list specified by screen name" in {
      val lists = twitter1.getAllUserLists(
          User.isSpecifiedBy(id1.screenName))
      lists.size must be_>(0)
    }
    
    "throw exception both of parameter are not set" in {
      twitter1.getAllUserLists(null) must
      throwA[IllegalArgumentException]
    }
  }
  
  "updateUserList" should {
    "update user list setting specified by id" in {
      val userList = makePrecondition
      Thread.sleep(3000)
      val targetList = twitter2.updateUserList(userList.id, "testpoint2", true, "description2")
      rawJSON(targetList.tw4jObj) must not equalTo(null)
      targetList.tw4jObj must equalTo(DataObjectFactory.createUserList(rawJSON(targetList.tw4jObj)))
      
      targetList.isPublic must beTrue
      targetList.name must equalTo("testpoint2")
      targetList.description must equalTo("description2")
    }
  }
  
  "addUserListMembers" should {
    "throw exception both of parameter specificUsers is null" in {
      twitter1.addUserListMembers(1, null) must 
      throwA[IllegalArgumentException]
    }
  }
  
  "ListMembersMethods API" should {
    "get list membership user added member list" in {
      // test no member list
      val userList = makePrecondition
      Thread.sleep(3000)
      twitter2.showUserListMembership(userList.id, id1.id) must
      throwA[TwitterException].like {case te:TwitterException => te.getStatusCode must equalTo(404)}
      
      // add user test
      val addedList1 = twitter2.addUserListMember(userList.id, id1.id)
      rawJSON(addedList1.tw4jObj) must not equalTo(null)
      addedList1.tw4jObj must equalTo(DataObjectFactory.createUserList(rawJSON(addedList1.tw4jObj)))
      
      val addedList2 = twitter2.addUserListMembers(userList.id, Users.areSpecifiedBy(Array(id3.id, id1.id)))
      rawJSON(addedList2.tw4jObj) must not equalTo(null)
      addedList2.tw4jObj must equalTo(DataObjectFactory.createUserList(rawJSON(addedList2.tw4jObj)))
      
      val addedList3 = twitter2.addUserListMembers(listId = userList.id, Users.areSpecifiedBy(Array(prop.getProperty("followsOneWay"))))
      rawJSON(addedList3.tw4jObj) must not equalTo(null)
      addedList3.tw4jObj must equalTo(DataObjectFactory.createUserList(rawJSON(addedList3.tw4jObj)))
      
      // getUserListMembers test
      val users = twitter2.getUserListMembers(userList.id, -1)
      rawJSON(users.tw4jObj) must not equalTo(null)
      users(0) must equalTo(DataObjectFactory.createUser(rawJSON(users(0))))
      users.size must be_>(0)
      
      // showUserListMembership test
      val user = twitter2.showUserListMembership(userList.id, id3.id)
      rawJSON(user.tw4jObj) must not equalTo(null)
      user.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(user.tw4jObj)))
      user.id must equalTo(id3.id)
      
      // deleteUserListMember test
      val deletedList = twitter2.deleteUserListMember(userList.id, id3.id)
      rawJSON(deletedList.tw4jObj) must not equalTo(null)
      deletedList.tw4jObj must equalTo(DataObjectFactory.createUserList(rawJSON(deletedList.tw4jObj)))
      
      // getUserListMemberships test
      val userLists = twitter2.getUserListMemberships(
          User.isSpecifiedBy(id1.screenName),
          -1)
      rawJSON(userLists.tw4jObj) must not equalTo(null)
      userLists(0) must equalTo(DataObjectFactory.createUserList(rawJSON(userLists(0))))
      
      // getUserListSubscriptions test
      val sbScripUserLists = twitter2.getUserListSubscriptions(-1, id1.screenName)
      rawJSON(sbScripUserLists.tw4jObj) must not equalTo(null)
      if(sbScripUserLists.size > 0)
        sbScripUserLists(0) must equalTo(DataObjectFactory.createUserList(rawJSON(sbScripUserLists(0))))
      sbScripUserLists must not equalTo(null)
    }
  }
  
  "ListSubscribersMethods APIs" should {
    "get list subscribers" in {
      val userList = makePrecondition
      Thread.sleep(3000)
      val users = twitter2.getUserListSubscribers(userList.id, -1)
      rawJSON(users.tw4jObj) must not equalTo(null)
      users.size must equalTo(0)
      
      twitter1.createUserListSubscription(userList.id) must
      throwA[TwitterException].like { case te: TwitterException => te.getStatusCode() must equalTo(404)}
      
      twitter1.destroyUserListSubscription(userList.id) must
      throwA[TwitterException].like { case te: TwitterException => te.getStatusCode() must equalTo(404)}
      
      twitter2.showUserListSubscription(userList.id, id3.id) must
      throwA[TwitterException].like { case te: TwitterException => te.getStatusCode() must equalTo(404)}
    }
  }
}