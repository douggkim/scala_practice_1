package lectures.part2oop.oop

object _6Generics extends App {
  //type A below is a note for Generic type
  // This also works with traits
  /* trait MyTrait[A] */
  class MyList[+A] {
    // use the type A inside the definition
    // If to a list of A, we put list of B, it will turn into a list of B
    def add[B >: A](element: B): MyList[B] = ???
    /*
    * A = Cat
    * B = Animal
    * --> the list will be a list of animals
    * */
  }

  // generic type for maps
  class MyMap[Key, Value]

  // A can be replaced by Int as below
  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // Generic Methods
  // objects cannot be type parameterized
  // You can parametrize methods with generic types as well
  object MyList {
    // type parameter A
    // you can use A inside the method implementation as well
    def empty[A]: MyList[A] = ???
  }

  // it can be used as below
  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal

  class Cat extends Animal

  class Dog extends Animal

  // 1. Yes List[Cat] extends List[Animal] = COVARIANCE
  // If a List can contain a subtype B, it can always contain superclass A
  // Can go more broadly than original class
  // +A : for covariant list
  class CovariantList[+A]

  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) -> Will this work?
  // This will turn the list of cats into list of animals

  // 2. No, List[Cat] shouldn't extend List[Animal] = INVARIANCE
  class InvariantList[A]
  // only this will work
  val invariantList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. HELL NO = CONTRAVARIANT
  // can go specific compared to original class
  // If a list can contain a supertype A, it can always contain subclass B
  // more wider application. Super Classes can make a list for the class defined bases on the subclass
  class Trainer[-A]

  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  // upper bound: class Cage only Accepts A which is Animal class and subtypes of Animal
  class Cage[A <: Animal](Animal: A)

  val cage = new Cage(new Dog)
  val cageWithAnimal = new Cage(new Animal)

  class Car
  // below won't work
  //  val newcage = new Cage(new Car)

  // lower bound: cage only accepts something that is equivalent or a super type of Cat
  class CageSuperTypes[A >: Cat](animal: A)
  // only cat or animal will work
  val cage2 = new CageSuperTypes(new Cat)

  /*
  * 1. Generic trait MyPredicate[-T] with a little method test(T) => boolean
  * 2. Generic trait MyTransformer[-A, B]: Converts A to B   with a method transform(A) => B
  * 3. My List:
  *   - map (transformer) => MyList
  *   - filter(predicate) => MyList
  *   - flatMap(transformer from A to MyList[B]) => MyList[B]
  *
  * - class EvenPredicate extends MyPredicate[Int]
  * - class StringToIntTransformer extends MyTransformer[String, Int]
  * [1,2,3].map(n * 2) = [2, 4, 6]
  * [1,2,3,4].filter(n%2) = [2,4]
  * [1,2,3].flatMap(n=> [n, n+1]) => [[1,2], [2,3], [3,4]]
  *
  *
  * */

}
