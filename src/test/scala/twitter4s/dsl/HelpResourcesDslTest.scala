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
      "returns ResourceContextBuilder with Languages context" in {
        get(Languages) must haveClass[ResourceContextBuilder]
      }
    }
  }

  // get languages
  // get termsOfService
  // get.context{condition}
  // conditionにどう制約をかけるか
  // 引数(制約)はどう決まるか => メソッド(GET,SHOW,UPDATE,DESTROY) + リソースコンテキスト => 引数リストと戻り値が決まる
  // メソッド(リソースコンテキスト) => 引数リストと戻り値の型をコンテキストにバインドして返してやる
  // ただ、これだと.でつないでいかないと

  // getResource(languages[ResourceContext]) {[ResourceBinder]
  //   user isSpecifiedBy ""
  // get(language[ResourcesContext]):ResourceBinder[ResourceContext] user_id "": ResourceBinder[ResourceContext]
  // user_is "" <- implicit, 対応するResourcesContextのオブジェクト？コンパイルエラーで対応できるようにはしたい
}
