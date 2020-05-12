package chadford.chapter4

import org.scalatest.funspec.AnyFunSpec

class DbReaderSpec extends AnyFunSpec {

  describe("4.8.3 Exercise: Hacking on Readers") {
    describe("DbReader") {
      describe("checkLogin") {
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
        it("should check and return true") {

          assert(checkLogin(1, "zerocool").run(db))
        }

        it("should check and return false") {
          assert(checkLogin(4, "davinci").run(db) == false)
        }
      }
    }
  }
}
