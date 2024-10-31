package lectures.part4patternmatching

object _3PatternsEverywhere extends App {
  //big idea # 1
  try {
    // code
  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }

  // catches are actually matches
  /*
  * try {
  * // code
  * } catch(e) {
  * e match{
  *   case e: ...
  * }}}
  * */

  // big idea #2
  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x % 2 == 0 //?!
  } yield 10 * x

  // generators are also PATTERN Matching
  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second

  // case classes, :: operators ...

  // big idea #3
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple // using name binding
  println(a) // 1
  // multiple value definition based on PATTERN MATCHING

  // below is a cons
  // used for lists - adding and deconstructing
  val head :: tail = list
  println(head) // 1
  println(tail) // 2,3,4

  // big idea #4 - NEW
  // partial function
  // it's like list.map { x => x match { ...
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even "
    case 1 => "the one"
    case _ => "something else"
  } // partial function literal

  val mappedList2 = list.map {
    x => x match {
      case v if v%2 ==0 => v + " is even"
      case 1 => "the one"
      case _ => "something else"
    }
  }

}
