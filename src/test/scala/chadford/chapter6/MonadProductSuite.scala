package chadford.chapter6

import cats.implicits._

import weaver._

object MonadProductSpec extends FunSuite {
  import MonadProduct._

  test(
    "6.3.1.1 Exercise: The Product of Monads - MonadProduct: product creates a product using flatMap"
  ) {
    expect(
      product(List(1, 2), List(3, 4)) == List((1, 3), (1, 4), (2, 3), (2, 4))
    )
  }
}
