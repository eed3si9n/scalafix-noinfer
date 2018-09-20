/*
rule = "class:com.eed3si9n.fix.NoInfer"
NoInfer.disabledTypes = [
  "scala.Any",
  "scala.AnyVal",
  "scala.Serializable",
  "java.io.Serializable",
  "scala.Product",
  "scala.Predef.any2stringadd"
]
*/
package fix

object InferExample {
  List(1, 2).contains("foo") // assert: NoInfer.Any
  List(1, false) // assert: NoInfer.AnyVal
  List(Left(1), "foo") // assert: NoInfer.Serializable
  List[java.io.Serializable](Left(1), "foo") // ok

  List(Vector(1), List(1)) // assert: NoInfer.Serializable
  List(Left(1), Right(1))  // ok
  Some(1) + "foo" // assert: NoInfer.any2stringadd
}
