name := "twitter4s"

scalaVersion := "2.10.3"

organization := "com.github.Shinsuke-Abe"

scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked"
)

libraryDependencies ++= Seq(
  "org.twitter4j" % "twitter4j-core" % "3.0.5",
  "org.specs2" %% "specs2" % "2.3.7" % "test",
  "org.mockito" % "mockito-core" % "1.9.5" % "test"
)

parallelExecution in Test := false

resolvers += "twitter4j.org Repository" at "http://twitter4j.org/maven2"

resolvers += "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots"

seq(bintrayResolverSettings:_*)

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
