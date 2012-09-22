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
/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
object Query {
  /**
   * factory method for twitter4j.Query class.
   * 
   * @param query(optional) is string.
   * @return twitter4j.Query instance
   * @since Twitter4S 1.0.0
   */
  def apply(query: String = null) = {
    Option(query) match {
      case Some(query) => new twitter4j.Query(query)
      case None => new twitter4j.Query()
    }
  }
  
  val MILES = twitter4j.Query.MILES
  val KILOMETERS = twitter4j.Query.KILOMETERS
  
  val MIXED = twitter4j.Query.MIXED
  val POPULAR = twitter4j.Query.POPULAR
  val RECENT = twitter4j.Query.RECENT
}