package twitter4s.dsl

/**
 * @author mao.instantlife at gmail.com
 */

import org.specs2.mutable._
import twitter4s.Twitter
import org.specs2.mock.Mockito
import twitter4s.mocked.FakeValuesUsedByMock

class Twitter4SDslTest extends Specification with Mockito {
  import dsl._

  val mockedTwitter4j = mock[twitter4j.Twitter]
  mockedTwitter4j.updateStatus(anyString) returns(FakeValuesUsedByMock.status)
  mockedTwitter4j.createUserListMember(anyInt, anyLong) returns(FakeValuesUsedByMock.userList)
  mockedTwitter4j.showUser(anyString) returns(FakeValuesUsedByMock.user)
  mockedTwitter4j.showUserList(anyLong, anyString) returns(FakeValuesUsedByMock.userList)
  mockedTwitter4j.getId returns(1L)
  mockedTwitter4j.createUserListMember(anyInt, anyLong) returns(FakeValuesUsedByMock.userList)

  val testUserName = "test_user_name"
  implicit val twitter = new Twitter(mockedTwitter4j)

  "TwitterStringContext" should {
    "update method execute Twitter4j.updateStatus" in {
      val tweetString = "test tweet with implicit twitter class"

      tweet"$tweetString with $testUserName" update

      there was one(mockedTwitter4j).updateStatus(s"$tweetString with $testUserName")
    }
  }

  "UserContext" should {
    "get user with user context" in {
      get(user"${testUserName}")

      there was one(mockedTwitter4j).showUser(testUserName)
    }
  }

  "ListContext" should {
    "get list with list context" in {
      val testListName = "test_user_list_name"

      get(list"${testListName}")

      there was one(mockedTwitter4j).getId
      there was one(mockedTwitter4j).showUserList(1L, testListName)
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
        FakeValuesUsedByMock.userList.getId, FakeValuesUsedByMock.user.getId)
    }
  }

  // user"screen name" to list"list name" -> twitter.createUserListMember(listid, userid)
  // users"screen name1, screen name2" to list"list name" -> twitter.createUserListMembers
  // send Message"hoge" to user"fuga" -> fromは自分なのでいらない
  // user"hoge" follows user"fuga" -> twitter.createFriendship(user"fuga", false)
  // user"hoge" follows user"fuga" with NoticeOnFollow -> twitter.createFriendship(user"fuga", true)

//  "withToken method" should {
//    "bind token and create twitter4s instance" in {
//      // oauth.kei
//      val consumerKey = ConsumerKey("6xw9EYTQlQ9fD3BWhZNA", "enP6NYIgLzuYJyOcTZTCrXheatYT08xr5ZyoolXS0Ø")
//      // id1
//      val accessToken = AccessToken("101515535-lVwBwVaxtg7QmRyw60HXnSfvmTYT4tIN7DTcVuUv", "0ualIiBHewgDoQtVbtCCPpGhjTXqjwllZaQM0gEVrc8")
//
//      var actualTwitter: Twitter = null
//
//      withToken(consumerKey, accessToken) { twitter =>
//        actualTwitter = twitter
//      }
//
//      actualTwitter must equalTo(Twitter(consumerKey, accessToken))
//    }
//  }

  // removeTweet(no) -> ラップするかどうか
  // retweet(no) -> ラップするかどうか
  // relation between user"hoge" and user"fuga" -> twitter.showFriendship -> to wrap
  // relation isFriends?
  // relation isFollowers?
  // relation areFollowEachOther?
  // relation isBlocked?
  // user"hoge" lookup (friend"fuga") -> twitter.lookupFriends(list)
  // TimelinesResourcesのメソッドやSearchResourcesのメソッドはそのまま使えれば良い -> implicitで渡す
  // with(user"")でコンテキストバインディングしたい
  // with(AccessToken)(implicit twitter: => Twitter)
  // with(ConsumerKey, AccessToken)(implicit twitter: => Twitter)
}
