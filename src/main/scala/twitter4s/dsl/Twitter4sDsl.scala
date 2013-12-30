package twitter4s.dsl

import twitter4s.{UserList, Twitter}

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

  trait ContextGetter[ContextType, ReturnType] {
    def get(self: ContextType)(implicit twitter:Twitter): ReturnType
  }

  implicit val userContextGetter = new ContextGetter[UserContext, twitter4s.User] {
    def get(self: UserContext)(implicit twitter:Twitter) = twitter.twitter4jObj.showUser(self.name)
  }

  implicit val listContextGetter = new ContextGetter[ListContext, twitter4s.UserList] {
    def get(self: ListContext)(implicit twitter: Twitter) = twitter.twitter4jObj.showUserList(twitter.id, self.name)
  }

  def get[C, R](self: C)(implicit twitter:Twitter, getter: ContextGetter[C, R]):R = getter.get(self)
}
