package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4s.mocked.FakeValuesUsedByMock

@RunWith(classOf[JUnitRunner])
class DirectMessagesResourcesTest extends Specification with TwitterResourcesTestBase {
  // mocking methods
  mockedTwitter4j.sendDirectMessage(anyString, anyString) returns FakeValuesUsedByMock.directMessage
  mockedTwitter4j.sendDirectMessage(anyLong, anyString) returns FakeValuesUsedByMock.directMessage
  mockedTwitter4j.getDirectMessages returns FakeValuesUsedByMock.responseList[twitter4j.DirectMessage]
  mockedTwitter4j.getDirectMessages(any[twitter4j.Paging]) returns FakeValuesUsedByMock.responseList[twitter4j.DirectMessage]
  mockedTwitter4j.showDirectMessage(anyLong) returns FakeValuesUsedByMock.directMessage
  mockedTwitter4j.getSentDirectMessages returns FakeValuesUsedByMock.responseList[twitter4j.DirectMessage]
  mockedTwitter4j.getSentDirectMessages(any[twitter4j.Paging]) returns FakeValuesUsedByMock.responseList[twitter4j.DirectMessage]
  mockedTwitter4j.destroyDirectMessage(anyLong) returns FakeValuesUsedByMock.directMessage
  
  "sendDirectMessage" should {
    "call twitter4j sendDirectMessage method by screen name" in {
      twitter.directMessages.sendDirectMessage(
        User.isSpecifiedBy(FakeValuesUsedByMock.friendName),
        "direct message to friend").senderScreenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).sendDirectMessage(FakeValuesUsedByMock.friendName, "direct message to friend")
    }

    "call twitter4j sendDirectMessage method by user id" in {
      twitter.directMessages.sendDirectMessage(
        User.isSpecifiedBy(1L),
        "direct message to friend").senderScreenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).sendDirectMessage(1L, "direct message to friend")
    }
    
    "throw exception both of parameter screenName and userId are not set" in {
      twitter.directMessages.sendDirectMessage(null, text = "unsuccess send") must
      throwA[IllegalArgumentException]
    }
  }
  
  "getDirectMessages" should {
    "call twitter4j getDirectMessages method without paging" in {
      twitter.directMessages.getDirectMessages().size must equalTo(1)
      there was one(mockedTwitter4j).getDirectMessages
    }

    "call twitter4j getDirectMessages method by paging" in {
      twitter.directMessages.getDirectMessages(Paging(2)).size must equalTo(1)
      there was one(mockedTwitter4j).getDirectMessages(Paging(2))
    }
  }
  
  "showDirectMessages" should {
    "call twitter4j showDirectMessages" in {
      twitter.directMessages.showDirectMessage(3L).recipientScreenName must equalTo(FakeValuesUsedByMock.friendName)
      there was one(mockedTwitter4j).showDirectMessage(3L)
    }
  }

  "getSentDirectMessages" should {
    "call twitter4j getSentDirectMessages method without paging" in {
      twitter.directMessages.getSentDirectMessages().size must equalTo(1)
      there was one(mockedTwitter4j).getSentDirectMessages
    }

    "call twitter4j getSentDirectMessage method by paging" in {
      twitter.directMessages.getSentDirectMessages(Paging(4)).size must equalTo(1)
      there was one(mockedTwitter4j).getSentDirectMessages(Paging(4))
    }
  }

  "destroyDirectMessage" should {
    "call twitter4j destroyDirectMessage" in {
      twitter.directMessages.destroyDirectMessage(5L).senderScreenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).destroyDirectMessage(5L)
    }
  }
}