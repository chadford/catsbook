package chadford.chapter4

import cats.syntax.all._

import weaver._

object TreeMonadSuite extends FunSuite {
  import TreeMonad._

  test(
    "4.10.1 Exercise: Branching out Further with Monads - TreeMonad: pure creates a pure tree"
  ) {
    expect(10.pure[Tree] == leaf(10))
  }

  test(
    "4.10.1 Exercise: Branching out Further with Monads - TreeMonad: flatMap evaluates a list of symbols"
  ) {
    val tree = branch(leaf(100), leaf(200)).flatMap(x => branch(leaf(x - 1), leaf(x + 1)))

    expect(tree == branch(branch(leaf(99), leaf(101)), branch(leaf(199), leaf(201))))
  }

  test(
    "4.10.1 Exercise: Branching out Further with Monads - TreeMonad: tailRecM runs tail recursion and returns right"
  ) {
    val result =
      20.tailRecM[Tree, String](a => if (a == 20) leaf(Right(a.toString())) else leaf(Left(a + 1)))

    expect(result == leaf("20"))
  }

  test(
    "4.10.1 Exercise: Branching out Further with Monads - TreeMonad: tailaRecM runs tail recursion and returns left"
  ) {
    val result =
      10.tailRecM[Tree, String](a => if (a == 20) leaf(Right(a.toString())) else leaf(Left(a + 1)))

    expect(result == leaf("20"))
  }
}
