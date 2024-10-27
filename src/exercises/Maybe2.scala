package exercises

abstract class Maybe2[+T] {
  def map[B](f: T => B): Maybe2[B]

  def flatMap[B](f: T => Maybe2[B]): Maybe2[B]

  def filter(p: T => Boolean): Maybe2[T]
}

case object NotMaybe2 extends Maybe2[Nothing] {
  def map[B](f: Nothing => B): Maybe2[B] = NotMaybe2

  def flatMap[B](f: Nothing => Maybe2[B]): Maybe2[B] = NotMaybe2

  def filter(p: Nothing => Boolean): Maybe2[Nothing] = NotMaybe2
}

case class Just2[+T](value: T) extends Maybe2[T] {
  def map[B](f: T => B): Maybe2[B] = Just2(f(value))

  def flatMap[B](f: T => Maybe2[B]): Maybe2[B] = f(value)

  def filter(p: T => Boolean): Maybe2[T] =
    if (p(value)) this
    else NotMaybe2
}


object MaybeTest2 extends App {
  val testJust = Just2(2)
  println(testJust.filter(_ % 2 == 0))

  println(testJust.map(_ * 2))

  println(testJust.flatMap(x => Just2(x)))

}