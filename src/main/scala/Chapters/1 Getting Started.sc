/**
 * The first chapter is mainly about configure
 * and basics what you gonna do
 *
 * Use REPL: Docs https://docs.scala-lang.org/overviews/repl/overview.html
 * - type `scala` to enter REPL mode OR sbt -> console
 * - example how to use
 * - multiline expressions
 * - :past (ctrl-D to finish)
 * - :type "string"
 * - :load '1 Getting Started.sc'
 * - :save filename.sc -  save the REPL session to worksheet file
 * - :help -  for anything else
 * More about Starting with Scala you can check in Lightbend Scala Language - Professional
 * https://academy.lightbend.com/courses/course-v1:lightbend+LSL-P-Scala-Language-Professional+v1/about
 * > First Steps in Scala (17:29)
 */

"Hello world!"

1 + 2 + 3

res1 * 2 //  if worksheet uses REPL res1 is available to use

println("Hello world!")

// in REPL - use as multiline example
if (true) {
 't'
} else {
 'f'
}

for (i <- 1 to 3) {
 println(i)
}

object FirstSteps {
 println("Welcome to the Scala worksheet")
 1 + 1
 if(20 > 10) "left" else "right"
 println("The ultimate answer is " + 42)
}
FirstSteps // executes the FirstSteps object