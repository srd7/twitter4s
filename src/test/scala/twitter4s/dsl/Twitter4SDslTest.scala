package twitter4s.dsl

/**
 * @author mao.instantlife at gmail.com
 */

import org.specs2.mutable._

class Twitter4SDslTest extends Specification {

  // tweet"hoge" update as user"fuga"
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
