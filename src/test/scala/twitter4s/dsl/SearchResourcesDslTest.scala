package twitter4s.dsl

import org.specs2.mutable._

class SearchResourcesDslTest extends Specification with Twitter4sDslTestBase with SearchResourcesDsl {
  "attach" should {
    "create twitter4s resources" in {
      attach(testAccessToken)(testConsumerKey)

      resources must haveInterface[ResourcesType]
    }
  }
}
