package twitter4s.internal.json
import twitter4j.Tweet
import twitter4s.QueryResult
import scala.collection.JavaConverters._

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class QueryResultImpl(tw4jQueryResult: twitter4j.QueryResult) extends QueryResult {
  def sinceId = tw4jQueryResult.getSinceId()
  
  def maxId = tw4jQueryResult.getMaxId()
  
  def refreshUrl = tw4jQueryResult.getRefreshUrl()
  
  def resultsPerPage = tw4jQueryResult.getResultsPerPage()
  
  def warning = tw4jQueryResult.getWarning()
  
  def completedIn = tw4jQueryResult.getCompletedIn()
  
  def page = tw4jQueryResult.getPage()
  
  def query = tw4jQueryResult.getQuery()
  
  def tweets = tw4jQueryResult.getTweets().asScala
}