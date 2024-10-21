package lectures.part2oop.oop

object Inheritance extends App {

  // single class inheritance : only can inherit one super class
  // super class

  class Animal {
    val creatureType = "wild"

    def eat = println("nom nom nom")
    // final def eat: ... will not let override the method in the sub class

    private def eatPrivate = println("eat private from animal") // -> doesn't let the cat call eat. only accessible in this app

    protected def eatProtected: Unit = println("eat protected from animal") // -> only usable within this class
  }

  // subclass
  class Cat extends Animal {
    def crunch = {
      this.eatProtected // protected method can be called here
      println("crunch crunch crunch")
    }
  }

  val cat = new Cat
  cat.eat
  cat.crunch

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0) // this allows you to define the instance using just the name e.g. Person(name)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name, age) //have to include the params for the super class

  // we can override the fields when taking them in the constructor as well
  class Dog(override val creatureType: String) extends Animal {
    //    override val creatureType = "domestic" // if this is commented, we will see "wild"
    override def eatProtected: Unit = println("crunch crunch (ovrrode eatProtected)")

    override def eat: Unit = {
      super.eat // call the method from the super class
      println("overridden eat")
    }
  }

  val dog = new Dog("K9")
  dog.eatProtected
  println(dog.creatureType)

  // type substitution (Polymorphism)
  // method call will go to the most overridden version
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat // this will show "overridden eat"

  // overRIDING vs overLOADING
  // super

  // preventing overrides
  // 1 - use final on member -> the member cannot be overridden
  // 2 - use final on the entire class -> the entire class won't be inherited
  // 3 - seal the class = extend the classes IN THIS FILE, prevent extension in other files

  // final class will not be overridden
  final class finalAnimal {
    def eat: Unit = println("final animal eating....")
  }
  // if you want the inheritance will be exclusive
  // if you want cat and dog to be the only subclass of animal
  sealed class sealedAnimal {
    def eatSealed: Unit = println("sealed animal eating")

  }


}
