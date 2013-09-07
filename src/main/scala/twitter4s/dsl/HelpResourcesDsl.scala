package twitter4s.dsl

import twitter4s.{ResponseList, TwitterAPIConfiguration, Twitter}
import twitter4s.api.impl.HelpResourcesImpl
import twitter4s.auth.ConsumerKey
import twitter4j.auth.AccessToken
import twitter4s.api.HelpResources
import twitter4j.{RateLimitStatus, TwitterException}
import twitter4j.api.HelpResources.Language
import scala.collection.mutable

/**
 * Created with IntelliJ IDEA.
 * User: mao
 * Date: 13/09/05
 * Time: 22:08
 * To change this template use File | Settings | File Templates.
 */
trait HelpResourcesDsl extends HelpResources {

  private var twitter4jResources: twitter4j.Twitter = _

  lazy val resources = new Twitter(twitter4jResources) with HelpResourcesImpl

  def attach(consumerKey: ConsumerKey, accessToken: AccessToken) {
    twitter4jResources = Twitter.buildTwitter4jInstance(consumerKey, accessToken)
  }

  // bellow methods implements are a cliche.
  def getAPIConfiguration: TwitterAPIConfiguration = resources.getAPIConfiguration
  def getLanguages: ResponseList[Language] = resources.getLanguages
  def getTermsOfService: String = resources.getTermsOfService
  def getPrivacyPolicy: String = resources.getPrivacyPolicy
  def getRateLimitStatus(res: String*): mutable.Map[String, RateLimitStatus] = resources.getRateLimitStatus(res: _*)

}
