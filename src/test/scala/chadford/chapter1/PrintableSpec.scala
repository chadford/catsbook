package chadford.chapter1

import cats._
import cats.implicits._

import org.scalatest.funspec.AnyFunSpec

class PrintableSpec extends AnyFunSpec {

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

  describe("Exercise 1.4.6") {
    describe("Show") {

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

  describe("Exercise 1.5.5") {
    val convertToEqualizer = () // scalafix:ok ; shadow ScalaTest

    describe("Eq") {
      implicit val eqCat: Eq[Cat] = Eq.instance[Cat] { (cat1, cat2) =>
        cat1.name === cat2.name && cat1.age === cat2.age && cat1.color === cat2.color
      }

      val cat1 = Cat("Garfield", 38, "orange and black")
      val cat2 = Cat("Healthcliff", 30, "orange and black")
      val cat3 = Cat("Garfield", 38, "orange and black")

      describe("Cat") {
        it("should be equivalent") {
          assert(cat1 === cat3)
        }

        it("should not be equivalent") {
          assert(cat1 =!= cat2)
        }
      }

      describe("Option[Cat]") {
        it("should not be equivalent") {
          assert(Option(cat1) === Option(cat3))
        }
        it("should be equivalent") {
          assert(Option(cat1) =!= Option(cat2))
        }
      }
    }
  }
}
