package chadford.chapter3

import munit.FunSuite

class CodecSpec extends FunSuite {
  test("Exercise 3.6.2.1") {
    test("Codec") {
      import Codec._

      test("should encode a Double to String") {
        assert(Codec.encode(1234.5) == "1234.5")
      }

      test("should decode a String to Double") {
        assert(Codec.decode[Double]("1234.5") == 1234.5)
      }

      test("should encode a Box[Double] to String") {
        assert(Codec.encode(Box(1234.5)) == "1234.5")
      }

      test("should decode a String to Box[Double]") {
        assert(Codec.decode[Box[Double]]("1234.5") == Box(1234.5))
      }
    }
  }
}
