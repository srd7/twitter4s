package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import Twitter4sTestHelper._
import twitter4j._
import twitter4s.api.impl.TweetsResourcesImpl
import twitter4s.mocked.FakeValuesUsedByMock

@RunWith(classOf[JUnitRunner])
class TweetsResourcesTest extends Specification with TwitterResourcesTestBase {
  type TargetResourcesType = TweetsResourcesImpl
  
  val twitter2StatusRole = new Twitter(twitter4jInstance(User2)) with TweetsResourcesImpl

  mockedTwitter4j.showStatus(anyLong) returns(FakeValuesUsedByMock.status)
  mockedTwitter4j.updateStatus(anyString) returns(FakeValuesUsedByMock.status)
  mockedTwitter4j.updateStatus(any[twitter4j.StatusUpdate]) returns(FakeValuesUsedByMock.status)
  mockedTwitter4j.destroyStatus(anyLong) returns(FakeValuesUsedByMock.status)
  mockedTwitter4j.retweetStatus(anyLong) returns(FakeValuesUsedByMock.status)
  mockedTwitter4j.getRetweets(anyLong) returns(FakeValuesUsedByMock.responseList[twitter4j.Status])
  mockedTwitter4j.getOEmbed(any[twitter4j.OEmbedRequest]) returns(FakeValuesUsedByMock.oembed)
  mockedTwitter4j.getRetweeterIds(anyLong, anyLong) returns(FakeValuesUsedByMock.ids)
  mockedTwitter4j.getRetweeterIds(anyLong, anyInt, anyLong) returns(FakeValuesUsedByMock.ids)

  override val twitter = new Twitter(mockedTwitter4j) with TweetsResourcesImpl

  "showStatus" should {
    "call twitter4j showStatus method" in {
      twitter.tweets.showStatus(1000).text must equalTo(FakeValuesUsedByMock.statusText)
      there was one(mockedTwitter4j).showStatus(1000)
    }
  }

  "updateStatus" should {
    "call twitter4j updateStatus by status string method" in {
      twitter.tweets.updateStatus(Status.isWrittenBy("call by string")).text must equalTo(FakeValuesUsedByMock.statusText)
      there was one(mockedTwitter4j).updateStatus("call by string")
    }

    "call twitter4j updateStatus by status object method" in {
      val setStatus = StatusUpdate("call by status object")
      twitter.tweets.updateStatus(Status.isSetBy(setStatus)).text must equalTo(FakeValuesUsedByMock.statusText)
      there was one(mockedTwitter4j).updateStatus(setStatus)
    }

    "throw IllegalArgumentException when status set null" in {
      twitter.tweets.updateStatus(null) must throwA[IllegalArgumentException]
    }
  }

  "destroyStatus" should {
    "call twitter4j destroyStatus method" in {
      twitter.tweets.destroyStatus(18594701629L).text must equalTo(FakeValuesUsedByMock.statusText)
      there was one(mockedTwitter4j).destroyStatus(18594701629L)
    }
  }

  "retweetStatus" should {
    "call twitter4j retweetStatus method" in {
      twitter.tweets.retweetStatus(18594701628L).text must equalTo(FakeValuesUsedByMock.statusText)
      there was one(mockedTwitter4j).retweetStatus(18594701628L)
    }
  }

  "getRetweets" should {
    "call twitter4j getRetweets method" in {
      twitter.tweets.getRetweets(18594701627L).accessLevel must equalTo(TwitterResponse.READ_WRITE_DIRECTMESSAGES)
      there was one(mockedTwitter4j).getRetweets(18594701627L)
    }
  }

  "getOEmbed" should {
    "call twitter4j getOEmbed method" in {
      twitter.tweets.getOEmbed(OEmbedRequest(18594701629L, "https://www.test.url")).url must equalTo(FakeValuesUsedByMock.statusUrl)
      there was one(mockedTwitter4j).getOEmbed(OEmbedRequest(18594701629L, "https://www.test.url"))
    }
  }

  "getRetweeterIds" should {
    "call twitter4j getRetweeterIds by status id and cursor" in {
      twitter.tweets.getRetweeterIds(18594701630L, 18594701631L).hasPrevious must beTrue
      there was one(mockedTwitter4j).getRetweeterIds(18594701630L, 18594701631L)
    }

    "call twitter4j getRetweeterIds by status id and count and cursor" in {
      twitter.tweets.getRetweeterIds(18594701632L, 18594701633L, 3).hasPrevious must beTrue
      there was one(mockedTwitter4j).getRetweeterIds(18594701632L, 3, 18594701633L)
    }
  }

  "Status#urlEntities" should {
    "get url entities from status" in {
      val status = twitter2StatusRole.tweets.showStatus(268294645526708226L)

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
      val status = twitter2StatusRole.tweets.showStatus(268294645526708226L)

      status.userMentionEntities.length must equalTo(2)
      status.userMentionEntities(1).getId() must equalTo(72297675)
      status.userMentionEntities(1).getScreenName() must equalTo("t4j_news")
      val mentionString = status.text.substring(status.userMentionEntities(0).getStart(), status.userMentionEntities(0).getEnd())
      mentionString must equalTo("@" + status.userMentionEntities(0).getScreenName())
    }
  }

  "Status#hastagEntities" should {
    "get hashtag from status" in {
      val status = twitter2StatusRole.tweets.showStatus(268294645526708226L)

      status.hashtagEntities.length must equalTo(1)
      status.hashtagEntities(0).getText() must equalTo("test")
      val hashtagString = status.text.substring(status.hashtagEntities(0).getStart(), status.hashtagEntities(0).getEnd())
      hashtagString must equalTo("#" + status.hashtagEntities(0).getText())
    }
  }

  "Status#mediaEntities" should {
    "get media from status" in {
      val status = twitter2StatusRole.tweets.showStatus(76360760606986241L)

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
}