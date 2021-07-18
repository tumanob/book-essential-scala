/**
 * 5.5 Sequencing Computation
 * We have now mastered generic data and folding over algebraic data types.
 * Now we will look as some other common patterns of computation that are
 * 1) often more convenient to use than fold for algebraic data types and
 * 2) can be implemented for certain types of data that do not support a fold.
 *
 * These methods are known as map and flatMap.
 */

















/**
 * 5.5.1 Map
 * https://en.wikipedia.org/wiki/Map_(mathematics)
 *    - In many branches of mathematics, the term map is used to mean a function.
 *
 * https://alvinalexander.com/scala/fp-book/how-to-write-scala-map-function
 * 
 * Book example: we have a list of Int user IDs, and a function which,
 * given a user ID,
 * returns a User record.
 * We want to get a list of user records for all the IDs in the list.
 * Written as types we have List[Int] and a function Int => User, and we want to get a List[User]
 *
 * What these all have in common is we have a type F[A] and a function A => B, and we want a result F[B]
 *
 * Problem: write a function that can be used to apply function to each element of the List[int]
 * Temp Solution: List[int].applyFunctionToEachElement(function) - But wait?!
 * Solution: It is a actually a .map function. Yay!!! We can replace List[int].map(function)
 */














/**
 * Let’s implement map for LinkedList.
 * Notice how using the types and patterns guided us to a solution
 * @tparam A
 */
sealed trait LinkedList[A] {
  def map[B](fn: A => B): LinkedList[B] =
    this match {
      case Pair(hd, tl) => Pair(fn(hd), tl.map(fn)) // For Pair we have to combine head and tail to return a LinkedList[B] (as the types tell us) and we also know we need to recurse on tail
      case End() => End[B]() // For End we don’t have any value of A to apply to the function. The only thing we can return is an End.

    }
}
case class Pair[A](hd: A, tl: LinkedList[A]) extends LinkedList[A]
case class End[A]() extends LinkedList[A]















// another example of a map  that apply function to each element - just as an example
// def map2[A,B](f: A => B, list: List[A]): List[B] = { // more generic
def map2[A](f: Int => A)(list: List[Int]): List[A] = {
  for { // TODO desugar for comp to show under the hood
    x <- list
  } yield f(x)
}

val charList = List('a', 'b', 'c', ' ', '@')
val intList = List(1, 2, 3,4,5)
val doubleInt = (x:Int) => x * 2

map2(doubleInt)(intList)
intList.map(doubleInt)

// TODO Play ith it - is it gonna work? what it gonna return?
//map2(x => x + "-<Test>")(List('a', 'b', 'c', ' ', '@')) // Tip 1: -- replace with charList
//List('a', 'b', 'c', ' ', '@').map(x => x + "-<Test>")
//val charList = List(67,33,45,44):List[Char]
// TODO add real code examples

















/**
 * 5.5.2 FlatMap
 * https://twitter.com/gades_io/status/572360954886930432/photo/1
 * Problem: We have a list of users and we want to get a list of all their orders.
 * That is, we have LinkedList[User] and a function User => LinkedList[Order], and we want LinkedList[Order].
 *
 * https://alvinalexander.com/scala/collection-scala-flatmap-examples-map-flatten/
 *
 */
sealed trait Maybe[A] {
  def flatMap[B](fn: A => Maybe[B]): Maybe[B] =
    this match {
      case Full(v) => fn(v)
      case Empty() => Empty[B]()
    }
}
final case class Full[A](value: A) extends Maybe[A]
final case class Empty[A]() extends Maybe[A]

// Quote: flatmap is like using map followed by flatten









/**
 * map vs flatMap signatures
 */
//def map[B](f: A => B): Traversable[B]
//def flatMap[B](f: A => Traversable[B]): Traversable[B]

val intListMap = intList.map(x => List(x,x))
val intListFlatMap = intList.flatMap(x => List(x,x))
















/**
 * 5.5.3 Functors and Monads
 * https://adit.io/posts/2013-04-17-functors,_applicatives,_and_monads_in_pictures.html
 *    https://adit.io/imgs/functors/fmap_list.png
 *
 * https://www.cocoawithlove.com/blog/an-aside-about-flatmap-and-monads.html
 *
 * To simplify
 * A type like F[A] with a map method is called a functor.
 * If a functor also has a flatMap method it is called a monad
 *
 * The general idea is a monad represents a value in some context.
 * The context depends on the monad we’re using
 *
 * We use map when we want to transform the value within the context to a new value, while keeping the context the same.
 * We use flatMap when we want to transform the value and provide a new context.
 */

def mightFail1: Maybe[Int] = Full(1)

def mightFail2: Maybe[Int] = Full(2)

def mightFail3: Maybe[Int] = Empty() // This one failed

val mightFailTest1 = mightFail1 flatMap { x =>
  mightFail2 flatMap { y =>
    mightFail3 flatMap { z =>  // failed case
      Full(x + y + z)
    }
  }
}

val mightFailTest2 = mightFail1 flatMap { x =>
  mightFail2 flatMap { y =>
    Full(x + y)
  }
}

val mightFailTest1 = mightFail3 flatMap { x =>  // failed case
  mightFail1 flatMap { y =>
    mightFail2 flatMap { z =>
      Full(x + y + z)
    }
  }
}


/**
 * Why do I need the functors and Monads?
 *
 * Answer is.... Well....:
 *  - to use Function Composition.
 *      - Math background article -> https://en.wikipedia.org/wiki/Function_composition
 *      - Another One -> https://www.mathwarehouse.com/algebra/relation/composition-of-function.php
 *      - https://www.mathwarehouse.com/algebra/relation/composition-of-function-visualizer-applet.php
 *  - to use for comprehension (more on that in next chapters).
 *  - fail gracefully if something goes wrong (see above example)
 *  - Anything else ???
 *
 * in book we have flatmap chain to show it but this leads to
 * for comprehension as a sugared version showed above.
 *
 * Monads are warapers around values with defines laws and properties
 *  "in an uncertain world - it is great to be sure monads still obey some laws!
 * Story Time: Teapot or pictures.
 *
 * val  funcComposition = f(g(x))
 *
 * https://i.imgur.com/rPa2Xfc.png
 *
 */
















/**
 * 5.5.4 Exercises
 */

//5.5.4.1 Mapping Lists
intList.map(_ * 2)
intList.map(_ + 1)
intList.map(_ / 3)



//5.5.4.2 Mapping Maybe
sealed trait Maybe[A] {
  def flatMap[B](fn: A => Maybe[B]): Maybe[B] =
    this match {
      case Full(v) => fn(v)
      case Empty() => Empty[B]()
    }
  def map[B](fn: A => B): Maybe[B] =
    this match {
      case Full(v) => Full(fn(v))
      case Empty() => Empty[B]()
    }
}
final case class Full[A](value: A) extends Maybe[A]
final case class Empty[A]() extends Maybe[A]



//5.5.4.3 Sequencing Computations

intList.map(x => List(x, -x))
intList.flatMap(x => List(x, -x))
