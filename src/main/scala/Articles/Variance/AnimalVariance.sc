
trait Animal
class Dog extends Animal
class Crocodile extends Animal

class AncientCat extends Animal // Animal is a superType of AncientCat
class Cat extends AncientCat // AncientCat is a superType of Cat
class Kitty extends Cat // Cat is a supertype of Kitty

// Lets put Animal into Variance Cages!

// Variance Positions in Scala, Demystified | Rock the JVM
// https://www.youtube.com/watch?v=aUmj7jnXet4
// text version https://blog.rockthejvm.com/scala-variance-positions/











/**
 * Contravariant
 *   A type Foo[-T] is contravariant in terms of T, meaning that Foo[A]
 *   is a subtype of Foo[B] if A is a supertype of B.
 */
class ContravariantCage[-T] { // TODO (var animal: T) - !! NOT working !!
  def addAnimal(animal: T) = true // just for the sake of test

//  def healAnimal():T // Error of Type
  def healAnimal[S<:T]():S = ???// Happy compiler - Upper Type Bounds

}

/**
 * ContravariantCage[Cat] is subtype of ContravariantCage[Kitty]
 * as Kitty is subtype of Cat
 *
 * OR
 * ContravariantCage[Animal] is subtype of ContravariantCage[Cat]
 * as Animal is supertype of Cat
 *
 *
 *
 */
val acc: ContravariantCage[Cat] = new ContravariantCage[AncientCat] // TODO play with new type
acc.addAnimal(new Cat)
//acc.addAnimal(new Dog) //  not!
acc.addAnimal(new Kitty)
//acc.addAnimal(new AncientCat) // not!


// another Good example of Contravariant is Vet
class Vet[-T]

val lassiesVet: Vet[Dog] = new Vet[Animal] // we can use any animal vet to heal our dog.







/**
 * Invariance
 * A type Foo[T] is invariant in terms of T, meaning that the types Foo[A] and Foo[B]
 * are unrelated regardless of the relationship between A and B.
 * This is the default variance of any generic type in Scala.
 *
 * separate types and no substitution one for another.
  */
class InvariantCage[T](val animal: T) {
  def addAnimal(animal: T) = true // just for the sake of test
}
val invCage: InvariantCage[AncientCat] = new InvariantCage[AncientCat](new Cat) // TODO play with cage Types
invCage.addAnimal(new Cat)
//invCage.addAnimal(new Dog) // No
invCage.addAnimal(new Kitty)
invCage.addAnimal(new AncientCat)












/** Covariant
 * A type Foo[+T] is covariant in terms of T, meaning that
 * Foo[A] is a supertype of Foo[B]
 * if A is a supertype of B.
 * Most Scala collection classes are covariant in terms of their contents.
 *
 * "If Dog is and Animal then Cage for dog is also Cage for animals"
 */
class CovariantCage[+T](val animal: T) {
//  def addAnimal(animal: T) = true // TODO  not gonna work
  def addAnimal[S >: T](animal: S) = true // using Lower Type Bounds
}

val covCage: CovariantCage[Cat] = new CovariantCage[Cat](new Cat) // all cats
val covCage2: CovariantCage[Cat] = new CovariantCage[Cat](new Kitty) // new Kitty
val covCage3: CovariantCage[Cat] = new CovariantCage[Kitty](new Kitty) // all Kitty

covCage.addAnimal(new Cat)
covCage.addAnimal(new Kitty)
covCage.addAnimal(new AncientCat)
covCage.addAnimal(new Dog)

// val covCage4: CovariantCage[Cat] = new CovariantCage[Kitty](new Cat) // all Kitty
// val covCage5: CovariantCage[Cat] = new CovariantCage[AncientCat](new AncientCat) // can NOT be Animal
