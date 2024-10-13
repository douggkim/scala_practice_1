package exercises

import scala.annotation.tailrec

abstract class MyList[+A] {
  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyList[B]

  def printElements: String

  // Changed to use printElements without parameters
  override def toString: String = "[" + printElements + "]"
}

case object EmptyList extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyList[B] = NonEmptyList(element, EmptyList)

  def printElements: String = ""
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
}