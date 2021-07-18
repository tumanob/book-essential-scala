/**
 * 5.6 Variance
 * In this section we cover variance annotations, which allow us to control subclass
 * relationships between types with type parameters.
 *
 * <Variance is Hard>
 * Variance is one of the trickier aspects of Scala’s type system.
 * Although it is useful to be aware of its existence, we rarely have to use it in application code.
 *
 * Links:
 *    - https://www.coursera.org/learn/progfun1/lecture/dnreZ/lecture-4-4-variance-optional
 *
 */











/**
 * 5.6.1 Invariance (default to scala), Covariance, and Contravariance
 *
 * Invariance
 * A type Foo[T] is invariant in terms of T, meaning that the types Foo[A] and Foo[B]
 * are unrelated regardless of the relationship between A and B.
 * This is the default variance of any generic type in Scala.
 *  -
 *
 * Covariant
 * A type Foo[+T] is covariant in terms of T, meaning that Foo[A] is a supertype of Foo[B]
 * if A is a supertype of B. Most Scala collection classes are covariant in terms of their contents.
 * We’ll see these next chapter.
 *
 * Contravariant
 * A type Foo[-T] is contravariant in terms of T, meaning that Foo[A]
 * is a subtype of Foo[B] if A is a supertype of B. The only example of contravariance that
 * I am aware of is function arguments.


    C[A] <: C[B]  - C is covariant
    C[A] >: C[B]  - C is contravariant
    neither C[A] or C[B] is a subtype of each other - C is nonvariant

 */






/**
 * T - is contravatiant appears only in method parameter types
 * U - is covariant appears in method result type
 * Note:  Invariant types can appear anywhere
 * @tparam T
 * @tparam U
 */
trait Function1[-T, +U] {
  def apply(x: T): U
}
















/**
 * 5.6.2 Function Types
 *
 * When we discussed function types we glossed over how exactly they are implemented.
 * Scala has 23 built-in generic classes for functions of 0 to 22 arguments.
 * Here’s what they look like:
 */

trait Function0[+R] { // Covariant
  def apply: R
}

trait Function1[-A, +B] { // Contra, Covariant
  def apply(a: A): B
}

trait Function2[-A, -B, +C] { // Contra, Contra, Covariant
  def apply(a: A, b: B): C
}

// and so on...


















/**
 * Functions are contravariant in terms of their arguments
 * and covariant in terms of their return type.
 * This seems counterintuitive but it makes sense if we look at it from
 * the point of view of function arguments.
 */

//Consider some code that expects a Function1[A, B]:

case class Box[A](value: A) {
  /** Apply `func` to `value`, returning a `Box` of the result. */
  def map[B](func: Function1[A, B]): Box[B] =
    Box(func(value))
}

/**
 * What functions can we apply func: Function1[A, B] ? :
 *  - A function from A to B is clearly ok.
 *  - A function from A to a subtype of B.This indicates that functions are covariant in their result type.
 *  - A function expecting a supertype of A is also ok, because the A
 *    we have in the Box will have all the properties that the function expects.
 *  - A function expecting a subtype of A is not ok, because our value may in reality be a different subtype of A.
 *
 */





/**
 * 5.6.3 Covariant Sum Types
 */

// Covariant Generic Sum Type Pattern
// If A of type T is a B or C, and C is not generic, write

sealed trait A[+T]
final case class B[T](t: T) extends A[T]
case object C extends A[Nothing]

// This pattern extends to more than one type parameter.
// If a type parameter is not needed for a specific case of a sum type,
// we can substitute Nothing for that parameter.»


/**
 * 5.6.4 Contravariant Position
 *
 * a new type that is a supertype of A  = [AA >: A]
 *
 * If A of a covariant type T and a method f of A complains that T is
 * used in a contravariant position, introduce a type TT >: T in f.
 */

case class A[+T]() {
  def f[TT >: T](t: TT): A[TT] = ???
}












/**
 * 5.6.5 Type Bounds
 *
 * We have seen some type bounds above, in the contravariant position pattern.
 *
 *  Type Bounds are nothing but used to enforce the restrictions on the Type parametrizations.
 *  Scala provides two types of Type Bounds namely Lower Type Bounds which are basically used
 *  to restrict the subtypes and Upper Type Bounds which are used to restrict the supertypes.
 *
 * Type bounds extend to specify subtypes as well as supertypes.
 * The syntax is:
 *    "A <: Type" to declare A must be a subtype of Type  - Upper Type Bounds
 *        [T <: S] => Type Parameter T must be either same as S or Sub-Type of S.
 *    "A >: Type" to declare a supertype. - Lower Type Bounds
 *        [T >: S] means this Type Parameter T must be either same as S or Super-Type of S
 *
 *  Type Bounds are nothing but used to enforce the restrictions on the Type parametrizations.
 *  Scala provides two types of Type Bounds namely Lower Type Bounds which are basically used
 *  to restrict the subtypes and Upper Type Bounds which are used to restrict the supertypes.
 *
 * For example, the following type allows us to store a Visitor or any subtype:
 */

class GrandParent
class Parent extends GrandParent
class Child1 extends Parent
class Child2 extends Parent

case class UserAnalytics[A <: Parent]( // Type upper bound
   visitor: A,
   pageViews: Int,
   searchTerms: List[String],
   isOrganic: Boolean
)
















/**
 * Any better way to explain Variance?
 * Check the links below and see if you find something that work for you
 *
 * https://blog.rockthejvm.com/scala-variance-positions/  rockthejvm text and video explanation
 *    - https://github.com/rockthejvm/advanced-scala/blob/master/src/lectures/part5ts/Variance.scala
         * Big rule
            - method arguments are in CONTRAVARIANT position
            - return types are in COVARIANT position

         * Rule of thumb
            - use covariance = COLLECTION OF THINGS
            - use contravariance = GROUP OF ACTIONS


 * https://i.stack.imgur.com/KjDLw.png - nice comics explaining some relationships
 * https://docs.scala-lang.org/tour/variances.html
 * https://docs.scala-lang.org/scala3/book/types-variance.html
 * https://www.benjamin.pizza/posts/2019-01-11-the-fourth-type-of-variance.html
 *
 * https://en.wikipedia.org/wiki/Covariance_and_contravariance_(computer_science)
 * https://www.freecodecamp.org/news/understand-scala-variances-building-restaurants/
 *
 * https://github.com/coffius/koffio-examples/blob/master/src/main/scala/io/koff/examples/variance/Variance.scala
 *
 * Intro to the Scala 3 Type System - Bill Venners cover some of the aspects and more of types
 * https://vimeo.com/showcase/8493621/video/554226951 HS has access code tot he video as we were
 * Gold partners of ScalaCon - as someone for the code
 */

