package chadford

trait Monad[F[_]] {
  def pure[A](a: A): F[A]

  def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]

  def map[A, B](value: F[A])(func: A => B): F[B] = flatMap(value)(a => pure(func(a)))
}

object MonadInstance {
  implicit def monadOption[A] =
    new Monad[Option] {
      override def pure[A](a: A): Option[A] = Option(a)

      override def flatMap[A, B](value: Option[A])(func: A => Option[B]): Option[B] =
        value match {
          case Some(a) => func(a)
          case None    => Option.empty[B]
        }
    }
}

object Monad {
  def apply[F[_]](implicit monad: Monad[F]) = monad
}

object IdMonad {
  import cats.Id

  def pure[A](a: A): cats.Id[A] = a

  def flatMap[A, B](ia: Id[A])(f: A => Id[B]): Id[B] = f(ia)

  def map[A, B](ia: Id[A])(f: A => B): Id[B] = f(ia)
}
