package twitter4s.api

import twitter4j.TwitterAPIConfiguration
import twitter4s.ResponseList
import twitter4j.api.HelpMethods.Language

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait HelpMethods {
  /**
   * Returns the string "ok" in the requested format with a 200 OK HTTP status code.
   * <br/>This method calls twitter4j.Twitter.test
   * 
   * @return true if the API is working
   * @throws twitter4j.TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/help/test">GET help/test | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def test: Boolean

  /**
   * Returns the current configuration used by Twitter including twitter.com slugs which are not usernames, maximum photo resolutons, and t.co URL lengths.</br>
   * It is recommended applications request this endpoint when they are loaded, but no more than once a day.
   * <br/>This method call twitter4j.Twitter.getAPIConfiguration
   * 
   * @return configuration
   * @throws twitter4j.TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/help/configuration">GET help/configuration | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getAPIConfiguration: TwitterAPIConfiguration

  /**
   * Returns the list of languages supported by Twitter along with their ISO 639-1 code. The ISO 639-1 code is the two letter value to use if you include lang with any of your requests.
   * <br/>This method calls twitter4j.Twitter.getLanguages
   * 
   * @return list of languages supported by Twitter
   * @throws twitter4j.TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/help/languages">GET help/languages | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getLanguages: ResponseList[Language]
}