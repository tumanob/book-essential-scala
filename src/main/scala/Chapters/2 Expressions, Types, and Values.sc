/**
 * Additional information about evaluation to a value could be found in Martin Osdersky course
 * https://www.coursera.org/learn/progfun1/lecture/vzbJj/lecture-1-2-elements-of-programming
 * Lecture discussing evaluation strategy and how it is used as well as other elements of programming.
 */

// 2.1 Your First Program
  "Hello world!"

  "Hello world!".toUpperCase

  "Hello world!".toLowerCase

/** 2.1.1 Compile-time and Run-time
 * To understand of how compiler is working check `Articles.CompilePhases`
 * use Below commented code in REPL to see errors output
 */
  import Articles.CompilePhases._
  // toUpperCase."Hello world!"
  // 2.toUpperCase
  // "try me
  // 2 / 0 // Run Time error when divide by 0

/** 2.1.2 Expressions, Types, and Values
 * Expressions are the parts of a program that evaluate to a value.
 * Expressions have types, which express some restrictions on programs. During compile-time the types of our programs are checked.
 * Values exist in the computer’s memory, and are what a running program manipulates. All values in Scala are objects.
 */
  2.min(3)
  2.max(3)
// REPL :type 2 / 0




/**
 * 2.2 Interacting with Object
 * 2.2.2 Method Calls
 */

  "hello".toUpperCase
  "abcdef".take(3)
  "abcdef".take(2)

  "hello".toUpperCase.toLowerCase
  "Hello world!".take(2 + 3) // TODO Use Articles.CompilePhases to see evaluation process

// 2.2.3 Operators
  123.toShort
  23.toByte

  43 - 3 + 2
  43.-(3).+(2)

  //Infix Operator Notation
  "the quick brown fox" split " "
  // Note that a b c d e is equivalent to a.b(c).d(e), not a.b(c, d, e).

  // Scala uses a set of precedence rule intuitive understanding from mathematics and logic
  2 * 3 + 4 * 5
  (2 * 3) + (4 * 5)
  2 * (3 + 4) * 5




/** 2.3 Literal Objects
 * 2.3.1 Numbers
 */
  42    // Int
  42.0  // Double
  42.0f // Float
  42L   // Long

  // -> 2.3.2 Booleans
  true
  false

  // -> 2.3.3 Characters
  'a' // 16-bit Unicode

  // -> 2.3.4 Strings
  "this is a string"
  "the\nusual\tescape characters apply"

  // -> 2.3.5 Nul, 2.3.6 Unit
  null
  println("something")



/**
 * 2.4 Object Literals
 */
  object Test {}
  Test

  // -> 2.4.1 Methods
  object Test2 {
    def name: String = "Probably the best object ever"
  }
  Test2.name

  object Test3 {
    def hello(name: String) =
      "Hello " + name
  }
  Test3.hello("Noel")

  /**
   * The syntax for declaring a method is
      def name(parameter: type, ...): resultType =
        bodyExpression
  or
      def name: resultType =
        bodyExpression
  */
  // NOTE: Return is Implicit
  // The return value of the method is determined by evaluating the body—there is no need to write return like you would in Java.

  // -> 2.4.2 Fields
  object Test4 {
    val name = "Noel"
    def hello(other: String): String =
      name + " says hi to " + other
  }
  Test4.hello("Dave")

  /**
   * The syntax for declaring a field is
      val name: type = valueExpression
   or
      var name: type = valueExpression
   */

/**
 * -> 2.4.3 Methods versus fields
 * You might wonder why we need fields when we can have methods of
 * no arguments that seem to work the same. The difference is subtle—
 *  - a field gives a name to a value,
 *  - whereas a method gives a name to a computation that produces a value
  */

  object Test7 {
    val simpleField = {
      println("Evaluating simpleField")
      42
    }

    def noParameterMethod = {
      println("Evaluating noParameterMethod")
      42
    }
  }
  Test7.simpleField // evaluation run only once after which the final value is stored in the object.
  Test7.noParameterMethod // is evaluated every time we call the method
  // TODO Find Real Code Example





/**
 * 2.6 Compound Expressions
 * -> 2.6.1 Conditional
 */

  if (1 < 2) "Yes" else "No"
  if(1 < 2) println("Yes") else println("No")
  val resultString = if (1 < 2) "Yes" else "No" // if/then statements return a value.
  def abs(x: Int) = if (x >= 0) x else -x

  /** 2.6.2 Blocks
   * Blocks are expressions that allow us to sequence computations together.
   */
  {
    1; 2; 3
  }

  {
    println("This is a side-effect")
    println("This is a side-effect as well")
    3
  }

  def name: String = {
    val title = "Professor"
    val name = "Funkenstein"
    title + " " + name
  }
  name
