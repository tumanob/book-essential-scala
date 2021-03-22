/**
 * 5.2 Function
 */

object add1 {
  def apply(in: Int) = in + 1
}
add1(2)

// 5.2.1 Function Types

// TODO MaybeAddSomeExamples
// (Int, Int) => Int

// 5.2.2 Function literals

val sayHi = () => "Hi!"

sayHi()

val add2 = (x: Int) => x + 2

add2(10)

val sum = (x: Int, y:Int) => x + y

sum(10, 20)
