package twitter4s.dsl

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

import twitter4s.{ResponseList, TwitterAPIConfiguration, Twitter}
import twitter4s.api.impl.HelpResourcesImpl
import twitter4s.api.HelpResources
import twitter4j.RateLimitStatus
import twitter4j.api.HelpResources.Language
import scala.collection.mutable

/**
 * @author mao.instantlife at gmail.com
 */
trait HelpResourcesDsl extends Twitter4sDslBase with HelpResources {
  type ResourcesType = HelpResourcesImpl
  override lazy val twitter4sResources = new Twitter(twitter4jResources) with ResourcesType

  object APIConfiguration extends ResourceContext {
    type GetParameterType = ParameterNothing.type
    type GetReturnType = TwitterAPIConfiguration

    def getDefaultParameters = ParameterNothing

    val getFunc: (GetParameterType) => GetReturnType = (param) => resources.getAPIConfiguration
  }

  object Languages extends ResourceContext {
    type GetParameterType = ParameterNothing.type
    type GetReturnType = ResponseList[Language]

    def getDefaultParameters = ParameterNothing

    val getFunc: (GetParameterType)=> GetReturnType = (param) => resources.getLanguages
  }

  object TermsOfService extends ResourceContext {
    type GetParameterType = ParameterNothing.type
    type GetReturnType = String

    def getDefaultParameters = ParameterNothing

    val getFunc: (GetParameterType)=> GetReturnType = (param) => resources.getTermsOfService
  }

  // bellow methods implements are a cliche.
  def getAPIConfiguration: TwitterAPIConfiguration = resources.getAPIConfiguration
  def getLanguages: ResponseList[Language] = resources.getLanguages
  def getTermsOfService: String = resources.getTermsOfService
  def getPrivacyPolicy: String = resources.getPrivacyPolicy
  def getRateLimitStatus(res: String*): mutable.Map[String, RateLimitStatus] = resources.getRateLimitStatus(res: _*)

}
