name := "twitter4s"

version := "2.1.0"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
	"org.twitter4j" % "twitter4j-core" % "3.0.5",
	"org.specs2" %% "specs2" % "2.3.7" % "test",
	"junit" % "junit" % "4.9" % "test",
	"org.mockito" % "mockito-core" % "1.9.5" % "test"
)

parallelExecution in Test := false

resolvers += "twitter4j.org Repository" at "http://twitter4j.org/maven2"

resolvers += "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots"
