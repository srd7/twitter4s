package twitter4s
import twitter4j.Tweet
import scala.collection.mutable.Buffer

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait QueryResult {
  def sinceId: Long
  
  def maxId: Long
  
  def refreshUrl: String
  
  def resultsPerPage: Int
  
  def warning: String
  
  def completedIn: Double
  
  def page: Int
  
  def query: String
  
  def tweets: Buffer[Tweet]
  
  /* TODO Twitter4j 3.0.0対応
  def nextQuery: twitter4j.Query
  
  def hasNext: Boolean
  */
}