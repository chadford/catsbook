package chadford.chapter6

import cats.implicits._

import org.scalatest.funspec.AnyFunSpec

class MonadProductSpec extends AnyFunSpec {
  describe("6.3.1.1 Exercise: The Product of Monads") {
    describe("MonadProduct") {
      import MonadProduct._

      describe("product") {
        it("should create a product using flatMap") {
          assert(
            product(List(1, 2), List(3, 4)) ==
              List((1, 3), (1, 4), (2, 3), (2, 4))
          )
        }
      }
    }
  }
}
