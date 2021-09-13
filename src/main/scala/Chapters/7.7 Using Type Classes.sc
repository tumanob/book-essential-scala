/**
 * «7.7 Using Type Classes»
 *
 *  7.7.1 Context Bounds
 */
//
//def pageTemplate[A : HtmlWriter](body: A): String = {
//  val renderedBody = body.toHtml
//
//  s"<html><head>...</head><body>${renderedBody}</body></html>"
//}

/*
 * «Context Bound Syntax
A context bound is an annotation on a generic type variable like so:

[A : Context]
It expands into a generic type parameter [A] along with an implicit parameter for a Context[A].»
 */


/**
 * 7.7.2 Implicitly
 * «The implicitly method takes no parameters but has a generic type parameters.
 * It returns the implicit matching the given type, assuming there is no ambiguity.»

 */


/**
 * 7.8 Implicit Conversions
 * has some dangers
 */

class B {
  def bar = "This is the best method ever!"
}

class A

implicit def aToB(in: A): B = new B()
new A().bar


/**
 * «7.8.2 Designing with Implicit Conversions»
 */

implicit def intToBoolean(int: Int) = int == 0
if(1) "yes" else "no"
// res3: String = no

if(0) "yes" else "no"
// res4: String = yes»

