package twitter4s.dsl

/**
 * @author mao.instantlife at gmail.com
 */

import org.specs2.mutable._
import twitter4s.Twitter
import org.specs2.mock.Mockito
import twitter4s.mocked.FakeValuesUsedByMock

class Twitter4SDslTest extends Specification with Mockito {
  import Twitter4sDsl._

  val mockedTwitter4j = mock[twitter4j.Twitter]
  mockedTwitter4j.updateStatus(anyString) returns(FakeValuesUsedByMock.status)
  mockedTwitter4j.createUserListMember(anyInt, anyLong) returns(FakeValuesUsedByMock.userList)
  mockedTwitter4j.showUser(anyString) returns(FakeValuesUsedByMock.user)

  "TwitterStringContext" should {
    "make tweet context" in {
      val username = "my name"
      tweet"hello ${username}".tweet must equalTo("hello my name")
    }

    "update method execute Twitter4j.updateStatus" in {
      implicit val twitter = new Twitter(mockedTwitter4j)

      tweet"hello world implicit" update

      there was one(mockedTwitter4j).updateStatus("hello world implicit")
    }
  }

  "UserContext" should {
    "make user context" in {
      val testUserName = "user_name1"
      user"$testUserName".name must equalTo(testUserName)
    }

    "get user with user context" in {
      implicit val twitter = new Twitter(mockedTwitter4j)

      val testUser = "showTestUser"
      get(user"${testUser}")

      there was one(mockedTwitter4j).showUser(testUser)
    }
  }

  "add user context string method" should {
    "returns Tuple UserAdder" in {
      (add(user"with_adder_user")).userContext must equalTo(user"with_adder_user")
    }
  }
//
//  "UserAdder" should {
//    "to(ListContext) method executes twitter4j.createUserListMember" in {
//
//    }
//  }

  // add (users) to list"" -> add いらないかも
  // user"screen name" to list"list name" -> twitter.createUserListMember(listid, userid)
  // users"screen name1, screen name2" to list"list name" -> twitter.createUserListMembers
  // user"" -> twitter.showMember
  // list"" -> twitter.showUserList
  // send Message"hoge" to user"fuga" from user"foo"
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
