package Articles

/**
 * Example how trait stack works
 */
object StackableTraits extends App {

  trait Cat { // also can be abstract class - but as we do not need a constructor we can use trait!
    def getWeight:Int
    def eat(x:Int):Unit
  }

  // Cat can eat only half of whatever fed
  trait eatingDisorder extends Cat {
    abstract override def eat(x: Int): Unit = {
      super.eat(x/2)
    }
  }

  // Filter if food = 10 then can will not touch it
  trait canNotEat10 extends Cat {
    abstract override def eat(x: Int): Unit = {
      x match {
        case 10 => 0
        case _ => super.eat(x)
      }
    }
  }

  class Tiger extends Cat {
    private var weight = 0
    def getWeight:Int = weight
    def eat(x:Int):Unit = {weight = weight + x}
  }

  val tiger = new Tiger()
  // TODO change trait order to see how it works
  val tigerWithEatingProblems = new Tiger() with eatingDisorder with canNotEat10

  // Feed both tigers the same amount of food
  tiger.eat(10)
  tigerWithEatingProblems.eat(10) // Todo: debug to see flow, and set different params

  println("Tiger 1 weight is:   " + tiger.getWeight)
  println("Tiger 2 need a doctor and weight is:   " + tigerWithEatingProblems.getWeight)
}
