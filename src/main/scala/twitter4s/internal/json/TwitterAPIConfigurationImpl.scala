package twitter4s.internal.json
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
import twitter4s.TwitterAPIConfiguration
import scala.collection.JavaConverters._
import twitter4s.AccessLevel

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class TwitterAPIConfigurationImpl(tw4jApiConfiguration: twitter4j.TwitterAPIConfiguration) extends TwitterAPIConfiguration {
  type Tw4jResponse = twitter4j.TwitterAPIConfiguration

  def photoSizeLimit = tw4jApiConfiguration.getPhotoSizeLimit()

  def shortURLLength = tw4jApiConfiguration.getShortURLLength()

  def shortURLLengthHttps = tw4jApiConfiguration.getShortURLLengthHttps()

  def charactersReservedPerMedia = tw4jApiConfiguration.getCharactersReservedPerMedia()

  def photoSizes = tw4jApiConfiguration.getPhotoSizes().asScala

  def nonUsernamePaths = tw4jApiConfiguration.getNonUsernamePaths()

  def maxMediaPerUpload = tw4jApiConfiguration.getMaxMediaPerUpload()
  
  def rateLimitStatus = RateLimitStatusImpl(tw4jApiConfiguration.getRateLimitStatus())
  
  def accessLevel = AccessLevel(tw4jApiConfiguration.getAccessLevel())
  
  def tw4jObj = tw4jApiConfiguration

}