package chadford.chapter4

import cats.implicits._

import org.scalatest.funspec.AnyFunSpec

class TreeMonadSpec extends AnyFunSpec {

  describe("4.10.1 Exercise: Branching out Further with Monads") {
    describe("TreeMonad") {
      import TreeMonad._

      describe("pure") {
        it("create a pure tree") {
          assert(10.pure[Tree] == leaf(10))
        }
      }

      describe("flatMap") {
        it("should evaluate a list of symbols") {
          val tree = branch(leaf(100), leaf(200)).flatMap(x => branch(leaf(x - 1), leaf(x + 1)))

          assert(tree == branch(branch(leaf(99), leaf(101)), branch(leaf(199), leaf(201))))
        }
      }

      describe("tailRecM") {
        it("should run tail recursion and return right") {
          val result =
            20.tailRecM[Tree, String](a =>
              if (a == 20) leaf(Right(a.toString())) else leaf(Left(a + 1))
            )

          assert(result == leaf("20"))
        }

        it("should run tail recursion and return left") {
          val result =
            10.tailRecM[Tree, String](a =>
              if (a == 20) leaf(Right(a.toString())) else leaf(Left(a + 1))
            )

          assert(result == leaf("20"))
        }
      }
    }
  }
}
