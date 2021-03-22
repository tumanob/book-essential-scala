/**
 * 5.3 Generic Folds for Generic Data
 */

sealed trait LinkedList[A] {
  def fold[B](end: B, f: (A, B) => B): B =
    this match {
      case End() => end
      case Pair(hd, tl) => f(hd, tl.fold(end, f))
    }
}

final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
final case class End[A]() extends LinkedList[A]

// 5.3.2 Working With Functions
// 5.3.2.1 Placeholder syntax
(1 until  10).map(
  (_: Int) * 2
)

// TODO continue

