package chadford.chapter3

import cats.syntax.all._

import weaver._

object TreeSuite extends FunSuite {
  import Tree._

  test("Exercise 3.5.4 - Tree Functor: should map a Branch from Branch[Int] to Branch[String]") {
    val branch1 = branch(branch(leaf(1), leaf(2)), branch(leaf(3), branch(leaf(4), leaf(5))))

    expect(
      branch1.map(_.toString()) == branch(
        branch(leaf("1"), leaf("2")),
        branch(leaf("3"), branch(leaf("4"), leaf("5")))
      )
    )
  }

  test("Exercise 3.5.4 - Tree Functor: should map a Leaf from Leaf[Int] to Leaf[String]") {
    expect(leaf(10).map(_.toString()) == Leaf("10"))
  }
}
