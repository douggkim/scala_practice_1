package lectures.part1basics

object _1ValuesVariablesTypes extends App {

  // types of Vals are inferrable as it won't change
  // compilers can infer types
  private val x: Int = 42
  println(x)

  //  Semicolons are allowed
  val aString: String = "hello";
  val anotherString = "goodbye"

  val aBoolean: Boolean = false
  // chars are wrapped by one quotation mark
  val aChar: Char = 'a'
  val anInt: Int = x
  //big numbers can't go into Int variables
  //  val aShort: Short = 46135
  // Longs need to include L at the end
  val aLong: Long = 590130928509125L
  // floats need `f` at the ned
  // if not it's counted as a double
  val aFloat: Float = 2.0f
  // Doubles don't need a specific notation
  val aDouble: Double = 3.14

  // variable
  var aVariable: Int = 4
  // can be reassigned
  aVariable = 5 // can introduce  side effects
  //  Side Effects:
  //A side effect occurs when a function modifies some state outside its local scope or has an observable interaction with the outside world beyond returning a value.


  // val cannot be reassigned
  // VALS ARE IMMUTABLE
  // x = 2


}
