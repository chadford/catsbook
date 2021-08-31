package chadford.chapter4

import org.scalatest.funspec.AnyFunSpec
import weaver._

object FoldRightEvalSpec extends FunSuite {

  test(
    "4.6.5 Exercise: Safer Folding using Eval - FoldRightEval: foldRight should evaluate foldRight in a stack safe manner"
  ) {
    val list = 1 to 1000000 toList

    expect(FoldRightEval.foldRight(list, 0)(_ + _) == 1784293664)
  }
}
