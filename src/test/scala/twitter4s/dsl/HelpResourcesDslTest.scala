package twitter4s.dsl

import org.specs2.mutable._

class HelpResourcesDslTest extends Specification with Twitter4sDslTestBase with HelpResourcesDsl {
  "attach" should {
    "create twitter4s resources" in {
      attach(testAccessToken)(testConsumerKey)

      resources must haveInterface[ResourcesType]
    }
  }
}
