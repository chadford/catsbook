package chadford.chapter4

import org.scalatest.funspec.AnyFunSpec

class FoldRightEvalSpec extends AnyFunSpec {

  describe("4.6.5 Exercise: Safer Folding using Eval") {
    describe("FoldRightEval") {
      describe("foldRight") {
        it("should evaluate foldRight in a stack safe manner") {
          val list = 1 to 1000000 toList

          assert(FoldRightEval.foldRight(list, 0)(_ + _) == 1784293664)
        }
      }
    }
  }
}
