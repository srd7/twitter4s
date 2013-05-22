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
//import twitter4j.Tweet
import twitter4s.QueryResult
import scala.collection.JavaConverters._

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class QueryResultImpl(tw4jQueryResult: twitter4j.QueryResult) extends QueryResult {
  def sinceId = tw4jQueryResult.getSinceId()
  
  def maxId = tw4jQueryResult.getMaxId()
  
  def refreshUrl = tw4jQueryResult.getRefreshUrl()
  
  def refreshURL = tw4jQueryResult.getRefreshURL()
  
  def count = tw4jQueryResult.getCount()
  
  def completedIn = tw4jQueryResult.getCompletedIn()
  
  def query = tw4jQueryResult.getQuery()
  
  def tweets = tw4jQueryResult.getTweets().asScala
  
  def nextQuery = tw4jQueryResult.nextQuery()
  
  def hasNext = tw4jQueryResult.hasNext()
}