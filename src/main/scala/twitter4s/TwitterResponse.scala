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
import twitter4j.TwitterException

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

abstract sealed class AccessLevel(val level: Int)

// TODO アクセスレベルが-1で返ってくる場合の対処(実際にあり得る)(HttpResponseがnullの時に発生する)
object AccessLevel {
  def apply(level: Int) = {
    if (level == TwitterResponse.NONE.level) TwitterResponse.NONE
    else if(level == TwitterResponse.READ.level) TwitterResponse.READ
    else if(level == TwitterResponse.READ_WRITE.level) TwitterResponse.READ_WRITE
    else if(level == TwitterResponse.READ_WRITE_DIRECTMESSAGES.level) TwitterResponse.READ_WRITE_DIRECTMESSAGES
    else throw new TwitterException("Access level that set response is abnormal pattern.")
  }
}

object TwitterResponse {
  case object NONE extends AccessLevel(0)
  case object READ extends AccessLevel(1)
  case object READ_WRITE extends AccessLevel(2)
  case object READ_WRITE_DIRECTMESSAGES extends AccessLevel(3)
}