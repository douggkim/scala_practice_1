package lectures.part4patternmatching

import exercises.{MyList, NonEmptyList, EmptyList}

object _2AllThePatterns extends App {
  // 1- constants
  val x: Any = "Scala"

  // can handle different data types
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "THE scala"
    case true => "The Truth"
    case _2AllThePatterns => "A singleton object"
  }
  // 2- match anything
  // 2.1 wildcard

  val matchAnything = x match {
    case _ =>
  }

  // 2.2 variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  // 3. Tuples
  val aTuple = (1, 2)

  val matchATuple = aTuple match {
    case (1, 1) =>
    case (something, 2) => s"I've found $something" // something can be used
  }

  val nestedTuple = (1, (2, 3))
  val matchNestedTuple = nestedTuple match {
    case (_, (2, v)) =>
  }

  // PMs can be NESTED

  // 4 - case classes - constructor pattern
  // PMs can be nested with CCs as well
  val aList: MyList[Int] = NonEmptyList(1, NonEmptyList(2, EmptyList))
  val matchAList = aList match {
    case EmptyList =>
    case NonEmptyList(head, NonEmptyList(subHead, subTail)) =>
  }

  // 5 - List Patterns
  val aStandardList = List(1, 2, 3, 42)
  val standardListMatching = aStandardList match {
    case List(1, _, _, _) => // extractor
    case List(1, _*) => // list of arbitrary length
    case 1 :: List(_) => // infix pattern
    case List(1, 2, 3) :+ 42 => // infix pattern
  }

  // 6 - type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => // checks for List[Int]
    case _ =>
  }

  // 7 - name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ NonEmptyList(_, _) => // name binding use the name later or here...?
    case NonEmptyList(1, rest @ NonEmptyList(2, _)) => // name binding inside patterns as well...
  }


  // 8 - multi-patterns
  val multiPattern = aList match {
    case EmptyList | NonEmptyList(0,_) => // compound pattern (multi-pattern)
  }

  // 9 - if guards
  val secondElementSpecial = aList match {
    case NonEmptyList(_, NonEmptyList(specialElement, _)) if specialElement % 2 == 0 =>
  }

  // ALL

  /*
  * Question
  * */

  val numbers = List(1,2,3)
  val numbersMatch = numbers match {
    case listOfString: List[String] => "a list of strings" // This is the case
    case listOfNumbers: List[Int] => "a list of numbers"
    case _ => ""
  }

  println(numbersMatch)
  // JVM trick question
  // generic types are not supported in the earlier version of Java
  // Therefore, when compile, it will be read as List


}
