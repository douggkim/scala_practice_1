package lectures.part3functionalprogramming

object AnonymousFunctions extends App {
  val doubler = new Function1[Int, Int] {
    override def apply(x: Int): Int = x * 2
  }

  // instantiates a Function1
  // overrides apply
  // Lambda: This is a syntactic sugar
  val doubler2: Int => Int = (x) => x * 2
  val doubler3 = (x: Int) => x * 2

  // multiple parameters in lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  println(doubler2(3)) // 6

  // no params
  val justDoSomething = () => 3
  val justDoSomething2: () => Int = () => 3

  // for lambdas, you have to include ()
  println(justDoSomething) // the function itself
  println(justDoSomething()) // 3

  // curly braces with Lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MOAR syntactic sugar
  val niceIncrementer: Int => Int = (x: Int) => x + 1
  val niceIncrementer2: Int => Int = _ + 1
  val niceAdder: (Int, Int) => Int = _ + _ // each underscore is a different parameter

  /*
  * 1. MyList: replace all FunctionX calls with lambdas
  * 2. Rewrite the "special" adder (the curried one) as an anonymous function
  * */

  val superAdder: (Int) => (Int => Int) = (x: Int) => ((y: Int) => x + y)
  val superAdder2 = (x: Int) => ((y: Int) => x + y)


  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) //also 7 => This is called curried function (multiple parameter list)

}
