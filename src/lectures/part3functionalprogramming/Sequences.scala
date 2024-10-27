package lectures.part3functionalprogramming

import java.util.Random

object Sequences extends App {
  /*Sequence: a general interface for data structures that
  - have a well defined order
  - can be indexed

  Support various operations
  - apply, iterator, length, reverse for indexing and iterating
  - concatenation, appending, prepending
  - a lot of others: grouping, sorting, zipping, searching, slicing
  * */

  val aSequence = Seq(1, 3, 4, 2)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2)) //.get(2) -> get value at index 2
  println(aSequence ++ Seq(5, 6, 7)) // (1,2,3,4,5,6,7
  println(aSequence.sorted) // works when type is default ordered?


  println("=== Ranges ===")
  // Ranges
  val aRange: Seq[Int] = 1 to 10 // 1 until 9
  aRange.foreach(println)
  (1 to 10).foreach(x => println("the number is: " + x))

  println("=== List ===")
  /* List: A linearSeq immutable linked list
  - head, tail, isEmpty methods are fast: O(1)
  - most operations are O(n): length, reverse

  Sealed - has two subtypes:
  - object Nil (empty)
  - class ::
  * */

  val aList = List(1, 2, 3, 4)
  val prepended = 42 :: aList // = +:
  println(prepended)
  val postPended = aList :+ 43
  println(postPended)

  val apples5 = List.fill(5)("apple") // curried function that takes a value and broadcast it five times
  println(apples5)
  println(apples5.mkString("-🍎-"))


  println("=== Arrays ===")
  /*Array: The equivalent of simple java arrays
  * - can be manually constructed with predefined lengths
  * - can be mutated (updated in place)
  * - are interoperable with Java's T[] arrays
  * - indexing is fast
  * */
  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[Int](3) // allocates space for three Integers
  println(threeElements)
  threeElements.foreach(println) // will print 0 for Int, null for String

  //mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2,0)
  println(numbers.mkString(" "))

  // arrays and Seq
  val numbersSeq: Seq[Int] = numbers //implicit conversion
  println(numbersSeq) // converted to WrappedArray

  println("=== Vectors ===")
  /* Vectors: default implementation for immutable sequences
  - effectively constant indexed read and write: O(log32(n))
  - fast element addition: append/ prepend
  - implemented as a fixed-branched trie (branch factor 32)
  - good performance for large sizes
  * */

  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  // vectors vs lists
  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random // random number generator
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      // operation
      collection.updated(r.nextInt(maxCapacity), 0) // put in 0 at a random location
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector
  val numbersSeq2 = (1 to maxCapacity)

  // if updating an element in the first, end of the list, it will be fast
  // updating an element in the middle takes long
  // because it keeps reference to tail
  println(getWriteTime(numbersList))
  // depth of the tree is small
  // needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector))

  // middle performance 
  println(getWriteTime(numbersSeq2))
}
