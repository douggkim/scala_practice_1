package lectures.part1basics

object _7StringOps extends App {
  val str: String = "Hello, I'm learning Scala"

  // string utils from Java
  println(str.charAt(2)) //strings are 0 indexed
  println(str.substring(7, 11)) // start: inclusive, end: exclusive
  println(str.split(" ").toList)
  println(str.startsWith("Hello")) // see if the string starts with "Hello"
  println(str.replace(" ", "!"))
  println(str.toLowerCase)
  println(str.length)

  val aNumberString: String = "45"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z') // prepending +: appending :+
  println(str.reverse)
  println(str.take(2)) // take two characters from the beginning

  // Scala-Specific: String interpolators
  // S-interpolators
  val name: String = "David"
  val age: Int = 12
  val greeting: String = s"Hello my name is $name and I am $age years old"
  println(greeting)
  val greetingFuture: String = s"Hello my name is $name and I'll be turning ${age + 1} years old next year" // s- interpolators can also evaluate expressions
  println(greetingFuture)

  // F-Interpolators
  val speed = 10.2f
  val myth = f"$name can eat $speed%1.3f burgers per minute" // two characters total, two decimals
  //  val mythError = f"$name can eat $speed%3d burgers per minute" // Will cause error as %3d requires Int
  println(myth)

  // raw-interpolator
  println(raw"This is a \n newline") // will just contain '\n'
  val escapedString: String = "This is a \n newline"
  println(escapedString) // will have a line change (escape
  println(s"$escapedString") // still will have a line change

}
