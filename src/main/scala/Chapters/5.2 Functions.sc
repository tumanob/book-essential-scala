
  /**
   * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
   *   <h1 style="font-size:24; color:white;">5.2 Function</h1>
   *   <h2 style="font-size: 20; color:#d19d43;">This is Function Programming for a reason, right?</h2>
   *   Functions allow us to abstract over methods, turning methods into values that we can pass around and manipulate within our programs
   *   <br>
   */
  // again code from previous chapter and try to remove duplications

  sealed trait IntList {
    def double: IntList =
      this match {
        case End => End
        case Pair(hd, tl) => Pair(hd * 2, tl.double)
      }

    // focus on methods from here
    def length: Int =
      this match {
        case End => 0
        case Pair(hd, tl) => 1 + tl.length
      }
    def product: Int =
      this match {
        case End => 1
        case Pair(hd, tl) => hd * tl.product
      }
    def sum: Int = {
      this match {
        case End => 0
        case Pair(hd, tl) => hd + tl.sum
      }
      // to here
    }
  }
  case object End extends IntList
  case class Pair(hd: Int, tl: IntList) extends IntList

  /**
 * All of these methods have the same general pattern, which is not surprising
 * as they all use structural recursion. It would be nice to be able to remove the duplication.
 */

// some sort of abstraction that can be used for the above methods
/**
 def abstraction(end: Int, f: ???): Int =
  this match {
    case End => end
    case Pair(hd, tl) => f(hd, tl.abstraction(end, f))
  }
*/










// Much earlier in this course we introduced the apply method, which lets us treat objects as functions in a syntactic sense:
object add1 {
  def apply(in: Int) = in + 1
}
add1(2)

  // BUT ^^^ here is no Types above











  /**
   *   5.2.1 Function Types

   We write a function type like (A, B) => C where A and B are the types of the parameters and C is the result type.
  The same pattern generalises from functions of no arguments to an arbitrary number of arguments.
   In our example above we want f to be a function that accepts two Ints as parameters and returns an Int.
  Thus we can write it as (Int, Int) => Int.

   Function Type Declaration Syntax
   To declare a function type, write
   (A, B, ...) => C
   where

  A, B, ... are the types of the input parameters; and
  C is the type of the result.

   If a function only has one parameter the parentheses may be dropped:
   A => B
   */










  /**
   * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
   *   <h2 style="font-size: 20; color:#d19d43;"> 5.2.2 Function literals</h2>
   */

// Here are some example function literals:
val sayHi = () => "Hi!"

sayHi()

val add2 = (x: Int) => x + 2

add2(10)

val sum = (x: Int, y:Int) => x + y

sum(10, 20)

// you can put type in function body expression but usually it is inferred (x: Int) => (x + 1): Int











{
  /**
 * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
 *   <h1 style="font-size:24; color:white;">5.2.3 Exercises</h1>
 *   <h2 style="font-size: 20; color:#d19d43;">5.2.3.1 A Better Abstraction</h2>
 */

  sealed trait IntList {

    def fold[A](end: A, f: (Int, A) => A): A =
      this match {
        case End => end
        case Pair(hd, tl) => f(hd, tl.fold(end, f))
      }

    def length: Int =
      fold[Int](0, (_, tl) => 1 + tl)
    def product: Int =
      fold[Int](1, (hd, tl) => hd * tl)
    def sum: Int =
      fold[Int](0, (hd, tl) => hd + tl)
    def double: IntList =
      fold[IntList](End, (hd, tl) => Pair(hd * 2, tl))
  }
  case object End extends IntList
  final case class Pair(head: Int, tail: IntList) extends IntList

}