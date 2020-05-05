package chadford.chapter3

trait Codec[A] {
  self =>
  def encode(value: A): String
  def decode(value: String): A

  def imap[B](dec: A => B, enc: B => A): Codec[B] =
    new Codec[B] {
      override def encode(value: B): String = self.encode(enc(value))
      override def decode(value: String): B = dec(self.decode(value))
    }
}

object Codec {
  case class Box[A](value: A)

  implicit val codecString = new Codec[String] {
    override def encode(value: String): String = value
    override def decode(value: String): String = value
  }

  implicit val codecDouble = codecString.imap[Double](_.toDouble, _.toString)

  implicit def codecBox[A](implicit c: Codec[A]) = c.imap[Box[A]](Box(_), _.value)

  def encode[A](value: A)(implicit c: Codec[A]): String = c.encode(value)

  def decode[A](value: String)(implicit c: Codec[A]): A = c.decode(value)
}
