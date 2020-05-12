package chadford.chapter4

import org.scalatest.funspec.AnyFunSpec

class PostCalculatorSpec extends AnyFunSpec {

  describe("4.9.3 Exercise: Post-Order Calculator") {
    describe("PostCalculator") {
      import PostCalculator._

      describe("evalOne") {
        it("should evaluate a single symbol expression") {
          assert(evalOne("42").runA(Nil).value == 42)
        }
      }

      describe("evalAll") {
        it("should evaluate a list of symbols") {
          val multistageProgram = evalAll(List("1", "2", "+", "3", "*"))

          assert(multistageProgram.runA(Nil).value == 9)
        }
      }

      describe("evalInput") {
        it("should evaluate an expression with + and *") {
          assert(evalInput("1 2 + 3 *") == 9)
        }

        it("should evaluate an expression with - and %") {
          assert(evalInput("19 9 - 3 %") == 3)
        }

        it("should throw an error if expression is invalid") {
          val caught = intercept[RuntimeException] {
            evalInput("19 - 3 ")
          }

          assert(caught.getMessage() == "Something bad happened!")
        }
      }
    }
  }
}
