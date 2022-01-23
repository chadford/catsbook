package chadford.chapter4
import cats.data.Reader
import cats.syntax.all._

final case class Db(
    usernames: Map[Int, String],
    passwords: Map[String, String]
)

object DbReader {
  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] =
    Reader(db => db.usernames.get(userId))

  def checkPassword(username: String, password: String): DbReader[Boolean] =
    Reader(db => db.passwords.get(username).map(_ == password).getOrElse(false))

  def checkLogin(userId: Int, password: String): DbReader[Boolean] =
    for {
      username <- findUsername(userId)
      check <-
        username
          .map(checkPassword(_, password))
          .getOrElse(false.pure[DbReader])
    } yield check
}
