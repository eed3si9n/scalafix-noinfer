lazy val V = _root_.scalafix.Versions
lazy val scalafixVersion = V.version
// Use a scala version supported by scalafix.
ThisBuild / scalaVersion := V.scala212

ThisBuild / organization := "com.eed3si9n.fix"
ThisBuild / organizationName := "eed3si9n"
ThisBuild / organizationHomepage := Some(url("http://eed3si9n.com/"))
ThisBuild / homepage := Some(url("https://github.com/eed3si9n/scalafix-noinfer"))
ThisBuild / scmInfo := Some(ScmInfo(url("https://github.com/eed3si9n/scalafix-noinfer"), "git@github.com:eed3si9n/scalafix-noinfer.git"))
ThisBuild / developers := List(
  Developer("eed3si9n", "Eugene Yokota", "@eed3si9n", url("https://github.com/eed3si9n"))
)
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / description := "A Scalafix rule to suppress specific type inference."
ThisBuild / licenses := Seq("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))

lazy val root = (project in file("."))
  .aggregate(rules, tests)
  .settings(
    name := "scalafix-noinfer-root",
    publish / skip := true
  )

lazy val rules = project
  .settings(
    name := "scalafix-noinfer",
    libraryDependencies += "ch.epfl.scala" %% "scalafix-core" % scalafixVersion
  )

lazy val input = project
  .settings(
    scalafixSourceroot := (Compile / sourceDirectory).value,
    addCompilerPlugin(scalafixSemanticdb),
    scalacOptions ++= List(
      "-Xlint",
      "-Yrangepos",
      "-Ywarn-unused-import",
      "-P:semanticdb:synthetics:on",
      s"-P:semanticdb:sourceroot:${baseDirectory.value}"),
    publish / skip := true
  )

lazy val output = project
  .settings(
    publish / skip := true
  )

lazy val tests = project
  .enablePlugins(BuildInfoPlugin)
  .dependsOn(input, rules)
  .settings(
    publish / skip := true,
    libraryDependencies += "ch.epfl.scala" % "scalafix-testkit" % scalafixVersion % Test cross CrossVersion.full,
    buildInfoPackage := "fix",
    buildInfoKeys := Seq[BuildInfoKey](
      "inputBaseDirectory" ->
        (input / baseDirectory).value,
      "inputSourceroot" ->
        (input / Compile / sourceDirectory).value,
      "outputSourceroot" ->
        (output / Compile / sourceDirectory).value,
      "inputClassdirectory" ->
        (input / Compile / classDirectory).value
    )
  )
