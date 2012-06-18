package twitter4s.api

import twitter4j.User

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait SpamReportingMethods {
  /**
   * The user specified in the id is blocked by the authenticated user and reported as a spammer.
   * <br/>This method calls twitter4j.Twitter.reportSpam(userId) or reportSpam(screenName)
   * <br/>This method calls http://api.twitter.com/1/report_spam.json
   *
   * @param userId (optional) The ID of the user you want to report as a spammer.
   * @param screenName (optional) The screen name of the user you want to report as a spammer.
   * @return The User reported as a spammer.
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/report_spam">POST report_spam | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def reportSpam(userId: Option[Long] = None, screenName: Option[String] = None): User
}