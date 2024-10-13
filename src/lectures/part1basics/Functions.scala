package lectures.part1basics

import scala.annotation.tailrec

object Functions extends App {
  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction(a = "Hello", b = 3))

  // parameterless functions without the parentheses ()
  def aParameterLessFunction(): Int = 42

  println(aParameterLessFunction())
  println(aParameterLessFunction)

  // in Scala or Function language
  // When you need loops, use recursion
  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + " " + aRepeatedFunction(aString, n - 1)
  }

  println(aRepeatedFunction(aString = "Hello", n = 3))

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }

  /*
  1. a Greeting function (name, age) => "Hi, my name is $name and I amd $age years old"
  2. Factorial Function 1* 2 * 3 * ... * n
  3. A fibonacci function
  f(1) = 1
  f(2) = 1
  f(n) = f(n-1) + f(n-2)
  4. Test if a number is prime
  * */
  // 1. a greeting function
  def greetingFunction(name: String, age: Int): String = {
    s"Hi, my name is $name and I amd $age years old"
  }

  println(greetingFunction(name = "Doug", age = 32))

  // 2. A Factorial Function
  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else n * factorial(n - 1)
  }

  println(factorial(10))

  // fibonacci
  // fibonacci
  def fibonacci(n: Int): Int = {
    def fibPostive(n: Int): Int = {
      if (n <= 1) n else fibonacci(n - 1) + fibonacci(n - 2)
    }

    if (n >= 0) {
      fibPostive(n)
    } else {
      val positiveResult = fibonacci(-n)
      if (n % 2 == 0) -positiveResult else positiveResult
    }
  }
  // test if a number is prime
  def isPrime(n: Int): Boolean = {
    def isPrimeHelper(targetNum: Int, divisor: Int): Boolean = {
      if (divisor * divisor > targetNum) true
      else if (targetNum % divisor == 0) false
      else isPrimeHelper(targetNum, divisor + 1)
    }

    if (n <= 1) false
    else if (n == 2) true
    else isPrimeHelper(n, 2)
  }

  def isPrime2(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true
      else n % 2 != 0 && isPrimeUntil(t - 1)
    }

    isPrimeUntil(n / 2)
  }

  println(isPrime(67))
  println(isPrime2(67))


}
