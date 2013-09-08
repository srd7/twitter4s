package twitter4s.dsl

import twitter4s.auth.{AccessToken, ConsumerKey}

/**
 * Created with IntelliJ IDEA.
 * User: mao
 * Date: 13/09/08
 * Time: 18:47
 * To change this template use File | Settings | File Templates.
 */
trait Twitter4sDslTestBase {
  val testConsumerKey = ConsumerKey("consumerKey", "consumerSecret")
  val testAccessToken = AccessToken("accessToken", "tokenSecret")
}
