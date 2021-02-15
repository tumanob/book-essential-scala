//4.6 Recursive Data

  sealed trait IntList
  case object End extends IntList
  final case class Pair(head: Int, tail: IntList) extends IntList

  val d = End
  val c = Pair(3, d)
  val b = Pair(2, c)
  val a = Pair(1, b)

  def sum(list: IntList): Int =
    list match {
      case End => 0
      case Pair(hd, tl) => hd + sum(tl)
    }

  val example = Pair(1, Pair(2, Pair(3, End)))
  sum(example)
  sum(example.tail)
  sum(End)


//4.6.2 Tail Recursion
  import scala.annotation.tailrec
  @tailrec
  def sum(list: IntList, total: Int = 0): Int =
    list match {
      case End => total
      case Pair(hd, tl) => sum(tl, total + hd)
    }

  sum(example, 5) // 5 is a starting accumulator to show difference