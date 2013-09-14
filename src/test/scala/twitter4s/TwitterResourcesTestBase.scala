package twitter4s

import org.specs2.mock.Mockito

trait TwitterResourcesTestBase extends Mockito{
  type TargetResourcesType

  val mockedTwitter4j = mock[twitter4j.Twitter]

  val twitter: Twitter with TargetResourcesType
}
