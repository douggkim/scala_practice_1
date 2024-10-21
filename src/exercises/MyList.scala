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


abstract class MyList[+A] {
  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyList[B]

  def printElements: String

  // Changed to use printElements without parameters
  override def toString: String = "[" + printElements + "]"

  // higher-order functions
  // receives functions as parameters or return a function
  def map[B](transformer: A => B): MyList[B]

  def filter(predicate: A => Boolean): MyList[A]

  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def ++[B >: A](list: MyList[B]): MyList[B]

}

// Putting Nothing as it can be subtypes of every other types
case object EmptyList extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyList[B] = NonEmptyList(element, EmptyList)

  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = EmptyList

  def filter(predicate: Nothing => Boolean): MyList[Nothing] = EmptyList

  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = EmptyList

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list


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

  def map[B](transformer: A => B): MyList[B] = NonEmptyList(transformer(h), t.map(transformer))

  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) NonEmptyList(h, t.filter(predicate))
    else t.filter(predicate)

  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  def ++[B >: A](list: MyList[B]): MyList[B] = new NonEmptyList(h, t ++ list)

}

object ListTest extends App {
  val listOfIntegers: MyList[Int] = NonEmptyList[Int](1, new NonEmptyList[Int](2, new NonEmptyList[Int](3, EmptyList)))
  val listOfStrings: MyList[String] = NonEmptyList[String]("Hello", new NonEmptyList[String](("Scala"), EmptyList))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)
  val oneElementList = NonEmptyList(1, EmptyList)
  println(oneElementList.head) // expected to print 1
  val list = NonEmptyList(1, NonEmptyList(2, NonEmptyList(3, EmptyList)))
  println(list.tail.head) // should see 2
  println(list.add(4).head) // should see 4
  println(list.add(4).toString) // [4 1 2 3]
  println(list.isEmpty) // should print false

  val doubleTransformer = new Function1[Int, Int] {
    def apply(a: Int): Int = a * 2
  }
  println(listOfIntegers.map((elem: Int) => elem * 2))
  println(listOfIntegers.map(_ * 2))
  val evenPredicate = new Function1[Int, Boolean] {
    def apply(a: Int): Boolean = a % 2 == 0
  }
  println(listOfIntegers.filter((elem: Int) => elem % 2 == 0))
  println(listOfIntegers.filter(_% 2 == 0))

  val flatMapPlusOne = new Function1[Int, MyList[Int]] {
    override def apply(a: Int): MyList[Int] = new NonEmptyList[Int](a, new NonEmptyList[Int](a + 1, EmptyList))
  }
  // this one's not replaceable using _ as _ is used twice
  println(listOfIntegers.flatMap((elem: Int) => new NonEmptyList(elem, new NonEmptyList(elem + 1, EmptyList))))
}