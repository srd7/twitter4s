package twitter4s.dsl

import twitter4s.Twitter

/**
 * @author mao.instantlife at gmail.com
 */
object Twitter4sDsl {
  implicit class TwitterDSLString(val sc: StringContext) extends AnyVal {
    def tweet(args: Any*) = TweetContext(sc.s(args: _*))

    def user(args: Any*) = UserContext(sc.s(args: _*))

    def list(args: Any*) = ListContext(sc.s(args: _*))
  }

  case class TweetContext(tweet: String) {
    def update(implicit twitter: Twitter) = {
      twitter.twitter4jObj.updateStatus(tweet)
    }
  }

  case class UserContext(name: String)

  case class ListContext(name: String)

  case class UserAdder(userContext: UserContext)

  def add(user: UserContext) = UserAdder(user)

  def get(user: UserContext)(implicit twitter:Twitter): twitter4s.User = twitter.twitter4jObj.showUser(user.name)
}
