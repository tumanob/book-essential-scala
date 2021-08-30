/**
 * 6.4 Options
 * 6.4.1 Option, Some, and None
 * Option is a generic sealed trait with two subtypes—Some and None
 */

val testOption = Option(Some(5)) // TODO follow declaration

// sample code
def readInt(str: String): Option[Int] =
  if(str matches "-?\\d+") Some(str.toInt) else None

readInt("123")

readInt("abc")

// 6.4.2 Extracting Values from Options
readInt("abc").getOrElse(0)

// pattern matching
readInt("123") match {
  case Some(number) => number + 1
  case None         => 0
}


/**
 * 6.4.3 Options as Sequences
 see Option declaration for methods supported
*/


def sum(optionA: Option[Int], optionB: Option[Int]): Option[Int] =
  optionA.flatMap(a => optionB.map(b => a + b))
sum(readInt("1"), readInt("2"))
// res2: Option[Int] = Some(3)

sum(readInt("1"), readInt("b"))
// res3: Option[Int] = None

sum(readInt("a"), readInt("2"))
// res4: Option[Int] = None

/**
 * Although map and flatMap don’t allow us to extract values from our Options,
 * they allow us to compose computations together in a safe manner.
 * If all arguments to the computation are Some, the result is a Some.
 * If any of the arguments are None, the result is None.
 */









/**
 * 6.5 Options as Flow Control
 * if this clause results in a Some, extract the value and continue…
 * if it results in a None, exit the for comprehension and return None.
 */

val optionA = readInt("123")
val optionB = readInt("234")

for {
  a <- optionA
  b <- optionB
} yield a + b

