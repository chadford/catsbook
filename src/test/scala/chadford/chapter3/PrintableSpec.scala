package chadford.chapter3

import org.scalatest.funspec.AnyFunSpec

class PrintableSpec extends AnyFunSpec {

  describe("Exercise 3.6.1.1") {
    import PrintableInstances._
    import PrintableSyntax._

    describe("Printable") {
      it("should contramap to create a Printable") {
        implicit val longPrintable = printableInt.contramap((a: Long) => a.toInt)

        assert(100L.format == "100")
      }

      it("should format a Box") {
        assert(Box(true).format == "yes")
        assert(Box(false).format == "no")
      }
    }
  }
}
