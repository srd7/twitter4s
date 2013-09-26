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
    "returns ContextBundler instance" in {
      get must haveSuperclass[ContextBundler]
    }

    "have languages context" in {
      (get languages) must equalTo(LanguagesContext)
      (get languages) must haveInterface[ContextExecutor]
    }
  }

  // get languages
  // get termsOfService
  // get.context{condition}
  // conditionにどう制約をかけるか
  // 引数(制約)はどう決まるか => メソッド(GET,SHOW,UPDATE,DESTROY) + リソースコンテキスト => 引数リストと戻り値が決まる
  // メソッド(リソースコンテキスト) => 引数リストと戻り値の型をコンテキストにバインドして返してやる
  // ただ、これだと.でつないでいかないと
}
