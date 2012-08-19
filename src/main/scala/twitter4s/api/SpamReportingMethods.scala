package twitter4s.api

import twitter4s.User

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait SpamReportingMethods {
  /**
   * The user specified in the id is blocked by the authenticated user and reported as a spammer.
   * <br />This method calls twitter4j.Twitter.reportSpam.
   * <br />reportSpam calls http://api.twitter.com/1/report_spam.json
   * <br />Note1: You must set userId or screenName at least.
   * <br />Note2: Parameter userId is taken priority over screenName.
   *
   * @param specificUser (requried) The user specific Information(screen name or ID) you want to report as a spammer.
   * @return The User reported as a spammer.
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when specificUser is set null.
   * @see <a href="https://dev.twitter.com/docs/api/1/post/report_spam">POST report_spam | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def reportSpam(specificUser: User.SpecificInfo): User
}