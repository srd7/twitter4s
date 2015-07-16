package twitter4s
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
import twitter4j.RateLimitStatusListener
import auth.OAuthSupport
import twitter4j.auth.AccessToken
import twitter4j.auth.RequestToken

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait TwitterBase extends AnyRef with OAuthSupport {
  /**
   * Get screenName from twitter object.
   * <br/>This method calls twitter4j.Twitter.getScreenName.
   *
   * @return screen name
   * @since Twitter4S 1.0.0
   */
  def screenName: String

  /**
   * Get user id from twitter object.
   * <br/>This method calls twitter4j.getId.
   *
   * @return user id
   * @since Twitter4S 1.0.0
   */
  def id: Long

  /**
   * Add rate limit status listener to twitter object.
   * <br/>This method calls twitter4j.addRateLimitStatusListener(listener)
   *
   * @param listener (required) twitter4j.RateLimitStatusListener object for add.
   * @since Twitter4S 1.0.0
   */
  def addRateLimitStatusListener(listener: RateLimitStatusListener): Unit

  /**
   * Get authorization object from twitter object.
   * <br/>This method calls twitter4j.getAuthorization.
   *
   * @return authorization object
   * @since Twitter4S 1.0.0
   */
  def authorization: twitter4j.auth.Authorization

  /**
   * Get configuration object from twitter object.
   * <br/>This method calls twitter4j.getConfiguration.
   *
   * @return configuration object
   * @since Twitter4S 1.0.0
   */
  def configuration: twitter4j.conf.Configuration

}
