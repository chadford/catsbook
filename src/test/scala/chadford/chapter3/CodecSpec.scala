package chadford.chapter3

import weaver._

object CodecSpec extends FunSuite {

  import Codec._

  test("Exercise 3.6.2.1 - Codec: encode encodes a Double to String") {
    expect(Codec.encode(1234.5) == "1234.5")
  }

  test("Exercise 3.6.2.1 - Codec: decode decodes a String to Double") {
    expect(Codec.decode[Double]("1234.5") == 1234.5)
  }

  test("Exercise 3.6.2.1 - Codec: encode encodes a Box[Double] to String") {
    expect(Codec.encode(Box(1234.5)) == "1234.5")
  }

  test("Exercise 3.6.2.1 - Codec: decode decodes a String to Box[Double]") {
    expect(Codec.decode[Box[Double]]("1234.5") == Box(1234.5))
  }
}
