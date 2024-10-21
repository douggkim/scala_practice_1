package lectures.part2oop.oop

import lectures.part1basics.CBNvsCBV.infinite

import java.nio.channels.OverlappingFileLockException

object Exceptions extends App {

  val x: String = null
  //    println(x.length())  // will crash
  // ^^ will crash with a NPE

  //throwing and catching exceptions

  //  throw new NullPointerException() // crashing the program intentionally
  // Exception is a class
  //  val aWeirdValue = throw new NullPointerException // this will return Nothing

  // throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes

  // 2. how to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  // return type : Int won't work below because catch block returns Unit
  val potentialFail = try {
    // code that might throw
    getInt(true)
  } catch {
    case e: RuntimeException => println("Caught a runtime exception")
    //      case e: NullPointerException => println("Caught a null pointer exception") //will crash
  } finally {
    // code will get executed NO MATTER WHAT
    // optional
    // does not influence the return type of this expression
    // use finally only for side effects e.g. logging
    println("Finally")
  }

  println(potentialFail)

  // 3. how to define your own exceptions
  class MyException extends Exception

  val exception = new MyException
  // below will crash the program
  //  throw exception

  /*
  * 1. crash your program with an OutOfMemoryError
  * 2. crash with Stack Over Flow Error
  * 3. PocketCalculator
  *   - adds(x: Int, y: Int)
  *   - multiply(x,y)
  *   - subtract(x,y)
  *   - divide(x,y)
  *
  *   Throw
  *   - OverflowException if add(x,y) exceeds Int.MAX_VALUE
  *   - UnderflowException if subtract(x,y) exceeds Int.MAX_VALUE
  *   - MathCalculationsException for division by 0
  * */
  // 1. Throw outOfMemoryError
  //  throw new OutOfMemoryError()
  //  val array = Array.ofDim(Int.MaxValue)
  // 2. Throw StackOverflow Error
  //  throw new StackOverflowError()
  //   val noLimit = infinite


  class OverflowException extends RuntimeException

  class UnderflowException extends RuntimeException

  class MathmCalculationException extends RuntimeException("Division by Zero")

  object Calculator {
    @throws[OverflowException]
    @throws[UnderflowException]
    def add(x: Int, y: Int): Int = {
      val result: Int = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }
    // this works as well
    def multiply(x: Int, y: Int): AnyVal = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException

    }

    def subtract(x: Int, y: Int): AnyVal = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int): AnyVal = {
      if (y == 0) throw new MathmCalculationException
      else x / y
    }
  }

  //  println(Calculator.add(Int.MaxValue, 1)) // => OverflowException
  //  Calculator.divide(10,0) // => MathCalculationException

}
