package lectures.part3functionalprogramming

import scala.annotation.tailrec

object HOFsCurries extends App {
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

  // higher order function (HOF)
  // map, flatMap, filter in MyList

  // function that applies another function n times over a given value
  // nTimes (f, n, x)
  // nTime(f,x, 3) => call 3 times application of f over x  => f(f(f(x)))

  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))
  }

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 0))

  // nTimesBetter(f,n) = x=> f(f(f...(x)))
  // increment10 = ntb(plusone, 10) = x+> plusOne(plusOne...(x))
  // val y = increment10(1)
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else x => nTimesBetter(f, n - 1)(f(x))

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

  // curried functions
  val superAdder: Int => (Int => Int) = (x: Int) => ((y: Int) => x + y)

  val add3 = superAdder(3) // y => 3 + y
  println(add3(10))
  println(superAdder(3)(10))

  // functions with multiple parameter lists
  def curriedFormatter(c: String)(x:Double): String = c.format(x)

  // you have to specify types for smaller functions
  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double=> String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  /*
  * 1. Expand MyList
  * - foreach method  A => Unit (apply it to all the lists)
  *   [1,2,3].foreach(x=> println(x))
  *
  * - sort Function ((A,A) => Int) => MyList
  *   [1,2,3].sort((x,y) => y-x) => [3,2,1]
  *
  * - zipWith Function (list, (A,A) => B) => MyList(B)
  *   [1,2,3].zipWith([4,5,6], x*y) => [4,10,18]
  *
  * - fold(start)(function) => a value
  *   [1,2,3].fold(0)(x+y) => 6
  *
  * 2. toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
  *    fromCurry(Int => Int => Int) => ((Int, Int) => Int )
  *
  * 3. compose(f,g) => f(g(x))
  *    andThen(f,g) => g(f(x))
  *
  * */
}


