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
import twitter4j.auth.{RequestToken, AccessToken}
import twitter4s.Twitter
import twitter4j.TwitterFactory
import twitter4s.api.impl.UsersResourcesImpl

/**
 * @author mao.instantlife at gmail.com
 */
// TODO 不要になったら消す
trait Twitter4sDslBase {
  type ResourcesType

  protected var twitter4jResources: twitter4j.Twitter = new TwitterFactory().getInstance()

  protected val twitter4sResources: Twitter with ResourcesType

  // if your application is needed another storer,
  // create storer object mixined AccessTokenStorer trait
  // and override this storeExecutor.
//  protected val storeExecutor: AccessTokenStoreExecutor = DefaultAccessTokenStoreExecutor

  def attach(accessToken: AccessToken)(implicit consumerKey: ConsumerKey) {
    resources.setOAuthConsumer(consumerKey.consumerKey, consumerKey.consumerSecret)
    resources.setOAuthAccessToken(accessToken)
  }

  def resources = twitter4sResources
//
//  // call Twitter API DSL methods
//  def get(context: ResourceContext) = new ResourceContextBuilder(context.getDefaultParameters, context.getFunc)
//
//  implicit def doContextBuilderExecute[P, R](contextBuilder: ResourceContextBuilder[P, R]) = contextBuilder.execute


  // authorization DSL methods
  def authorizationURL(callbackUrl: String = null)(implicit consumerKey: ConsumerKey): (String, RequestToken) = {
    val requestToken = Option(callbackUrl) match {
      case Some(callbackUrl) => twitterForAuth.getOAuthRequestToken(callbackURL = callbackUrl)
      case None => twitterForAuth.getOAuthRequestToken()
    }

    (requestToken.getAuthorizationURL, requestToken)
  }

  def userAccessToken(requestToken: RequestToken, oauthVerifier: String)(implicit consumerKey: ConsumerKey):(Long, AccessToken) = {
    val twitter = twitterForAuth
    val accessToken = twitter.getOAuthAccessToken(oauthVerifier, requestToken)

    (twitter.verifyCredentials.id, accessToken)
  }

  protected def twitterForAuth(implicit consumerKey: ConsumerKey) = {
    // if consumerKey has be set, twitter4j instance cannot consumerKey
    // create new instance by call
    val twitter = new Twitter(new TwitterFactory().getInstance()) with UsersResourcesImpl
    twitter.setOAuthConsumer(consumerKey)

    twitter
  }

//  implicit class RequestTokenStoreExecutor(userTokenArgs: Pair[Long, AccessToken]) {
//    val tokenStoreExecutor = storeExecutor
//    def andThenStore = {
//      tokenStoreExecutor.store(userTokenArgs)
//    }
//  }
}
