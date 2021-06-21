/**
 * 5.4 Modelling Data with Generic Types
 */



/** 5.4.1 Generic Product Types
 Let’s look at using generics to model a product type.
 Consider a method that returns two values—for example, an Int and a String, or a Boolean and a Double:
*/
def intAndString: Pair[Int, String] = ??? // ...

def booleanAndDouble: Pair[Boolean, Double] = ??? //


//5.4.1.1 Exercise: Pairs

val pair = Tuple2[String, Int]("hi", 2) // book has Pair
pair._1
pair._2












// 5.4.2 Tuples
val tuplePair = ("hi", 2) // sugared syntax
val tuplePair2 = Tuple2("hi", 2) // unsugared syntax

// method that accept Tuple
def tuplized[A, B](in: (A, B)) = in._1

tuplized(("a", 1)) // In some places you can see that methods accepts Tuples vs parameters

// We can also pattern match on tuples as follows:

(1, "a") match {
 case (a, b) => a + b // deconstructor of Tuple2
}











// 5.4.3 Generic Sum Types

// book example
def intOrString(input: Boolean): Sum[Int, String] =
 if(input) {
  Left[Int, String](123)
 } else {
  Right[Int, String]("abc")
 }

// Exersize
sealed trait Sum[A, B]
final case class Left[A, B](value: A) extends Sum[A, B]
final case class Right[A, B](value: B) extends Sum[A, B]









//«5.4.4.1 Exercise: Maybe that Was a Mistake»

// Solution
sealed trait Maybe[A]
final case class Full[A](value: A) extends Maybe[A]
final case class Empty[A]() extends Maybe[A]

// usage
val perhaps: Maybe[Int] = Empty[Int]

val perhaps: Maybe[Int] = Full(1)




