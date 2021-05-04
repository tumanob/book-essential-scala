/** <!-- this is title slide -->
 * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
 *   <h1 style="font-size:24; color:white;">5 Sequencing Computations:</h1>
 *   <h2 style="font-size: 20; color:#d19d43;">5.1 Generics</h2>
 *   Definition:
  */
final case class Box[A](value: A)

Box(2)
res0.value
// if we omit the type parameter, scala will infer its value
Box("hi")

def generic[A](in: A): A = in
generic[String]("foo")

generic(1) // again, if we omit the type parameter, scala will infer it

// 5.1.2 Generic Algebraic Data Types
// check article section for more information
import Articles.GenericAlgebraicDataTypes51

// Before
sealed trait Calculation
final case class Success(result: Double) extends Calculation
final case class Failure(reason: String) extends Calculation

// After
sealed trait Result[A]
case class Success[A](result: A) extends Result[A]
case class Failure[A](reason: String) extends Result[A]

