package exercises

import scala.annotation.tailrec

abstract class MyList {
  def head: Int

  def tail: MyList

  def isEmpty: Boolean

  def add(element: Int): MyList

  def printElements: String

  // Changed to use printElements without parameters
  override def toString: String = "[" + printElements + "]"
}

case object EmptyList extends MyList {
  def head: Nothing = throw new NoSuchElementException

  def tail: Nothing = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add(element: Int): MyList = NonEmptyList(element, EmptyList)

  def printElements: String = ""
}

case class NonEmptyList(h: Int, t: MyList) extends MyList {
  def head: Int = h

  def tail: MyList = t

  def isEmpty: Boolean = false

  def add(element: Int): MyList = NonEmptyList(element, this)

  @tailrec
  private def printElementsTailRec(currentList: MyList, accumulator: String): String = currentList match {
    case EmptyList => accumulator.trim
    case NonEmptyList(h, t) => printElementsTailRec(t, accumulator + h + " ")
  }

  // Public method that initiates the tail recursion
  def printElements: String = printElementsTailRec(this, "")
}

object ListTest extends App {
  val oneElementList = NonEmptyList(1, EmptyList)
  println(oneElementList.head) // expected to print 1
  val list = NonEmptyList(1, NonEmptyList(2, NonEmptyList(3, EmptyList)))
  println(list.tail.head) // should see 2
  println(list.add(4).head) // should see 4
  println(list.add(4).toString) // [4 1 2 3]
  println(list.isEmpty) // should print false
}