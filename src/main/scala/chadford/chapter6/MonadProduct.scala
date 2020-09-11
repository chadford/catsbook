package chadford.chapter6

object MonadProduct {
  import cats.Monad
  import cats.implicits._

  def product[M[_]: Monad, A, B](x: M[A], y: M[B]): M[(A, B)] =
    for {
      a <- x
      b <- y
    } yield (a, b)
}
