package twitter4s

import scala.collection.mutable.Buffer

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait QueryResult {
  def sinceId: Long
  
  def maxId: Long
  
  /**
   * @deprecated use {@link #refreshURL} instead
   */
  def refreshUrl: String
  
  def refreshURL: String
  
  def count: Int
  
  def completedIn: Double
  
  def query: String
  
  def tweets: Buffer[twitter4j.Status]
  
  /**
   * Returns a Query instance to fetch next page or null if there is no next page.
   * 
   * @return Query instance to fetch 
   * @since Twitter4S 2.0.0
   */
  def nextQuery: twitter4j.Query
  /**
   * test if there is next page
   *
   * @return if there is next page
   * @since Twitter4S 2.0.0
   */
  def hasNext: Boolean
}