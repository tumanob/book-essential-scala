/**
 * 7.2 Organising Type Class Instances
 * 7.2.1 Implicit Scope
 *
 * local scope
 * «any enclosing class, object, or trait, or imported from elsewhere»
 *
 * Companion objects
 * «This leads us to our first pattern for packaging type class instances.»
 * «Type Class Instance Packaging: Companion Objects»
 *
    When defining a type class instance, if
   1 there is a single instance for the type; and
   2 you can edit the code for the type that you are defining the instance for
    then define the type class instance in the companion object of the type.

 This allows users to override the instance by defining one in the
local scope whilst still providing sensible default behaviour.
*/












/**
 * 7.2.2 Implicit Priority
 *
 * The practical implication is that the local scope takes precedence over
 * instances found in companion objects.
 */
final case class Rational(numerator: Int, denominator: Int)

object Rational {
  implicit val ordering = Ordering.fromLessThan[Rational]((x, y) =>
    (x.numerator.toDouble / x.denominator.toDouble) <
      (y.numerator.toDouble / y.denominator.toDouble)
  )
}
object Example {
  implicit val higherPriorityImplicit = Ordering.fromLessThan[Rational]((x, y) =>
    (x.numerator.toDouble / x.denominator.toDouble) >
      (y.numerator.toDouble / y.denominator.toDouble)
  )

  def example(): Unit =
    assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted ==
      List(Rational(3, 4), Rational(1, 2), Rational(1, 3)))
}












/**
 * 7.2.3 Packaging Implicit Values Without Companion Objects

  If there is no good default instance for a type class instance,
  or if there are several good defaults, we should not place
  type class instances in the companion object but instead require
  the user to explicitly import an instance into the local scope.

 */

final case class Rational(numerator: Int, denominator: Int)

object RationalLessThanOrdering {
  implicit val ordering = Ordering.fromLessThan[Rational]((x, y) =>
    (x.numerator.toDouble / x.denominator.toDouble) <
      (y.numerator.toDouble / y.denominator.toDouble)
  )
}

object RationalGreaterThanOrdering {
  implicit val ordering = Ordering.fromLessThan[Rational]((x, y) =>
    (x.numerator.toDouble / x.denominator.toDouble) >
      (y.numerator.toDouble / y.denominator.toDouble)
  )
}

/**
 * In use the user would import RationalLessThanOrdering._
 * or import RationalGreaterThanOrdering._ as appropriate.»
 */
//import RationalGreaterThanOrdering._
import RationalLessThanOrdering._
List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted