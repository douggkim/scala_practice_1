package exercises

abstract class Mock[+T] {

  def map[B](f: T => B): Mock[B]

  def flatMap[B](f: T => Mock[B]): Mock[B]

  def filter(p: T => Boolean): Mock[T]

}


case object emptyMock extends Mock {


  override def map[B](f: Nothing => B): Mock[B] = emptyMock

  override def flatMap[B](f: Nothing => Mock[B]): Mock[B] = emptyMock

  override def filter(p: Nothing => Boolean): Mock[Nothing] = emptyMock
}

case class nonEmptyMock[+T](value: T) extends Mock[T] {

  override def map[B](f: T => B): Mock[B] = nonEmptyMock(f(value))

  override def flatMap[B](f: T => Mock[B]): Mock[B] = f(value)

  override def filter(p: T => Boolean): Mock[T] =
    if (p(value)) this
    else emptyMock
}


object mockTest extends App {

  val testMock = nonEmptyMock(3)
  println(testMock.map(x => x * 2))
  println(testMock.filter(x => x %2 == 0 ))
  println(testMock.flatMap(x => nonEmptyMock(x+1)))

}