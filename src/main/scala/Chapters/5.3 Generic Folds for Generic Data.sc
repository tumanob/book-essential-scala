/**
 * 5.3 Generic Folds for Generic Data
 * The idea of the chapter is to show some aspects of functions and what else you can do with it
 * https://colah.github.io/posts/2015-02-DataList-Illustrated/ Folds and other functions
 * https://www.youtube.com/watch?v=0qvi_sTJbEw Haskell for Imperative Programmers #9 - Folding (foldr, foldl)
 * After this chapter check more on function in src/main/scala/Articles/Functions.sc
 */

sealed trait LinkedList[A] {
  def fold[B](end: B, f: (A, B) => B): B =
    this match {
      case End() => end
      case Pair(hd, tl) => f(hd, tl.fold(end, f)) // Note: This is where match pattern come handy
    }
}

final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
final case class End[A]() extends LinkedList[A]












// 5.3.2 Working With Functions
// 5.3.2.1 Placeholder syntax
(1 until  10).map(
  (_: Int) * 2 // expanded to (a: Int) => a * 2
)

/** Few more examples
  _ + _     // expands to `(a, b) => a + b`
  foo(_)    // expands to `(a) => foo(a)`
  foo(_, b) // expands to `(a) => foo(a, b)`
  _(foo)    // expands to `(a) => a(foo)`
  // and so on...
 */













/**
 * 5.3.3 Converting methods to functions
 */


  def sum(x: Int, y: Int) = x + y
  val testSum = sum
  val testSumWorkingOne = sum _









/**
 * 5.3.3.1 Multiple Parameter Lists
  */


def example(x: Int)(y: Int) = x + y
example(1)(2)

def fold[B](end: B)(pair: (A, B) => B): B =
  this match {
    case End() => end
    case Pair(hd, tl) => pair(hd, tl.fold(end, pair))
  }