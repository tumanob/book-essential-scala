/**
 * 7.5 Enriched Interfaces
 *
 * «A second type of type class interface, called type enrichment allow us to create
 * interfaces that act as if they were methods defined on the classes of interest. »
 *
  */

def numberOfVowels(str: String) =
  str.filter(Seq('a', 'e', 'i', 'o', 'u').contains(_)).length

numberOfVowels("the quick brown fox")


/**
 * «Scala has a feature called implicit classes that allow us
 * to add new functionality to an existing class without editing its source code.»
*/






/**
 * 7.5.1 Implicit Classes
 */

implicit class ExtraStringMethods(str: String) {
  val vowels = Seq('a', 'e', 'i', 'o', 'u')

  def numberOfVowels =
    str.toList.filter(vowels contains _).length
}


"the quick brown fox".numberOfVowels

/*
«When the compiler processes our call to numberOfVowels,
it interprets it as a type error because there is no such method in String.
Rather than give up, the compiler attempts to fix the »
«error by searching for an implicit class that provides
the method and can be constructed from a String. It finds ExtraStringMethods.
The compiler then inserts an invisible constructor call, and our code type checks correctly.»
 */


/**
 * «7.6 Combining Type Classes and Type Enrichment»
 */

trait HtmlWriter[A] {
  def toHtml(in: A): String
}

final case class Person(name: String, email: String)
implicit object PersonWriter extends HtmlWriter[Person] {
  def toHtml(person: Person) = s"<span>${person.name} &lt;${person.email}&gt;</span>"
}

implicit class HtmlOps[T](data: T) {
  def toHtml(implicit writer: HtmlWriter[T]) =
    writer.toHtml(data)
}

Person("John", "john@example.com").toHtml







/**
 * «7.6.1 Take Home Points»
«Implicit classes are a Scala language feature that allows us to define extra
functionality on existing data types without using conventional inheritance.
This is a programming pattern called type enrichment.

The Scala compiler uses implicit classes to fix type errors in our code.
When it encounters us accessing a method or field that doesn’t exist,
it looks through the available implicits to find some code it can insert to fix the error.

The rules for implicit classes are the same as for implicit values,
with the additional restriction that only a single implicit class
will be used to fix a type error.
 */