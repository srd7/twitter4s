package twitter4s.api
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
import twitter4j.Query
import twitter4s.QueryResult

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait SearchResources {
  /**
   * Returns tweets that match a specified query.
   * <br />This method calls twitter4j.Twitter.search.
   * <br />twitter4j.Twitter.search method calls http://search.twitter.com/search.json
   * 
   * @param query - the search condition
   * @return the result
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1.1/get/search"">GET search | Twitter Developers</a>
   * @see <a href="http://search.twitter.com/operators">Twitter API / Search Operators</a>
   * @since Twitter4S 1.0.0
   */
  def search(query: Query): QueryResult
}