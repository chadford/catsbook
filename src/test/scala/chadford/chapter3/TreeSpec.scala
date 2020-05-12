package chadford.chapter3

import cats.implicits._

import org.scalatest.funspec.AnyFunSpec

class TreeSpec extends AnyFunSpec {

  describe("Exercise 3.5.4") {
    describe("Tree Functor") {
      import Tree._

      it("should map a Branch from Branch[Int] to Branch[String]") {
        val branch1 = branch(branch(leaf(1), leaf(2)), branch(leaf(3), branch(leaf(4), leaf(5))))

        assert(
          branch1.map(_.toString()) == branch(
            branch(leaf("1"), leaf("2")),
            branch(leaf("3"), branch(leaf("4"), leaf("5")))
          )
        )
      }

      it("should map a Leaf from Leaf[Int] to Leaf[String]") {
        assert(leaf(10).map(_.toString()) == Leaf("10"))
      }
    }
  }
}
