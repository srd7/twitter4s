package twitter4s.dsl

/**
 * @author mao.instantlife at gmail.com
 */

import org.specs2.mutable._
import twitter4s.auth.{AccessToken, ConsumerKey}
import twitter4s.Twitter4sTestHelper._
import twitter4s.Twitter

class Twitter4sDSLWithTokenTest extends Specification {
  "withToken method" should {
    "Twitter instance context binding" in {
      implicit val consumerKey = ConsumerKey(prop.getProperty("oauth.consumerKey"), prop.getProperty("oauth.consumerSecret"))
      val accessToken = AccessToken(prop.getProperty("id2.oauth.accessToken"), prop.getProperty("id2.oauth.accessTokenSecret"))

      var actualTwitter: Twitter = null

      withToken(accessToken) { implicit twitter =>
        actualTwitter = twitter
      }

      actualTwitter must equalTo(Twitter(consumerKey, accessToken))
    }
  }
}
