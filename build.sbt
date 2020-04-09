coverageMinimum := 100
coverageFailOnMinimum := true

scalafixDependencies in ThisBuild +=
  "com.nequissimus" %% "sort-imports" % "0.3.2"

val format = taskKey[Unit]("Format files using scalafmt and scalafix")

val CatsEffectVersion = "2.1.2"
val CatsVersion       = "2.1.1"
val LogbackVersion    = "1.2.3"
val ScalaMockVersion  = "4.4.0"
val ScalaTestVersion  = "3.1.1"

lazy val root = (project in file("."))
  .settings(
    organization := "chadford",
    name := "chadford",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % LogbackVersion,
      "org.scalamock"  %% "scalamock"      % ScalaMockVersion % Test,
      "org.scalatest"  %% "scalatest"      % ScalaTestVersion % Test,
      "org.typelevel"  %% "cats-core"      % CatsVersion,
      "org.typelevel"  %% "cats-effect"    % CatsEffectVersion
    ),
    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
    addCompilerPlugin(scalafixSemanticdb),
    format := {
      Command.process("scalafmt", state.value)
      Command.process("scalafmtSbt", state.value)
      Command.process("scalafix", state.value)
      Command.process("scalafix RemoveUnused", state.value)
      Command.process("test:scalafix", state.value)
      Command.process("test:scalafix RemoveUnused", state.value)
    }
  )

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
