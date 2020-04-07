package chadford

import org.scalatest.funspec.AnyFunSpec

class Chapter2Spec extends AnyFunSpec {
  import chadford.Monoid

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

  describe("Exercise 2.3") {
    val booleanValues = List(true, false)

    describe("BooleanMonoid") {
      describe("andBooleanMonoid") {
        import BooleanMonoid.andMonoid

        it("should have an identity") {
          testIdentityLaws(booleanValues)
        }

        it("should have a combine that is associative") {
          testAssociativeLaws(booleanValues)
        }
      }

      describe("orBooleanMonoid") {
        import BooleanMonoid.orMonoid

        it("should have an identity") {
          testIdentityLaws(booleanValues)
        }

        it("should have a combine that is associative") {
          testAssociativeLaws(booleanValues)
        }
      }
    }

    describe("xandBooleanMonoid") {
      import BooleanMonoid.xandMonoid

      it("should have an identity") {
        testIdentityLaws(booleanValues)
      }

      it("should have a combine that is associative") {
        testAssociativeLaws(booleanValues)
      }
    }

    describe("xorBooleanMonoid") {
      import BooleanMonoid.xorMonoid

      it("should have an identity") {
        testIdentityLaws(booleanValues)
      }

      it("should have a combine that is associative") {
        testAssociativeLaws(booleanValues)
      }
    }
  }

  describe("Exercise 2.4") {
    describe("SetUnionMonoid") {
      describe("IntSetUnionMonoid") {
        implicit val setIntMonoid = SetMonoid.setUnionMonoid[Int]

        val set1 = Set(1, 2, 3)
        val set2 = Set(4, 5, 6)
        val set3 = Set(3, 4)

        val setValues = List(set1, set2, set3)

        it("should have an identity") {
          testIdentityLaws(setValues)
        }

        it("should have a combine that is associative") {
          testAssociativeLaws(setValues)
        }
      }
    }
  }
}
