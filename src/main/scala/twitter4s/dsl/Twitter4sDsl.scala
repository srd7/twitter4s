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

  implicit class ListNameString(val sc: StringContext) extends AnyVal {
    def list(args: Any*) = ListContext(sc.s(args: _*))
  }

  case class ListContext(name: String)

  case class UserAdder(userContext: UserContext)

  def add(user: UserContext) = UserAdder(user)

  def get(user: UserContext)(implicit twitter:Twitter): twitter4s.User = twitter.twitter4jObj.showUser(user.name)
}
