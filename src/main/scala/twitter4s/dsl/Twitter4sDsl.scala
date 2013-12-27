package twitter4s.dsl

import twitter4s.Twitter

/**
 * @author mao.instantlife at gmail.com
 */
object Twitter4sDsl {
  implicit class TweetString(val sc: StringContext) extends AnyVal {
    def tweet(args: Any*) = TweetContext(sc.s(args: _*))
  }

  case class TweetContext(tweet: String) {
    def update(implicit twitter: Twitter) = {
      twitter.twitter4jObj.updateStatus(tweet)
    }
  }

  implicit class UserString(val sc: StringContext) extends AnyVal {
    def user(args: Any*) = UserContext(sc.s(args: _*))
  }

  case class UserContext(name: String)

  case class UserAdder(userContext: UserContext)

  def add(user: UserContext) = UserAdder(user)
}
