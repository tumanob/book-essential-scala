package Articles

import scala.annotation.tailrec

/**
 * Rational number class example from "the scala book" v4
 * @param n
 * @param d
 */
class Rational(n: Int, d: Int) { // rational numbers such as 1/2, 5/7

  require(d != 0) // If false object will not be created in cases  n/0

  private val g = gcd(n.abs, d.abs)
  val numer: Int = n / g // add field to have access to the value.
  val denom: Int = d / g

  def this(n: Int) = this(n, 1) // AUXILIARY constructor for number such as 5/1

  def +(that: Rational): Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  def +(i: Int): Rational = // for integers in cases such as 1/2 + 5
    new Rational(numer + i * denom, denom)

  override def toString = s"$numer/$denom"

  @tailrec // find greatest common denominator
  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }
}

object testRational extends App {
  println(new Rational(1,2))
  println(new Rational(8,2))
  //println(new Rational(8,0)) // Exception ... requirement failed
  println(new Rational(1,2) + 5)
}