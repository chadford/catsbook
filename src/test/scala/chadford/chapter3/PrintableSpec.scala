package chadford.chapter3

import weaver._

object PrintableSpec extends FunSuite {
  import PrintableInstances._
  import PrintableSyntax._

  test("Exercise 3.6.1.1 - Printable: contramap to create a Printable") {
    implicit val longPrintable = printableInt.contramap((a: Long) => a.toInt)

    expect(100L.format == "100")
  }

  test("Exercise 3.6.1.1 - Printable: Box.format converts a boolean to a string") {
    expect(Box(true).format == "yes")
    expect(Box(false).format == "no")
  }
}
