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
object AccessToken {
  /**
   * factory method for twitter4j.auth.AccessToken class.
   * 
   * @param token
   * @param tokenSecret
   * @return twitter4j.auth.AccessToken instance
   * @since Twitter4S 1.0.0
   */
  def apply(token: String, tokenSecret: String, userId: java.lang.Long = null) = Option(userId) match {
    case None => new twitter4j.auth.AccessToken(token, tokenSecret)
    case Some(userId) => new twitter4j.auth.AccessToken(token, tokenSecret, userId)
  }
}