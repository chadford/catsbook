package chadford.chapter5
import cats.data.EitherT
import cats.implicits._

import scala.concurrent.Await
import scala.concurrent.Future
import scala.concurrent.duration._

object Autobots {
  import scala.concurrent.ExecutionContext.Implicits.global

  type Response[A] = EitherT[Future, String, A]

  val powerLevels = Map(
    "Jazz"      -> 6,
    "Bumblebee" -> 8,
    "Hot Rod"   -> 10
  )

  def getPowerLevel(autobot: String): Response[Int] =
    powerLevels.get(autobot) match {
      case None        => EitherT.left(Future(s"$autobot does not exist"))
      case Some(value) => EitherT.right(Future(value))
    }

  def canSpecialMove(ally1: String, ally2: String): Response[Boolean] =
    for {
      a1 <- getPowerLevel(ally1)
      a2 <- getPowerLevel(ally2)
    } yield (a1 + a2) > 15

  def tacticalReport(ally1: String, ally2: String): String = {
    val result = canSpecialMove(ally1, ally2)

    Await.result(result.value, 1.second) match {
      case Left(err)    => s"Error: $err"
      case Right(true)  => s"$ally1 and $ally2 can perform the special move together"
      case Right(false) => s"$ally1 and $ally2 cannot perform the special move together"
    }
  }
}
