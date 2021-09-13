/**
 * 7 Type Classes
 *
 * Type classes are a powerful feature of Scala that allow us to extend existing
 * libraries with new functionality,
 * without using inheritance and without having access to the original library source code.
 *
 * "We also wanted to have the other attributes of functional programming, such as
 * types, generics, pattern matching.
 * And we wanted to integrate the functional and object-oriented parts in a cleaner
 * way than what we were able to achieve before with the Pizza language.
 * That was something we deeply cared about from the start." - Martin Odersky
 * https://www.artima.com/articles/the-goals-of-scalas-design
 *
 * A type class is like a trait, defining an interface. However, with type classes we can:
    --> plug in different implementations of an interface for a given class; and
    --> implement an interface without modifying existing code.

 This means we can add new methods or new data without changing any existing code.

 */













/**
 * 7.1 Type Class Instances
 * 7.1.1 Ordering
 *
 * A simple example of a type class is the Ordering trait.
 */

import scala.math.Ordering
val minOrdering = Ordering.fromLessThan[Int](_ < _)

val maxOrdering = Ordering.fromLessThan[Int](_ > _)

List(3, 4, 2).sorted(minOrdering)
List(3, 4, 2).sorted(maxOrdering)

/**
 * The type class pattern separates the implementation of functionality
 * (the type class instance, an Ordering[A] in our example)
 * from the type the functionality is provided for (the A in an Ordering[A]).
 */













/**
 * 7.1.2 Implicit Values
 *
 * Note we didn’t supply an ordering to sorted.
 * Instead, the compiler provides it for us.
 */

implicit val ordering: Ordering[Int] = Ordering.fromLessThan[Int](_ < _)
List(2, 4, 3).sorted
List(1, 7 ,5).sorted











/**
 * 7.1.3 Declaring Implicit Values
 *
 * An implicit value must be declared within a surrounding object, class, or trait.
 */

implicit val exampleOne = ???
implicit var exampleTwo = ???
implicit object exampleThree = ???
implicit def exampleFour = ???

/**
 * 7.1.4 Implicit Value Ambiguity
 * The rule is simple: the compiler will signal an error
 * if there is any ambiguity in which implicit value should be used.
 */

implicit val minOrdering = Ordering.fromLessThan[Int](_ < _)
//implicit val maxOrdering = Ordering.fromLessThan[Int](_ > _)

List(3,4,5).sorted
