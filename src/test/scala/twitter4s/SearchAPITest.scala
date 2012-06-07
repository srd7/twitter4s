package twitter4s

import scala.collection.JavaConverters.asScalaBufferConverter
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4j.Trends
import twitter4s.Twitter4sTestHelper._
import java.util.Date
import twitter4j.Trend
import twitter4j.ResponseList

@RunWith(classOf[JUnitRunner])
class SearchAPITest extends Specification {
  
  private def trendListAssert(trendList: ResponseList[Trends], expectSize: Int) = {
    var trendAt: Date = null
    forall(trendList.asScala) { (singleTrends: Trends) =>
      singleTrends.getTrends().size must be_>(expectSize - 10)
      if (trendAt != null) trendAt.before(singleTrends.getTrendAt()) must beTrue
      trendAt = singleTrends.getTrendAt()
      forall(singleTrends.getTrends().toList) { (trend: Trend) =>
        trend.getName() mustNotEqual(null)
        trend.getUrl() mustEqual(null)
        trend.getQuery() mustNotEqual(null)
      }
    }
  }

  "getDailyTrends" should {
    val expectListSize = 20
    
    "get top 20 trends without parameter" in {
      val trendList = unauthenticated.getDailyTrends()
      trendListAssert(trendList, expectListSize)
    }
    
    "get top 20 trends exclude hash tags with date and excludeHashTags parameter" in {
      val trendList = unauthenticated.getDailyTrends(Some(new Date()), Some(true))
      trendListAssert(trendList, expectListSize)
    }
  }
  
  "getWeeklyTrends" should {
    val expectedListSize = 30
    
    "get top 30 trends without parameter" in {
      val trendList = unauthenticated.getWeeklyTrends()
      trendListAssert(trendList, expectedListSize)
    }
    
//    "get top 30 trends exclude hash tags with data and excludeHashTags parameter" in {
//      val trendList = unauthenticated.getWeeklyTrends(Some(new Date()), Some(true))
//      trendListAssert(trendList, expectedListSize)
//    }
  }
}