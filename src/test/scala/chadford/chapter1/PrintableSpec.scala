package chadford.chapter1

import cats._
import cats.implicits._

import munit.FunSuite

class PrintableSpec extends FunSuite {

  test("Exercise 1.3") {
    import PrintableInstances._

    test("Printable") {
      test("format") {
        test("should format an Int as a String") {
          assert(Printable.format(10) == "10")
        }

        test("should format an String as a String") {
          assert(Printable.format("10") == "10")
        }

        test("should format a Cat to String") {
          val azrael = Cat("Azrael", 10, "orange")

          assert(Printable.format(azrael) == "Azrael is a 10 year old orange cat")
        }
      }

      test("print") {
        test("should print an Int") {
          Printable.print(10)
        }

        test("should fprint a String") {
          Printable.print("10")
        }

        test("should format a Cat to String") {
          val azrael = Cat("Azrael", 10, "orange")

          Printable.print(azrael)
        }
      }
    }

    test("PrintableSyntax") {
      import PrintableSyntax._

      test("format") {
        test("should format an Int as a String") {
          assert(10.format == "10")
        }

        test("should format an String as a String") {
          assert("10".format() == "10")
        }

        test("should format a Cat to String") {
          val azrael = Cat("Azrael", 10, "orange")

          assert(azrael.format == "Azrael is a 10 year old orange cat")
        }
      }

      test("print") {
        test("should print an Int") {
          10.print
        }

        test("should fprint a String") {
          "10".print
        }

        test("should format a Cat to String") {
          val azrael = Cat("Azrael", 10, "orange")

          azrael.print
        }
      }
    }
  }

  test("Exercise 1.4.6") {
    test("Show") {

      implicit val showCat: Show[Cat] = Show.show { cat =>
        val name  = cat.name.show
        val age   = cat.age.show
        val color = cat.color.show

        s"$name is a $age year old $color cat"
      }

      test("should format a Cat to String") {
        val azrael = Cat("Azrael", 10, "orange")

        assert(azrael.show == "Azrael is a 10 year old orange cat")
      }
    }
  }

  test("Exercise 1.5.5") {
    val convertToEqualizer = () // scalafix:ok ; shadow ScalaTest

    test("Eq") {
      implicit val eqCat: Eq[Cat] = Eq.instance[Cat] { (cat1, cat2) =>
        cat1.name === cat2.name && cat1.age === cat2.age && cat1.color === cat2.color
      }

      val cat1 = Cat("Garfield", 38, "orange and black")
      val cat2 = Cat("Healthcliff", 30, "orange and black")
      val cat3 = Cat("Garfield", 38, "orange and black")

      test("Cat") {
        test("should be equivalent") {
          assert(cat1 === cat3)
        }

        test("should not be equivalent") {
          assert(cat1 =!= cat2)
        }
      }

      test("Option[Cat]") {
        test("should not be equivalent") {
          assert(Option(cat1) === Option(cat3))
        }
        test("should be equivalent") {
          assert(Option(cat1) =!= Option(cat2))
        }
      }
    }
  }
}
