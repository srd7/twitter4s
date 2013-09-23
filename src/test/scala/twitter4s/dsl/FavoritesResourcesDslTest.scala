package twitter4s.dsl

import org.specs2.mutable._

class FavoritesResourcesDslTest extends Specification with Twitter4sDslTestBase with FavoritesResourcesDsl {
  "attach" should {
    "create twitter4s resouces" in {
      attach(testAccessToken)(testConsumerKey)

      resources must haveInterface[ResourcesType]
    }
  }
}
