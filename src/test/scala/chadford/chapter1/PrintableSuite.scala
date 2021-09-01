package chadford.chapter1

import cats._
import cats.syntax.all._

import weaver._

object PrintableSuite extends FunSuite {
  import PrintableInstances._

  test("Exercise 1.3: Printable.format formats an Int as a String") {
    expect(Printable.format(10) == "10")
  }

  test("Exercise 1.3: Printable.format formats a String as a String") {
    expect(Printable.format("10") == "10")
  }

  test("Exercise 1.3: Printable.format formats a Cat to String") {
    val azrael = Cat("Azrael", 10, "orange")

    expect(Printable.format(azrael) == "Azrael is a 10 year old orange cat")
  }

  test("Exercise 1.3: Printable.print prints an Int") {
    Printable.print(10)
    expect(true)
  }

  test("Exercise 1.3: Printable.print fprints a String") {
    Printable.print("10")
    expect(true)
  }

  test("Exercise 1.3: Printable.print prints a formatted Cat") {
    val azrael = Cat("Azrael", 10, "orange")

    Printable.print(azrael)
    expect(true)
  }

  import PrintableSyntax._
  test("Exercise 1.3: PrintableSyntax: format formats an Int as a String") {
    assert(10.format == "10")
  }

  test("Exercise 1.3: PrintableSyntax: format formats a String as a String") {
    assert("10".format() == "10")
  }

  test("Exercise 1.3: PrintableSyntax: format formats a Cat to String") {
    val azrael = Cat("Azrael", 10, "orange")

    assert(azrael.format == "Azrael is a 10 year old orange cat")
  }

  test("Exercise 1.3: PrintableSyntax: print prints an Int") {
    10.print
    expect(true)
  }

  test("Exercise 1.3: PrintableSyntax: print prints a String") {
    "10".print
    expect(true)
  }

  test("Exercise 1.3: PrintableSyntax: print prints a Cat") {
    val azrael = Cat("Azrael", 10, "orange")

    azrael.print
    expect(true)
  }

  test("Exercise 1.4.6: Show: show formats a Cat as a String") {

    implicit val showCat: Show[Cat] = Show.show { cat =>
      val name  = cat.name.show
      val age   = cat.age.show
      val color = cat.color.show

      s"$name is a $age year old $color cat"
    }

    val azrael = Cat("Azrael", 10, "orange")

    expect(azrael.show == "Azrael is a 10 year old orange cat")
  }

  implicit val eqCat: Eq[Cat] = Eq.instance[Cat] { (cat1, cat2) =>
    cat1.name === cat2.name && cat1.age === cat2.age && cat1.color === cat2.color
  }

  val cat1 = Cat("Garfield", 38, "orange and black")
  val cat2 = Cat("Healthcliff", 30, "orange and black")
  val cat3 = Cat("Garfield", 38, "orange and black")

  test("Exercise 1.5.5: Eq - Cat: cats are equivalent") {
    assert(cat1 === cat3)
  }

  test("Exercise 1.5.5: Eq - Cat: cats are not equivalent") {
    assert(cat1 =!= cat2)
  }

  test("Exercise 1.5.5: Eq - Option[Cat]: option cat are equivalent") {
    assert(Option(cat1) === Option(cat3))
  }

  test("Exercise 1.5.5: Eq - Option[Cat]: option cat are not equivalent") {
    assert(Option(cat1) =!= Option(cat2))
  }
}
