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
import scala.collection.mutable.Buffer
import twitter4s.ResponseList
import twitter4s.AccessLevel

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class ResponseListImpl[T](twt4jResponseList: twitter4j.ResponseList[T]) extends ResponseList[T] {
  
  type Tw4jResponse = twitter4j.ResponseList[T]
  
  /**
   * scala converted list
   */
  def listAsScala = twt4jResponseList.asScala
  
  /**
   * rate limit status
   */
  def rateLimitStatus = RateLimitStatusImpl(twt4jResponseList.getRateLimitStatus())
  
  /**
   * access level
   */
  def accessLevel = AccessLevel(twt4jResponseList.getAccessLevel())
  
  /**
   * Get object in scala converted list method.
   */
  def apply(idx: Int):T = listAsScala(idx)
  
  /**
   * Get Twitter4J Object
   */
  def tw4jObj = twt4jResponseList
}