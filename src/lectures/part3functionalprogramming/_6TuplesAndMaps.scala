package lectures.part3functionalprogramming

object _6TuplesAndMaps extends App {
  // tuples = finite ordered "lists"
  val aTuple = new Tuple2(2, "hello, Scala") // Tuple2[Int, String] = (Int, String)
  val aTuple2 = (2, "hello, Scala") // syntactic sugar

  // can group at most 22 types together
  // because of function types (there are 22 function types)
  println(aTuple)
  println(aTuple._1) // first element
  println(aTuple.copy(_2 = "goodbye Java")) // (2, "goodbye Java"))
  println(aTuple.toString())
  println(aTuple.swap) // "hello, scala", 2)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phoneBook = Map(("Jim", 555), ("Daniel" -> 789), ("JIM" -> 999)) // syntactic sugar : "Daniel" , 789
  // a-> b is a sugar for (a,b)
  println(phoneBook)

  println(phoneBook.contains("Jim")) // true
  println(phoneBook("Jim"))
  //  println(phoneBook("Mary")) // this will crash -> causes exception
  val safePhoneBook = Map(("Jim", 555), ("Daniel" -> 789)).withDefaultValue(null) // syntactic sugar : "Daniel" , 789
  println(safePhoneBook("Mary"))

  // add a new pairing
  val newPairing = "Mary" -> 678
  val newPhoneBook = phoneBook + newPairing
  println(newPhoneBook)

  // functionals on maps
  // map, flatMap, filter
  // Jim -> 555 , JIM -> 999 => No error, overridden by the later entry
  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(phoneBook.filterKeys(key => key.startsWith("J")))

  // mapValues
  println(phoneBook.mapValues(number => number * 10))
  println(phoneBook.mapValues(number => "0245" + number))

  //conversions to other collections
  println(phoneBook.toList) // list of Tuple
  println(List(("Daniel", 555)).toMap)
  val names = List("Bob", "James","Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0))) // group it by first character

  /*
  1. What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 900 and ran println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))
  2. overly simplified social network based on maps
     Person = String
     Map (name, Friends )
     - add a person to the network
     - remove
     - friend (mutual)
     - unfriend

     - number of friends of a person
     - person with most friends
     - how many people have NO friends
     - if there is a social connection between two people (direct or not)
  * */

}
