package lectures.part3functionalprogramming

object _4MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list.head) //1
  println(list.tail) // List(2,3)

  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))
  println(list.map((x) => List(x, x + 1)))

  println(list.filter(_ % 2 == 0))

  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // print all combinations between two lists
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")


  // iterating
  println(numbers.map((x) => (chars.map((y) => List(x.toString, y)))))
  println(numbers.flatMap((x) => (chars.map((y) => x.toString + y))))
  println(numbers.filter(_ % 2 == 0).flatMap((x) =>
    (chars.flatMap((y) =>
      colors.map((color) =>
        "" + x + y + "-" + color)
    ))
  ))

  // foreach
  list.foreach(println)

  //for-comprehensions
  // translated into map and flatMap automatically
  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color

  println(forCombinations)

  for {
    n <- numbers
  } println(n)

  // syntax overload
  list.map { x =>
    x * 2
  }

  /*
  * 1. MyList supports for comprehensions?
  *  map(f: A=> B) => MyList[B]
  * filter(p: A=> Boolean) => MyList[A]
  * flatMap(f: A=> MyList[B]) => MyList[B]
  *
  * 2. A small collection of at most ONE element -> Maybe[+T]
  *   - map, flatMap, filter
  * */



}
