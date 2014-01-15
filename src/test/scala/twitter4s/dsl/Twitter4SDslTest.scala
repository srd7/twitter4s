package twitter4s.dsl

/**
 * @author mao.instantlife at gmail.com
 */

import org.specs2.mutable._
import twitter4s.Twitter
import org.specs2.mock.Mockito
import twitter4s.mocked.FakeValuesUsedByMock

class Twitter4SDslTest extends Specification with Mockito {

  val mockedTwitter4j = mock[twitter4j.Twitter]
  mockedTwitter4j.createUserListMember(anyInt, anyLong) returns(FakeValuesUsedByMock.userList)
  mockedTwitter4j.createUserListMember(anyLong, anyString, anyLong) returns(FakeValuesUsedByMock.userList)
  mockedTwitter4j.showUser(anyString) returns(FakeValuesUsedByMock.user)
  mockedTwitter4j.showUser(anyLong) returns(FakeValuesUsedByMock.user)
  mockedTwitter4j.showUserList(anyLong, anyString) returns(FakeValuesUsedByMock.userList)
  mockedTwitter4j.showUserList(anyInt) returns(FakeValuesUsedByMock.userList)
  mockedTwitter4j.getId returns(1L)

  implicit val twitter = new Twitter(mockedTwitter4j)

  val testUserName = "test_user_name"
  val testMessage = "test direct message"

  "TwitterStringContext" should {
    "update method execute Twitter4j.updateStatus" in {
      val tweetString = "test tweet with implicit twitter class"

      tweet"$tweetString with $testUserName" update

      there was one(mockedTwitter4j).updateStatus(s"$tweetString with $testUserName")
    }
  }

  "UserContext" should {
    "get user with user's screen name context" in {
      get(user"${testUserName}")

      there was one(mockedTwitter4j).showUser(testUserName)
    }

    "get user with user's id context" in {
      val testUserId = 111L
      get(user"id:${testUserId}")

      there was one(mockedTwitter4j).showUser(testUserId)
    }
  }

  "ListContext" should {
    "get list with list's name context" in {
      val testListName = "test_user_list_name"

      get(list"${testListName}")

      there was atLeastOne(mockedTwitter4j).getId
      there was one(mockedTwitter4j).showUserList(1L, testListName)
    }

    "get list with list's id context" in {
      val testListId = 333
      get(list"id:$testListId")

      there was one(mockedTwitter4j).showUserList(testListId)
    }
  }

  "add user context string method" should {
    "returns UserAdder instance" in {
      val userContext = user"${testUserName}"
      (add(userContext)).userContext must equalTo(userContext)
    }
  }

  "UserAdder" should {
    "to(ListContext) method executes twitter4j.createUserListMember" in {
      (add(user"testUserName") to list"testListName").id must equalTo(FakeValuesUsedByMock.userList.getId)

      there was one(mockedTwitter4j).createUserListMember(
        1L, "testListName", FakeValuesUsedByMock.user.getId)
    }
  }

  "remove user string context method" should {
    "returns UserRemover instance" in {
      val userContext = user"${testUserName}"
      ((remove(userContext))).userContext must equalTo(userContext)
    }
  }

  "UserRemover" should {
    "from(ListContext specifiedBy list id) method executes twitter4j.destroyUserListMember" in {
      val listName = "testListName"
      val testUserId = 666L
      remove(user"id:${testUserId}") from list"${listName}"

      there was one(mockedTwitter4j).showUser(testUserId)
      there was one(mockedTwitter4j).destroyUserListMember(1L, listName, FakeValuesUsedByMock.user.getId)
    }

    "from(ListContext specifiedBy list slug) method executes twitter4j.destroyUserListMember" in {
      val listId = 777
      val testUserId = 888L
      remove(user"id:${testUserId}") from list"id:${listId}"

      there was one(mockedTwitter4j).showUser(testUserId)
      there was one(mockedTwitter4j).destroyUserListMember(listId, FakeValuesUsedByMock.user.getId)
    }
  }

  "MessageContext" should {
    "get message context" in {
      (message"$testMessage with $testUserName").message must equalTo(s"$testMessage with $testUserName")
    }

    "create MessageSender with send method" in {
      send(message"$testMessage with send method").messageContext must equalTo(message"$testMessage with send method")
    }
  }

  "MessageSender" should {
    "to(UserContext specifiedBy screen name) method executes twitter4j.sendDirectMessage" in {
      send(message"$testMessage on execute to method") to user"$testUserName"

      there was one(mockedTwitter4j).sendDirectMessage(testUserName, s"$testMessage on execute to method")
    }

    "to(UserContext specifiedBy user id) method executes twitter4j.sendDirectMessage" in {
      val testUserId = 444L
      send(message"$testMessage on execute to method") to user"id:$testUserId"

      there was one(mockedTwitter4j).sendDirectMessage(testUserId, s"$testMessage on execute to method")
    }
  }

  "follow user string context method" should {
    "execute twitter4j.createFriendship with screen name" in {
      follow(user"$testUserName")

      there was one(mockedTwitter4j).createFriendship(testUserName)
    }

    "execute twitter4j.createFriendship with user id" in {
      val testUserId = 555L
      follow(user"id:$testUserId")

      there was one(mockedTwitter4j).createFriendship(testUserId)
    }
  }

  "unfollow user string context method" should {
    "execute twitter4j.destroyFriendship with screen name" in {
      unfollow(user"$testUserName")

      there was one(mockedTwitter4j).destroyFriendship(testUserName)
    }

    "execute twitter4j.destroyFriendship with user id" in {
      val testUserId = 999L
      unfollow(user"id:$testUserId")

      there was one(mockedTwitter4j).destroyFriendship(testUserId)
    }
  }

  "block user string context method" should {
    "execute twitter4j.createBlock with screen name" in {
      block(user"$testUserName")

      there was one(mockedTwitter4j).createBlock(testUserName)
    }

    "execute twitter4j.createBlock with user id" in {
      val testUserId = 111L
      block(user"id:$testUserId")

      there was one(mockedTwitter4j).createBlock(testUserId)
    }
  }

  // list of DSL candidates
  // lookup(user"hoge", user"fuga") -> twitter.lookupFriends(list)
  // add(user"screen name1", user"screen name2") to list"list name" -> twitter.createUserListMembers
  // Status.favos
  // Status.retweet
  // searchQuery"query".save
}
