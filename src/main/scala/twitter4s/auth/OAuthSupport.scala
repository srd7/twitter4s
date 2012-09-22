package twitter4s.auth
/*
 * Copyright (C) 2012 Shinsuke Abe
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
/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait OAuthSupport {
  /**
   * sets the OAuth consumer key and consumer secret
   * <br/>This method call twitter4j.Twitter.setOAuthConsumer(consumerKey, consumerSecret)
   *
   * @param consumerKey OAuth consumer key
   * @param consumerSecret OAuth consumer secret
   * @throws IllegalStateException when OAuth consumer has already been set, or the instance is using basic authorization
   * @since Twitter4S 1.0.0
   */
  def setOAuthConsumer(consumerKey: String, consumerSecret: String): Unit
  
  /**
   * sets the OAuth consumer key and consumer secret
   * <br/>This method call twitter4j.Twitter.setOAuthConsumer(consumerKey, consumerSecret)
   *
   * @param consumerKey OAuth object has comsumer key and secret. twitter4s.auth.ConsumerKey.
   * @throws IllegalStateException when OAuth consumer has already been set, or the instance is using basic authorization
   * @since Twitter4S 1.0.0
   */
  def setOAuthConsumer(consumerKey: ConsumerKey): Unit
  
  def getOAuthRequestToken(callbackURL: String = null, xAuthAccessType: String = null): twitter4j.auth.RequestToken
  
  def getOAuthAccessToken(
      oauthVerifier: String = null,
      requestToken: twitter4j.auth.RequestToken = null,
      screenName: String = null,
      password: String = null): twitter4j.auth.AccessToken
  
  def setOAuthAccessToken(accessToken: twitter4j.auth.AccessToken)
}