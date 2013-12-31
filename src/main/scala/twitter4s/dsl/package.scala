package twitter4s.dsl

import twitter4s.{User, Twitter}

/**
 * @author mao.instantlife at gmail.com
 */
package object dsl {
  implicit class TwitterDSLString(val sc: StringContext) extends AnyVal {
    def tweet(args: Any*) = TweetContext(sc.s(args: _*))

    def user(args: Any*) = UserContext(
      sc.s(args: _*) match {
        case str if str.toLowerCase.startsWith("id:") => User.isSpecifiedBy(str.drop("id:".length).toLong)
        case str => User.isSpecifiedBy(str)
      })

    def list(args: Any*) = ListContext(sc.s(args: _*))
  }

  case class TweetContext(tweet: String) {
    def update(implicit twitter: Twitter) = {
      twitter.twitter4jObj.updateStatus(tweet)
    }
  }

  case class UserContext(user: twitter4s.User.SpecificInfo)

  case class ListContext(name: String)

  def add(user: UserContext) = UserAdder(user)

  // context getter binding
  implicit val userContextGetter = UserContextGetter
  implicit val listContextGetter = ListContextGetter

  /**
   * Get resource with specified context string from Twitter API.
   *
   * @param self Twitter4S DSL context instance
   * @param twitter Twitter4S instance
   * @param getter ContextGetter instance
   * @tparam C Twitter4S DSL context type
   * @tparam R Return value type
   * @return Context resources from API
   */
  def get[C, R](self: C)(implicit twitter:Twitter, getter: ContextGetter[C, R]):R = getter.get(self)

  case class UserAdder(userContext: UserContext) {
    def to(listContext: ListContext)(implicit twitter: Twitter): twitter4s.UserList =
      twitter.twitter4jObj.createUserListMember(get(listContext).id, get(userContext).id)
  }
}
