package chadford.chapter4

import cats.data.State
import cats.implicits._

object PostCalculator {

  type CalcState[A] = State[List[Int], A]

  def operator(o: (Int, Int) => Int): CalcState[Int] =
    State[List[Int], Int] {
      case a :: b :: tail =>
        val result = o(a, b)
        (result :: tail, result)
      case _ => sys.error("Something bad happened!")
    }

  def operand(value: Int): CalcState[Int] =
    State[List[Int], Int] { oldStack =>
      (value :: oldStack, value)
    }

  def evalOne(sym: String): CalcState[Int] =
    sym match {
      case "+"   => operator(_ + _)
      case "-"   => operator(_ - _)
      case "*"   => operator(_ * _)
      case "%"   => operator(_ % _)
      case value => operand(value.toInt)
    }

  def evalAll(input: List[String]): CalcState[Int] =
    input.foldLeft(0.pure[CalcState]) { (stack, sym) =>
      stack *> evalOne(sym)
    }

  def evalInput(input: String): Int =
    evalAll(input.split(" ").toList).runA(Nil).value
}
