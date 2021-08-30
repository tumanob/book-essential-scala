/**
 * 6 Collections
 *
 * In this section we’re going to look at three key datastructures in Scala’s
 * collection library: sequences, options, and maps.
 *
 */
















/**
 * 6.1 Sequences
 * A sequence is a collection of items with a defined and stable order.
 */

val sequence = Seq(1, 2, 3)

sequence.apply(0)
sequence(0) // sugared syntax from ^^^
//sequence(5) // exception -  access element that does not exist.


sequence.head
sequence.tail
sequence.tail.head

//Seq().head // exception -  access element that does not exist.

// If we want to safely get the head without risking an exception, we can use headOption:

sequence.headOption
Seq().headOption

/**
 * The Option class here is Scala’s built-in equivalent of our Maybe class from earlier.
 * It has two subtypes—Some and None—representing the presence and absence of a value respectively.
 */


//6.1.2 Sequence length
sequence.length














//6.1.3 Searching for elements
sequence.contains(2)

// The find method is like a generalised version of contains
sequence.find(_ == 3)
sequence.find(_ > 4)
sequence.find(_ > 1) //  return first found element

//Filter
sequence.filter(_ > 1)

//6.1.4 Sorting elements
sequence.sortWith(_ > _) // List(3, 2, 1)













//6.1.5 Appending/prepending elements
//Appending
sequence.:+(4) //

sequence :+ 5 //

//prepend
sequence.+:(0)
0 +: sequence // Here the trailing colon makes it right associative

/**
 * This is another of Scala’s general syntax rules—any method ending
 * with a : character becomes right associative when written as
 * an infix operator. This rule is designed to replicate
 * Haskell-style operators for things like list prepend (::)
 * and list concatenation (:::). We’ll look at this in more detail in a moment.
 */

// concatenate
sequence ++ Seq(4, 5, 6)











/**
 * 6.1.6 Lists
 * The default implementation of Seq is a List, which is a classic linked list data structure similar
 * to the one we developed in an earlier exercise. Some Scala libraries work specifically with
 * Lists rather than using more generic types like Seq
 *
 * Lists have well known performance characteristics—constant-time in prepend,
 * head and tail operations and linear-time in append, apply and update operations.
 * more info: https://docs.scala-lang.org/overviews/collections/performance-characteristics.html
 *
 */

val listNil = Nil //

// Longer lists can be created by prepending elements in classic linked-list style using the :: method, which is equivalent to +:
val list = 1 :: 2 :: 3 :: Nil

4 :: 5 :: list

List(1, 2, 3) // List apply method

/**
 * Finally, the ::: method is a right-associative List-specific version of ++:
 * :: and ::: are specific to lists whereas +:, :+ and ++ work on any type of sequence.
 */
List(1, 2, 3) ::: List(4, 5, 6)
List(4, 5, 6) ::: List(1, 2, 3)



/**
 * 6.1.7 Importing Collections and Other Libraries
 * https://en.wikibooks.org/wiki/Scala/Import
 */

import scala.collection.immutable.Vector
//import scala.collection.immutable._ // import everything

Vector(1, 2, 3)

