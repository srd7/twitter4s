package twitter4s.api

import twitter4j.Query
import twitter4j.QueryResult

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait SearchMethods {
  /**
   * Returns tweets that match a specified query.
   * <br/>This method calls twitter4j.Twitter.search.
   * <br/>twitter4j.Twitter.search method calls http://search.twitter.com/search.json
   * 
   * @param query - the search condition
   * @return the result
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/search">GET search | Twitter Developers</a>
   * @see <a href="http://search.twitter.com/operators">Twitter API / Search Operators</a>
   * @since Twitter4S 1.0.0
   */
  def search(query: Query): QueryResult
}