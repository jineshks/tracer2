import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "Tracer"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
     "mysql" % "mysql-connector-java" % "5.0.8",
    javaCore,
    javaJdbc,
    javaEbean
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
