# Twitter4S

[Twitter4J](https://github.com/yusuke/twitter4j)のScalaラッパです。<br/>
Twitter4Jの基本的な機能をScalaである程度書きやすいようにラップしています。

## 利用方法

現状、jarに固めた形での配布は行っていません。<br/>
sbtにこのリポジトリに対する依存性を追加するか、ソースをダウンロードしてビルドして下さい。

### 依存性を追加する場合

[この辺](http://d.hatena.ne.jp/Yoshiori/20120324/1332618170)を参考にbuild.scalaを作って依存性を追加して下さい。

### ソースをダウンロードしてビルドする場合

```
(ソースを展開したい箇所で)
> git close git://github.com/Shinsuke-Abe/twitter4s.git
> sbt package
```

target/scala-2.10配下にtwitter4s_2.10-2.0.0.jarができます。<br/>
上記のjarファイルとTwitter4J 3.0.3のjarファイルの両方にパスを通して使って下さい。

## ビルド環境について

Twitter4Sは以下の環境で構築されています。

* Scala 2.10.0
* sbt 0.12.2
* Twitter4J 3.0.3

注：2013/07/27時点で最新の安定版のライブラリを元に構築しています。

## Twitter4Jとの依存関係

Twitter4Sのビルド依存性にTwitter4Jのライブラリを含めています。<br/>
2013.07.27現在、Twitter4J 3.0.3を依存するライブラリとしてビルドします。

## サンプルについて

現状、テストコードしかサンプルに相当する物はありません。<br/>
基本的な利用の仕方は、テストコードをご参照ください。<br/>
が、大体以下の流れでとりあえずは使ってみることができるはずです。

```
import twitter4s._ // packageオブジェクトがimportされていないと暗黙の型変換がされないので、importは必ず。

val twitter = Twitter()
twitter.setOAuthConsumer(ConsumerKey("consumerKey", "consumerSecret"))
twitter.setOAuthAccessToken(AccessToken("accessToken", "tokenSecret"))

twitter.各APIメソッド
```