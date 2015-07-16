package twitter4s.internal.json
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
import twitter4s.RateLimitStatus

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class RateLimitStatusImpl(twt4jRateLimitStatus: twitter4j.RateLimitStatus) extends RateLimitStatus {
  def remaining = twt4jRateLimitStatus.getRemaining()

  def limit = twt4jRateLimitStatus.getLimit()

  def resetTimeInSeconds = twt4jRateLimitStatus.getResetTimeInSeconds()

  def secondsUntilReset = twt4jRateLimitStatus.getSecondsUntilReset()

  def tw4jObj = twt4jRateLimitStatus
}
