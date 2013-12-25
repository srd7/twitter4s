package twitter4s.dsl

import twitter4s.Twitter

/**
 * @author mao.instantlife at gmail.com
 */
object Twitter4sDsl {
  implicit class TweetString(val sc: StringContext) extends AnyVal {
    def tweet(args: Any*) = {
      sc.checkLengths(args)

      println((sc.parts.length != args.length + 1))

      val string = sc.s(args: _*)
      TweetContext(string)
    }
  }

  case class TweetContext(tweet: String) {
    def update(implicit twitter: Twitter) = {
      twitter.twitter4jObj.updateStatus(tweet)
    }
  }
}
