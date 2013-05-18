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
import scala.collection.JavaConverters.asScalaBufferConverter
import twitter4s.SimilarPlaces
import twitter4j.Place
import twitter4s.AccessLevel

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class SimilarPlacesImpl(tw4jSimilarPlaces: twitter4j.SimilarPlaces) extends SimilarPlaces {
  type Tw4jResponse = twitter4j.SimilarPlaces
  
  /**
   * scala converted list
   */
  def listAsScala = tw4jSimilarPlaces.asScala
  
  /**
   * rate limit status
   */
  def rateLimitStatus = RateLimitStatusImpl(tw4jSimilarPlaces.getRateLimitStatus())
  
  /**
   * feature specific rate limit status
   */
  // TODO 削除メソッド
//  def featureSpecificRateLimitStatus = RateLimitStatusImpl(tw4jSimilarPlaces.getFeatureSpecificRateLimitStatus())
  
  /**
   * access level
   */
  def accessLevel = AccessLevel(tw4jSimilarPlaces.getAccessLevel())
  
  /**
   * Get object in scala converted list method.
   */
  def apply(idx: Int):Place = listAsScala(idx)
  
  /**
   * Get Twitter4J Object
   */
  def tw4jObj = tw4jSimilarPlaces
  
  def token = tw4jSimilarPlaces.getToken()
}