package lectures.part1basics

import scala.annotation.tailrec

object _4Recursion extends App {
  // debugging the stack frame of factorial
  // it stacks the stack frame until it figures out the base case
  //  @tailrec : I can not use this here becuase the recursive function is not at the end of the code
  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else {
      println(s"Computing factorial of $n - I first need factorial of ${(n - 1)}")
      val result = n * factorial(n - 1)

      println(s"Computed factorial of $n")
      result
    }
  }

  println(factorial(10))
  // below will probably crash
  // println(factorial(5000))
  //  Stack Frame Reuse:
  //In normal recursion, each call adds a new frame to the call stack. In tail recursion, the compiler reuses the same
  // stack frame for each recursive call. This is possible because the recursive call is the last operation, so there's
  // no need to keep track of the previous state.

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factorialHelper(x: Int, accumulator: BigInt): BigInt = {
      if (x <= 1) accumulator
      else factorialHelper(x - 1, x * accumulator) // TAIL RECURSION = use recursive call as the LAST expression
    }

    factorialHelper(n, 1)
  }
  /*
  * anotherFactorial(10) = factorial(10, 1)
  * = factorial Helper(9, 10*1)
  * = factorialHelper(8, 9*10*1)
  * = factorialHelper(7, 8*9*10*1)
  * = factorialHelper(6, 7*8*9*10*1)
  * = factorialHelper(2, 3*4* ... 8*9*10*1)
  * = factorialHelper(1, 1*2*3... 8*9*10*1)
  * = 1* 2* 3*... *10
  * */

  println(anotherFactorial(5000))

  /*
  * Assignment:
  * Create TAIL RECURSION version of below functions
  * 1. Concatenate a string n times
  * 2. IsPrime function tail recursive
  * 3. Fibonacci function tail recursive
  * */

  // normal version
  def concatStringNTimes(targetString: String, n: Int): String = {
    if (n <= 0) ""
    else if (n == 1) targetString
    else targetString + concatStringNTimes(targetString, n - 1)
  }

  @tailrec
  def anotherConcatStringNTimes(targetString: String, n: Int, accumulator: String): String = {
    if (n == 0) accumulator
    else anotherConcatStringNTimes(targetString, n - 1, accumulator + targetString)
  }

  def time[R](block: => R): (R, Long) = {
    val start = System.nanoTime()
    val result = block
    val end = System.nanoTime()
    (result, end - start)
  }


  val testSizes = List(10, 100, 1000, 10000)

  println("n\tNormal Recursion (ns)\tTail Recursion (ns)")
  for (n <- testSizes) {
    val (_, normalTime) = time {
      concatStringNTimes("Hi", n)
    }
    val (_, tailTime) = time {
      anotherConcatStringNTimes("Hi", n, "")
    }
    println(f"$n\t$normalTime%d\t\t\t$tailTime%d")
  }

  println(concatStringNTimes("Hi", 3))
  println(anotherConcatStringNTimes("Hi", 3, ""))

  //2. IsPrime
  // test if a number is prime
  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeHelper(targetNum: Int, divisor: Int): Boolean = {
      if (divisor * divisor > targetNum) true
      else if (targetNum % divisor == 0) false
      else isPrimeHelper(targetNum, divisor + 1)
    }

    if (n <= 1) false
    else if (n == 2) true
    else isPrimeHelper(n, 2)
  }

      // 3. Fibonacci
      // fibonacci
      def fibonacci(n: Int): Int = {
        @tailrec
        def fibPositive(n: Int, nMinusOne: Int, nMinusTwo: Int): Int = {
          if (n == 0) nMinusTwo
          else if (n == 1) nMinusOne
          else fibPositive(n - 1, nMinusOne + nMinusTwo, nMinusOne)
        }

        @tailrec
        def fibNegative(n: Int, nMinusOne: Int, nMinusTwo: Int, isEven: Boolean): Int = {
          if (n == 0) nMinusTwo
          else if (n == -1) if (isEven) -nMinusOne else nMinusOne
      else fibNegative(n + 1, nMinusTwo - nMinusOne, nMinusOne, !isEven)
    }

    if (n >= 0) {
      fibPositive(n, 1, 0)
    } else {
      fibNegative(n, 1, 0, n % 2 == 0)
    }
  }

  // Test cases
  def testPositiveFibonacci(): Unit = {
    assert(fibonacci(0) == 0, "F(0) should be 0")
    assert(fibonacci(1) == 1, "F(1) should be 1")
    assert(fibonacci(2) == 1, "F(2) should be 1")
    assert(fibonacci(3) == 2, "F(3) should be 2")
    assert(fibonacci(4) == 3, "F(4) should be 3")
    assert(fibonacci(5) == 5, "F(5) should be 5")
    assert(fibonacci(10) == 55, "F(10) should be 55")
    assert(fibonacci(20) == 6765, "F(20) should be 6765")
    println("Positive Fibonacci numbers test passed")
  }

  def testNegativeFibonacci(): Unit = {
    assert(fibonacci(-1) == 1, "F(-1) should be 1")
    assert(fibonacci(-2) == -1, "F(-2) should be -1")
    assert(fibonacci(-3) == 2, "F(-3) should be 2")
    assert(fibonacci(-4) == -3, "F(-4) should be -3")
    assert(fibonacci(-5) == 5, "F(-5) should be 5")
    assert(fibonacci(-10) == -55, "F(-10) should be -55")
    assert(fibonacci(-20) == -6765, "F(-20) should be -6765")
    println("Negative Fibonacci numbers test passed")
  }

  testPositiveFibonacci()
  testNegativeFibonacci()


}
