package lectures.part2oop.oop

object CaseClasses extends App {
  /*
  * equals, hashCode, toString
  * */
  // 1. class paramaters are promoted to fields
  case class Person(name: String, age: Int)

  val jim = new Person("Jim", 34)
  println(jim.name)

  // 2. Sensible toString
  // println(caseClassInstance) = println(caseClassInstance.toString)
  println(jim.toString) // Person(Jim.34)
  println(jim) // Person(Jim, 34)

  // 3. equals and hashCode implemented out of the box
  val jim2 = new Person("Jim", 34)
  println(jim == jim2) // true

  // 4. Case Classes have handy copy methods
  val jim3 = jim.copy()
  println(jim == jim3) // also true

  val jim4 = jim.copy(age=45) // creates a similar instance with a different field

  // 5. Case Classes have companion objects
  // thePerson is a companion object
  // companion object's apply() function
  val thePerson = Person
  val mary = Person("Mary", 23) // apply method

  // 6. CCs are serializable
  // you can send the CCs through networks

  // 7. Case Classes have extractor patterns
  // Case classes can be used for PATTERN MATCHING

  // we also have case objects
  // They don't get companion objects
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }


}
