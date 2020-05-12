package chadford.chapter4

import chadford.IdMonad
import org.scalatest.funspec.AnyFunSpec

class MonadSpec extends AnyFunSpec {

  describe("4.1.2 Exercise: Getting Func-y") {
    import chadford.Monad
    import chadford.MonadInstance._

    describe("Monad") {
      it("should have a map defined using flatmap and pure") {
        assert(Monad[Option].map(Option(5))(a => a + 5) == Option(10))
      }

      it("should work on Option.empty") {
        assert(Monad[Option].map(Option.empty[Int])(a => a + 5) == None)
      }
    }
  }

  describe("4.3.1 Exercise: Monadic Secret Identities") {
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

  describe("4.4.5 Exercise: What is Best?") {
    //open ended question on better error handling
  }
}
