package lectures.part3functionalprogramming

object WhatsFunction extends App {
  // DREAM: use functions as first class elements (as variables)
  // Problem: JVM is designed based on OOP

  // => All Scala Functions are objects
  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  // you can call doubler like a function (call class functions)
  println(doubler(2))

  // function types = Function1[A,B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }
  println(stringToIntConverter("4"))

  // syntactic sugar saying the function takes two Ints and returns an Int
  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // FUnction  types Function2[A,B,R] === (A,B) => R

  // ALL Scala FUNCTIONS ARE OBJECTS

  /*
  * 1. a function which takes 2 strings and concatenates them
  * 2. transform the MyPredicate and MyTransformer into function types
  * 3. define a function which takes an argument an int and returns another function which takes an int and returns an int
  * - what's the type of this function
  * - how to do it?
  * */
  val aFunction: ((String, String) => String) = new Function2[String, String, String] {
    override def apply(firstString: String, secondString: String) = firstString + secondString
  }

  val superAdder: (Int) => Function1[Int, Int] = new Function1[Int, Function1[Int, Int]] {
    override def apply(myInt: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = myInt + y
    }
  }

  val adder3 = superAdder(3)
  println(adder3(4)) // 7
  println(superAdder(3)(4)) //also 7 => This is called curried function (multiple parameter list)


}

// to use function execute, you'll have to instantiate this class
// at most an object oriented language would do
trait MyFunction[A, B] {
  def apply(element: A): B = ???
}
