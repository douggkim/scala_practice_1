package exercises

import scala.annotation.tailrec

/*
  * 1. Generic trait MyPredicate[-T] with a little method test(T) => boolean
  * 2. Generic trait MyTransformer[-A, B]: Converts A to B   with a method transform(A) => B
  * 3. My List:
  *   - map (transformer) => MyList
  *   - filter(predicate) => MyList
  *   - flatMap(transformer from A to MyList[B]) => MyList[B]
  *
  * - class EvenPredicate extends MyPredicate[Int]
  * - class StringToIntTransformer extends MyTransformer[String, Int]
  * [1,2,3].map(n * 2) = [2, 4, 6]
  * [1,2,3,4].filter(n%2) = [2,4]
  * [1,2,3].flatMap(n=> [n, n+1]) => [[1,2], [2,3], [3,4]]
  *
  *
  * */

trait MyPredicate[-T] {
  def test(t: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(a: A): B
}

class EvenPredicate extends MyPredicate[Int] {
  def test(n: Int): Boolean = n % 2 == 0
}

class StringToIntTransformer extends MyTransformer[String, Int] {
  def transform(stringInput: String): Int = stringInput.toInt
}

abstract class MyList[+A] {
  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyList[B]

  def printElements: String

  // Changed to use printElements without parameters
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: MyTransformer[A, B]): MyList[B]

  def filter(predicate: MyPredicate[A]): MyList[A]

  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]

  def ++[B >: A](list: MyList[B]) : MyList[B]

}

// Putting Nothing as it can be subtypes of every other types
case object EmptyList extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyList[B] = NonEmptyList(element, EmptyList)

  def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = EmptyList

  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = EmptyList

  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = EmptyList

  def ++[B >: Nothing](list: MyList[B]) : MyList[B] = list


}

case class NonEmptyList[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyList[B] = NonEmptyList(element, this)

  @tailrec
  private def printElementsTailRec[B >: A](currentList: MyList[B], accumulator: String): String = currentList match {
    case EmptyList => accumulator.trim
    case NonEmptyList(h, t) => printElementsTailRec(t, accumulator + h + " ")
  }

  // Public method that initiates the tail recursion
  def printElements: String = printElementsTailRec(this, "")

  def map[B](transformer: MyTransformer[A, B]): MyList[B] = NonEmptyList(transformer.transform(h), t.map(transformer))

  def filter(predicate: MyPredicate[A]): MyList[A] =
    if (predicate.test(h)) NonEmptyList(h, t.filter(predicate))
    else t.filter(predicate)

  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)

  def ++[B>: A](list:MyList[B]): MyList[B] = new NonEmptyList(h, t ++ list)

}

object ListTest extends App {
  val listOfIntegers: MyList[Int] = new NonEmptyList[Int](1, new NonEmptyList[Int](2, new NonEmptyList[Int](3, EmptyList)))
  val listOfStrings: MyList[String] = new NonEmptyList[String]("Hello", new NonEmptyList[String](("Scala"), EmptyList))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)
  val oneElementList = NonEmptyList(1, EmptyList)
  println(oneElementList.head) // expected to print 1
  val list = NonEmptyList(1, NonEmptyList(2, NonEmptyList(3, EmptyList)))
  println(list.tail.head) // should see 2
  println(list.add(4).head) // should see 4
  println(list.add(4).toString) // [4 1 2 3]
  println(list.isEmpty) // should print false

  val doubleTransformer = new MyTransformer[Int, Int] {
    def transform(a: Int): Int = a * 2
  }
  println(listOfIntegers.map(doubleTransformer))
  val evenPredicate = new MyPredicate[Int] {
    def test(a: Int): Boolean = a % 2 == 0
  }
  println(listOfIntegers.filter(evenPredicate))

  val flatMapPlusOne = new MyTransformer[Int, MyList[Int]] {
    override def transform(a: Int): MyList[Int] = new NonEmptyList[Int](a, new NonEmptyList[Int](a+1, EmptyList))
  }
  println(listOfIntegers.flatMap(flatMapPlusOne))
}