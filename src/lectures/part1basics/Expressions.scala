package lectures.part1basics

object Expressions extends App {
  // the right hand side is expression
  val x = 1 + 2
  println(x)


  println(2 + 3 * 4)
  // order of execution + - * / & | ^ << >> >>> (last one: right shift with zero extension)

  println(3 == x)
  // == != > >= < <=

  println(!(3 == x))
  // ! && ||

  var aVariable = 2
  aVariable += 3 // also works with -= *= /=
  println(aVariable)

  // Instructions (DO) va Expressions (VALUE)
  // Instructions are executed, expressions are evaluated
  // Python : Instructions
  // Scala : Expressions
  // e.g. IF expression : if in scala is an expression
  val aCondition = true
  val aConditionedValue = if (aCondition) 5 else 3 // IF Expression

  println(aConditionedValue)
  println(if (aCondition) 5 else 3) // this will work
  println(1 + 3)

  // Loops: not encouraged in Scala
  // NEVER WRITE THIS AGAIN
  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }
  // Everything in Scala is an Expression!
  // Below variable's type will be unit, equivalent to void in Java
  // this will result in `()`
  // Side effects in Scala returns a unit
  // why is side effect not encouraged?
  //  Why it matters:
  //
  //  Predictability: Functions with side effects like println are less predictable because their behavior depends on more than just their inputs.
  //  Testing: Side effects can make testing more difficult because you need to capture or mock the output.
  //  Functional programming: In functional programming, we often try to minimize or isolate side effects for better reasoning about code.
  //  Concurrency: Side effects can lead to issues in concurrent programming if not managed carefully.
  val aWeirdValue = (aVariable = 3)
  println(aWeirdValue)

  // side effects: println(), whiles, reassigning
  // why println()? you're interacting with I/O operation, which is an external environment

  // Code Blocks
  // everything inside the code block is scoped to its own local
  // can't println(z) outside the code block below
  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }
  println(aCodeBlock)

  // 1. what is the difference between "hello world" vs println("hello world")?
  // "hello world": String
  // println("hello world") : expression, result in unit, side effect (expressions returning units)
  // 2. Value of the below code block
  // Boolean: true
  val someValue = {
    2 < 3
  }
  // Int: 42
  val someOtherValue = {
    if(someValue) 239 else 986
    42
  }


}
