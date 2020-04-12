package chadford.chapter1

trait Printable[A] {
  def format(a: A): String
}

final case class Cat(name: String, age: Int, color: String)

object PrintableInstances {
  implicit val printableString = new Printable[String] {
    override def format(a: String): String = a
  }
  implicit val printableInt = new Printable[Int] {
    override def format(a: Int): String = String.valueOf(a)
  }

  implicit val printableCat = new Printable[Cat] {
    override def format(cat: Cat): String = {
      val name  = Printable.format(cat.name)
      val age   = Printable.format(cat.age)
      val color = Printable.format(cat.color)
      s"$name is a $age year old $color cat"
    }
  }
}

object PrintableSyntax {
  implicit class PrintableOps[A](a: A) {
    def format(implicit printable: Printable[A]) = printable.format(a)
    def print(implicit printable: Printable[A])  = println(a.format)
  }
}

object Printable {
  def format[A](a: A)(implicit printable: Printable[A]): String = printable.format(a)
  def print[A](a: A)(implicit printable: Printable[A]): Unit    = println(format(a))
}
