

/**
 * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
 *   <h2 style="font-size: 20; color:#d19d43;">5.2 Function</h2>
 *   TODO: update description
 */
object add1 {
  def apply(in: Int) = in + 1
}
add1(2)















/**
 * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
 *   <h2 style="font-size: 20; color:#d19d43;">5.2.1 Function Types</h2>
 *   <h2 style="font-size: 20; color:#d19d43;">5.2.2 Function literals</h2>
 */

// TODO MaybeAddSomeExamples
// (Int, Int) => Int

val sayHi = () => "Hi!"

sayHi()

val add2 = (x: Int) => x + 2

add2(10)

val sum = (x: Int, y:Int) => x + y

sum(10, 20)


