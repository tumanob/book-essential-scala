/**
 * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
 *   <h1 style="font-size:24; color:white;">Patten matching in Scala</h1>
 *   <h2 style="font-size: 20; color:#d19d43;">Not your Switch statement!</h2>
 *   With Scala’s pattern matching, your cases can include types, wildcards, sequences, regular expressions, and even deep inspections of an object’s state.
 *   <br>
 *   <br>
 *   <br>"We also wanted Scala to have the other attributes of functional programming, such as types, generics, pattern matching." - Martin Odersky
 *   <br>
 *   <br>
 *   <h3> Links: </h3>
 *   https://docs.scala-lang.org/overviews/scala-book/match-expressions.html
 *   <br>https://www.artima.com/articles/the-point-of-pattern-matching-in-scala
 *   <br> https://www.scala-lang.org/files/archive/spec/2.11/08-pattern-matching.html
 *   <br>https://www.coursera.org/learn/progfun1/lecture/cdHAM/lecture-4-6-pattern-matching
 *   <br> https://www.oreilly.com/library/view/programming-scala-2nd/9781491950135/ch04.html
 *   <br><br><br>Important Points:
 *   <div>
 *   <li>Each match keyword must have at least one case clause.
 *   <li>The last “_“, is a “catch-all” case, will be executed if none of the cases matches. Cases are also called alternatives.
 *   <li>Pattern matching does not have any break statement.
 *   <li>Pattern matching always returns some value.
 *   <li>Match blocks are expressions, not statements. This means that they evaluate the body of whichever case matches. This is a very important feature of functional programming.
 *   <li>Pattern matching can also be used for value assignment and for comprehension, not only in match block.
 *   <li>Pattern matching allows matching any sort of data with the first match policy.
 *   <li>Each case statement returns a value, and the whole match statement is virtually a function that returns a matched value.
 * <li>Multiple values can be tested in a single line by using “|“.
 * </div>
 */

// Lets use Cats as examples.
case class Address(street: String, city: String, country: String)
abstract class Cat // could be trait
case class Tiger(name: String) extends Cat
case class Puma(name: String, color: String) extends Cat
case class HomeCat(name: String, address:Address) extends Cat


/**
 * Java switch/if statements
 *

switch(expression) {
  case x:
    // code block
    break;
  case y:
    // code block
    break;
  default:
    // code block
}

int month = 8;
if (month == 1) {
    System.out.println("January");
} else if (month == 2) {
    System.out.println("February");
}
...  // and so on

 */


{
  /**
   * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
   *   <h2 style="font-size: 20; color:#d19d43;">Pattern Matching</h2>
   *   https://www.coursera.org/learn/progfun1/lecture/cdHAM/lecture-4-6-pattern-matching<br><br>
   *   Pattern matching is a generalization of switch from C/Java to class hierarchies.
   *   <br>It’s expressed in Scala using the keyword match
   *   <h3>Rules:</h3>
        ▶ match is followed by a sequence of cases, pat => expr. <br>
        ▶ Each case associates an expression expr with a pattern pat.<br>
        ▶ A MatchError exception is thrown if no pattern matches the value of the selector.<br>
   */
  // To coves some aspect of the matching lets use the following case classes
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr

  object Number {
    def apply(n: Int) = new Number(n)
  }
  object Sum {
    def apply(e1: Expr, e2: Expr) = new Sum(e1, e2)
  }









/**
  def eval(e: Expr): Int = <Selector> match {
    case <Pattern1> => <Expression>
    case <Pattern2> => <Expression>
  }

  Patterns are constructed from:
      ▶ constructors, e.g. Number, Sum,
      ▶ variables, e.g. n, e1, e2,
      ▶ wildcard patterns _,
      ▶ constants, e.g. 1, true, "string", 'c'

    -Variables always begin with a lowercase letter.
    -The same variable name can only appear once in a pattern. So,
      Sum(x, x) is not a legal pattern.
    -Names of constants begin with a capital letter, with the exception
      of the reserved words null, true, false

   */

  def eval(e: Expr): Int = e match {
    case Number(n) => n
    case Sum(e1, e2) => eval(e1) + eval(e2)
  }

  println(
    eval(
      Sum(Number(5),Number(2))
    )
  )

  /** Evaluation steps of Sum(Number(5),Number(2))
   *
   * 1: Sum(Number(5),Number(2))
   * 2: eval(Number(5)) + eval(Number(2))
   * 3: 5 + eval(Number(2))
   * 4: 5 + 2
   * 5: 7
   */
}












/**
 * Time to start with Patterns Scala has
 */
{
  /**
   * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
   *   <h2 style="font-size: 20; color:#d19d43;">Constant Pattern OR Literal Patterns</h2>
   *   Closest to Switch statement you can get, in my opinion of course.
   */
  def describe(x: Any) = x match {
    case 5 => "five"
    case true => "truth"
    case "hello" => "hi!"
    case Nil => "the empty list"
    case _ => "handle the default case"
  }

  println(describe(true))
  println(describe(Tiger("Alex")))
}














{/**
 * Pattern Alternatives  "this OR that ..."
 * Pattern   ::=  Pattern1 { | Pattern2 }
 */

  def describe(x: Any) = x match {
    case 0 | 2 | 4 => "0or2or4"
    case 1 | 3 | 5 => "1or3or5"
    case _ => "handle the default case"
  }

  println(describe(1)) // is match with variable pattern Tiger(name)
  println(describe(0))// Matched with the constructed Tiger("Alex")
  println(describe(Puma("Brian", "nice"))) // match with Wildcard pattern
}










{
  /**
   * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
   *   <h2 style="font-size: 20; color:#d19d43;">Wildcard && Variable pattern</h2>
   *   SimplePattern   ::=  '_' |  varid
   */

  def describe (a:Any): Unit = a match {
    case Tiger(_) => println(s"Tiger with wildcard is called") // Any Tiger cause _ used
    case _ => println("handle the default case")
  }

  describe(Tiger("WildCard"))
  describe(Puma("Brian", "nice")) // default case as there is no case for Puma
}

{ // Variable pattern
  def describe(x: Any) = x match {
    case 0 => "zero" // constant patern
    // VVV variable pattern will used as fall back not wildcard as it goes first
    case somethingElse => "not zero: " + somethingElse // use variable or wildcard not both
    case _ => "wildcard is a special case for Variable pattern when you do not care about variable"
  }

  println(describe(0))
  println(describe(Tiger("Alex")))
}











{
  /**
   * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
   *   <h2 style="font-size: 20; color:#d19d43;">Typed patterns</h2>
   *   Pattern1   ::=  varid : TypePat   |    '_' : TypePat
   */
  def getSize(x: Any) = x match {
    case s: String => s.length
    case m: Map[_, _] => m.size
    case l: List[Int] => l.size
    case _: List[Int] => "List[Int]" // this is an example of '_' : TypePat
    case _ => -1
  }

  println("Typed patterns")

  println(getSize(List(1,3,5)))
  println(getSize((1,3,5))) // wildcard => -1 as this is tuple and not in case list
  println(getSize("test string"))
}














{
  /**
   * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
   *   <h2 style="font-size: 20; color:#d19d43;">Stable Identifier Patterns</h2>
   *   SimplePattern   ::=  StableId <br><br>
   *   To resolve the syntactic overlap with a variable pattern,
   * a stable identifier pattern may not be a simple name starting with a lower-case letter. However, it is possible to enclose such a variable name in backquotes; then it is treated as a stable identifier pattern.
   */
  def f(x: Int, y: Int) = x match {
    case y => s"$y not stable" // variable pattern not from scope
    case _ => "default"
  }

  def fStable(x: Int, y: Int) = x match {
    case `y` => s"$y stable" // match if function x and y are equal
    case _ => "default"

  }

  println("// Stable Identifier Patterns")

  println(f(1,5))

  println(fStable(1,5))
  println(fStable(3,3))
}












{
  /**
   * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
   *   <h2 style="font-size: 20; color:#d19d43;">Constructor Pattern use of apply method of case class</h2>
   *   SimplePattern   ::=  StableId ( [Patterns] )
   */
  def describe(x: Any) = x match {
    case Tiger("Max") => "Max Calling us to hangout"
    case Tiger("Alex") => "Alex Calling us and want to say Hi!!!"
    case _ => "handle the default case"
  }

  println(describe(Tiger("test"))) // is match with variable pattern Tiger(name)
  println(describe(Tiger("Alex")))// Matched with the constructed Tiger("Alex")
  println(describe(Puma("Brian", "nice"))) // match with Wildcard pattern
}








{
  /**
   * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
   *   <h2 style="font-size: 20; color:#d19d43;">Extractor Pattern use of unapply</h2>
   *   SimplePattern   ::=  StableId ( [Patterns] )
   */

  def describe(x: Any) = x match {
    case Tiger(name) => s"$name Calling us"
    case _ => "handle the default case"
  }

  println(describe(Tiger("testTiger"))) // is match with variable pattern Tiger(name)
  println(describe(Puma("Brian", "nice"))) // match with Wildcard pattern
}











{
  /**
   * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
   * <h2 style="font-size: 20; color:#d19d43;">Sequence patterns & Tuple patterns & Infix Operation Patterns</h2>
   *s
   * SimplePattern   ::=  ( [Patterns] ) <br><br>
   * You can match against sequence types, like List or Array, and against tuples too
   * just like you match against case classes.
   * <br><br>
   *
   * InfixPattern  ::=  SimplePattern {id [nl] SimplePattern}<br><br>
   * An infix operation pattern 𝑝;op;(𝑞1,…,𝑞𝑛) is a shorthand for the constructor or extractor pattern op(𝑝,𝑞1,…,𝑞𝑛).
   *
   */

  def describe(expr: Any): Unit = expr match {
    case List(0, _, _) => println("found it: List(0, _, _) ")
    case List(5, _*) => println("found it List(5, _*)") // first element is 5 and does not meter how many others
    case head +: tail  =>  println(s"Head : $head +: Tail $tail ") // Infix Operation Patterns (::, +:)
    case (5, _, _)  =>  println("matched (5, _, _)")
    case (5, _, _)  =>  println("matched (5, _, _)")
    case (a, b, c)  =>  println("matched " + a + b + c)
    case _ => println("handle the default case")
  } // TODO add more cases and examples


  println("Sequence and Tuple patterns")
  describe(List(0,3,7))
  describe(List(0,3,7,4,5,6)) // fall under _ wildcard pattern
  describe(List(5,3,7,4,5,6)) // List(5, _*) as it check first element only
  describe(Seq(15,3,7,4,5,6)) // head +: tail
  describe(List(15)) // head +: tails
  describe(('a', 5, 'y')) // (a, b, c) -  variable pattern
  describe((5, 'y', 11)) // (5, _, _)
  describe((5, 'y', 11, 35)) // wildcard as more the 3 elements-  there is no `_*` pattern
  describe(Tiger("Alex")) // wildcard
}










{
  /**
   * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
   *   <h2 style="font-size: 20; color:#d19d43;">Variable binding pattern & Patten overlap & Pattern Sequences</h2>
   *   Pattern binders look like x@p. Here, x is a pattern variable
   *   <br>and p is the pattern.
   *   <br><br>PatternTwo ::= varid '@' PatternThree
   *   <br>variableName @ pattern
   *   <br>if the pattern succeeds, set the variable to the matched object just as with a simple variable pattern.
   *
   *   <br><br> SimplePattern ::= StableId ( [Patterns] [varid @] _* )
   */

  def describe(x: Any) = x match {

    //case x: List(1, _*) => s"$x"          // doesn't compile
    case l @ List(1,_*) => s"List $l size is ${l.size} is binded - but what is l? "
    case List(col, val1, vals @ _*) => s"WHERE $col - $val1 - $vals" // TODO as Question: what is value of vals?
    case Tiger(name) => s"$name Calling us"
    //case Some(_) => "got a Some"          // works, but can't access the Some
    //case Some(x) => s"$x"                 // works, returns "foo"
    case x @ Some(_) => s"$x"               // works, returns "Some(foo)"
    case p @ Puma(first, "nice") => s"$p name: ${p.name}"  // works, returns "Puma(Brian,nice)"
    case hc @ HomeCat(_,a @ _) => s"Hi Cat ${hc.name}! that live in ${a.city}"
    case _ => "handle the default case"
  }

  println("Variable binding pattern")

  println(describe(Tiger("TigerName")))
  println(describe(List(1,2,3,4,5)))

  println(describe(List("state", "BC", "AL", "NF", "NW"))) // 5
  println(describe(List("name", "Alex", "Max"))) // 3

  println(describe(Puma("Brian", "nice"))) // match with Wildcard pattern
  println(describe(HomeCat("testHomeCatName", Address("street", "CatCity", "state"))))

}



{ // Option pattern

  def toInt(s: String): Option[Int] = {
    try {
      Some(Integer.parseInt(s.trim))
    } catch {
      case e: Exception => None
    }
  }

  println("Option pattern")
  toInt("42") match {
    case Some(i) => println(i)
    case None => println("That wasn't an Int.")
  }
}








{
  /**
   * Pattern Guard - If statement
   * https://www.oreilly.com/library/view/scala-cookbook/9781449340292/ch03s14.html
   */

  def describe(count: Int): Unit = count match {
    case 1 => println("one, a lonely number")
    case x
      if x == 2 || x == 3 => println("two's company, three's a crowd")
    case x
      if x > 3 => println("4+, that's a party")
    case _ => println("i'm guessing your number is zero or less")
  }

  describe(1)
  describe(5)
  describe(-20)
}









{ // Regular Expressions Pattern

  val Pattern = "([a-cA-C])".r //
  val Pattern2 = """TEST: (.*)""".r //
  def describe(x: Any) = x match {
    case Pattern(c) => c // is deprecated
    case Pattern2(c2) => s"variable is: '$c2' this is TEST: ***"
    case _ => "Nothing found"
  }

  println("Regular Expressions Pattern")
  println(describe("alex"))
  println(describe("a"))
  println(describe("TEST: yes")) // match """TEST: (.*)"""

}


/**
 * Other Uses of Pattern Matching
 */

{
  // extractor  for variables `name` and `city` from HomeCat
  val HomeCat(name, Address(_,city,_)) =
    HomeCat("Daizy", Address("streetCat", "CatCity" , "C"))

  println(name)
  println(city)

  // extractor for List
  val head +: tail = List(1,2,3)
  println(head)
  println(tail)
}



/**
 * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
 *   <h2 style="font-size: 20; color:#d19d43;">Why Pattern Matching is important</h2>
 *   Why it is so cool and important feature of Functional programming and Scala?
 *   <br><br>
 *   "We also wanted Scala to have the other attributes of functional programming,
 *   such as types, generics, <b>pattern matching</b>." - Martin Odersky
 */
var personal = "note"

/**
 * Pattern matching has been in languages from the mid-seventies: ML, Caml, Erlang, and Haskell
 *
 * <The purpose of pattern matching>
 * In OOP - we want to call methods, and let the methods do the right thing.
 *
 * When the objects don't have the methods we need?!,
 * and we can't (or don't want to) add new methods to the objects.
 *
 * In essence, pattern matching is essential when you have structured object graphs
 * at which you need get from the outside, because you aren't able
 * to add methods to these objects yourself.
 *
 * <De-constructing objects>
 *   Let's say I have a method that takes some parameters and constructs
 *   some complicated object structure from those parameters.
 *
 *   Pattern matching does the reverse. It takes a complicated object structure
 *   and pulls out the parameters that were used to construct the same structure.
 *
 * <Two directions of extensibility>
 *
 * there's an object-oriented solution to the problem of adding
 * new <behavior> that involves data inside existing objects.
 * Ideally you would add methods to all of those subtypes, Memo, Address,
 * and whatever the other types of nodes are.
 * But the problem is that you can't always easily add those methods.
 *
 * It's a question of when can you add methods a question of extensibility?!
 *
 * When the set of structures is relatively fixed. You don't want to change that. But the set of operations
 * you might want to do on those structures is open. You want to add new operations all the time.
 *
 *  If you want to extend with new data, you pick the classical object-oriented approach with virtual methods.
 *  If you want to keep the data fixed and extend with new operations, then patterns are a much better fit.
 *
 *  Depend of your need one way or another could be a better shot.
 *
 * <OOP - visitor pattern> has some similarities with what we do here
 *  Link: https://refactoring.guru/design-patterns/visitor
 *
 *  The Visitor pattern suggests that you place the new behavior into a separate class called visitor,
 *  instead of trying to integrate it into existing classes. The original object that had
 *  to perform the behavior is now passed to one of the visitor’s methods as an argument,
 *  providing the method access to all necessary data contained within the object.
 *
 * <Applicability>
 *  Use the Visitor when you need to perform an operation on all elements
 *  of a complex object structure (for example, an object tree).
 *
 *  Use the Visitor to clean up the business logic of auxiliary behaviors.
 *
 *  Use the pattern when a behavior makes sense only in some classes of a class hierarchy, but not in others.
 *
 */