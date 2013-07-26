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
import twitter4s.User
import twitter4s.api.SpamReportingResources
import scala.util.Either
import twitter4s.Twitter

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 * @since Twitter4S 2.0.0
 */
trait SpamReportingResourcesImpl extends SpamReportingResources {
  self: Twitter =>
  
  /* SpamReportingMethods */
  /**
   * {@inheritDoc}
   */
  def reportSpam(specificUser: User.SpecificInfo): User = {
    require(specificUser != null)
    
    specificUser match {
      case Right(userId) => twitter4jObj.reportSpam(userId)
      case Left(screenName) => twitter4jObj.reportSpam(screenName)
    } 
  }
}