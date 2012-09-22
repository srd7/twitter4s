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