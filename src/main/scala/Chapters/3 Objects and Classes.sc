// 3.1 Classes
// -> 3.1.1 Defining a Class
class Person {
  val firstName = "Noel"
  val lastName = "Welsh"
  def name = firstName + " " + lastName
}
//Person
val noel = new Person

noel.firstName
val newNoel = new Person
val anotherNewNoel = new Person
anotherNewNoel.lastName

object alien {
  def greet(p: Person) =
    "Greetings, " + p.firstName + " " + p.lastName
}
alien.greet(noel)
alien.greet(newNoel)

// ->3.1.2 Constructors
class Person(first: String, last: String) {
  val firstName = first
  val lastName = last
  def name = firstName + " " + lastName
}
val dave = new Person("Dave", "Gurnell")
dave.name

class Person2(val firstName: String, val lastName: String) {
  def name = firstName + " " + lastName
}
new Person2("Dave", "Gurnell").firstName

/**
 * The syntax for declaring a class is
     class Name(parameter: type, ...) {
      declarationOrExpression ...
    }
 or
     class Name(val parameter: type, ...) {
      declarationOrExpression ...
    }
 */

// ->3.1.3 Default and Keyword Parameters
new Person2(lastName = "Last", firstName = "First")

def greet(firstName: String = "Some", lastName: String = "Body") =
  "Greetings, " + firstName + " " + lastName + "!"
greet("Busy")
greet(lastName = "Dave")

def greet2(title: String = "Citizen", firstName: String = "Some", lastName: String = "Body") =
  "Greetings, " + title + " " + firstName + " " + lastName + "!"
greet2("Busy") // this is now incorrect
greet2(firstName = "Busy") // this is still correct

// 3.2 Objects as Functions
// -> 3.2.1 The apply method
class Adder(amount: Int) {
  def apply(in: Int): Int = in + amount
}
val add3 = new Adder(3)
add3.apply(2)
add3(4) // shorthand for add3.apply(4)

// -> 3.3 Companion Objects
class Timestamp(val seconds: Long)

object Timestamp {
  def apply(hours: Int, minutes: Int, seconds: Int): Timestamp =
    new Timestamp(hours*60*60 + minutes*60 + seconds)
}
Timestamp(1, 1, 1).seconds

// -> 3.4 Case Classes - Case classes are the bread and butter of Scala data types. Use them, learn them, love them.
case class Person(firstName: String, lastName: String) {
  def name = firstName + " " + lastName
}
val dave = new Person("Dave", "Gurnell") // we have a class
Person

dave.firstName
dave

new Person("Noel", "Welsh").equals(new Person("Noel", "Welsh"))
new Person("Noel", "Welsh") == new Person("Noel", "Welsh")

dave.copy()
dave.copy(firstName = "Dave2")
dave.copy(lastName = "Gurnell2")

new Person("Noel", "Welsh") eq (new Person("Noel", "Welsh"))
dave eq dave

Person("Dave", "Gurnell") == Person("Noel", "Welsh")
Person("Dave", "Gurnell") == Person("Dave", "Gurnell")

// -> 3.4.3 Case objects
case object Citizen {
  def firstName = "John"
  def lastName  = "Doe"
  def name = firstName + " " + lastName
}
Citizen.toString

// -> 3.5 Pattern Matching
case class Person(firstName: String, lastName: String)
object Stormtrooper {
  def inspect(person: Person): String =
    person match {
      case Person("Luke", "Skywalker") => "Stop, rebel scum!"
      case Person("Han", "Solo") => "Stop, rebel scum!"
      case Person(first, last) => s"Move along, $first"
    }
}
Stormtrooper.inspect(Person("Noel", "Welsh"))
Stormtrooper.inspect(Person("Han", "Solo"))
