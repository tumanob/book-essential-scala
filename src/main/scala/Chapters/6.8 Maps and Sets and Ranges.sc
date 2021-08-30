/**
 * 6.8.1 Maps
 */

val example = Map("a" -> 1, "b" -> 2, "c" -> 3)

// «6.8.1.1 Accessing values using keys»
example("a") // The same as example.apply("a")
//example("d") // throws E

example.get("d") // Option
example.getOrElse("d", -1)

example.contains("a")
example.size

// add
example.+("c" -> 10, "d" -> 11, "e" -> 12)

// remove
example.-("b", "c")

//If we are only specifying a single argument, we can write + and - as infix operators.
example + ("d" -> 4) - "c"


//6.8.1.5 Mutable maps

val example2 = scala.collection.mutable.Map("x" -> 10, "y" -> 11, "z" -> 12)

example2 += ("x" -> 20)

// update mutable map
example2("w") = 30

example2


// 6.8.1.6 Sorted maps

scala.collection.immutable.ListMap("a" -> 1) + ("b" -> 2) + ("c" -> 3) + ("d" -> 4) + ("e" -> 5)

// And many more...


/**
 * 6.8.2 Sets
 *
 * Sets are unordered collections that contain no duplicate elements.
 * You can think of them as sequences without an order, or maps with keys and no values.
 */








/**
 * 6.9 Ranges
 *
for(i = 0; i < array.length; i++) {
  doSomething(array[i])
}
 */

1 until 10

10 until 1

10 until 1 by -1

(1 until 10) ++ (20 until 30)



