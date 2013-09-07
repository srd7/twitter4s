package twitter4s.dsl

import twitter4s.Twitter
import twitter4s.api.impl.HelpResourcesImpl
import twitter4s.auth.ConsumerKey
import twitter4j.auth.AccessToken

/**
 * Created with IntelliJ IDEA.
 * User: mao
 * Date: 13/09/05
 * Time: 22:08
 * To change this template use File | Settings | File Templates.
 */
trait HelpResourcesDsl {

  private var twitter4jResources: twitter4j.Twitter = _

  lazy val resources = new Twitter(twitter4jResources) with HelpResourcesImpl

  def attach(consumerKey: ConsumerKey, accessToken: AccessToken) {
    twitter4jResources = Twitter.buildTwitter4jInstance(consumerKey, accessToken)
  }
}
