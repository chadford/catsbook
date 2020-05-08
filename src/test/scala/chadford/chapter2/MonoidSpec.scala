package chadford.chapter2

import munit.FunSuite

class MonoidSpec extends FunSuite {

  def identityLaw[A](x: A)(implicit m: Monoid[A]): Boolean = {
    (m.combine(x, m.empty) == x) && (m.combine(m.empty, x) == x)
  }

  def associativeLaw[A](x: A, y: A, z: A)(implicit m: Monoid[A]): Boolean = {
    m.combine(x, m.combine(y, z)) == m.combine(m.combine(x, y), z)
  }

  def testIdentityLaws[A](values: List[A])(implicit m: Monoid[A]): Unit = {
    for {
      x <- values
    } {
      assert(identityLaw(x), s"Identity failed for $x")
    }
  }

  def testAssociativeLaws[A](values: List[A])(implicit m: Monoid[A]): Unit = {
    for {
      x <- values
      y <- values
      z <- values
    } {
      assert(associativeLaw(x, y, z), s"Associativity failed for $x, $y, $z")
    }
  }

  test("Exercise 2.3") {
    val booleanValues = List(true, false)

    test("BooleanMonoid") {
      test("andBooleanMonoid") {
        import BooleanMonoid.andMonoid

        test("should have an identity") {
          testIdentityLaws(booleanValues)
        }

        test("should have a combine that is associative") {
          testAssociativeLaws(booleanValues)
        }
      }

      test("orBooleanMonoid") {
        import BooleanMonoid.orMonoid

        test("should have an identity") {
          testIdentityLaws(booleanValues)
        }

        test("should have a combine that is associative") {
          testAssociativeLaws(booleanValues)
        }
      }
    }

    test("xandBooleanMonoid") {
      import BooleanMonoid.xandMonoid

      test("should have an identity") {
        testIdentityLaws(booleanValues)
      }

      test("should have a combine that is associative") {
        testAssociativeLaws(booleanValues)
      }
    }

    test("xorBooleanMonoid") {
      import BooleanMonoid.xorMonoid

      test("should have an identity") {
        testIdentityLaws(booleanValues)
      }

      test("should have a combine that is associative") {
        testAssociativeLaws(booleanValues)
      }
    }
  }

  test("Exercise 2.4") {
    test("SetUnionMonoid") {
      test("IntSetUnionMonoid") {
        implicit val setIntMonoid = SetMonoid.setUnionMonoid[Int]

        val set1 = Set(1, 2, 3)
        val set2 = Set(4, 5, 6)
        val set3 = Set(3, 4)

        val setValues = List(set1, set2, set3)

        test("should have an identity") {
          testIdentityLaws(setValues)
        }

        test("should have a combine that is associative") {
          testAssociativeLaws(setValues)
        }
      }

      test("IntSetSymmetricDifferenceMonoid") {
        implicit val setIntMonoid = SetMonoid.setSymmetricDifference[Int]

        val set1 = Set(1, 2, 3)
        val set2 = Set(4, 5, 6)
        val set3 = Set(3, 4)

        val setValues = List(set1, set2, set3)

        test("should have an identity") {
          testIdentityLaws(setValues)
        }

        test("should have a combine that is associative") {
          testAssociativeLaws(setValues)
        }
      }
    }
  }

  test("Exercise 2.5.4") {
    import cats.implicits._
    import SuperAdder._

    test("SuperAdder") {
      test("should add a list of Int") {
        val ints = List(1, 2, 4, 6)

        assert(add(ints) == 13)
      }

      test("should a list of Option[Int]") {
        val optionInts = List(Option(1), Option(2), Option(4), Option.empty, Option(6))

        assert(add(optionInts) == Option(13))
      }

      test("should add a list of Order") {
        val orders = List[Order](
          Order(10.0, 1.0),
          Order(15.3, 1.5),
          Order(20, 5.0)
        )

        assert(add(orders) == Order(45.3, 7.5))
      }
    }
  }
}
