package twitter4s.api

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait LegalResources {
  /**
   * Returns Twitter's' Terms of Service.
   * <br />This method calls twitter4j.Twitter.getTermsOfService.
   * <br />getTermsOfService calls http://api.twitter.com/1/legal/tos.json
   *
   * @return Terms of Service
   * @throws twitter4j.TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/legal/tos">GET legal/tos | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getTermsOfService: String

  /**
   * Returns Twitter's Privacy Policy.
   * <br />This method calls twitter4j.Twitter.getPrivacyPolicy.
   * <br />getPrivacyPolicy calls http://api.twitter.com/1/legal/privacy.json
   *
   * @return privacy policy
   * @throws twitter4j.TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/legal/privacy">GET legal/privacy | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getPrivacyPolicy: String
}