package chadford

import org.scalatest.funspec.AnyFunSpec

class Chapter1Spec extends AnyFunSpec {

  describe("Exercise 1.3") {
    import PrintableInstances._

    describe("Printable") {
      describe("format") {
        it("should format an Int as a String") {
          assert(Printable.format(10) == "10")
        }

        it("should format an String as a String") {
          assert(Printable.format("10") == "10")
        }

        it("should format a Cat to String") {
          val azrael = Cat("Azrael", 10, "orange")

          assert(Printable.format(azrael) == "Azrael is a 10 year old orange cat")
        }
      }

      describe("print") {
        it("should print an Int") {
          Printable.print(10)
        }

        it("should fprint a String") {
          Printable.print("10")
        }

        it("should format a Cat to String") {
          val azrael = Cat("Azrael", 10, "orange")

          Printable.print(azrael)
        }
      }
    }

    describe("PrintableSyntax") {
      import PrintableSyntax._

      describe("format") {
        it("should format an Int as a String") {
          assert(10.format == "10")
        }

        it("should format an String as a String") {
          assert("10".format() == "10")
        }

        it("should format a Cat to String") {
          val azrael = Cat("Azrael", 10, "orange")

          assert(azrael.format == "Azrael is a 10 year old orange cat")
        }
      }

      describe("print") {
        it("should print an Int") {
          10.print
        }

        it("should fprint a String") {
          "10".print
        }

        it("should format a Cat to String") {
          val azrael = Cat("Azrael", 10, "orange")

          azrael.print
        }
      }
    }
  }

  describe("Exercise 1.4") {
    describe("Show") {
      import cats._
      import cats.implicits._

      implicit val showCat: Show[Cat] = Show.show { cat =>
        val name  = cat.name.show
        val age   = cat.age.show
        val color = cat.color.show

        s"$name is a $age year old $color cat"
      }

      it("should format a Cat to String") {
        val azrael = Cat("Azrael", 10, "orange")

        assert(azrael.show == "Azrael is a 10 year old orange cat")
      }
    }
  }
}
