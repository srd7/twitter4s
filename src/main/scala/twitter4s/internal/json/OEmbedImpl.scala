package twitter4s.internal.json

import twitter4s.{RateLimitStatus, AccessLevel, OEmbed}

/**
 * @author mao.instantlife at gmail.com
 */
case class OEmbedImpl(twt4jOEmbed: twitter4j.OEmbed) extends OEmbed {
  /**
   * The HTML required to display the resource. The HTML should have no padding or margins. Consumers may wish to load the HTML in an off-domain iframe to avoid XSS vulnerabilities. The markup should be valid XHTML 1.0 Basic.
   * @return The HTML required to display the resource.
   */
  def html: String = twt4jOEmbed.getHtml

  /**
   * The name of the author/owner of the resource.
   * @return The name of the author/owner of the resource.
   */
  def authorName: String = twt4jOEmbed.getAuthorName

  /**
   * The url of the resource provider.<br>
   * The source URL of the image. Consumers should be able to insert this URL into an &lt;img&gt; element. Only HTTP and HTTPS URLs are valid.
   * @return The url of the resource provider.
   */
  def url: String = twt4jOEmbed.getURL

  /**
   * The oEmbed version number.
   * @return The oEmbed version number.
   */
  def version: String = twt4jOEmbed.getVersion

  /**
   * The suggested cache lifetime for this resource, in seconds. Consumers may choose to use this value or not.
   * @return The suggested cache lifetime for this resource, in seconds. Consumers may choose to use this value or not.
   */
  def cacheAge: Long = twt4jOEmbed.getCacheAge

  /**
   * A URL for the author/owner of the resource.
   * @return A URL for the author/owner of the resource.
   */
  def authorURL: String = twt4jOEmbed.getAuthorURL

  /**
   * The width in pixels of the image specified in the url parameter.
   * @return The width in pixels of the image specified in the url parameter.
   */
  def width: Int = twt4jOEmbed.getWidth

  type Tw4jResponse = twitter4j.OEmbed

  /**
   * get rate limit status from twitter response.
   *
   * @return rate limit status from twitter response.
   * @since Twitter4S 1.0.0
   */
  def rateLimitStatus: RateLimitStatus = RateLimitStatusImpl(twt4jOEmbed.getRateLimitStatus)

  /**
   * get access level
   *
   * @return access level from twitter response.
   * @since Twitter4S 1.0.0
   */
  def accessLevel: AccessLevel = AccessLevel(twt4jOEmbed.getAccessLevel)

  /**
   * get twitter4j original response object.
   *
   * @return response object.
   * @since Twitter4S 1.0.0
   */
  def tw4jObj: OEmbedImpl#Tw4jResponse = twt4jOEmbed
}
