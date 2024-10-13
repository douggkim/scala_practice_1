package lectures.part2.oop

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, age: Int) {
    def likes(movie: String): Boolean = movie.toLowerCase() == favoriteMovie.toLowerCase()

    def hangsOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    // you can name methods with +, - or other reserved methods
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def +(nickName: String): Person = new Person(name = nickName + " (the rockstar)", favoriteMovie = this.favoriteMovie, age = this.age)

    def unary_+ : Person = new Person(name = this.name, favoriteMovie = this.favoriteMovie, age = this.age + 1)

    def learns(topic:String): String = s"${this.name} learns $topic!"

    def learnsScala: String = this.learns(topic = "Scala")

    def apply(times:Int): String = s"${this.name} watched ${this.favoriteMovie} ${times} times!"

    def unary_! : String = s"What the hell!"

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name, and I like $favoriteMovie"
  }

  val mary = new Person("Mary", "Inception", age=28)
  // Infix notation = operator notation
  // This is an example of syntactic sugars
  // only works with methods with single parameter
  println(mary.likes("inception"))
  println(mary likes "Inception") // equivalent

  val tom = new Person("Tom", "Fight Club", 29)
  println(mary hangsOutWith tom) // works like an operator yielding the third thing
  println(mary + tom)


  // All operators are methods
  println(1 + 2)
  println(1.+(2)) // same result

  // Akka actors have ? !
  //prefix notation
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-
  // unary_prefix only works with - + ! ~

  // unary operators
  println(!mary)

  // postfix notation
  // functions that does not receive any params can work as postfix notations
  println(mary.isAlive)
  println(mary isAlive) // but not used often as it can confuse readers


  // 'apply'
  println(mary.apply())
  println(mary()) // equivalent

  println(mary(2))
  println(mary.+("the bird").name)
  val olderMary = +mary
  println(olderMary.name)
  println(olderMary learnsScala)

  /*
  * 1. overload the + operator
  *   mary + "the rockstar" => new Person "Mary (the rockstar)"
  * 2. add an age to the Person Class with default 0 value
  *   add a unary + operator => new person with the age + 1
  *   +mary => mary with the age incrementor
  *
  * 3. add a "learns" method in the class => Mary learns Scala
  *   add a leanrsScala method which doesn't receive any params and calls the learns method with "Scala".
  *   with postfix notation
  *
  * 4. overload the apply method
  *   mary.apply(2) => "Mary watched Inception 2 times"
  *
  * 3.
  * */


}
