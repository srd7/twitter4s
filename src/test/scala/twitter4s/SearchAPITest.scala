package twitter4s

import scala.collection.JavaConverters.asScalaBufferConverter
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4j.Trends
import twitter4s.Twitter4sTestHelper._
import java.util.Date
import twitter4j.Trend
import java.text.SimpleDateFormat
import twitter4j.Query
import twitter4j.json.DataObjectFactory
import twitter4j.GeoLocation
import twitter4s.implicits.Twitter4SImplicits._

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
@RunWith(classOf[JUnitRunner])
class SearchAPITest extends Specification {
  
  private def trendListAssert(trendList: ResponseList[Trends], expectSize: Int) = {
    var trendAt: Date = null
    forall(trendList) { (singleTrends: Trends) =>
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
  
  // TODO Queryはラップするときにテストを書く
  
  "search" should {
    val testQueryStr = "test"
    val dateFormatter = new SimpleDateFormat("yyyy-MM-dd")
    val dateStr = dateFormatter.format(new java.util.Date(System.currentTimeMillis() - 24 * 3600 * 1000))
    
    "get result include query string until yesterday" in {
      val query  = new Query(testQueryStr).until(dateStr)
      
      val queryResult = unauthenticated.search(query)
      queryResult.getSinceId() must not equalTo(-1)
      queryResult.getMaxId() must be_>(1265204883L) // 値はTwitter4Jから引き継ぎ。期待値がどこから来てるか不明
      queryResult.getRefreshUrl().indexOf(testQueryStr) must not equalTo(-1)
      queryResult.getResultsPerPage() must equalTo(15)
      queryResult.getCompletedIn() must be_>(0d)
      queryResult.getPage() must equalTo(1)
    }
    
    "get tweets from search result" in {
      val query = new Query(testQueryStr).until(dateStr)
      val tweets = unauthenticated.search(query).getTweets()
      
      tweets.size() must be_>=(1)
      tweets.get(0) must equalTo(DataObjectFactory.createTweet(rawJSON(tweets.get(0))))
      tweets.get(0).getText() must not equalTo(null)
      tweets.get(0).getCreatedAt() must not equalTo(null)
      tweets.get(0).getFromUser() must not equalTo(null)
      tweets.get(0).getFromUserName() must not equalTo(null)
      tweets.get(0).getId() must not equalTo(-1L)
      tweets.get(0).getProfileImageUrl() must not equalTo(null)
      val source = tweets.get(0).getSource()
      (source.indexOf("<a href=\"") != -1 or "web" == source or "API" == source)
    }
    
    "get search result if does not hit" in {
      val notHitQueryStr = "from:twit4j doesnothit"
      val query = new Query(notHitQueryStr)
      val queryResult = unauthenticated.search(query)
      
      queryResult.getSinceId() must equalTo(0)
      queryResult.getResultsPerPage() must equalTo(15)
      queryResult.getWarning() must equalTo(null)
      queryResult.getCompletedIn() must be_<(4d)
      queryResult.getQuery() must equalTo(notHitQueryStr)
    }
    
    "get search result if Japanese query" in {
      val japaneseQueryStr = "%... 日本語"
      // 一般的すぎてupdateStatusの意味がない
      // 他の箇所でupdateStatusのテストがなければクエリ文字列を変更してテストする
      // twitter1.updateStatus(status = Some("テスト：" + japaneseQueryStr + new Date()))
      
      val query = new Query(japaneseQueryStr)
      val queryResult1 = unauthenticated.search(query)
      
      queryResult1.getQuery() must equalTo(japaneseQueryStr)
      queryResult1.getTweets.size must be_>(0)
      
      query.setQuery("from:al3x")
      query.setGeoCode(new GeoLocation(37.78233252646689, -122.39301681518555), 10, Query.KILOMETERS)
      
      val queryResult2 = unauthenticated.search(query)
      queryResult2.getTweets.size must be_>=(0)
    }
    
    "get search result is tweeted twit4j user" in {
      val query = new Query("from:twit4j")
      query.setSinceId(1671199128)
      
      val queryResult = unauthenticated.search(query)
      queryResult.getTweets.size must be_>(0)
      queryResult.getTweets.get(0).getFromUserId must equalTo(6358482)
    }
    
    "get search result from authed user" in {
      // 状況によって変更する必要あり
      // 時事性の強いテストデータになっている
      val query = new Query("#sendro to:yusukey").rpp(100).page(1)
      
      val queryResult = twitter1.search(query)
      queryResult.getTweets().get(0).getHashtagEntities() must not equalTo(null)
      queryResult.getTweets().get(0).getUserMentionEntities() must not equalTo(null)
      queryResult.getTweets().get(0).getURLEntities() must not equalTo(null)
    }
  }

  "getDailyTrends" should {
    val dailyTrendsListSize = 20
    
    "get top 20 trends without parameter" in {
      val trendList = unauthenticated.getDailyTrends()
      trendListAssert(trendList, dailyTrendsListSize)
    }
    
    "get top 20 trends exclude hash tags with date and excludeHashTags parameter" in {
      val trendList = unauthenticated.getDailyTrends(Some(new Date()), Some(true))
      trendListAssert(trendList, dailyTrendsListSize)
    }
  }
  
  "getWeeklyTrends" should {
    val weeklyTrendsListSize = 30
    
    "get top 30 trends without parameter" in {
      val trendList = unauthenticated.getWeeklyTrends()
      trendListAssert(trendList, weeklyTrendsListSize)
    }
    
    "get top 30 trends exclude hash tags with data and excludeHashTags parameter" in {
      val trendList = unauthenticated.getWeeklyTrends(Some(new Date()), Some(false))
      trendListAssert(trendList, weeklyTrendsListSize)
    }
  }
}