package chadford.chapter3

import munit.FunSuite

class PrintableSpec extends FunSuite {
  test("Exercise 3.6.1.1") {
    import PrintableInstances._
    import PrintableSyntax._

    test("Printable") {
      test("should contramap to create a Printable") {
        implicit val longPrintable = printableInt.contramap((a: Long) => a.toInt)

        assert(100L.format == "100")
      }

      test("should format a Box") {
        assert(Box(true).format == "yes")
        assert(Box(false).format == "no")
      }
    }
  }
}
