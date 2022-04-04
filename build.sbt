coverageMinimum       := 100
coverageFailOnMinimum := true

ThisBuild / scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.6.0"
ThisBuild / semanticdbEnabled                              := true
ThisBuild / semanticdbVersion                              := scalafixSemanticdb.revision

val format = taskKey[Unit]("Format files using scalafmt and scalafix")

val catsEffect = "3.3.10"
val cats       = "2.7.0"
val logback    = "1.2.11"
val scalaMock  = "5.2.0"
val weaver     = "0.7.11"

lazy val root = (project in file("."))
  .settings(
    organization := "chadford",
    name         := "chadford",
    version      := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.8",
    libraryDependencies ++= Seq(
      "ch.qos.logback"       % "logback-classic"   % logback,
      "org.scalamock"       %% "scalamock"         % scalaMock % Test,
      "org.typelevel"       %% "cats-core"         % cats,
      "org.typelevel"       %% "cats-effect"       % catsEffect,
      "com.disneystreaming" %% "weaver-cats"       % weaver    % Test,
      "com.disneystreaming" %% "weaver-scalacheck" % weaver    % Test
    ),
    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
    testFrameworks += new TestFramework("weaver.framework.CatsEffect")
  )

addCommandAlias("build", ";clean ;assembly")
addCommandAlias("cover", ";clean ;coverageOn ;test ;coverageReport ;coverageOff")
addCommandAlias("format", ";scalafmtAll ;scalafmtSbt ;scalafix ;test:scalafix")

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Ywarn-unused",
  "-Yrangepos"
)
