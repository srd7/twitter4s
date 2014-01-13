package twitter4s

import org.specs2.mock.Mockito

trait TwitterResourcesTestBase extends Mockito{
  val mockedTwitter4j = mock[twitter4j.Twitter]

  val twitter: Twitter = new Twitter(mockedTwitter4j)
}
