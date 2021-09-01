package chadford.chapter4

import weaver._

object DbReaderSuite extends FunSuite {
  import DbReader.checkLogin

  val users = Map(
    1 -> "dade",
    2 -> "kate",
    3 -> "margo"
  )

  val passwords = Map(
    "dade"  -> "zerocool",
    "kate"  -> "acidburn",
    "margo" -> "secret"
  )

  val db = Db(users, passwords)
  test("4.8.3 Exercise: Hacking on Readers - DbReader: checkLogin should check and return true") {

    expect(checkLogin(1, "zerocool").run(db))
  }

  test("4.8.3 Exercise: Hacking on Readers - DbReader: checkLogin should check and return false") {
    expect(checkLogin(4, "davinci").run(db) == false)
  }
}
