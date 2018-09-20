package fix

import scala.meta._
import scalafix._
import scalafix.testkit._

class NoInferTest
  extends SemanticRuleSuite(
    new TestkitProperties(
      Classpath(AbsolutePath(BuildInfo.inputClassdirectory)),
      List(AbsolutePath(BuildInfo.inputSourceroot)),
      List(AbsolutePath(BuildInfo.outputSourceroot)),
      AbsolutePath(BuildInfo.inputBaseDirectory),
      "2.12.6",
      Nil
    )
  ) {
  runAllTests()
}
