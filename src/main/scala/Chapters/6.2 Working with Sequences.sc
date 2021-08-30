/**
 * 6.2 Working with Sequences
 * 6.2.1 Bulk Processing of Elements
 */


/**
 * 6.2.2 Map // Functor
 *   https://en.wikipedia.org/wiki/Functor
 *   https://dcl-prog.stanford.edu/images/map.png
 *   https://dcl-prog.stanford.edu/images/map-length.png
 * AND
 * 6.2.3 FlatMap //
 */

val sequence = Seq(1, 2, 3)
sequence.map(elt => elt * 2) // apply function to each element of the sequence
sequence.map(_ * 2) // same as ^^^

"dog".permutations.toList
Seq("a", "wet", "dog").map(_.permutations.toList)

// adding flatMap
Seq("a", "wet", "dog").flatMap(_.permutations.toList)

Seq(1, 2, 3).flatMap(num => Seq(num, num * 10))








/**
 * 6.2.4 Folds
 * Say we have a Seq[Int] and we want to add all the numbers together.
 */
Seq(1, 2, 3).foldLeft(0)(_ + _) // (((0 + 1) + 2) + 3)

Seq(1, 2, 3).foldRight(0)(_ + _) // (1 + (2 + (3 + 0)))









/**
 * 6.2.5 Foreach
 * foreach does not return a useful result—we use it purely for its side-effects.
 */

List(1, 2, 3).foreach(num => println("And a " + num + "..."))


/**
 * 6.2.6 Algebra of Transformations
 * Nice table in the book that shows what you can use if you need specific result
 */
