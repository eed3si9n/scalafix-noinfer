scalafix-noinfer
================

scalafix-noinfer is a Scalafix rule to warn about undesired type inference. It
offers a replacement for the `NoInfer` rule that was built into Scalafix 0.5.

See the blog post [stricter Scala with -Xlint, -Xfatal-warnings, and
Scalafix][blog post].

[blog post]: http://eed3si9n.com/stricter-scala-with-xlint-xfatal-warnings-and-scalafix

### Installation

In a project with Scalafix enabled (0.9.0 or later):

#### build.sbt

```scala
ThisBuild / scalafixDependencies +=
  "com.eed3si9n.fix" %% "scalafix-noinfer" % "0.1.0-M1"

lazy val root = (project in file(".")).
  settings(
    name := "hello",
    addCompilerPlugin(scalafixSemanticdb),
    scalacOptions ++= List(
      "-P:semanticdb:synthetics:on",
      "-Yrangepos",
    ),
  )
```

#### .scalafix.conf

```
rules = [
  NoInfer
]
```

### Usage

Run `scalafix` or `scalafix NoInfer` with sbt.

### Configuration

By default this rule forbids the inference of `scala.Any`, `scala.AnyVal`,
`java.io.Serializable`, `scala.Serializable`, and `scala.Product`. You can
customize this using `.scalafix.conf` as follows:

```
rules = [
  NoInfer
]
NoInfer.disabledTypes = [
  scala.Any
  scala.AnyVal
  scala.Serializable
  java.io.Serializable
  scala.Product
  scala.Predef.any2stringadd
]
```

### License

Apache-2.0
