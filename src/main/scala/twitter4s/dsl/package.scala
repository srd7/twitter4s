package twitter4s

/**
 * @author mao.instantlife at gmail.com
 */
package object dsl {
  val idPrefix = "id:"

  implicit def twitter4SToTwitter4J(twitter: twitter4s.Twitter): twitter4j.Twitter = twitter.twitter4jObj

  implicit class TwitterDSLString(val sc: StringContext) extends AnyVal {
    def tweet(args: Any*) = TweetContext(sc.s(args: _*))

    def user(args: Any*) = UserContext(
      sc.s(args: _*) match {
        case str if str.toLowerCase.startsWith(idPrefix) => User.isSpecifiedBy(str.drop(idPrefix.length).toLong)
        case str => User.isSpecifiedBy(str)
      })

    def list(args: Any*) = ListContext(
      sc.s(args: _*) match {
        case str if str.toLowerCase.startsWith(idPrefix) => Right(str.drop(idPrefix.length).toInt)
        case str => Left(str)
      })

    def message(args: Any*) = MessageContext(sc.s(args: _*))
  }

  case class TweetContext(tweet: String) {
    def update(implicit twitter: Twitter) = {
      twitter.twitter4jObj.updateStatus(tweet)
    }
  }

  case class UserContext(user: User.SpecificInfo)

  case class ListContext(list: Either[String, Int])

  case class MessageContext(message: String)

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
      twitter.createUserListMember(get(listContext).id, get(userContext).id)
  }

  def send(messageContext: MessageContext) = MessageSender(messageContext)

  case class MessageSender(messageContext: MessageContext) {
    def to(userContext: UserContext)(implicit twitter: Twitter): twitter4s.DirectMessage = userContext.user match {
      case Right(userId) => twitter.sendDirectMessage(userId, messageContext.message)
      case Left(screenName) => twitter.sendDirectMessage(screenName, messageContext.message)
    }
  }
}
