package twitter4s.dsl

import org.specs2.mutable._

class HelpResourcesDslTest extends Specification with Twitter4sDslTestBase with HelpResourcesDsl {
  "attach" should {
    "create twitter4s resources" in {
      attach(testAccessToken)(testConsumerKey)

      resources must haveInterface[ResourcesType]
    }
  }

  "get" should {
    "with Language context" in {
      "returns ResourceContextBuilder" in {
        get(Languages) must haveClass[ResourceContextBuilder]
      }

      "have get operation" in {
        get(Languages).op must equalTo(Get)
        get(Languages).op must haveSuperclass[Operation]
      }
    }
  }

//  get(languages) where {
//    User isSpecifiedBy "hogehoge" and
//      Paging 5
//  }
}
