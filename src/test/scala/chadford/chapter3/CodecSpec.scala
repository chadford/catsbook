package chadford.chapter3

import org.scalatest.funspec.AnyFunSpec

class CodecSpec extends AnyFunSpec {

  describe("Exercise 3.6.2.1") {
    describe("Codec") {
      import Codec._

      it("should encode a Double to String") {
        assert(Codec.encode(1234.5) == "1234.5")
      }

      it("should decode a String to Double") {
        assert(Codec.decode[Double]("1234.5") == 1234.5)
      }

      it("should encode a Box[Double] to String") {
        assert(Codec.encode(Box(1234.5)) == "1234.5")
      }

      it("should decode a String to Box[Double]") {
        assert(Codec.decode[Box[Double]]("1234.5") == Box(1234.5))
      }
    }
  }
}
