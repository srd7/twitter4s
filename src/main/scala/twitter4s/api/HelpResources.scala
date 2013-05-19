package twitter4s.api
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
import twitter4s.TwitterAPIConfiguration
import twitter4s.ResponseList
import twitter4j.api.HelpResources.Language

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait HelpResources {
  /**
   * Returns the string "ok" in the requested format with a 200 OK HTTP status code.
   * <br />This method calls twitter4j.Twitter.test.
   * <br />test calls https://api.twitter.com/1/help/test.json 
   * 
   * @return true if the API is working
   * @throws twitter4j.TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/help/test">GET help/test | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  // TODO 削除メソッド
//  def test: Boolean

  /**
   * Returns the current configuration used by Twitter including twitter.com slugs which are not usernames, maximum photo resolutons, and t.co URL lengths.<br />
   * It is recommended applications request this endpoint when they are loaded, but no more than once a day.
   * <br />This method calls twitter4j.Twitter.getAPIConfiguration
   * <br />getAPIConfiguration calls https://api.twitter.com/1/help/configuration.json
   * 
   * @return configuration
   * @throws twitter4j.TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/help/configuration">GET help/configuration | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getAPIConfiguration: TwitterAPIConfiguration

  /**
   * Returns the list of languages supported by Twitter along with their ISO 639-1 code. The ISO 639-1 code is the two letter value to use if you include lang with any of your requests.
   * <br />This method calls twitter4j.Twitter.getLanguages
   * <br />getLoanguages calls https://api.twitter.com/1/help/languages.json
   * 
   * @return list of languages supported by Twitter
   * @throws twitter4j.TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/help/languages">GET help/languages | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getLanguages: ResponseList[Language]
}