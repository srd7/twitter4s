package twitter4s
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
import twitter4j.Status // TODO 不要？
import scala.collection.mutable.Buffer

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait QueryResult {
  def sinceId: Long
  
  def maxId: Long
  
  def refreshUrl: String
  
  // TODO 削除メソッド
//  def resultsPerPage: Int
  
  // TODO 削除メソッド
//  def warning: String
  
  def completedIn: Double
  
  // TODO 削除メソッド
//  def page: Int
  
  def query: String
  
  // TODO Statusに変更になった
//  def tweets: Buffer[Status]
  
  /* TODO Twitter4j 3.0.0対応
  def nextQuery: twitter4j.Query
  
  def hasNext: Boolean
  */
}