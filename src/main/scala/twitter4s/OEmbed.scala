package twitter4s
/*
 * Copyright (C) 2014 Shinsuke Abe
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
 * @author mao.instantlife at gmail.com
 * @since Twitter4S 2.1.0
 */
trait OEmbed extends TwitterResponse {
  /**
   * The HTML required to display the resource. The HTML should have no padding or margins. Consumers may wish to load the HTML in an off-domain iframe to avoid XSS vulnerabilities. The markup should be valid XHTML 1.0 Basic.
   * @return The HTML required to display the resource.
   */
  def html: String

  /**
   * The name of the author/owner of the resource.
   * @return The name of the author/owner of the resource.
   */
  def authorName: String

  /**
   * The url of the resource provider.<br>
   * The source URL of the image. Consumers should be able to insert this URL into an &lt;img&gt; element. Only HTTP and HTTPS URLs are valid.
   * @return The url of the resource provider.
   */
  def url: String

  /**
   * The oEmbed version number.
   * @return The oEmbed version number.
   */
  def version: String

  /**
   * The suggested cache lifetime for this resource, in seconds. Consumers may choose to use this value or not.
   * @return The suggested cache lifetime for this resource, in seconds. Consumers may choose to use this value or not.
   */
  def cacheAge: Long

  /**
   * A URL for the author/owner of the resource.
   * @return A URL for the author/owner of the resource.
   */
  def authorURL: String

  /**
   * The width in pixels of the image specified in the url parameter.
   * @return The width in pixels of the image specified in the url parameter.
   */
  def width: Int
}
