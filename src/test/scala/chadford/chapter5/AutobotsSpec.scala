package chadford.chapter5

import org.scalatest.funspec.AnyFunSpec

class AutobotsSpec extends AnyFunSpec {
  import Autobots._

  describe("5.4 Exercise: Monads: Transform and Roll Out") {
    describe("Autobots") {
      describe("tacticalReport") {
        it("should return special move is not possible") {
          assert(
            tacticalReport(
              "Jazz",
              "Bumblebee"
            ) == "Jazz and Bumblebee cannot perform the special move together"
          )
        }

        it("should return special move is possible") {
          assert(
            tacticalReport(
              "Bumblebee",
              "Hot Rod"
            ) == "Bumblebee and Hot Rod can perform the special move together"
          )
        }

        it("should return an error message") {
          assert(tacticalReport("Jazz", "Ironhide") == "Error: Ironhide does not exist")
        }
      }
    }
  }
}
