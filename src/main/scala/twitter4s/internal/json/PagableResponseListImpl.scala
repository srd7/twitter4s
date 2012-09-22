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
import twitter4j.PagableResponseList
import twitter4s.PagableResponseList
import scala.collection.JavaConverters._
import twitter4s.AccessLevel

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class PagableResponseListImpl[T <: twitter4j.TwitterResponse](twt4jPagableResponseList: twitter4j.PagableResponseList[T])
extends twitter4s.PagableResponseList[T] {
  type Tw4jResponse = twitter4j.PagableResponseList[T]
  
  def hasPrevious = twt4jPagableResponseList.hasPrevious()

  def previousCursor = twt4jPagableResponseList.getPreviousCursor()

  def hasNext = twt4jPagableResponseList.hasNext()

  def nextCursor = twt4jPagableResponseList.getNextCursor()

  def listAsScala = twt4jPagableResponseList asScala

  def rateLimitStatus = RateLimitStatusImpl(twt4jPagableResponseList.getRateLimitStatus())

  def featureSpecificRateLimitStatus = RateLimitStatusImpl(twt4jPagableResponseList.getFeatureSpecificRateLimitStatus())

  def accessLevel = AccessLevel(twt4jPagableResponseList.getAccessLevel())
  
  def apply(idx: Int) = listAsScala(idx)
  
  def tw4jObj = twt4jPagableResponseList

}