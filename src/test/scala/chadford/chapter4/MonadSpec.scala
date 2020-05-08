package chadford.chapter4

import chadford.IdMonad
import org.scalatest.funspec.AnyFunSpec

class MonadSpec extends AnyFunSpec {

  describe("Exercise 4.1.2") {
    import chadford.Monad
    import chadford.MonadInstance._

    describe("Monad") {
      it("should have a map defined using flatmap and pure") {
        assert(Monad[Option].map(Option(5))(a => a + 5) == Option(10))
      }
    }
  }

  describe("Exercise 4.3.1") {
    describe("IdMonad") {
      it("should work with pure") {
        assert(IdMonad.pure(5) == 5)
      }

      it("should work with flatMap") {
        assert(IdMonad.flatMap(5)(_ + 5) == 10)
      }

      it("should work with map") {
        assert(IdMonad.map(5)(_ + 5) == 10)
      }
    }
  }

  describe("Exercise 4.4.5") {
    //open ended question on better error handling
  }

  describe("4.6.5 Exercise: Safer Folding using Eval") {
    describe("Eval") {
      describe("foldRight") {
        it("") {}
      }
    }
  }
}
