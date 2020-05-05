package chadford.chapter2

trait Semigroup[A] {
  def combine(x: A, y: A): A
}

trait Monoid[A] extends Semigroup[A] {
  def empty: A
}

object Monoid {
  def apply[A](implicit monoid: Monoid[A]) = monoid
}

object BooleanMonoid {
  implicit val andMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

  implicit val orMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = x || y
  }

  implicit val xandMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = x & y
  }

  implicit val xorMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = x | y
  }
}

object SetMonoid {
  def setUnionMonoid[A]: Monoid[Set[A]] =
    new Monoid[Set[A]] {
      override def empty: Set[A] = Set.empty[A]

      override def combine(x: Set[A], y: Set[A]): Set[A] = x ++ y
    }

  def setSymmetricDifference[A]: Monoid[Set[A]] =
    new Monoid[Set[A]] {
      override def empty: Set[A] = Set.empty[A]

      override def combine(x: Set[A], y: Set[A]): Set[A] = (x ++ y).diff(x.intersect(y))
    }
}

object SuperAdder {
  import cats.implicits._

  final case class Order(totalCost: Double, quantity: Double)

  implicit val orderMonoid = new cats.Monoid[Order] {
    def empty: Order = Order(0, 0)

    def combine(a: Order, b: Order) =
      Order(a.totalCost + b.totalCost, a.quantity + b.quantity)
  }

  def add[A: cats.Monoid](items: List[A]): A = items.foldLeft(cats.Monoid[A].empty)(_ |+| _)
}
