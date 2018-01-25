resolvers += "Jenkins repo" at "http://repo.jenkins-ci.org/public/"

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.22")
addSbtPlugin("com.dwijnand" % "sbt-dynver" % "2.0.0")
addSbtPlugin("ch.epfl.scala" % "sbt-release-early" % "2.0.0")
addSbtPlugin("ohnosequences" % "sbt-github-release" % "0.5.1")
