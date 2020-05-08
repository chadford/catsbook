package chadford.chapter4

import cats._
import cats.data.Writer
import cats.implicits._

object Factorial {
  def slowly[A](body: => A) =
    try body
    finally Thread.sleep(100)

  type Logged[A] = Writer[Vector[String], A]

  def factorial(n: Int): Logged[Int] = {
    for {
      ans <-
        if (n == 0)
          1.pure[Logged]
        else
          slowly(factorial(n - 1).map(_ * n))
      _ <- Vector(s"fact $n $ans").tell
    } yield ans
  }
}
