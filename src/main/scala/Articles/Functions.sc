/**
 * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
 *   <h1 style="font-size:24; color:white;">Functions and Methods and Procedures</h1>
 *   <h2 style="font-size: 20; color:#d19d43;">a bit more on functions</h2>
 *<br><br>

 * </div>
 */
val title = "Functions"
val baseList = List(1,2,3,4,5)

/**
 * What is a function?
 * Is a method, a procedure, a function the same thing or not?
 * is def and val the same?
 *
 * Lets talk what we know and What I think is a function or my take on it.
 * Language is one thing but what we mean by saying it is another.
 *
 * Let me show some of the Scala functions features
 * and lets discuss what is functions in Scala
 */



/**
 * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
 *   <h2 style="font-size: 20; color:#d19d43;">1 Methods.</h2>
 * </div>
 */
def methodAsFunction (x : Int) = x + 1
println(methodAsFunction(5))
// ^^^ Question to ask - is method really a function?












/**
 * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
 *   <h2 style="font-size: 20; color:#d19d43;">2 Local functions - helper functions, nested functions</h2>
 * </div>
 */
def localFunction (list: List[Int]):Unit = {
  def x2 (x:Int) = x * 2 // local function, helper

  for (int <- list)
    println(x2(int))

}

println("Local functions ")
localFunction(baseList)
// What is here a function?  or both?

















/**
 * ======== Important concept ========
 * Methods as functions? or functions as functions?
 * convert DEF to VAL
 */


def divideByTwo(number:Int) = number / 2
val divideByThree = (number:Int) => number / 3 // implicit type
val divideBy4: Int => Int = (number:Int) => number / 4 // explicit type

List(2,4,6,8).map(divideByTwo)  // method
/**
 * This still works with method even if .map need a function parameter
 * TODO make a compile and check diff - DIY
 * It is working thanks to ETA expansion feature since scala 2.
 * https://www.scala-lang.org/files/archive/spec/2.13/06-expressions.html#eta-expansion-section
 * https://dotty.epfl.ch/docs/reference/changed-features/eta-expansion-spec.html
 */

List(3,6,9).map(divideByThree)

val devide2First = divideByTwo(_)
val devide2Second = divideByTwo _

List(2,4,6,8).map(devide2First)
List(2,4,6,8).map(devide2Second)

/** So the def and val could be used same way. and even though
 * implementation is different they can be use interchangeably
 * in some books both ways reference as function.
 *
 * I guess we need to understand difference but
 * in terminology level it is fine to call it a function.
 *
 * under the hood it is different but also same?
 *
 * This is important concept as this is used in many way
 */














// 4 Short forms of function literals & Placeholders
println("Short forms")

baseList.filter(x => x > 2)
baseList.filter(_ > 2) // same as above ^^^

// lets make it longer - just for fun - functions that do the same thing.
baseList.filter(x => x > 2) //
baseList.filter(x => if(x > 2) true else false)
baseList.filter(x => { if(x > 2) true else false })
baseList.filter(x => {
  if(x > 2) {
    true
  } else {
    false
  }
}
)


// val f1 = _ + _  // not gonna work -  Question: Why?
val f1 = (_:Int) + (_:Int) // ^ Hint this is gonna work

/**
_ + _     // expands to `(a, b) => a + b`
foo(_)    // expands to `(a) => foo(a)`
foo(_, b) // expands to `(a) => foo(a, b)`
_(foo)    // expands to `(a) => a(foo)`

// and so on...

 */










// 5 Partially applied functions

def sum(a:Int, b:Int, c:Int):Int = a + b + c // method
def sumF = (a:Int, b:Int, c:Int) => a + b + c // function TODO make val
sum(1,2,3) // 6 as a result as expected

val s = sum _ //  _ at the end replace for all parameteres also makes function out of the methods
s.apply(1,2,3) // The variable named s refers to a function value object of a class generated automatically by the Scala compiler from sum _.
val s2 = sum(1,_,1) // middle element is not provided. the Scala compiler generates a new function class whose apply method takes one argument.
s2(2) // In this case, s2.apply invoked sum(1,2,1) =  4

baseList.foreach(println) // println - no () and no _ .


/**
 * Curried functions
 */

def multiply (x:Int)(y:Int) = x * y
// val multiplyV2 = (x:Int)(y:Int) => x * y // does not work
multiply(2)(4)

val timesTwo = multiply(2)(_) // or multiply(2) _
timesTwo(4)

// take function and curry it -  lets take a look closer what happens here.

val curriedAdd = (sum _).curried // def sum(a:Int, b:Int, c:Int)
val addTwo = curriedAdd(2)
addTwo(10)(12)





// 6 CLOSURES
// TODO rewrite: The function value (the object) that's created at runtime from this function literal is called a closure.
var freeValue = 5
val func6 = (x: Int) => x + freeValue
func6(6)
freeValue = 15 // check the var change and act accordingly
func6(6)
// todo read more and get more examples













// 7 Repeated parameters
def pr (args:String*) =
  for (arg<-args) println(arg) // args:String* tells compiler that there might be more of the same type params
pr("test")
pr("test", "test2")


val seq = Seq("What's", "up", "doc?") // change the example
//pr(seq: _) //  not gonna work
pr(seq: _*) // gonna wsork - This notation tells the compiler to pass each element of seq as its own argument to echo, rather than all of it as a single argument.





// 8  Named argument -  handy isn't it?

def speed(distance: Float, time: Float): Float =
  distance / time

speed(100, 10)
speed(distance = 100, time = 10)
speed(time = 10, distance = 100)














/**
 * It looks like some basics features are covered now lets
 * discuss and see other thing what to do with functions
 *
 * For this section I want to use Function as I see it
 * in the format of  x => x + 1 and other option of it.
 */

val firstClassFunction = (x : Int) => x + 1

/**
 * as it is a value - variable??? can we call it this ?
 * any variable has value,  type, name. which is which in here
 *
 * lets take the function and discuss some aspect of it.
 *  - name is - firstClassFunction
 *  - What is the type of the val firstClassFunction?  int or something else?
 *  - what is the value of a function?
 *
 *
 *
 *
 *
 *
 *
 *
 *  if you put function in REPL you will get
 *   scala> val firstClassFunction = (x : Int) => x + 1
 *   firstClassFunction: Int => Int = $Lambda$7449/674419444@16925016
 *
 *       ^name^               ^Type^      ^Value^
 *
 *  compare with:
 *
 *  scala> val name = "value"
 *  name: String = value
 *
 *  Cool! isn't it?
 */

// What is Function1? in `scalac -Xprint:flatten CompilePhases.scala`
// https://www.scala-lang.org/api/2.13.3/scala/Function1.html
// Article 10 min read: https://alvinalexander.com/scala/fpbook/explaining-scala-val-function-syntax-functional-programming/








// you can pass function to ...

val isEven = (i:Int) => i % 2 == 0

List(1,2,3,4,5,6).filter(isEven) // other Functions? methods?
// def filter(p: A => Boolean): Repr = filterImpl(p, isFlipped = false)












// lets make an array from functions - Why not?

val functions = Map(
  "/2" -> isEven, // What isEven <implicit> type?
  "+1" -> firstClassFunction // What is firstClassFunction type?
)

List(1,2,3,4,5,6).map(functions("+1"))
// So what is function? a method? a variable? -   TODO discuss?

























/**
 *  Function compositon
 *  compose
 *  andThen
 *
 */






/**
 * Partial Functions -  not partially applied one
 *
 */

val one: PartialFunction[Int, String] = {
  case 1 => "one"
}
val four: PartialFunction[Int, String] = {
  case 4 => "four"
}

val wild: PartialFunction[Int, String] = {
  case _ => "something else"
}


one(1)
// try composition
val two = one orElse four orElse wild orElse "nothing found"

two(1) // one
two(4) // four
two(5) // n -  why "n"?
two(6) // g







/**
 *  curried functions
 */








/**
 * BY-NAME and BY-VALUE PARAMETERS
 * https://alvinalexander.com/scala/fp-book/how-to-use-by-name-parameters-scala-functions/
 */

//def callByValue(x: Int)

def callByValue(i: Int)
{
  println("Tanya did article " +
    "on day one is 1 - Total = " + i)
  println("Tanya did article " +
    "on day two is 1 - Total = " + i)
  println("Tanya did article "+
    "on day three is 1 - Total = " + i)
  println("Tanya did article " +
    "on day four is 1 - Total = " + i)
}

var Total = 0;

// function call
callByValue
{
  Total + 1

}

/**

def callByName(x: => Int)
A call-by-name mechanism passes a code block to the function call
and the code block is complied, executed and calculated the value.
message will be printed first than returns the value.
 */
// TODO a better examples
def callByName(i: => Int)
{
  println("Tanya did articles " +
    " on day one is 1 - Total = " + i)
  println("Tanya did articles "+
    "on day two is 1 - Total = " + i)
  println("Tanya did articles " +
    "on day three is 1 - Total = " + i)
  println("Tanya did articles " +
    "on day four is 1 - Total = " + i)
}

var Total = 0;

// calling function
callByName
{
  Total + 1; Total
}






/**
 * TODO Higher-Order Function
 */


/**
 * <div style="font-size: 14;background-color:#233745; border: 1pt solid #dbb261; padding:15px 30px ; color:#d1d3d4;">
 *   <h2 style="font-size: 20; color:#d19d43;">3 First Class functions</h2>
 *   function literals exist in the source code, whereas function values<br>
 *   exist as objects at runtime. The distinction is much like that between classes (source code)
 *   and objects (runtime).<br><br>
// TODO Higher-Order Functions ? is it the same?
 * Functional languages treat functions as first-class values.
This means that, like any other value, a function can be passed as a
parameter and returned as a result.<br><br>
This provides a flexible way to compose programs.<br>
Functions that take other functions as parameters or that return<br>
functions as results are called higher order functions.<br>

 */
val firstClassFunction = (x : Int) => x + 1
firstClassFunction(10)

println("First Class functions")
baseList.map(firstClassFunction)
baseList.map((x : Int) => x * 2)
baseList.foreach((x: Int) => println(x))
