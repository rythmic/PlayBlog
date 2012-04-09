import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

  val appName = "blog"
  val appVersion = "0.1"

  val appDependencies = Seq(
    "eu.henkelmann" % "actuarius_2.9.1" % "0.2.3" // Markdown parser
  )

  val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
    // Add your own project settings here
    resolvers ++= Seq(
      "Christophs Maven Repo" at "http://maven.henkelmann.eu/"
    )
  )

}
