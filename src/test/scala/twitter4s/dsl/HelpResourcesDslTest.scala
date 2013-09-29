package twitter4s.dsl

import org.specs2.mutable._
import twitter4s.{ResponseList, TwitterAPIConfiguration}
import twitter4j.api.HelpResources.Language

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
        get(Languages) must haveClass[ResourceContextBuilder[ParameterNothing.type, ResponseList[Language]]]
      }

      "have languages context" in {
        get(Languages).context must equalTo(Languages)
      }

      "have conditions is ParameterNothing" in {
        get(Languages).conditions must equalTo(ParameterNothing)
      }
    }

    "with APIConfiguration Context" in {
      "returns ResourceContextBuilder" in {
        get(APIConfiguration) must haveClass[ResourceContextBuilder[ParameterNothing.type, TwitterAPIConfiguration]]
      }

      "have APIConfiguration context" in {
        get(APIConfiguration).context must equalTo(APIConfiguration)
      }

      "have condition is ParameterNothing" in {
        get(APIConfiguration).conditions must equalTo(ParameterNothing)
      }
    }

    "with TermsOfService Context" in {
      "returns ResourceContextBuilder" in {
        get(TermsOfService) must haveClass[ResourceContextBuilder[ParameterNothing.type, String]]
      }

      "have TermsOfService Context" in {
        get(TermsOfService).context must equalTo(TermsOfService)
      }

      "have condition is ParameterNothing" in {
        get(TermsOfService).conditions must equalTo(ParameterNothing)
      }
    }
  }

//  get(languages) where {
//    User isSpecifiedBy "hogehoge" and
//      Paging 5
//  }
}
