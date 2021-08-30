/**
 * 6.3 For Comprehensions
 *
 * !!! Not Your Father’s For Loops !!!
 * for comprehensions in Scala are very different to the C-style for loops in Java.
 * There is no direct equivalent of either language’s syntax in the other.
 */

Seq(1, 2, 3).map(_ * 2)

for { // same  as ^^^
  x <- Seq(1, 2, 3)
} yield x * 2

/**
 * We call the expression containing the <- a generator,
 * with a pattern on the left hand side and a generator expression on the right.
 */


/**
 * Say we want to double all the numbers in Seq(Seq(1), Seq(2, 3), Seq(4, 5, 6)) and return
 * a flattened sequence of the results. To do this with map and flatMap we must nest calls:
 */

val data = Seq(Seq(1), Seq(2, 3), Seq(4, 5, 6))

data.flatMap(_.map(_ * 2))

for { // same as ^^^ but clearer
  subseq  <- data
  element <- subseq
} yield element * 2


/**

a.flatMap(x => b.flatMap(y => c.map(z => e)))

same as

for {
  x <- a
  y <- b
  z <- c
} yield e

 OR use ()

 for (
  x <- a;
  y <- b;
  z <- c
) yield e

 OR

for {
  // ...
} yield {
  // ...
}

*/


for {
  seq <- Seq(Seq(1), Seq(2, 3))
  elt <- seq
} println(elt * 2) // Note: no 'yield' keyword  // c.foreach






