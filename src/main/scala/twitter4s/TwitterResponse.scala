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

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait TwitterResponse {
  /**
   * Type annotation is twitter4j original response
   */
  type Tw4jResponse <: twitter4j.TwitterResponse

  /**
   * get rate limit status from twitter response.
   * 
   * @return rate limit status from twitter response.
   * @since Twitter4S 1.0.0
   */
  def rateLimitStatus:RateLimitStatus

  /**
   * get access level
   * 
   * @return access level from twitter response.
   * @since Twitter4S 1.0.0
   */
  def accessLevel: AccessLevel

  /**
   * get twitter4j original response object.
   * 
   * @return response object.
   * @since Twitter4S 1.0.0
   */
  def tw4jObj:Tw4jResponse
}

case class AccessLevel(level: Int)

object TwitterResponse {
  val NULL_HTTP_RESPONSE = AccessLevel(-1)
  val NONE = AccessLevel(0)
  val READ = AccessLevel(1)
  val READ_WRITE = AccessLevel(2)
  val READ_WRITE_DIRECTMESSAGES = AccessLevel(3)
}