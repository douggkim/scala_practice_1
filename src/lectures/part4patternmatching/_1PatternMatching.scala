package lectures.part4patternmatching

import java.util.Random

object _1PatternMatching extends App {
  //switch on steroids
  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "the One"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    case _ => "I'm not interested"
  }

  println(x)
  println(description)

  case class Person(name: String, age: Int)

  val bob = Person("Bob", 20)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I am a minor"
    case Person(n, a) => s"Hi, my name is $n and I am $a years old"
    case _ => "I don't know who I am"
  }

  println(greeting)
  /*
  * 1. cases are matched in order
  * 2. what if no cases match? => A MatchError
  * 3. type of pattern matching expression? String if all string, Any if mixed (unified type of all the types in all the cases)
  * 4. PM works really well with case classes
  * */

  // PM on sealed hierarchies
  sealed class Animal

  case class Dog(breed: String) extends Animal

  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Terra Nova")
  // compiler will fail with seal class if it doesn't cover all the cases
  animal match {
    case Dog(someBreed) => println(s"a dog of $someBreed")
  }

  // match everything
  val isEven = x match {
    case x if x % 2 == 0 => true
    case _ => false
  }

  // WHY?!
  val isEvenCond = if (x % 2 == 0) true else false
  val isEvenNormal = x % 2 == 0
  /*
  * Exercise
  * Simple function uses PM
  *  takes an Expr => human readable form
  *
  * SUM(Number(2), Number(3)) => 2 + 3
  * SUM(Number(2), Number(4), Number(4)) => 2+ 3 + 4
  * Prod(Sum(Number(2), Number(1)), Number(3)) => (2+1) * 3
  * Sum(Prod(Number(2), Number(1)), Number(3)) => 2 * 1 + 3
  * */

  trait Expr

  case class Number(n: Int) extends Expr

  case class Sum(e1: Expr, e2: Expr) extends Expr

  case class Prod(e1: Expr, e2: Expr) extends Expr

  def readableCalculation(expression: Expr): Any = {
    expression match {
      case Number(n) => s"$n"
      case Sum(e1, e2) => readableCalculation(e1) + "+" + readableCalculation(e1)
      case Prod(e1, e2) => {
        def maybeShowParenthese(exp: Expr) = exp match {
          case Prod(_, _) => readableCalculation(exp)
          case Number(_) => readableCalculation(exp)
          case _ => "(" + readableCalculation(exp) + ")"
        }
        maybeShowParenthese(e1) + "*" + maybeShowParenthese(e2)
      }
    }
  }


}
