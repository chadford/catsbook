package chadford.chapter4
import cats.implicits._

import weaver._

object PostCalculatorSuite extends FunSuite {
  import PostCalculator._

  test(
    "4.9.3 Exercise: Post-Order Calculator - PostCalculator: evalOne evaluates a single symbol expression"
  ) {
    expect(evalOne("42").runA(Nil).value == 42)
  }

  test(
    "4.9.3 Exercise: Post-Order Calculator - PostCalculator: evalAll evaluates a list of symbols"
  ) {
    val multistageProgram = evalAll(List("1", "2", "+", "3", "*"))

    expect(multistageProgram.runA(Nil).value == 9)
  }

  test(
    "4.9.3 Exercise: Post-Order Calculator - PostCalculator: evalInput evaluates an expression with + and *"
  ) {
    expect(evalInput("1 2 + 3 *") == 9)
  }

  test(
    "4.9.3 Exercise: Post-Order Calculator - PostCalculator: evalInput evaluates an expression with - and %"
  ) {
    expect(evalInput("19 9 - 3 %") == 3)
  }

  test(
    "4.9.3 Exercise: Post-Order Calculator - PostCalculator: evalInput throws an error if expression is invalid"
  ) {
    val result = Either.catchNonFatal {
      evalInput("19 - 3 ")
    }

    expect(
      result.swap.map(
        _.toString()
      ) == "java.lang.RuntimeException: Something bad happened!".asRight
    )
  }
}
