/**
 * 5 Sequencing Computations:
 */

{
  /**
   * <Review Code from previous chapter>
   *
   * There are two problems with this code:
   * > The first is that our list is restricted to storing Ints.
   * > The second problem is that here is a lot of repetition.
*/
  case object End extends IntList
  final case class Pair(head: Int, tail: IntList) extends IntList

  sealed trait IntList {

    def length: Int =
      this match {
        case End => 0
        case Pair(hd, tl) => 1 + tl.length
      }

    def double: IntList =
      this match {
        case End => End
        case Pair(hd, tl) => Pair(hd * 2, tl.double)
      }

    def product: Int =
      this match {
        case End => 1
        case Pair(hd, tl) => hd * tl.product
      }

    def sum: Int =
      this match {
        case End => 0
        case Pair(hd, tl) => hd + tl.sum
      }
  }

  /**
   * In this chapter we gonna use Two ways to abstract code and deal with the problems
   * > For the former we will use generics to abstract over types,
   *    so we can create data that works with user specified types.
   * > For the latter we will use functions to abstract over methods, so we can reduce duplication in our code
   */

}











{
  /**
 * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
 *   <h1 style="font-size:24; color:white;">5.1 Generics</h1>
   * <h2 style="font-size: 20; color:#d19d43;">5.1.1 Pandora’s Box To start with it we use a generic type</h2>
 *   "We also wanted Scala to have the other attributes of functional programming, such as types, <b>generics</b>, pattern matching." - Martin Odersky
 *   <br><br>
 *   Link for related interview: https://www.artima.com/articles/the-purpose-of-scalas-type-system
 */
  final case class Box[A](value: A)

  println(Box(2))
  // if we omit the type parameter, scala will infer its value
  println(Box("hi"))


  /**
   * The syntax [A] is called a type parameter. We can also add type parameters to methods,
   * which limits the scope of the parameter to the method declaration and body
   */
  def generic[A](in: A): A = in

  println(generic[String]("foo")) // A bound to String
  println(generic(1)) // if we omit the type parameter, scala will infer it

  // two step assignment -  1 type, 2 parameter
  val testGeneric = generic[String] _ //convert method to function. Soon ...
  val testString = testGeneric("string")

  generic(1) // again, if we omit the type parameter, scala will infer it

  // TODO: to see how compiler add type
  // scalac -Xprint:packageobjects CompilePhases.scala // step 3
  //val test: Int = generic(1)
  // scalac -Xprint:typer CompilePhases.scala
  //private[this] val test: Int = CompilePhases.this.generic[Int](1); // step 4


  /**
   * Type parameters work in a way analogous to method parameters. When we call a method
   * we bind the method’s parameter names to the values given in the method call.
   *
   * When we call a method or construct a class with a type parameter, the type parameter
   * is bound to the concrete type within the method or class body.
   *
   * We declare generic types with a list of type names within square brackets like [A, B, C].
   * By convention we use single uppercase letters for generic types.
   *
   * Generic types can be declared in Class, Trait, Method
   */
}









/**
 * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
 * <h2 style="font-size: 20; color:#d19d43;">5.1.2 Generic Algebraic Data Types</h2>
 * check article section for more information about ADT: Articles.GenericAlgebraicDataTypes51
 */


// Before Generic types
sealed trait Calculation
final case class Success(result: Double) extends Calculation
final case class Failure(reason: String) extends Calculation

// After Generic types
sealed trait Result[A]
case class Success[A](result: A) extends Result[A]
case class Failure[A](reason: String) extends Result[A]

/**
 * <Notice> that both Success and Failure introduce a type parameter A which is passed to Result
 * when it is extended. Success also has a value of type A, but Failure only introduces A
 * so it can pass it onward to Result. In a later section we’ll introduce <variance>,
 * giving us a cleaner way to implement this, but for now this is the pattern we’ll use.
 *
 */








/**
 * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
 *   <h2 style="font-size: 20; color:#d19d43;">Why ADT is important?</h2>
 *   This is a very important concept and a start for the next book Scala with cats
 *<br/>
 * Few articles about ADT:<br/>
 * <li>https://alvinalexander.com/scala/fp-book/algebraic-data-types-adts-in-scala/
 * <li>https://dotty.epfl.ch/docs/reference/enums/adts.html
 * <li>https://docs.scala-lang.org/scala3/book/types-adts-gadts.html
 */
val codeNote = "I need More"


