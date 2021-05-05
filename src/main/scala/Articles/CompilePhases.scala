package Articles

/**
 * To get list of compile phases type:
 * `scalac -Xshow-phases`
 * to see the compiler phases result on certain phase type
 * `scalac -Xprint:<phaseName> CompilePhases.scala`
 * `scalac -Xprint:namer CompilePhases.scala`
 * namer,pickler,erasure,cleanup
 */
object CompilePhases {
    def generic[A](in: A): A = in

    val test: Int = generic(1)
}
