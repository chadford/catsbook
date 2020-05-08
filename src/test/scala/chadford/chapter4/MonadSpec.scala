package chadford.chapter4

import chadford.IdMonad
import munit.FunSuite

class MonadSpec extends FunSuite {

  test("Exercise 4.1.2") {
    import chadford.Monad
    import chadford.MonadInstance._

    test("Monad") {
      test("should have a map defined using flatmap and pure") {
        assert(Monad[Option].map(Option(5))(a => a + 5) == Option(10))
      }

      test("should work on Option.empty") {
        assert(Monad[Option].map(Option.empty[Int])(a => a + 5) == None)
      }
    }
  }

  test("Exercise 4.3.1") {
    test("IdMonad") {
      test("should work with pure") {
        assert(IdMonad.pure(5) == 5)
      }

      test("should work with flatMap") {
        assert(IdMonad.flatMap(5)(_ + 5) == 10)
      }

      test("should work with map") {
        assert(IdMonad.map(5)(_ + 5) == 10)
      }
    }
  }

  test("Exercise 4.4.5") {
    //open ended question on better error handling
  }

  test("4.6.5 Exercise: Safer Folding using Eval") {
    test("Eval") {
      test("foldRight") {
        test("") {}
      }
    }
  }
}
