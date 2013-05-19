package twitter4s.api.impl
/*
 * Copyright (C) 2013 Shinsuke Abe
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
import twitter4s._
import api.HelpResources
import twitter4j.api.HelpResources.Language
import api.impl._

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait HelpResourcesImpl extends HelpResources {
  self: Twitter => 
  
  /**
   * {@inheritDoc}
   */
  def getAPIConfiguration: TwitterAPIConfiguration = {
    twitter4jObj.getAPIConfiguration()
  }
  
  /**
   * {@inheritDoc}
   */
  def getLanguages: ResponseList[Language] = {
    twitter4jObj.getLanguages()
  }
  
  /**
   * {@inheritDoc}
   */
  def getTermsOfService: String = {
    twitter4jObj.getTermsOfService()
  }

  /**
   * {@inheritDoc}
   */
  def getPrivacyPolicy: String = {
    twitter4jObj.getPrivacyPolicy()
  }
}