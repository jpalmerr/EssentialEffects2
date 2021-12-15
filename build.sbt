ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.0"

lazy val root = (project in file("."))
  .settings(
    name := "EssentialEffects2"
  )

libraryDependencies += "org.typelevel" %% "cats-effect" % "3.3.0"
