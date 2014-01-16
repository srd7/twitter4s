# Twitter4S

[Twitter4J](https://github.com/yusuke/twitter4j)のScalaラッパです。<br/>
Twitter4Jの基本的な機能をScalaである程度書きやすいようにラップしています。<br/>

## インストール

build.sbtに以下の依存性を追加して下さい。

```
resolvers += "bintray" at "http://dl.bintray.com/shinsuke-abe/maven"

libraryDependencies += "com.github.Shinsuke-Abe" %% "twitter4s" % "2.1.0"
```

## ビルド環境について

Twitter4Sは以下の環境で構築されています。

* Scala 2.10.3
* sbt 0.13.1
* Twitter4J 3.0.5

注：2014/01/18時点で最新の安定版のライブラリを元に構築しています。

## 他のライブラリとの依存関係

Twitter4J以外の依存関係はありません。

## 使い方

Twitter4Sは、Twitter4J準拠の書き方とDSL(Twitter4S独自機能)を使う書き方の2種類の利用方法があります。<br/>
DSLはまだ全機能をカバーできている訳ではありません。利用頻度が高いと思われる機能を中心に実装しています。

### Twitter4J準拠

```
import twitter4s._ // packageオブジェクトがimportされていないと暗黙の型変換がされないので、importは必ず。

val twitter = Twitter()
twitter.setOAuthConsumer(ConsumerKey("consumerKey", "consumerSecret"))
twitter.setOAuthAccessToken(AccessToken("accessToken", "tokenSecret"))

twitter.各APIメソッド(Twitter4Jとおおよそ1対1でAPIがひもづくようにしています)
```

### DSL

DSLはStringContextと各種メソッドを提供しています。<br/>

```
import twitter4s.dsl._

implicit val consumerKey = ConsumerKey("consumerKey", "consumerSecret")

withToken(AccessToken("accessToken", "tokenSecret") { implicit twitter =>
  tweet"test tweet" udpate
}
```

withTokenの引数で指定したAccessTokenで認証したユーザを主語とする操作を行うと考えて下さい。<br/>

#### DSLが提供するStringContext

現時点で実装しているStringContextは以下の通りです。

```
user"スクリーン名"
user"id:ユーザID"

list"リストSlug"
list"id:リストID"

tweet"つぶやき"

message"DMの内容"
```

#### DSLが提供する機能

```
// ツイートに関する機能
tweet"テスト" update // 「テスト」とツイートします

// ユーザに関する機能
get(user"ユーザA") // ユーザAの情報を取得します
follow(user"ユーザA") // ユーザAをフォローします
unfollow(user"ユーザA") // ユーザAのフォローをやめます
block(user"ユーザA") // ユーザAをブロックします

// リストに関する機能
get(list"リストA") // リストAの情報を取得します
add(user"ユーザA") to list"リストA" // リストAにユーザAを追加します
remove(user"ユーザA") from list"リストA" // リストAからユーザAを削除します

// DMに関する機能
send(message"テスト") to user"ユーザA" // ユーザAに「テスト」という内容でDirectMessageを送信します
```

#### DSLの機能追加について

今後のバージョンでも機能追加していきます。<br/>
欲しいDSLの構文があれば、Issueを追加するなりTwitter(@mao_instantlife)でコンタクトを取るなりしていただければ検討します。