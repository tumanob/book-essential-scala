package Articles

/**
 * To get list of compile phases type:
 * `scalac -Xshow-phases`
 * to see the compiler phases result on certain phase type
 * `scalac -Xprint:<phaseName> CompilePhases.scala`
 * `scalac -Xprint:namer CompilePhases.scala`
 * namer,pickler,erasure,cleanup, constructors
 */
object CompilePhases {


    def divideByTwo(number:Int) = number / 2

    val divideByThree = (number:Int) => number / 3 // implicit type

    /**
     * This still works with method even if .map need a function parameter
     * TODO make a compile steps and see difference
     *
     */
    List(2,4,6,8).map(divideByTwo)

    List(3,6,9).map(divideByThree)

}
