package twitter4s

import twitter4s._
import scala.collection.JavaConverters.asScalaBufferConverter
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import Twitter4sTestHelper._
import java.util.Date
import twitter4j.Trend
import java.text.SimpleDateFormat
import twitter4j.json.DataObjectFactory

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
@RunWith(classOf[JUnitRunner])
class SearchResourcesTest extends Specification {
  private def trendListAssert(trendList: ResponseList[twitter4j.Trends], expectSize: Int) = {
    var trendAt: Date = null
    forall(trendList) { (singleTrends: twitter4j.Trends) =>
      singleTrends.trends.size must be_>(expectSize - 10)
      if (trendAt != null) trendAt.before(singleTrends.trendAt) must beTrue
      trendAt = singleTrends.trendAt
      forall(singleTrends.trends.toList) { (trend: Trend) =>
        trend.getName() mustNotEqual(null)
        trend.getUrl() mustEqual(null)
        trend.getQuery() mustNotEqual(null)
      }
    }
  }
  
  "search" should {
    val testQueryStr = "test"
    val dateFormatter = new SimpleDateFormat("yyyy-MM-dd")
    val dateStr = dateFormatter.format(new java.util.Date(System.currentTimeMillis() - 24 * 3600 * 1000))
    
    "get result include query string until yesterday" in {
      val query  = Query(testQueryStr).until(dateStr)
      
      val queryResult = twitter1.search(query)
      queryResult.sinceId must not equalTo(-1)
      queryResult.maxId must be_>(1265204883L) // 値はTwitter4Jから引き継ぎ。期待値がどこから来てるか不明
      queryResult.refreshUrl.indexOf(testQueryStr) must not equalTo(-1)
//      queryResult.resultsPerPage must equalTo(15)
      queryResult.completedIn must be_>(0d)
//      queryResult.page must equalTo(1)
    }
    
    // TODO QueryResultモデルの変更対応
//    "get tweets from search result" in {
//      val query = Query(testQueryStr).until(dateStr)
////      val tweets = unauthenticated.search(query).tweets
//      
////      tweets.size must be_>=(1)
////      tweets(0) must equalTo(DataObjectFactory.createTweet(rawJSON(tweets(0))))
////      tweets(0).getText() must not equalTo(null)
////      tweets(0).getCreatedAt() must not equalTo(null)
////      tweets(0).getFromUser() must not equalTo(null)
////      tweets(0).getFromUserName() must not equalTo(null)
////      tweets(0).getId() must not equalTo(-1L)
////      tweets(0).getProfileImageUrl() must not equalTo(null)
////      val source = tweets(0).getSource()
////      (source.indexOf("<a href=\"") != -1 or "web" == source or "API" == source)
//    }
    
    "get search result if does not hit" in {
      val notHitQueryStr = "from:twit4j doesnothit"
      val query = Query(notHitQueryStr)
      val queryResult = twitter1.search(query)
      
      queryResult.sinceId must equalTo(0)
//      queryResult.resultsPerPage must equalTo(15)
//      queryResult.warning must equalTo(null)
      queryResult.completedIn must be_<(4d)
      queryResult.query must equalTo(notHitQueryStr)
    }
    
    "get search result if Japanese query" in {
      val japaneseQueryStr = "%... 日本語"
      // 一般的すぎてupdateStatusの意味がない
      // 他の箇所でupdateStatusのテストがなければクエリ文字列を変更してテストする
      // twitter1.updateStatus(status = Some("テスト：" + japaneseQueryStr + new Date()))
      
      val query = Query(japaneseQueryStr)
      val queryResult1 = twitter1.search(query)
      
      queryResult1.query must equalTo(japaneseQueryStr)
//      queryResult1.tweets.size must be_>(0)
      
      query.setQuery("from:al3x")
      query.setGeoCode(GeoLocation(37.78233252646689, -122.39301681518555), 10, Query.KILOMETERS)
      
//      val queryResult2 = unauthenticated.search(query)
//      queryResult2.tweets.size must be_>=(0)
    }
    
    /*
    "get search result is tweeted twit4j user" in {
      // 状況によって変更する必要あり
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
    */
  }
}