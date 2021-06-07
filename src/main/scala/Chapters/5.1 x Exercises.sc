/**
 * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
 *   <h1 style="font-size:24; color:white;">5.1.3 Exercises</h1>
 */

// 5.1.3.1 Generic List
// Change the name to LinkedList and make it generic in the type of data stored in the list.
sealed trait IntList
case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList

// Solution
sealed trait LinkedList[A]
final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
final case class End[A]() extends LinkedList[A]



