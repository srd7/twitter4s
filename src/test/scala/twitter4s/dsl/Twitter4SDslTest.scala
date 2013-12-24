package twitter4s.dsl

/**
 * @author mao.instantlife at gmail.com
 */

import org.specs2.mutable._
import twitter4s.auth.{AccessToken, ConsumerKey}
import twitter4s.Twitter
import org.specs2.mock.Mockito
import twitter4s.mocked.FakeValuesUsedByMock

class Twitter4SDslTest extends Specification with Mockito {

  // tweet"hoge" update
  // tweet"hoge".update(implicit twitter: => Twitter)
  // with(AccessToken)(implicit twitter: => Twitter)
  // with(ConsumerKey, AccessToken)(implicit twitter: => Twitter)

  "TweetStringContext.update" should {
    "call twitter updateStatus method" in {
      // oauth.kei
      val consumerKey = ConsumerKey("6xw9EYTQlQ9fD3BWhZNA", "enP6NYIgLzuYJyOcTZTCrXheatYT08xr5ZyoolXS0Ø")
      // id1
      val accessToken = AccessToken("101515535-lVwBwVaxtg7QmRyw60HXnSfvmTYT4tIN7DTcVuUv", "0ualIiBHewgDoQtVbtCCPpGhjTXqjwllZaQM0gEVrc8")

      val mockedTwitter4j = mock[twitter4j.Twitter]

      mockedTwitter4j.updateStatus(anyString) returns(FakeValuesUsedByMock.status)

      implicit val targetTwitter = Twitter(consumerKey, accessToken)


      tweet"hoge" update

      there was one(mockedTwitter4j).updateStatus("hoge")
    }
  }

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
  // user"hoge" follows user"fuga" -> twitter.createFriendship(user"fuga", false)
  // user"hoge" follows user"fuga" with NoticeOnFollow -> twitter.createFriendship(user"fuga", true)
  // relation between user"hoge" and user"fuga" -> twitter.showFriendship -> to wrap
  // relation isFriends?
  // relation isFollowers?
  // relation areFollowEachOther?
  // relation isBlocked?
  // user"hoge" lookup (friend"fuga") -> twitter.lookupFriends(list)
  // TimelinesResourcesのメソッドやSearchResourcesのメソッドはそのまま使えれば良い -> implicitで渡す
  // send Message"hoge" to user"fuga" from user"foo"
  // add (users) to list""
  // with(user"")でコンテキストバインディングしたい
}
