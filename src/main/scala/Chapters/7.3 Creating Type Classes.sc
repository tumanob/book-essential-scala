/**
 * «7.3 Creating Type Classes»
 *
 * «7.3.1 Elements of Type Classes»

There are four components of the type class pattern:

    1 the actual type class itself;
    2 the type class instances;
    3 interfaces using implicit parameters; and
    4 interfaces using enrichment and implicit parameters.
*/










/**
 * 7.3.2 Creating a Type Class
 *
 *  an example—converting data to HTML.
 */

// one implementation strategy - trait -  has multiple drawbacks
// - same render for all. - our own code only
trait HtmlWriteable {
    def toHtml: String
}

final case class Person(name: String, email: String) extends HtmlWriteable {
    def toHtml = s"<span>$name &lt;$email&gt;</span>"
}
Person("John", "john@example.com").toHtml














// Second - pattern matching
//- new type  and we need edit code - lost type safety
object HtmlWriter {
    def write(in: Any): String =
        in match {
            case Person(name, email) => ???
//            case d: Date => ???
            case _ => throw new Exception(s"Can't render ${in} to HTML")
        }
}











// Third - rendering to an adapter class
trait HtmlWriter[A] {
    def write(in: A): String
}

object PersonWriter extends HtmlWriter[Person] {
    def write(person: Person) = s"<span>${person.name} &lt;${person.email}&gt;</span>"
}
PersonWriter.write(Person("John", "john@example.com"))

// now can write own adapters for other types
import java.util.Date

object DateWriter extends HtmlWriter[Date] {
    def write(in: Date) = s"<span>${in.toString}</span>"
}
DateWriter.write(new Date)













/**
 * Type Class Pattern
        A type class is a trait with at least one type variable.
        The type variables specify the concrete types
            the type class instances are defined for.
        Methods in the trait usually use the type variables.
 */

trait ExampleTypeClass[A] {
  def doSomething(in: A): Date
}








/**
 * 7.4 Implicit Parameter and Interfaces
 */
trait HtmlWriter[A] {
    def write(in: A): String
}

object PersonWriter extends HtmlWriter[Person] {
    def write(person: Person) = s"<span>${person.name} &lt;${person.email}&gt;</span>"
}

object HtmlUtil {
    def htmlify[A](data: A)(implicit writer: HtmlWriter[A]): String = {
        writer.write(data)
    }
}

HtmlUtil.htmlify(Person("John", "john@example.com"))(PersonWriter)









// implicit exampe  - work well - compiler is smart
implicit object ApproximationWriter extends HtmlWriter[Int] {
    def write(in: Int): String =
        s"It's definitely less than ${((in / 10) + 1) * 10}"
}

HtmlUtil.htmlify(2)











/**
 * 7.4.2 Interfaces Using Implicit Parameters
 *
 * Type Class Interface Pattern
    If the desired interface to a type class TypeClass is exactly
    the methods defined on the type class trait, define an interface
    on the companion object using a no-argument apply method like

 */

object TypeClass {
    def apply[A](implicit instance: TypeClass[A]): TypeClass[A] =
        instance
}

