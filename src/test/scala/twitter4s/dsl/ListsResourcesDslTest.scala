package twitter4s.dsl

/**
 * @author mao.instantlife at gmail.com
 */

import org.specs2.mutable._

class ListsResourcesDslTest extends Specification with Twitter4sDslTestBase with ListsResourcesDsl {
  "attach" should {
    "create twitter4s resouces" in {
      attach(testAccessToken)(testConsumerKey)

      resources must haveInterface[ResourcesType]
    }
  }
}
