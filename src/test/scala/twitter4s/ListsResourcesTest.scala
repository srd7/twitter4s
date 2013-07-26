package twitter4s
import twitter4s._
import scala.collection.JavaConverters.asScalaBufferConverter
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import twitter4j.TwitterException
import twitter4s.api.impl.ListsResourcesImpl

@RunWith(classOf[JUnitRunner])
class ListsResourcesTest extends Specification {
  
  val twitter1ListRole = new Twitter(twitter4jInstance(User1)) with ListsResourcesImpl
  val twitter2ListRole = new Twitter(twitter4jInstance(User2)) with ListsResourcesImpl

  def makePrecondition = {
    val userLists = twitter2ListRole.getUserLists(
        User.isSpecifiedBy(id2.screenName))
    userLists.foreach(alist => try {twitter2ListRole.destroyUserList(UserList.isSpecifiedBy(alist.getId))} catch {case e:TwitterException =>})
    twitter2ListRole.createUserList("testpoint1", true, "description1")
  }
  
  "getUserList" should {
    "get list specified by screen name" in {
      // Note: is not destroyUserList API reflected immediatly ?
      val userList = makePrecondition
      rawJSON(userList.tw4jObj) must not equalTo(null) 
      userList.tw4jObj must equalTo(DataObjectFactory.createUserList(rawJSON(userList.tw4jObj)))
      userList.name must equalTo("testpoint1")
      userList.description must equalTo("description1")
    }
    
    "get list specified by user id" in {
      val userList = twitter1ListRole.getUserLists(User.isSpecifiedBy(id1.id))
      rawJSON(userList.tw4jObj) must not equalTo(null)
      userList.size must not equalTo(0)
    }
    
    "throw exception both of parameter listOwnerScreenName and listOwnerUserId are not set" in {
      twitter1ListRole.getUserLists(null) must
      throwA[IllegalArgumentException]
    }
  }
  
  "showUserList" should {
    "show user list specified by list id" in {
      val userList = makePrecondition
      val showUserList = twitter2ListRole.showUserList(UserList.isSpecifiedBy(userList.id))
      
      showUserList must not equalTo(null)
      rawJSON(showUserList.tw4jObj) must not equalTo(null)
      showUserList.tw4jObj must equalTo(DataObjectFactory.createUserList(rawJSON(showUserList.tw4jObj)))
    }
    
    "show user list specified by userid and slug" in {
      val userList = makePrecondition
      val showUserList = twitter2ListRole.showUserList(UserList.isSpecifiedBy(User.isSpecifiedBy(id2.id), userList.slug))
      
      showUserList must not equalTo(null)
      rawJSON(showUserList.tw4jObj) must not equalTo(null)
      showUserList.tw4jObj must equalTo(DataObjectFactory.createUserList(rawJSON(showUserList.tw4jObj)))
    }
    
    "show user list specified by screen name and slug" in {
      val userList = makePrecondition
      val showUserList = twitter2ListRole.showUserList(UserList.isSpecifiedBy(User.isSpecifiedBy(id2.screenName), userList.slug))
      
      showUserList must not equalTo(null)
      rawJSON(showUserList.tw4jObj) must not equalTo(null)
      showUserList.tw4jObj must equalTo(DataObjectFactory.createUserList(rawJSON(showUserList.tw4jObj)))
    }
    
    "throws exception list specific parameter is null" in {
      twitter2ListRole.showUserList(null) must
      throwA[IllegalArgumentException]
    }
    
    "throws exception user specific parameter is null" in {
      twitter2ListRole.showUserList(UserList.isSpecifiedBy(null, "test")) must
      throwA[IllegalArgumentException]
    }
  }
  
  "getUserListStatuses" should {
    "get status from user list specified by list id" in {
      val userLists = twitter1ListRole.getUserLists(User.isSpecifiedBy(id1.id))
      val statuses = twitter1ListRole.getUserListStatuses(UserList.isSpecifiedBy(userLists(0).getId), Paging())
      
      statuses(0) must equalTo(DataObjectFactory.createStatus(rawJSON(statuses(0))))
      rawJSON(statuses.tw4jObj) must not equalTo(null)
      statuses must not equalTo(null)
    }
    
    "get status from user list specified by user id and slug" in {
      val userLists = twitter1ListRole.getUserLists(User.isSpecifiedBy(id1.id))
      val statuses = twitter1ListRole.getUserListStatuses(UserList.isSpecifiedBy(User.isSpecifiedBy(userLists(0).getUser().getId()), userLists(0).getSlug()), Paging())
      
      statuses(0) must equalTo(DataObjectFactory.createStatus(rawJSON(statuses(0))))
      rawJSON(statuses.tw4jObj) must not equalTo(null)
      statuses must not equalTo(null)
    }
    
    "get status from user list specified by screen name and slug" in {
      val userLists = twitter1ListRole.getUserLists(User.isSpecifiedBy(id1.id))
      val statuses = twitter1ListRole.getUserListStatuses(UserList.isSpecifiedBy(User.isSpecifiedBy(userLists(0).getUser().getScreenName()), userLists(0).getSlug()), Paging())
      
      statuses(0) must equalTo(DataObjectFactory.createStatus(rawJSON(statuses(0))))
      rawJSON(statuses.tw4jObj) must not equalTo(null)
      statuses must not equalTo(null)
    }
    
    "throw exception list specific parameter is null" in {
      twitter1ListRole.getUserListStatuses(null, Paging()) must
      throwA[IllegalArgumentException]
    }
    
    "throw exception user specific parameter is null" in {
      twitter1ListRole.getUserListStatuses(UserList.isSpecifiedBy(null, "test"), Paging()) must
      throwA[IllegalArgumentException]
    }
  }
  
  "updateUserList" should {
    "update user list setting specified by list id" in {
      val userList = makePrecondition
      Thread.sleep(3000)
      val targetList = twitter2ListRole.updateUserList(
          UserList.isSpecifiedBy(userList.id),
          "testpoint2", true, "description2")
      
      assertTestUserList(targetList)
    }
    
    "update user list setting specified by user id and slug" in {
      val userList = makePrecondition
      Thread.sleep(3000)
      val targetList = twitter2ListRole.updateUserList(
          UserList.isSpecifiedBy(User.isSpecifiedBy(userList.user.id), userList.slug),
          "testpoint2", true, "description2")
      
      assertTestUserList(targetList)
    }
    
    "throw exception list specific parameter is null" in {
      twitter2ListRole.updateUserList(null, "test", true, "test") must
      throwA[IllegalArgumentException]
    }
    
    "throw exception user specific parameter is null" in {
      twitter2ListRole.updateUserList(UserList.isSpecifiedBy(null, "slug"), "test", true, "test") must
      throwA[IllegalArgumentException]
    }
    
    def assertTestUserList(targetList: UserList) = {
      rawJSON(targetList.tw4jObj) must not equalTo(null)
      targetList.tw4jObj must equalTo(DataObjectFactory.createUserList(rawJSON(targetList.tw4jObj)))
      
      targetList.isPublic must beTrue
      targetList.name must equalTo("testpoint2")
      targetList.description must equalTo("description2")
    }
  }
  
  "showUserListMembership" should {
    // other test is wrote to createUserListMember specs
    // a measure for rate limit
    "throw exception on showing user not added" in {
      val userList = makePrecondition
      Thread.sleep(3000)
      twitter2ListRole.showUserListMembership(UserList.isSpecifiedBy(userList.id), id1.id) must
      throwA[TwitterException].like {case te:TwitterException => te.getStatusCode must equalTo(404)}
    }
  }
  
  "createUserListMember" should {
    "add user to list specified by list id" in {
      val userList = makePrecondition
      Thread.sleep(3000)
      
      val addedList1 = twitter2ListRole.createUserListMember(UserList.isSpecifiedBy(userList.id), id1.id)
      rawJSON(addedList1.tw4jObj) must not equalTo(null)
      addedList1.tw4jObj must equalTo(DataObjectFactory.createUserList(rawJSON(addedList1.tw4jObj)))
      
      // getUserListMembers test
      val users = twitter2ListRole.getUserListMembers(UserList.isSpecifiedBy(userList.id), -1)
      rawJSON(users.tw4jObj) must not equalTo(null)
      users(0) must equalTo(DataObjectFactory.createUser(rawJSON(users(0))))
      users.size must be_>(0)
      
      // showUserListMembership test
      val user = twitter2ListRole.showUserListMembership(UserList.isSpecifiedBy(userList.id), id1.id)
      rawJSON(user.tw4jObj) must not equalTo(null)
      user.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(user.tw4jObj)))
      user.id must equalTo(id1.id)
      
      // destroyUserListMember test
      val deletedList = twitter2ListRole.destroyUserListMember(UserList.isSpecifiedBy(userList.id), id1.id)
      rawJSON(deletedList.tw4jObj) must not equalTo(null)
      deletedList.tw4jObj must equalTo(DataObjectFactory.createUserList(rawJSON(deletedList.tw4jObj)))
    }
    
    "add user to list specified by user id and slug" in {
      val userList = makePrecondition
      Thread.sleep(3000)
      
      val addedList1 = twitter2ListRole.createUserListMember(
          UserList.isSpecifiedBy(User.isSpecifiedBy(userList.user.id), userList.slug), id1.id)
      rawJSON(addedList1.tw4jObj) must not equalTo(null)
      addedList1.tw4jObj must equalTo(DataObjectFactory.createUserList(rawJSON(addedList1.tw4jObj)))
      
      // getUserListMembers test
      val users = twitter2ListRole.getUserListMembers(
          UserList.isSpecifiedBy(User.isSpecifiedBy(userList.user.id), userList.slug), -1)
      rawJSON(users.tw4jObj) must not equalTo(null)
      users(0) must equalTo(DataObjectFactory.createUser(rawJSON(users(0))))
      users.size must be_>(0)
      
      // showUserListMembership test
      val user = twitter2ListRole.showUserListMembership(
          UserList.isSpecifiedBy(User.isSpecifiedBy(userList.user.id), userList.slug), id1.id)
      rawJSON(user.tw4jObj) must not equalTo(null)
      user.tw4jObj must equalTo(DataObjectFactory.createUser(rawJSON(user.tw4jObj)))
      user.id must equalTo(id1.id)
      
      // destroyUserListMember test
      val deletedList = twitter2ListRole.destroyUserListMember(
          UserList.isSpecifiedBy(User.isSpecifiedBy(userList.user.id), userList.slug), id1.id)
      rawJSON(deletedList.tw4jObj) must not equalTo(null)
      deletedList.tw4jObj must equalTo(DataObjectFactory.createUserList(rawJSON(deletedList.tw4jObj)))
    }
    
    "throw exception list specific parameter is null" in {
      twitter2ListRole.createUserListMember(null, 1L) must
      throwA[IllegalArgumentException]
    }
    
    "throw exception user specific parameter is null" in {
      twitter2ListRole.createUserListMember(UserList.isSpecifiedBy(null, "test"), 1L) must
      throwA[IllegalArgumentException]
    }
  }
  
  "createUserListMembers" should {
    "add users to list specified by list id" in {
      val userList = makePrecondition
      Thread.sleep(3000)
      
      val addedList1 = twitter2ListRole.createUserListMembers(UserList.isSpecifiedBy(userList.id), Users.areSpecifiedBy(Array(id3.id, id1.id)))
      rawJSON(addedList1.tw4jObj) must not equalTo(null)
      addedList1.tw4jObj must equalTo(DataObjectFactory.createUserList(rawJSON(addedList1.tw4jObj)))
    }
    
    "add users to list specified by screen name and slug" in {
      val userList = makePrecondition
      Thread.sleep(3000)
      
      val addedList1 = twitter2ListRole.createUserListMembers(
          UserList.isSpecifiedBy(User.isSpecifiedBy(userList.user.screenName), userList.slug),
          Users.areSpecifiedBy(Array(prop.getProperty("followsOneWay"))))
      rawJSON(addedList1.tw4jObj) must not equalTo(null)
      addedList1.tw4jObj must equalTo(DataObjectFactory.createUserList(rawJSON(addedList1.tw4jObj)))
    }
    
    "throw exception list specific parameter is null" in {
      twitter2ListRole.createUserListMembers(null, Users.areSpecifiedBy(Array(prop.getProperty("followsOneWay")))) must
      throwA[IllegalArgumentException]
    }
    
    "throw exception user specific parameter is null" in {
      twitter2ListRole.createUserListMembers(UserList.isSpecifiedBy(null, "test"), Users.areSpecifiedBy(Array(id3.id, id1.id))) must
      throwA[IllegalArgumentException]
    }
    
    "throw exception add users specific parameter is null" in {
      twitter2ListRole.createUserListMembers(UserList.isSpecifiedBy(1), null) must
      throwA[IllegalArgumentException]
    }
  }
  
  "createUserListSubscription" should {
    "create subscription to list specified by list id" in {
      val userList = makePrecondition
      Thread.sleep(3000)
      
      val list = twitter2ListRole.createUserListSubscription(UserList.isSpecifiedBy(userList.id))
      list must not equalTo(null)
      DataObjectFactory.createUser(rawJSON(list.tw4jObj)) must not equalTo(null)
    }
    
    "throw exception list specific info is null" in {
      twitter2ListRole.createUserListSubscription(null) must
      throwA[IllegalArgumentException]
    }
    
    "throw exception list owner specific info is null" in {
      twitter2ListRole.createUserListSubscription(UserList.isSpecifiedBy(null, "test")) must
      throwA[IllegalArgumentException]
    }
  }
  
  "ListMembersMethods API" should {
    "get list membership user added member list" in {
      // test no member list
      val userList = makePrecondition
      Thread.sleep(3000)
      
      // getUserListMemberships test
      val userLists = twitter2ListRole.getUserListMemberships(
          User.isSpecifiedBy(id1.screenName),
          -1)
      rawJSON(userLists.tw4jObj) must not equalTo(null)
      userLists(0) must equalTo(DataObjectFactory.createUserList(rawJSON(userLists(0))))
      
      // getUserListSubscriptions test
      val sbScripUserLists = twitter2ListRole.getUserListSubscriptions(-1, id1.screenName)
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
      val users = twitter2ListRole.getUserListSubscribers(UserList.isSpecifiedBy(userList.id), -1)
      rawJSON(users.tw4jObj) must not equalTo(null)
      users.size must equalTo(0)
      
      twitter2ListRole.createUserListSubscription(UserList.isSpecifiedBy(userList.id)) must
      throwA[TwitterException].like { case te: TwitterException => te.getStatusCode() must equalTo(404)}
      
      twitter2ListRole.destroyUserListSubscription(UserList.isSpecifiedBy(userList.id)) must
      throwA[TwitterException].like { case te: TwitterException => te.getStatusCode() must equalTo(404)}
      
      twitter2ListRole.showUserListSubscription(UserList.isSpecifiedBy(userList.id), id3.id) must
      throwA[TwitterException].like { case te: TwitterException => te.getStatusCode() must equalTo(404)}
    }
  }
}