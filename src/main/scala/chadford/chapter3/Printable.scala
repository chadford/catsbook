package chadford.chapter3

trait Printable[A] {
  self =>
  def format(a: A): String
  def contramap[B](func: B => A): Printable[B] =
    new Printable[B] {
      def format(value: B): String = self.format(func(value))
    }
}

final case class Box[A](value: A)

object PrintableInstances {
  implicit val printableString = new Printable[String] {
    override def format(a: String): String = s"$a"
  }
  implicit val printableBoolean = new Printable[Boolean] {
    override def format(a: Boolean): String = if (a) "yes" else "no"
  }
  implicit val printableInt = new Printable[Int] {
    override def format(a: Int): String = String.valueOf(a)
  }
  implicit def printableBox[A](implicit printable: Printable[A]): Printable[Box[A]] =
    printable.contramap(box => box.value)
}

object PrintableSyntax {
  implicit class PrintableOps[A](a: A) {
    def format(implicit printable: Printable[A]) = printable.format(a)
  }
}
