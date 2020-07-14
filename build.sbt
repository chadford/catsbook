coverageMinimum := 100
coverageFailOnMinimum := true

scalafixDependencies in ThisBuild +=
  "com.github.liancheng" %% "organize-imports" % "0.2.1"

val format = taskKey[Unit]("Format files using scalafmt and scalafix")

val CatsEffectVersion = "2.1.4"
val CatsVersion       = "2.1.1"
val LogbackVersion    = "1.2.3"
val ScalaMockVersion  = "4.4.0"
val ScalaTestVersion  = "3.2.0"

lazy val root = (project in file("."))
  .settings(
    organization := "chadford",
    name := "chadford",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.3",
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % LogbackVersion,
      "org.scalamock" %% "scalamock"       % ScalaMockVersion % Test,
      "org.scalatest" %% "scalatest"       % ScalaTestVersion % Test,
      "org.typelevel" %% "cats-core"       % CatsVersion,
      "org.typelevel" %% "cats-effect"     % CatsEffectVersion
    ),
    addCompilerPlugin("com.olegpy"   %% "better-monadic-for" % "0.3.1"),
    addCompilerPlugin("org.scalameta" % "semanticdb-scalac"  % "4.3.20" cross CrossVersion.full)
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
