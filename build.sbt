name := "twitter4s"

version := "1.0"

scalaVersion := "2.9.2"

libraryDependencies ++= Seq(
	"org.twitter4j" % "twitter4j-core" % "[2.2,)"
)

resolvers += "twitter4j.org Repository" at "http://twitter4j.org/maven2"