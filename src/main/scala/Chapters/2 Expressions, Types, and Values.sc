// 2.1 Your First Program
  "Hello world!"

  "Hello world!".toUpperCase

// 2.1.1 Compile-time and Run-time
  // toUpperCase."Hello world!"

  // 2.toUpperCase

  // 2 / 0

// 2.1.2 Expressions, Types, and Values
  2.min(3)
  2.max(3)

// 2.2.2 Method Calls
  "hello".toUpperCase
  "abcdef".take(3)
  "abcdef".take(2)

  "hello".toUpperCase.toLowerCase
  "Hello world!".take(2 + 3)

// 2.2.3 Operators
  123.toShort
  23.toByte

  43 - 3 + 2
  43.-(3).+(2)

  //Infix Operator Notation
  "the quick brown fox" split " "

  2 * 3 + 4 * 5
  (2 * 3) + (4 * 5)
  2 * (3 + 4) * 5

// 2.3 Literal Objects
  // -> 2.3.1 Numbers
  42
  42.0
  42.0f
  42L

  // -> 2.3.2 Booleans
  true
  false

  // -> 2.3.3 Characters
  'a'

  // -> 2.3.4 Strings
  "this is a string"
  "the\nusual\tescape characters apply"

  // -> 2.3.5 Nul, 2.3.6 Unit
  null
  println("something")

// 2.4 Object Literals
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

  // -> 2.4.3 Methods versus fields
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

// 2.6 Compound Expressions
  // -> 2.6.1 Conditional
  if (1 < 2) "Yes" else "No"
  if(1 < 2) println("Yes") else println("No")

  // -> 2.6.2 Blocks
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
