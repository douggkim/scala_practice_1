package lectures.part2oop.oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }


  //anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("funny animal eating")
  }

  /*above is equivalaent to this
  *   class AnonymousClasses$$anon$1 extends Animal {
    override def eat:Unit = println("hahahaha")
  }
  * val funnyAnimal : Animal = new AnonymousClasses$$anon$1
  * */

  funnyAnimal.eat
  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi my name is $name, how can I help?")
  }

  // below is also possible
  val jim = new Person("Jim") {
    override def sayHi: Unit = println("Hi my name is Jim. Can I help?")
  }
}
