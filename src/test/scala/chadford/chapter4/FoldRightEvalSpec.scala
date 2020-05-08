package chadford.chapter4

import munit.FunSuite

class FoldRightEvalSpec extends FunSuite {

  test("4.6.5 Exercise: Safer Folding using Eval") {
    test("FoldRightEval") {
      test("foldRight") {
        test("should evaluate foldRight in a stack safe manner") {
          val list = 1 to 1000000 toList

          assert(FoldRightEval.foldRight(list, 0)(_ + _) == 1784293664)
        }
      }
    }
  }
}
