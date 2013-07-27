name := "twitter4s"

version := "1.0"

scalaVersion := "2.10.1"

libraryDependencies ++= Seq(
	"org.twitter4j" % "twitter4j-core" % "[3.0,)",
	"org.specs2" %% "specs2" % "1.14" % "test",
	"org.specs2" %% "specs2-scalaz-core" % "7.0.0" % "test",
	"junit" % "junit" % "4.9" % "test"
)

resolvers += "twitter4j.org Repository" at "http://twitter4j.org/maven2"

resolvers += "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots"
