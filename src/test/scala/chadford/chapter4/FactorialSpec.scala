package chadford.chapter4

import org.scalatest.funspec.AnyFunSpec
import scala.concurrent._
import scala.concurrent.duration._

class FactorialSpec extends AnyFunSpec {

  describe("4.7.3 Exercise: Show Your Work") {
    describe("factorial") {
      import Factorial.factorial
      import scala.concurrent.ExecutionContext.Implicits.global

      it("should calculate the factorial without interleaving the log statements") {
        val result = Await.result(
          Future.sequence(
            Vector(
              Future(factorial(3).run),
              Future(factorial(6).run)
            )
          ),
          30.seconds
        )

        assert(
          result == Vector(
            (Vector("fact 0 1", "fact 1 1", "fact 2 2", "fact 3 6"), 6),
            (
              Vector(
                "fact 0 1",
                "fact 1 1",
                "fact 2 2",
                "fact 3 6",
                "fact 4 24",
                "fact 5 120",
                "fact 6 720"
              ),
              720
            )
          )
        )
      }
    }
  }
}
