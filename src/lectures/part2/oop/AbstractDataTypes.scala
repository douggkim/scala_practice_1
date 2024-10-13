package lectures.part2.oop

object AbstractDataTypes extends App {
  // you do not supply the specific methods and fields
  // abstract classes cannot be instantiated
  abstract class Animal {
    // you can actually have non abstract members for an abstract class
    val creatureType: String = "wild" // no value -> means it's abstract

    def eat: Unit
  }

  // can be overridden while instantiating
  val animal = new Animal {
    override val creatureType: String = "wild"

    override def eat: Unit = println("abstract animal instantiated eating")
  }

  animal.eat

  // subclass implementing the specifics
  class Dog extends Animal {
    override val creatureType: String = "Canine"

    // you actually don't have to use override keyword when extending abstract classes
    def eat: Unit = println("crunch crunch")

  }

  // traits
  // they can inherited along classes
  // traits can have non-abstract members
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat"
  }

  trait ColdBlooded

  // you can mix in any traits as we want
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"

    override def eat: Unit = "eat from abstract class animal"

    override def eat(animal: Animal): Unit = println(s"I'm a croc and eating ${animal.creatureType}")
  }

  val croc = new Crocodile
  val dog = new Dog

  croc.eat(dog)
  println(croc.preferredMeal)

  // traits vs abstract classes
  // 1 - traits do not have constructor parameters
  // 2 - multiple traits may be inherited by the same class (abstract class can not)
  // 3- traits = behavior, abstract class = type of thing


}
