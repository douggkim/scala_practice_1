package lectures.part2oop.oop
import playground.{Cinderella, PrinceCharming => Prince}// you can do ._ to import everything

import java.util.Date
import java.sql.{Date => sqlDate} // you can use aliases

object _9PackagingAndImports extends App {

  //package members are accesible by their simple name
  val writer = new Author("Daniel", "RocktheJVM", 2018)

  // import the pacakage
  val princess = new Cinderella
  val princess2 = new playground.Cinderella //you can use fully qualified names without imports

  // packages are in hierarchy
  // matching the folder structure

  // package object
  // sometimes we want to write constants outside of everything else
  // package objects are visible throughout the entire package
  sayHello
  println(SPEED_OF_LIGHT)

  val prince = new Prince // PrinceCharming is replaced with princess

  // 1. use FQ names
  val d = new Date
  val sqlDate = new java.sql.Date(2018, 5, 5) // full qualified

  // 2. use aliasing
  val sqlDate2 = new sqlDate(2018, 5, 5) // full qualified

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???


}
