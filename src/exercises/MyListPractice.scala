package exercises

abstract class MyList {
  /*
  * singly linked list
  * head = first element of the list
  * tail = remainder of the list
  * is empty = is this list empty
  * add(int) => a new list with this element added
  * toSting (have to override) => a string representation of the list
  *  */

  def head: Int

  def tail: MyList

  def isEmpty: Boolean

  def add(element: Int): MyList

  def printElements: String

  override def toString(): String = "[" + printElements + "]"

}

object emptyList extends MyList {

  override def head: Int = throw new NoSuchElementException

  override def tail: MyList = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add(element: Int): MyList = new nonEmptyList(h = element, t = emptyList)

  override def printElements: String = ""
}


class nonEmptyList(h: Int, t: MyList) extends MyList {

  override def head: Int = this.h

  override def tail: MyList = this.t

  override def isEmpty: Boolean = false

  override def add(element: Int): MyList = new nonEmptyList(h = element, t = this)

  override def printElements: String =
    if (this.tail.isEmpty) "" + h
    else this.head + " " + this.tail.printElements
}

object ListTest extends App {
  val oneElementList = new nonEmptyList(1, emptyList)
  println(oneElementList.head) // expected to print 1
  val list = new nonEmptyList(1, new nonEmptyList(2, new nonEmptyList(3, emptyList)))
  println(list.tail.head) // should see 2
  println(list.add(4).head) // should see 4
  println(list.add(4).toString) // 4 1 2 3
  println(list.isEmpty) // should element false
}