package Articles

/**
 *
 * Why traits and classes?
 * From https://www.artima.com/articles/the-goals-of-scalas-design
 * Quote: ""We wanted to have a way where Java code could easily call Scala code, and traits
 * don't have a natural mapping back into Java, because they don't exist in Java." - Martin Odersky
 *
 * Scala's Stackable Trait Pattern
 * example link: https://www.artima.com/articles/scalas-stackable-trait-pattern
 */
object StackableTraits extends App {
  /**
   * Base trait with abstract methods defined in it.
   */
  trait Cat { // also can be abstract class - but as we do not need a constructor I just use trait!
    def getWeight:Int
    def eat(x:Int):Unit
  }

  /**
   * Traits taht we gonna use for stacking
   */
  // Cat with eating disorder can eat only half of whatever fed
  trait eatingDisorder extends Cat {
    abstract override def eat(x: Int): Unit = {
      super.eat(x/2)
    }
  }

  /**
   * Filter if food = 10 then cat will not touch it.
   * it might be too small or just cat is picky.
   * it is mystery why
   */
  trait canNotEat10 extends Cat {
    abstract override def eat(x: Int): Unit = {
      x match {
        case 10 => 0
        case _ => super.eat(x)
      }
    }
  }

  /**
   * Concrete class that extend Cat trait.
   */
  class Tiger extends Cat {
    private var weight = 0
    def getWeight:Int = weight
    def eat(x:Int):Unit = {weight = weight + x}
  }





  /**
   * Now lets play with the pattern and create few object
   */
  val tiger = new Tiger()
  // TODO: change trait order to see how it works and what would change
  val tigerWithEatingProblems = new Tiger() with eatingDisorder with canNotEat10

  // Feed both tigers the same amount of food
  tiger.eat(10)
  tigerWithEatingProblems.eat(10) // Todo: debug to see flow, and set different params

  println("Tiger 1 weight is:   " + tiger.getWeight)
  println("Tiger 2 need a doctor and weight is:   " + tigerWithEatingProblems.getWeight)
}
