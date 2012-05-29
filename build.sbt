name := "twitter4s"

version := "1.0"

scalaVersion := "2.9.1"

libraryDependencies ++= Seq(
	"org.twitter4j" % "twitter4j-core" % "[2.2,)",
	"org.specs2" %% "specs2" % "1.8.2" % "test",
	"org.specs2" %% "specs2-scalaz-core" % "6.0.1" % "test",
	"junit" % "junit" % "4.9" % "test"
)

resolvers += "twitter4j.org Repository" at "http://twitter4j.org/maven2"

resolvers += "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots"
