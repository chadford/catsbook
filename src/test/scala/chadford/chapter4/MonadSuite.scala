package chadford.chapter4

import chadford.IdMonad
import weaver._

object MonadSuite extends FunSuite {
  import chadford.Monad
  import chadford.MonadInstance._

  test(
    "4.1.2 Exercise: Getting Func-y - Monad: map is defined using flatmap and pure"
  ) {
    expect(Monad[Option].map(Option(5))(a => a + 5) == Option(10))
  }

  test("4.1.2 Exercise: Getting Func-y - Monad: map works on Option.empty") {
    expect(Monad[Option].map(Option.empty[Int])(a => a + 5) == None)
  }

  test("4.3.1 Exercise: Monadic Secret Identities - IdMonad: pure returns the original value") {
    expect(IdMonad.pure(5) == 5)
  }

  test("4.3.1 Exercise: Monadic Secret Identities - IdMonad: flatMap returns the result of the function") {
    expect(IdMonad.flatMap(5)(_ + 5) == 10)
  }

  test("4.3.1 Exercise: Monadic Secret Identities - IdMonad: map returnss the result of the function") {
    expect(IdMonad.map(5)(_ + 5) == 10)
  }

  /*
  test("4.4.5 Exercise: What is Best?") {
   // open ended question on better error handling
  }
   */
}
