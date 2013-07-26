package twitter4s
import twitter4s._
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import twitter4j.MediaEntity
import java.util.Date
import java.io.File
import twitter4s.api.impl.TweetsResourcesImpl
import twitter4s.api.impl.TimelinesResourcesImpl

@RunWith(classOf[JUnitRunner])
class TweetsResourcesTest extends Specification {
  
  val twitter2StatusRole = new Twitter(twitter4jInstance(User2)) with TweetsResourcesImpl
  val twitter1StatusRole = new Twitter(twitter4jInstance(User1)) with TweetsResourcesImpl with TimelinesResourcesImpl
  val twitter3StatusRole = new Twitter(twitter4jInstance(User3)) with TweetsResourcesImpl with TimelinesResourcesImpl

  "showStatus" should {
    "get status specified by tweet id" in {
      val status = twitter2StatusRole.showStatus(1000)
      rawJSON(status.tw4jObj) must not equalTo(null)
      status.tw4jObj must equalTo(DataObjectFactory.createStatus(rawJSON(status.tw4jObj)))
      status.user.id must equalTo(52)
      status.rateLimitStatus must not equalTo(null)
    }
  }
  
  "updateStatus" should {
    val dateString = new Date().toString()
    "update status by status string" in {
      val status = twitter2StatusRole.updateStatus(
          Status.isWrittenBy(dateString + "test by status string http://t.co/VEDROet #twitter4stest"))
      rawJSON(status.tw4jObj) must not equalTo(null)
      status.tw4jObj must equalTo(DataObjectFactory.createStatus(rawJSON(status.tw4jObj)))
      
      status.text must startWith(dateString)
      
      // retweet test
      val retweetStatus = twitter3StatusRole.retweetStatus(status.id)
      val statuses = twitter3StatusRole.getHomeTimeline()
      retweetStatus.text must endWith(status.text)
      statuses(0).currentUserRetweetId must not equalTo(-1L)
      
      // destory test
      val destroyedStatus = twitter2StatusRole.destroyStatus(status.id)
      rawJSON(destroyedStatus.tw4jObj) must not equalTo(null)
      destroyedStatus.tw4jObj must equalTo(DataObjectFactory.createStatus(rawJSON(destroyedStatus.tw4jObj)))
    }
    
    "update status by StatusUpdate object" in {
      val repliedStatus = twitter2StatusRole.updateStatus(
          Status.isWrittenBy(dateString + "test by status object http://t.co/VEDROet #twitter4stest"))
      
      val status = twitter3StatusRole.updateStatus(
          Status.isSetBy(
              StatusUpdate("@" + id2.screenName + " " + dateString)
              	.inReplyToStatusId(repliedStatus.id)))
              	
      rawJSON(status.tw4jObj) must not equalTo(null)
      status.tw4jObj must equalTo(DataObjectFactory.createStatus(rawJSON(status.tw4jObj)))
      status.text must startWith("@" + id2.screenName + " " + dateString)
      status.inReplyToStatusId must equalTo(repliedStatus.id)
      status.inReplyToUserId must equalTo(id2.id)
    }
    
    "update status with media" in {
      val status = twitter3StatusRole.updateStatus(Status.isSetBy(
          StatusUpdate(dateString + "test by staus with media http://t.co/VEDROet #twitter4stest")
          	.possiblySensitive(false)
          	.media(getRandomlyChosenFile)))
      rawJSON(status.tw4jObj) must not equalTo(null)
      status.tw4jObj must equalTo(DataObjectFactory.createStatus(rawJSON(status.tw4jObj)))
      status.text must startWith(dateString)
      status.mediaEntities.length must equalTo(1)
    }
    
    "throw IllegalArgumentException when status set null" in {
      twitter2StatusRole.updateStatus(null) must throwA[IllegalArgumentException]
    }
  }
  
  def testRetweetResponseList(statuses: ResponseList[twitter4j.Status]) = {
    rawJSON(statuses.tw4jObj) must not equalTo(null)
    statuses(0) must equalTo(DataObjectFactory.createStatus(rawJSON(statuses(0))))
    forall(statuses) {(status:twitter4j.Status) => status.getText must startWith("RT ")}
  }
  
  "getRetweetsOfMe" should {
    "get retweet from authorized user's tweet without page" in {
      val statuses = twitter1StatusRole.getRetweetsOfMe()
      rawJSON(statuses.tw4jObj) must not equalTo(null)
      statuses(0) must equalTo(DataObjectFactory.createStatus(rawJSON(statuses(0))))
    }
    
    "get retweet from authorized user's tweet with page" in {
      val statuses = twitter1StatusRole.getRetweetsOfMe(Paging(1))
      rawJSON(statuses.tw4jObj) must not equalTo(null)
      statuses(0) must equalTo(DataObjectFactory.createStatus(rawJSON(statuses(0))))
    }
  }
  
  "getRetweets" should {
    "get retweet specified by tweet id" in {
      val statuses = twitter1StatusRole.getRetweets(18594701629L)
      testRetweetResponseList(statuses)
      statuses.size must be_>(20)
    }
  }
  
  "getOEmbed" should { // TODO 3.0.4対応時に
//    "get embedded representation" in {
//      val oembed = twitter2StatusRole.getOEmbed(OEmbedRequest(240192632003911681L, "http://d.hatena.ne.jp/mao_instantlife/"))
//      
//      rawJSON(oembed.tw4jObj) must not equalTo(null) 
//    }
  }
  
  "Status#symbolEntities" should {
//    "get symbol from status" in { // TODO 3.0.4対応時
//      val status = twitter2StatusRole.showStatus(332341548203261953L)
//      
//      status.symbolEntities.size must 4
//      getSymbolString(status, 0) must equalTo("$APPL")
//      getSymbolString(status, 1) must equalTo("$C")
//      getSymbolString(status, 2) must equalTo("$LNKD")
//      getSymbolString(status, 3) must equalTo("$FB")
//    }
    
//    def getSymbolString(status: Status, index: Int): String = {
//      status.text.substring(status.symbolEntities(index).getStart(), status.symbolEntities(index).getEnd())
//    }
  }
  
  "Status#urlEntities" should {
    "get url entities from status" in {
      val status = twitter2StatusRole.showStatus(268294645526708226L)
      
      status.urlEntities.length must equalTo(1)
      status.urlEntities(0).getURL() must equalTo("http://t.co/HwbSpYFr")
      status.urlEntities(0).getExpandedURL() must equalTo("http://twitter4j.org/en/index.html#download")
      status.urlEntities(0).getDisplayURL() must equalTo("twitter4j.org/en/index.html#…")
      
      status.urlEntities(0).getStart() must be_>(0)
      status.urlEntities(0).getEnd() must be_>(status.urlEntities(0).getStart())
      val urlString = status.text.substring(status.urlEntities(0).getStart(), status.urlEntities(0).getEnd())
      urlString must equalTo(status.urlEntities(0).getURL())
    }
  }
  
  "Status#userMentionEntities" should {
    "get user mention status from status" in {
      val status = twitter2StatusRole.showStatus(268294645526708226L)
      
      status.userMentionEntities.length must equalTo(2)
      status.userMentionEntities(1).getId() must equalTo(72297675)
      status.userMentionEntities(1).getScreenName() must equalTo("t4j_news")
      val mentionString = status.text.substring(status.userMentionEntities(0).getStart(), status.userMentionEntities(0).getEnd())
      mentionString must equalTo("@" + status.userMentionEntities(0).getScreenName())
    }
  }
  
  "Status#hastagEntities" should {
    "get hashtag from status" in {
      val status = twitter2StatusRole.showStatus(268294645526708226L)
      
      status.hashtagEntities.length must equalTo(1)
      status.hashtagEntities(0).getText() must equalTo("test")
      val hashtagString = status.text.substring(status.hashtagEntities(0).getStart(), status.hashtagEntities(0).getEnd())
      hashtagString must equalTo("#" + status.hashtagEntities(0).getText())
    }
  }
  
  "Status#mediaEntities" should {
    "get media from status" in {
      val status = twitter2StatusRole.showStatus(76360760606986241L)
      
      status.mediaEntities.length must equalTo(1)
      
      val mediaEntity = status.mediaEntities(0)
      mediaEntity.getDisplayURL() must equalTo("pic.twitter.com/qbJx26r")
      mediaEntity.getExpandedURL() must equalTo("http://twitter.com/twitter/status/76360760606986241/photo/1")
      mediaEntity.getId() must equalTo(76360760611180544L)
      mediaEntity.getMediaURL() must equalTo("http://pbs.twimg.com/media/AQ9JtQsCEAA7dEN.jpg")
      mediaEntity.getMediaURLHttps() must equalTo("https://pbs.twimg.com/media/AQ9JtQsCEAA7dEN.jpg")
      mediaEntity.getURL() must equalTo("http://t.co/qbJx26r")
      mediaEntity.getStart() must equalTo(34)
      mediaEntity.getEnd() must equalTo(53)
      mediaEntity.getType() must equalTo("photo")
      
      val sizes = mediaEntity.getSizes()
      
      assertSize(MediaEntity.Size.FIT, sizes.get(MediaEntity.Size.LARGE), 700, 466)
      assertSize(MediaEntity.Size.FIT, sizes.get(MediaEntity.Size.MEDIUM), 600, 399)
      assertSize(MediaEntity.Size.FIT, sizes.get(MediaEntity.Size.SMALL), 340, 226)
      assertSize(MediaEntity.Size.CROP, sizes.get(MediaEntity.Size.THUMB), 150, 150)
    }
  }
  
  def assertSize(expectedSize: Int, size: MediaEntity.Size, width: Int, height: Int) = {
    size.getResize() must equalTo(expectedSize)
    size.getWidth() must equalTo(width)
    size.getHeight() must equalTo(height)
  }
//  TODO 3.0.4対応
//  def getEntityString(status: Status, entity: TweetEntity) = {
//    status.text.substring(entity.getStart(), entity.getEnd())
//  }
}