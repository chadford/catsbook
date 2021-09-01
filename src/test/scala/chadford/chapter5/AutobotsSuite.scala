package chadford.chapter5

import weaver._

object AutobotsSuite extends FunSuite {
  import Autobots._

  test(
    "5.4 Exercise: Monads: Transform and Roll Out - Autobots: tacticalReport returns that a special move is not possible"
  ) {
    expect(
      tacticalReport(
        "Jazz",
        "Bumblebee"
      ) == "Jazz and Bumblebee cannot perform the special move together"
    )
  }

  test(
    "5.4 Exercise: Monads: Transform and Roll Out - Autobots: tacticalReport returns that a special move is possible"
  ) {
    expect(
      tacticalReport(
        "Bumblebee",
        "Hot Rod"
      ) == "Bumblebee and Hot Rod can perform the special move together"
    )
  }

  test(
    "5.4 Exercise: Monads: Transform and Roll Out - Autobots: tacticalReport returns an error message"
  ) {
    expect(tacticalReport("Jazz", "Ironhide") == "Error: Ironhide does not exist")
  }
}
