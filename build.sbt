coverageMinimum := 100
coverageFailOnMinimum := true

ThisBuild / scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.5.0"
ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision

val format = taskKey[Unit]("Format files using scalafmt and scalafix")

val CatsEffectVersion = "2.5.1"
val CatsVersion       = "2.6.1"
val LogbackVersion    = "1.2.3"
val ScalaMockVersion  = "5.1.0"
val ScalaTestVersion  = "3.2.9"

lazy val root = (project in file("."))
  .settings(
    organization := "chadford",
    name := "chadford",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.6",
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % LogbackVersion,
      "org.scalamock" %% "scalamock"       % ScalaMockVersion % Test,
      "org.scalatest" %% "scalatest"       % ScalaTestVersion % Test,
      "org.typelevel" %% "cats-core"       % CatsVersion,
      "org.typelevel" %% "cats-effect"     % CatsEffectVersion
    ),
    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")
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
