
/**
 * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
 *   <h1 style="font-size:24; color:white;">Chapter 5</h1>
 *   <h2 style="font-size: 20; color:#d19d43;">Sequencing Computa􏰀tions</h2>
 *   In this sec􏰀on we’re going to look at two more language features, generics and func􏰁ons, and see some
 *   abstrac􏰀ons we can build using these features: functors, and monads.
 */
//Code from previous section to refresh. With Two problems: restricted to storing Ints, a lot of repetition
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
      case Pair(hd, tl) => hd + tl.sum }
}

case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList










/** <!-- this is title slide -->
 * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
 *   <h1 style="font-size:24; color:white;">5.1 Generics</h1>
 *   Generic types allow us to abstract over types. There are useful for all sorts of data structures,
 *   but commonly encountered in collec􏰀ons so that’s where we’ll start.
 *   <h2 style="font-size: 20; color:#d19d43;">5.1.1 Pandora’s Box</h2>
 *   When we call a method or construct a class with a type parameter, the type parameter is bound to the concrete type within the method or class body. So when we call generic(1)
 *   he type parameter A is bound to Int in the body of generic.
 *   <br/>
 *   <br/>
 *   <li>Generic types can be declared in a class or trait declara􏰀on in which case they are visible throughout the rest of the declara􏰀on.
 *   <li>Alterna􏰀vely they may be declared in a method declara􏰀on, in which case they are only visible within the method.
 */
// Case Class
final case class Box[A](value: A) // Question: What case class has?

val inBox = Box(2) // int

val stringBox = Box("hi") // if we omit the type parameter, scala will infer its value

// two step conversion for experiment
val testBoxGeneric = Box[String] _ // function - Question: why/how this works?
val testBoxString = testBoxGeneric("test string")


// Method
def generic[A](in: A): A = in

generic[String]("foo")

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
 * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
 *   <h2 style="font-size: 20; color:#d19d43;">5.1.2 Generic Algebraic Data Types</h2>
 *   We described type parameters as analogous to method parameters, and this analogy con􏰀nues when extending
 *   a trait that has type parameters. Extend- ing a trait, as we do in a sum type, is the type level equivalent
 *   of calling a method and we must supply values for any type parameters of the trait we’re extending.
 */
// In previous sec􏰀ons we’ve seen sum types like the following:
sealed trait Calculation
final case class Success(result: Double) extends Calculation
final case class Failure(reason: String) extends Calculation









/**
 * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
<h2 style="font-size: 20; color:#d19d43;">Let’s generalise</h2>
No􏰀ce that both Success and Failure introduce a type parameter A which is passed to Result when it is extended. Success also has a value of type A, but Failure only introduces A so it can pass it onward to Result. In a later sec􏰀on we’ll introduce variance, giving us a cleaner way to implement this, but for now this is the pa􏰂ern we’ll use.
 */
sealed trait Result[A]
case class Success[A](result: A) extends Result[A]
case class Failure[A](reason: String) extends Result[A]








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


