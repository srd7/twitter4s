package twitter4s.dsl

/*
 * Copyright (C) 2013 Shinsuke Abe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import twitter4s.auth.ConsumerKey
import twitter4j.auth.AccessToken
import twitter4s.Twitter
import twitter4j.TwitterFactory

/**
 * @author mao.instantlife at gmail.com
 */
trait Twitter4sDslBase {
  type ResourcesType

  protected var twitter4jResources: twitter4j.Twitter = _

  protected val twitter4sResources: Twitter with ResourcesType

  def attach(consumerKey: ConsumerKey, accessToken: AccessToken) {
    twitter4jResources = Twitter.buildTwitter4jInstance(consumerKey, accessToken)
  }

  def resources = twitter4sResources

  def authorizationURL(consumerKey: ConsumerKey, callbackUrl: String = null) = {
    val twitterInstance = new Twitter(new TwitterFactory().getInstance())

    twitterInstance.setOAuthConsumer(consumerKey)
    val requestToken = Option(callbackUrl) match {
      case Some(callbackUrl) => twitterInstance.getOAuthRequestToken(callbackURL = callbackUrl)
      case None => twitterInstance.getOAuthRequestToken()
    }

    requestToken.getAuthorizationURL
  }
}
