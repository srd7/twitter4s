package twitter4s

import org.specs2.mutable._
import twitter4s.api.impl.SpamReportingResourcesImpl
import twitter4s.mocked.FakeValuesUsedByMock

class SpamReportingResourcesTest extends Specification with TwitterResourcesTestBase {
  type TargetResourcesType = SpamReportingResourcesImpl

  mockedTwitter4j.reportSpam(anyString) returns FakeValuesUsedByMock.user
  mockedTwitter4j.reportSpam(anyLong) returns FakeValuesUsedByMock.user

  val twitter = new Twitter(mockedTwitter4j) with TargetResourcesType

  "reportSpam" should {
    "call twitter4j reportSpam method by screen name" in {
      twitter.spamReporting.reportSpam(User.isSpecifiedBy(FakeValuesUsedByMock.friendName)).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).reportSpam(FakeValuesUsedByMock.friendName)
    }

    "call twitter4j reportSpam method by user id" in {
      twitter.spamReporting.reportSpam(User.isSpecifiedBy(1L)).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).reportSpam(1L)
    }

    "throw exception if user setting info is null" in {
      twitter.spamReporting.reportSpam(null) must
      throwA[IllegalArgumentException]
    }
  }
}
