# Twitter4S

This is a Scala wrapper of [Twitter4J](https://github.com/yusuke/twitter4j). <br/>
It provides easy way to use basic functions of Twitter4J in Scala.<br/>

## Install

Add the following dependencies to build.sbt.

```
resolvers += "bintray" at "http://dl.bintray.com/shinsuke-abe/maven"

libraryDependencies += "com.github.Shinsuke-Abe" %% "twitter4s" % "2.1.0"
```

## About Build environment

Twitter4S is built in following environments.

* Scala 2.10.3
* sbt 0.13.1
* Twitter4J 3.0.5

Note: Built on latest stable library at January 18,2014.

## Dependencies with other libraries

Twitter4S has no dependencies without Twitter4J.

## How to use

Twitter4S, there are available two ways of writing that use the DSL (Twitter4S unique features) and compliant Twitter4J way.<br/>
DSL is not implemented all of Twiiter4J functions yet. Twitter4S implements frequency used functions only. 

### Compliant Twitter4J way

```
import twitter4s._ // You must import for implicit type converion.

val twitter = Twitter()
twitter.setOAuthConsumer(ConsumerKey("consumerKey", "consumerSecret"))
twitter.setOAuthAccessToken(AccessToken("accessToken", "tokenSecret"))

twitter.(API Methods...) (It have one-to-one correspondence between Twitter4S and Twitter4J.)
```

### DSL

DSL provides StringContext and some methods.<br/>

```
import twitter4s.dsl._

implicit val consumerKey = ConsumerKey("consumerKey", "consumerSecret")

withToken(AccessToken("accessToken", "tokenSecret") { implicit twitter =>
  tweet"test tweet" udpate
}
```

Please notice that operate for user authorized by AccessToken with withToken argument.<br/>

#### StringContext provided by DSL

StringContext that implementation is as follows.

```
user"screen name"
user"id:user id"

list"List Slug"
list"id:List ID"

tweet"Tweet"

message"Content of DM"
```

#### Ability to provide DSL

```
// method for Tweet.
tweet"test" update // tweet 'test'

// methods for User.
get(user"USER-A") // Get infomation of USER-A
follow(user"USER-A") // Follow USER-A
unfollow(user"USER-A") // Unfollow USER-A
block(user"USER-A") // Block USER-A

// methods for List.
get(list"LIST-A") // Get infomation of USER-A
add(user"USER-A") to list"LIST-A" // Add USER-A to LIST-A
remove(user"USER-A") from list"LIST-A" // Remove USER-A from LIST-A

// methods for DM.
send(message"TEST") to user"USER-A" // Send DirectMessage 'test' to USER-A.
```

#### About function addition for DSL

New functions will be added for new version.<br/>
If you want any other DSL, add issue or send mention to @mao_instantlife on Twitter please.