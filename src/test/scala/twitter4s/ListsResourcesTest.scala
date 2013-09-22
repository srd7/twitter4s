package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import Twitter4sTestHelper._
import twitter4s.api.impl.ListsResourcesImpl
import twitter4s.mocked.FakeValuesUsedByMock

@RunWith(classOf[JUnitRunner])
class ListsResourcesTest extends Specification with TwitterResourcesTestBase {

  type TargetResourcesType = ListsResourcesImpl

  mockedTwitter4j.getUserLists(anyString) returns FakeValuesUsedByMock.responseList[twitter4j.UserList]
  mockedTwitter4j.getUserLists(anyLong) returns FakeValuesUsedByMock.responseList[twitter4j.UserList]
  mockedTwitter4j.showUserList(anyInt) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.showUserList(anyString, anyString) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.showUserList(anyLong, anyString) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.getUserListStatuses(anyInt, any[twitter4j.Paging]) returns FakeValuesUsedByMock.responseList[twitter4j.Status]
  mockedTwitter4j.getUserListStatuses(anyString, anyString, any[twitter4j.Paging]) returns FakeValuesUsedByMock.responseList[twitter4j.Status]
  mockedTwitter4j.getUserListStatuses(anyLong, anyString, any[twitter4j.Paging]) returns FakeValuesUsedByMock.responseList[twitter4j.Status]
  mockedTwitter4j.updateUserList(anyInt, anyString, any[java.lang.Boolean], anyString) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.updateUserList(anyString, anyString, anyString, any[java.lang.Boolean], anyString) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.updateUserList(anyLong, anyString, anyString, any[java.lang.Boolean], anyString) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.showUserListMembership(anyInt, anyLong) returns FakeValuesUsedByMock.user
  mockedTwitter4j.showUserListMembership(anyString, anyString, anyLong) returns FakeValuesUsedByMock.user
  mockedTwitter4j.showUserListMembership(anyLong, anyString, anyLong) returns FakeValuesUsedByMock.user
  mockedTwitter4j.createUserListMember(anyInt, anyLong) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.createUserListMember(anyString, anyString, anyLong) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.createUserListMember(anyLong, anyString, anyLong) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.createUserListMembers(anyInt, any[Array[String]]) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.createUserListMembers(anyInt, any[Array[Long]]) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.createUserListMembers(anyString, anyString, any[Array[String]]) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.createUserListMembers(anyString, anyString, any[Array[Long]]) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.createUserListMembers(anyLong, anyString, any[Array[String]]) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.createUserListMembers(anyLong, anyString, any[Array[Long]]) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.createUserListSubscription(anyInt) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.createUserListSubscription(anyString, anyString) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.createUserListSubscription(anyLong, anyString) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.createUserList(anyString, any[java.lang.Boolean], anyString) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.destroyUserList(anyInt) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.destroyUserList(anyString, anyString) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.destroyUserList(anyLong, anyString) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.getUserListMemberships(anyLong) returns FakeValuesUsedByMock.pagableResponseList[twitter4j.UserList]
  mockedTwitter4j.getUserListMemberships(anyString, anyLong) returns FakeValuesUsedByMock.pagableResponseList[twitter4j.UserList]
  mockedTwitter4j.getUserListMemberships(anyString, anyLong, any[java.lang.Boolean]) returns FakeValuesUsedByMock.pagableResponseList[twitter4j.UserList]
  mockedTwitter4j.getUserListMemberships(anyLong, anyLong) returns FakeValuesUsedByMock.pagableResponseList[twitter4j.UserList]
  mockedTwitter4j.getUserListMemberships(anyLong, anyLong, any[java.lang.Boolean]) returns FakeValuesUsedByMock.pagableResponseList[twitter4j.UserList]
  mockedTwitter4j.getUserListSubscriptions(anyString, anyLong) returns FakeValuesUsedByMock.pagableResponseList[twitter4j.UserList]
  mockedTwitter4j.getUserListMembers(anyInt, anyLong) returns FakeValuesUsedByMock.pagableResponseList[twitter4j.User]
  mockedTwitter4j.getUserListMembers(anyString, anyString, anyLong) returns FakeValuesUsedByMock.pagableResponseList[twitter4j.User]
  mockedTwitter4j.getUserListMembers(anyLong, anyString, anyLong) returns FakeValuesUsedByMock.pagableResponseList[twitter4j.User]
  mockedTwitter4j.addUserListMember(anyInt, anyLong) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.addUserListMembers(anyInt, any[Array[String]]) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.addUserListMembers(anyInt, any[Array[Long]]) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.deleteUserListMember(anyInt, anyLong) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.destroyUserListMember(anyInt, anyLong) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.destroyUserListMember(anyString, anyString, anyLong) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.destroyUserListMember(anyLong, anyString, anyLong) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.getUserListSubscribers(anyInt, anyLong) returns FakeValuesUsedByMock.pagableResponseList[twitter4j.User]
  mockedTwitter4j.getUserListSubscribers(anyString, anyString, anyLong) returns FakeValuesUsedByMock.pagableResponseList[twitter4j.User]
  mockedTwitter4j.getUserListSubscribers(anyLong, anyString, anyLong) returns FakeValuesUsedByMock.pagableResponseList[twitter4j.User]
  mockedTwitter4j.destroyUserListSubscription(anyInt) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.destroyUserListSubscription(anyString, anyString) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.destroyUserListSubscription(anyLong, anyString) returns FakeValuesUsedByMock.userList
  mockedTwitter4j.showUserListSubscription(anyInt, anyLong) returns FakeValuesUsedByMock.user
  mockedTwitter4j.showUserListSubscription(anyString, anyString, anyLong) returns FakeValuesUsedByMock.user
  mockedTwitter4j.showUserListSubscription(anyLong, anyString, anyLong) returns FakeValuesUsedByMock.user

  val twitter = new Twitter(mockedTwitter4j) with TargetResourcesType
  
  "getUserLists" should {
    "call twitter4j getUserLists method by screen name" in {
      twitter.getUserLists(User.isSpecifiedBy(FakeValuesUsedByMock.userName)).size must equalTo(1)
      there was one(mockedTwitter4j).getUserLists(FakeValuesUsedByMock.userName)
    }

    "call twitter4j getUserLists method by user id" in {
      twitter.getUserLists(User.isSpecifiedBy(1L)).size must equalTo(1)
      there was one(mockedTwitter4j).getUserLists(1L)
    }
    
    "throw exception both of parameter listOwnerScreenName and listOwnerUserId are not set" in {
      twitter.getUserLists(null) must
      throwA[IllegalArgumentException]
    }
  }
  
  "showUserList" should {
    "call twitter4j showUserList method by list id" in {
      twitter.showUserList(UserList.isSpecifiedBy(2)).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).showUserList(2)
    }

    "call twitter4j showUserList method by screen name and slug" in {
      twitter.showUserList(
        UserList.isSpecifiedBy(
          User.isSpecifiedBy(FakeValuesUsedByMock.userName),
          "slug with screen name")).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).showUserList(FakeValuesUsedByMock.userName, "slug with screen name")
    }

    "call twitter4j showUserList method by user id and slug" in {
      twitter.showUserList(
        UserList.isSpecifiedBy(
          User.isSpecifiedBy(3L), "slug with user id")).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).showUserList(3L, "slug with user id")
    }
    
    "throws exception list specific parameter is null" in {
      twitter.showUserList(null) must
      throwA[IllegalArgumentException]
    }
    
    "throws exception user specific parameter is null" in {
      twitter.showUserList(UserList.isSpecifiedBy(null, "test")) must
      throwA[IllegalArgumentException]
    }
  }
  
  "getUserListStatuses" should {
    "call twitter4j getUserListStatuses by list id and paging" in {
      twitter.getUserListStatuses(
        UserList.isSpecifiedBy(4), Paging()).size must equalTo(1)
      there was one(mockedTwitter4j).getUserListStatuses(4, Paging())
    }

    "call twitter4j getUserListStatuses by screen name with slug and paging" in {
      twitter.getUserListStatuses(
        UserList.isSpecifiedBy(
          User.isSpecifiedBy(FakeValuesUsedByMock.userName),
          "slug with screen name"),
        Paging()).size must equalTo(1)
      there was one(mockedTwitter4j).getUserListStatuses(FakeValuesUsedByMock.userName, "slug with screen name", Paging())
    }

    "call twitter4j getUserListStatuses by user id with slug and paging" in {
      twitter.getUserListStatuses(
        UserList.isSpecifiedBy(
          User.isSpecifiedBy(5L), "slug with user id"), Paging()).size must equalTo(1)
      there was one(mockedTwitter4j).getUserListStatuses(5L, "slug with user id", Paging())
    }
    
    "throw exception list specific parameter is null" in {
      twitter.getUserListStatuses(null, Paging()) must
      throwA[IllegalArgumentException]
    }
    
    "throw exception user specific parameter is null" in {
      twitter.getUserListStatuses(UserList.isSpecifiedBy(null, "test"), Paging()) must
      throwA[IllegalArgumentException]
    }
  }
  
  "updateUserList" should {
    "call twitter4j updateUserList by list id and other parameters" in {
      twitter.updateUserList(
        UserList.isSpecifiedBy(6),
        "new list, specified by list id", true, "description: new list").fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).updateUserList(6, "new list, specified by list id", true, "description: new list")
    }

    "call twitter4j updateUserList by screen name with slug and other parameters" in {
      twitter.updateUserList(
        UserList.isSpecifiedBy(
          User.isSpecifiedBy(FakeValuesUsedByMock.userName),
          "slug with screen name"),
        "new list, specified by list id", true, "description: new list").fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).updateUserList(
        FakeValuesUsedByMock.userName,
        "slug with screen name",
        "new list, specified by list id", true, "description: new list")
    }

    "call twitter4j updateUserList by user id with slug and other parameters" in {
      twitter.updateUserList(
        UserList.isSpecifiedBy(User.isSpecifiedBy(7L), "slug with user id"),
        "new list, specified by list id", true, "description: new list").fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).updateUserList(
        7L, "slug with user id",
        "new list, specified by list id", true, "description: new list")
    }
    
    "throw exception list specific parameter is null" in {
      twitter.updateUserList(null, "test", true, "test") must
      throwA[IllegalArgumentException]
    }
    
    "throw exception user specific parameter is null" in {
      twitter.updateUserList(UserList.isSpecifiedBy(null, "slug"), "test", true, "test") must
      throwA[IllegalArgumentException]
    }
  }
  
  "showUserListMembership" should {
    "call twitter4j showUserListMembership method by list id" in {
      twitter.showUserListMembership(UserList.isSpecifiedBy(8), 9L).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).showUserListMembership(8, 9L)
    }

    "call twitter4j showUserListMembership method by screen name with slug" in {
      twitter.showUserListMembership(
        UserList.isSpecifiedBy(
          User.isSpecifiedBy(FakeValuesUsedByMock.userName),
          "slug with screen name"),
        10L).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).showUserListMembership(FakeValuesUsedByMock.userName, "slug with screen name", 10L)
    }

    "call twitter4j showUserListMembership method by user id with slug" in {
      twitter.showUserListMembership(
        UserList.isSpecifiedBy(
          User.isSpecifiedBy(11L), "slug with user id"), 12L).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).showUserListMembership(11L, "slug with user id", 12L)
    }

    "throw exception list specific parameter is null" in {
      twitter.showUserListMembership(null, 13L) must
      throwA[IllegalArgumentException]
    }

    "throw exception user specific parameter is null" in {
      twitter.showUserListMembership(UserList.isSpecifiedBy(null, "slug"), 14L) must
      throwA[IllegalArgumentException]
    }
  }
  
  "createUserListMember" should {
    "call twitter4j createUserListMember method by list id" in {
      twitter.createUserListMember(UserList.isSpecifiedBy(15), 16L).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).createUserListMember(15, 16L)
    }

    "call twitter4j createUserListMember method by screen name with slug" in {
      twitter.createUserListMember(
        UserList.isSpecifiedBy(
          User.isSpecifiedBy(FakeValuesUsedByMock.userName),
          "slug with screen name"),
        17L).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).createUserListMember(FakeValuesUsedByMock.userName, "slug with screen name", 17L)
    }

    "call twitter4j createUserListMember method by user id with slug" in {
      twitter.createUserListMember(
        UserList.isSpecifiedBy(
          User.isSpecifiedBy(18L),
          "slug with user id"),
        19L).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).createUserListMember(18L, "slug with user id", 19L)
    }
    
    "throw exception list specific parameter is null" in {
      twitter.createUserListMember(null, 1L) must
      throwA[IllegalArgumentException]
    }
    
    "throw exception user specific parameter is null" in {
      twitter.createUserListMember(UserList.isSpecifiedBy(null, "test"), 1L) must
      throwA[IllegalArgumentException]
    }
  }
  
  "createUserListMembers" should {
    "call twitter4j createUserListMembers method by list id and screen name users" in {
      twitter.createUserListMembers(
        UserList.isSpecifiedBy(20),
        Users.areSpecifiedBy(Array("add user 1", "add user 2"))).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).createUserListMembers(20, Array("add user 1", "add user 2"))
    }

    "call twitter4j createUserListMembers method by list id and user id members" in {
      twitter.createUserListMembers(
        UserList.isSpecifiedBy(21),
        Users.areSpecifiedBy(Array(22L, 23L, 24L))).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).createUserListMembers(21, Array(22L, 23L, 24L))
    }

    "call twitter4j createUserListMembers method by screen name with slug and screen name users" in {
      twitter.createUserListMembers(
        UserList.isSpecifiedBy(User.isSpecifiedBy(FakeValuesUsedByMock.userName), "slug with screen name"),
        Users.areSpecifiedBy(Array("add user 1", "add user 2", "add user 3"))).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).createUserListMembers(
        FakeValuesUsedByMock.userName, "slug with screen name", Array("add user 1", "add user 2", "add user 3"))
    }

    "call twitter4j createUserListMembers method by screen name with slug and user id members" in {
      twitter.createUserListMembers(
        UserList.isSpecifiedBy(User.isSpecifiedBy(FakeValuesUsedByMock.userName), "slug with screen name"),
        Users.areSpecifiedBy(Array(25L))).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).createUserListMembers(FakeValuesUsedByMock.userName, "slug with screen name", Array(25L))
    }

    "call twitter4j createUserListMembers method by user id with slug and screen name users" in {
      twitter.createUserListMembers(
        UserList.isSpecifiedBy(User.isSpecifiedBy(26L), "slug with user id"),
        Users.areSpecifiedBy(Array("add user 1"))).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).createUserListMembers(26L, "slug with user id", Array("add user 1"))
    }

    "call twitter4j createUserListMembers method by user id with slug and user id members" in {
      twitter.createUserListMembers(
        UserList.isSpecifiedBy(User.isSpecifiedBy(27L), "slug with user id"),
        Users.areSpecifiedBy(Array(28L, 29L))).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).createUserListMembers(27L, "slug with user id", Array(28L, 29L))
    }

    "throw exception list specific parameter is null" in {
      twitter.createUserListMembers(null, Users.areSpecifiedBy(Array(prop.getProperty("followsOneWay")))) must
      throwA[IllegalArgumentException]
    }
    
    "throw exception user specific parameter is null" in {
      twitter.createUserListMembers(UserList.isSpecifiedBy(null, "test"), Users.areSpecifiedBy(Array(id3.id, id1.id))) must
      throwA[IllegalArgumentException]
    }
    
    "throw exception add users specific parameter is null" in {
      twitter.createUserListMembers(UserList.isSpecifiedBy(1), null) must
      throwA[IllegalArgumentException]
    }
  }
  
  "createUserListSubscription" should {
    "call twitter4j createUserListSubscription method by list id" in {
      twitter.createUserListSubscription(UserList.isSpecifiedBy(30)).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).createUserListSubscription(30)
    }

    "call twitter4j createUserListSubscription method by screen name with slug" in {
      twitter.createUserListSubscription(
        UserList.isSpecifiedBy(
          User.isSpecifiedBy(FakeValuesUsedByMock.userName), "slug with screen name")).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).createUserListSubscription(FakeValuesUsedByMock.userName, "slug with screen name")
    }

    "call twitter4j createUserListSubscription method by user id with slug" in {
      twitter.createUserListSubscription(
        UserList.isSpecifiedBy(
          User.isSpecifiedBy(31L), "slug with user id")).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).createUserListSubscription(31L, "slug with user id")
    }
    
    "throw exception list specific info is null" in {
      twitter.createUserListSubscription(null) must
      throwA[IllegalArgumentException]
    }
    
    "throw exception list owner specific info is null" in {
      twitter.createUserListSubscription(UserList.isSpecifiedBy(null, "test")) must
      throwA[IllegalArgumentException]
    }
  }

  "createUserList" should {
    "call twitter4j createUserList method" in {
      twitter.createUserList(FakeValuesUsedByMock.listName, true, "test new list").fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).createUserList(FakeValuesUsedByMock.listName, true, "test new list")
    }
  }

  "destroyUserList" should {
    "call twitter4j destroyUserList method by list id" in {
      twitter.destroyUserList(UserList.isSpecifiedBy(32)).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).destroyUserList(32)
    }

    "call twitter4j destroyUserList method by screen name with slug" in {
      twitter.destroyUserList(
        UserList.isSpecifiedBy(
          User.isSpecifiedBy(FakeValuesUsedByMock.userName),
          "slug with screen name")).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).destroyUserList(FakeValuesUsedByMock.userName, "slug with screen name")
    }

    "call twitter4j destroyUserList method by user id with slug" in {
      twitter.destroyUserList(
        UserList.isSpecifiedBy(
          User.isSpecifiedBy(33L), "slug with user id")).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).destroyUserList(33L, "slug with user id")
    }

    "throw exception list specific info is null" in {
      twitter.destroyUserList(null) must
        throwA[IllegalArgumentException]
    }

    "throw exception list owner specific info is null" in {
      twitter.destroyUserList(UserList.isSpecifiedBy(null, "test")) must
        throwA[IllegalArgumentException]
    }
  }

  "getUserListMemberships" should {
    "call twitter4j getUserListMemberships by cursor only" in {
      twitter.getUserListMemberships(cursor = 34L).size must equalTo(50)
      there was one(mockedTwitter4j).getUserListMemberships(34L)
    }

    "call twitter4j getUserListMemberships by screen name and cursor" in {
      twitter.getUserListMemberships(User.isSpecifiedBy(FakeValuesUsedByMock.userName), 35L).size must equalTo(50)
      there was one(mockedTwitter4j).getUserListMemberships(FakeValuesUsedByMock.userName, 35L)
    }

    "call twitter4j getUserListMemberships by screen name and cursor and filter flag" in {
      twitter.getUserListMemberships(User.isSpecifiedBy(FakeValuesUsedByMock.userName), 36L, false).size must equalTo(50)
      there was one(mockedTwitter4j).getUserListMemberships(FakeValuesUsedByMock.userName, 36L, false)
    }

    "call twitter4j getUserListMemberships by user id and cursor" in {
      twitter.getUserListMemberships(User.isSpecifiedBy(37L), 38L).size must equalTo(50)
      there was one(mockedTwitter4j).getUserListMemberships(37L, 38L)
    }

    "call twitter4j getUserListMemberships by user id and cursor and filter flag" in {
      twitter.getUserListMemberships(User.isSpecifiedBy(39L), 40L, true).size must equalTo(50)
      there was one(mockedTwitter4j).getUserListMemberships(39L, 40L, true)
    }
  }

  "getUserListSubscriptions" should {
    "call twitter4j getUserListSubscriptions" in {
      twitter.getUserListSubscriptions(41L, FakeValuesUsedByMock.listName).size must equalTo(50)
      there was one(mockedTwitter4j).getUserListSubscriptions(FakeValuesUsedByMock.listName, 41L)
    }
  }

  "getUserListMembers" should {
    "call twitter4j getUesrListMembers method by list id" in {
      twitter.getUserListMembers(UserList.isSpecifiedBy(42), 43L).size must equalTo(50)
      there was one(mockedTwitter4j).getUserListMembers(42, 43L)
    }

    "call twitter4j getUserListMembers method by screen name with slug" in {
      twitter.getUserListMembers(
        UserList.isSpecifiedBy(
          User.isSpecifiedBy(FakeValuesUsedByMock.userName), "slug with screen name"),
        44L).size must equalTo(50)
      there was one(mockedTwitter4j).getUserListMembers(FakeValuesUsedByMock.userName, "slug with screen name", 44L)
    }

    "call twitter4j getUserListMembers method by user id with slug" in {
      twitter.getUserListMembers(UserList.isSpecifiedBy(User.isSpecifiedBy(45L), "slug with user id"), 46L).size must equalTo(50)
      there was one(mockedTwitter4j).getUserListMembers(45L, "slug with user id", 46L)
    }

    "throw exception list specific info is null" in {
      twitter.getUserListMembers(null, 46L) must
        throwA[IllegalArgumentException]
    }

    "throw exception list owner specific info is null" in {
      twitter.getUserListMembers(UserList.isSpecifiedBy(null, "test"), 47L) must
        throwA[IllegalArgumentException]
    }
  }

  "addUserListMember" in {
    // Note:this method is deprecated on twitter4j 3.0.3
    "call twitter4j addUserListMember method" in {
      twitter.addUserListMember(48, 49L).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).addUserListMember(48, 49L)
    }
  }

  "addUserListMembers" in {
    // Note:this method is deprecated on twitter4j 3.0.3
    "call twitter4j addUserListMembers method by screen name list" in {
      twitter.addUserListMembers(50, Users.areSpecifiedBy(Array("add user 1", "add user 2"))).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).addUserListMembers(50, Array("add user 1", "add user 2"))
    }

    "call twitter4j addUserListMembers method by user id list" in {
      twitter.addUserListMembers(51, Users.areSpecifiedBy(Array(52L, 53L, 54L))).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).addUserListMembers(51, Array(52L, 53L, 54L))
    }

    "throw exception add user list is null" in {
      twitter.addUserListMembers(52, null) must
      throwA[IllegalArgumentException]
    }
  }

  "deleteUserListMember" should {
    // Note:this method is deprecated on twitter4j 3.0.3
    "call twitter4j deleteUserListMember method" in {
      twitter.deleteUserListMember(55, 56L).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).deleteUserListMember(55, 56L)
    }
  }

  "destroyUserListMember" should {
    "call twitter4j destroyUserListMember method by list id" in {
      twitter.destroyUserListMember(UserList.isSpecifiedBy(57), 58L).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).destroyUserListMember(57, 58L)
    }

    "call twitter4j destroyUserListMember method by screen name with slug" in {
      twitter.destroyUserListMember(
        UserList.isSpecifiedBy(
          User.isSpecifiedBy(FakeValuesUsedByMock.userName),
          "slug with screen name"),
        59L).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).destroyUserListMember(FakeValuesUsedByMock.userName, "slug with screen name", 59L)
    }

    "call twitter4j destroyUserListMember method by user id and slug" in {
      twitter.destroyUserListMember(
        UserList.isSpecifiedBy(
          User.isSpecifiedBy(60L), "slug with user id"),
        61L).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).destroyUserListMember(60L, "slug with user id", 61L)
    }

    "throw exception list specific info is null" in {
      twitter.destroyUserListMember(null, 62L) must
        throwA[IllegalArgumentException]
    }

    "throw exception list owner specific info is null" in {
      twitter.destroyUserListMember(UserList.isSpecifiedBy(null, "test"), 63L) must
        throwA[IllegalArgumentException]
    }
  }

  "getUserListSubscribers" should {
    "call twitter4j getUserListSubscribers method by list id" in {
      twitter.getUserListSubscribers(UserList.isSpecifiedBy(64), 65L).size must equalTo(50)
      there was one(mockedTwitter4j).getUserListSubscribers(64, 65L)
    }

    "call twitter4j getUserListSubscribers method by screen name with slug" in {
      twitter.getUserListSubscribers(
        UserList.isSpecifiedBy(
          User.isSpecifiedBy(FakeValuesUsedByMock.userName),
          "slug with screen name"),
        66L).size must equalTo(50)
      there was one(mockedTwitter4j).getUserListSubscribers(FakeValuesUsedByMock.userName, "slug with screen name", 66L)
    }

    "call twitter4j getUserListSubscribers method by user id with slug" in {
      twitter.getUserListSubscribers(
        UserList.isSpecifiedBy(
          User.isSpecifiedBy(67L), "slug with user id"),
        68L).size must equalTo(50)
      there was one(mockedTwitter4j).getUserListSubscribers(67L, "slug with user id", 68L)
    }

    "throw exception list specific info is null" in {
      twitter.getUserListSubscribers(null, 69L) must
        throwA[IllegalArgumentException]
    }

    "throw exception list owner specific info is null" in {
      twitter.getUserListSubscribers(UserList.isSpecifiedBy(null, "test"), 70L) must
        throwA[IllegalArgumentException]
    }
  }

  "destroyUserListSubscription" should {
    "call twitter4j destroyUserListSubscription by list id" in {
      twitter.destroyUserListSubscription(UserList.isSpecifiedBy(71)).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).destroyUserListSubscription(71)
    }

    "call twitter4j destroyUserListSubscription by screen name with slug" in {
      twitter.destroyUserListSubscription(
        UserList.isSpecifiedBy(
          User.isSpecifiedBy(FakeValuesUsedByMock.userName),
          "slug with screen name")).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).destroyUserListSubscription(FakeValuesUsedByMock.userName, "slug with screen name")
    }

    "call twitter4j destroyUserListSubscription by user id with slug" in {
      twitter.destroyUserListSubscription(UserList.isSpecifiedBy(User.isSpecifiedBy(72L), "slug with user id")).fullName must equalTo(FakeValuesUsedByMock.listName)
      there was one(mockedTwitter4j).destroyUserListSubscription(72L, "slug with user id")
    }

    "throw exception list specific info is null" in {
      twitter.destroyUserListSubscription(null) must
        throwA[IllegalArgumentException]
    }

    "throw exception list owner specific info is null" in {
      twitter.destroyUserListSubscription(UserList.isSpecifiedBy(null, "test")) must
        throwA[IllegalArgumentException]
    }
  }

  "showUserListSubscription" should {
    "call twitter4j showUserListSubscription method by list id" in {
      twitter.showUserListSubscription(UserList.isSpecifiedBy(73), 74L).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).showUserListSubscription(73, 74L)
    }

    "call twitter4j showUserListSubscription method by screen name with slug" in {
      twitter.showUserListSubscription(
        UserList.isSpecifiedBy(
          User.isSpecifiedBy(FakeValuesUsedByMock.userName),
          "slug with screen name"),
        75L).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).showUserListSubscription(FakeValuesUsedByMock.userName, "slug with screen name", 75L)
    }

    "call twitter4j showUserListSubscription method by user id with slug" in {
      twitter.showUserListSubscription(UserList.isSpecifiedBy(User.isSpecifiedBy(76L), "slug with user id"), 77L).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).showUserListSubscription(76L, "slug with user id", 77L)
    }

    "throw exception list specific info is null" in {
      twitter.showUserListSubscription(null, 78L) must
        throwA[IllegalArgumentException]
    }

    "throw exception list owner specific info is null" in {
      twitter.showUserListSubscription(UserList.isSpecifiedBy(null, "test"), 79L) must
        throwA[IllegalArgumentException]
    }
  }
}