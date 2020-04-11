package chadford

import cats._

import org.scalatest.funspec.AnyFunSpec

class Chapter3Spec extends AnyFunSpec {
  describe("Exercise 3.5.4") {
    describe("Tree Functor") {
      import Tree._

      it("should map a Branch from Branch[Int] to Branch[String]") {
        val branch1 = Branch(
          Branch(Leaf(1), Leaf(2)),
          Branch(Leaf(3), Branch(Leaf(4), Leaf(5)))
        )

        val result = Functor[Tree].map(branch1)((a: Int) => a.toString())

        assert(
          result == Branch(
            Branch(Leaf("1"), Leaf("2")),
            Branch(Leaf("3"), Branch(Leaf("4"), Leaf("5")))
          )
        )
      }

      it("should map a Leaf from Leaf[Int] to Leaf[String]") {
        val result = Functor[Tree].map(Leaf(10))((a: Int) => a.toString())

        assert(result == Leaf("10"))
      }
    }
  }
}
