package twitter4s

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4j._
import twitter4s.api.impl.SearchResourcesImpl
import java.util

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
@RunWith(classOf[JUnitRunner])
class SearchResourcesTest extends Specification with TwitterResourcesTestBase {
  type TargetResourcesType = SearchResourcesImpl

  mockedTwitter4j.search(any[Query]) returns (new twitter4j.QueryResult {
    def getRateLimitStatus: twitter4j.RateLimitStatus = ???
    def getAccessLevel: Int = ???
    def getSinceId: Long = ???
    def getMaxId: Long = ???
    def getRefreshUrl: String = ???
    def getRefreshURL: String = ???
    def getCount: Int = ???
    def getCompletedIn: Double = ???
    def getQuery: String = "returned search query"
    def getTweets: util.List[twitter4j.Status] = ???
    def nextQuery(): Query = ???
    def hasNext: Boolean = ???
  })

  override val twitter = new Twitter(mockedTwitter4j) with TargetResourcesType
  
  "search" should {
    "call twitter4j search method" in {
    twitter.search.search(Query("search query")).query must equalTo("returned search query")
      there was one(mockedTwitter4j).search(Query("search query"))
    }
  }
}