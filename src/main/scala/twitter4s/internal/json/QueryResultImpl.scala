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
  
  // TODO 削除メソッド
//  def resultsPerPage = tw4jQueryResult.getResultsPerPage()
//  
  // TODO 削除メソッド
//  def warning = tw4jQueryResult.getWarning()
  
  def completedIn = tw4jQueryResult.getCompletedIn()
  
  // TODO 削除メソッド
//  def page = tw4jQueryResult.getPage()
  
  def query = tw4jQueryResult.getQuery()
  
  // TODO 戻り値がStatusに変更
//  def tweets = tw4jQueryResult.getTweets().asScala
}