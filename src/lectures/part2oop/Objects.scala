package lectures.part2oop.oop

object Objects {


  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
  // Objects do not receive parameters
  object Person { // type + its only instance
    // "static" / "class" level functionality
    val N_EYES = 2

    def canFly: Boolean = false

    // factory method
    // the whole purpose is to create a new object
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }

  // We usually define object and the class in the same scope
  // This is called COMPANIONS
  class Person(val name: String) {
    // instance-level functionality
  }

  println(Person.N_EYES)
  println(Person.canFly)



  // Scala Applications = Scala Object with
  // def main(args: Array[String]): Unit -> Needs to follow this exact format
  def main(args: Array[String]): Unit = {
    // Scala Object = SINGLETON INSTANCE
    // mary and john point to the same instance
    val mary = Person
    val john = Person
    println(mary == john) // will be true

    val newMary = new Person("Mary")
    val newJohn = new Person("John")
    println(newMary == newJohn) // will be false


    val bobbie = Person(newMary, newJohn)
    println(bobbie.name)
  }


}
