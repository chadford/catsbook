package chadford.chapter2

import cats.Show
import cats.effect._
import cats.implicits._

import org.scalacheck.Gen
import weaver._
import weaver.scalacheck.Checkers

object MonoidSpec extends SimpleIOSuite with Checkers {

  def identityLaw[A](x: A)(implicit m: Monoid[A]): Boolean = {
    (m.combine(x, m.empty) == x) && (m.combine(m.empty, x) == x)
  }

  def associativeLaw[A](x: A, y: A, z: A)(implicit m: Monoid[A]): Boolean = {
    m.combine(x, m.combine(y, z)) == m.combine(m.combine(x, y), z)
  }

  def testIdentityLaws[A: Show](values: List[A])(implicit m: Monoid[A]): IO[Expectations] = {
    forall(Gen.oneOf(values)) { x =>
      expect(identityLaw(x)) // s"Identity failed for $x")
    }
  }

  def testAssociativeLaws[A: Show](values: List[A])(implicit m: Monoid[A]): IO[Expectations] = {

    val product = for {
      x <- Gen.oneOf(values)
      y <- Gen.oneOf(values)
      z <- Gen.oneOf(values)
    } yield (x, y, z)

    forall(product) { case (x, y, z) =>
      expect(associativeLaw(x, y, z)) //s"Associativity failed for $x, $y, $z")
    }
  }

  val booleanValues = List(true, false)

  test("Exercise 2.3: BooleanMonoid: andBooleanMonoid has an identity") {
    import BooleanMonoid.andMonoid

    testIdentityLaws(booleanValues)
  }

  test("Exercise 2.3: BooleanMonoid: andBooleanMonoid has a combine that is associative") {
    import BooleanMonoid.andMonoid

    testAssociativeLaws(booleanValues)
  }

  test("Exercise 2.3: BooleanMonoid: orBooleanMonoid has an identity") {
    import BooleanMonoid.orMonoid

    testIdentityLaws(booleanValues)
  }

  test("Exercise 2.3: BooleanMonoid: orBooleanMonoid has a combine that is associative") {
    import BooleanMonoid.orMonoid

    testAssociativeLaws(booleanValues)
  }

  test("Exercise 2.3: BooleanMonoid: xandBooleanMonoid has an identity") {
    import BooleanMonoid.xandMonoid

    testIdentityLaws(booleanValues)
  }

  test("Exercise 2.3: BooleanMonoid: xandBooleanMonoid has a combine that is associative") {
    import BooleanMonoid.xandMonoid

    testAssociativeLaws(booleanValues)
  }

  test("Exercise 2.3: BooleanMonoid: xorBooleanMonoid has an identity") {
    import BooleanMonoid.xorMonoid

    testIdentityLaws(booleanValues)
  }

  test("Exercise 2.3: BooleanMonoid: xorBooleanMonoid has a combine that is associative") {
    import BooleanMonoid.xorMonoid

    testAssociativeLaws(booleanValues)
  }

  val set1 = Set(1, 2, 3)
  val set2 = Set(4, 5, 6)
  val set3 = Set(3, 4)

  val setValues = List(set1, set2, set3)

  test("Exercise 2.4: SetUnionMonoid: intSetUnionMonoid has an identity") {
    implicit val intSetUnionMonoid = SetMonoid.setUnionMonoid[Int]

    testIdentityLaws(setValues)
  }

  test("Exercise 2.4: SetUnionMonoid: intSetUnionMonoid has a combine that is associative") {
    implicit val intSetUnionMonoid = SetMonoid.setUnionMonoid[Int]

    testAssociativeLaws(setValues)
  }

  test("Exercise 2.4: IntSetSymmetricDifferenceMonoid: setSymmetricDifference has an identity") {
    implicit val intSetSymmetricDifferenceMonoid = SetMonoid.setSymmetricDifference[Int]

    testIdentityLaws(setValues)
  }

  test(
    "Exercise 2.4: IntSetSymmetricDifferenceMonoid: setSymmetricDifference has a combine that is associative"
  ) {
    implicit val intSetSymmetricDifferenceMonoid = SetMonoid.setSymmetricDifference[Int]

    testAssociativeLaws(setValues)
  }

  import SuperAdder._

  pureTest("Exercise 2.5.4: SuperAdder: add sums a list of Int") {
    val ints = List(1, 2, 4, 6)

    expect(add(ints) == 13)
  }

  pureTest("Exercise 2.5.4: SuperAdder: add sums a list of Option[Int]") {
    val optionInts = List(Option(1), Option(2), Option(4), Option.empty, Option(6))

    expect(add(optionInts) == Option(13))
  }

  pureTest("Exercise 2.5.4: SuperAdder: add sums a list of Order") {
    val orders = List[Order](
      Order(10.0, 1.0),
      Order(15.3, 1.5),
      Order(20, 5.0)
    )

    expect(add(orders) == Order(45.3, 7.5))
  }
}
