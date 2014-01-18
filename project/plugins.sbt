resolvers += Resolver.url(
  "sbt-plugin-releases",
  new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/")
)(Resolver.ivyStylePatterns)

resolvers += Resolver.url(
  "bintray-sbt-plugin-releases",
  url("http://dl.bintray.com/content/sbt/sbt-plugin-releases"))(
    Resolver.ivyStylePatterns)

addSbtPlugin("com.github.gseitz" % "sbt-release" % "0.8")

addSbtPlugin("me.lessis" % "bintray-sbt" % "0.1.1")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.5.1")

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.2.0")
