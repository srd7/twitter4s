package twitter4s
import java.util.Date
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
trait RateLimitStatus {

  /**
   * @since Twitter4S 2.0.0
   */
  def remaining: Int

  /**
   * @since Twitter4S 2.0.0
   */
  def limit: Int

  def resetTimeInSeconds: Int

  def secondsUntilReset: Int

  def tw4jObj: twitter4j.RateLimitStatus
}
