package lectures.part1basics

object CBNvsCBV extends App {
  def calledByValue(x: Long): Unit = {
    println("by value: " + x) // 469517901275333
    println("by value: " + x) // 469517901275333
  }

  // expression is called realtime as it's executed
  def calledByName(x: => Long): Unit = {
    println("by name: " + x) // System.nanoTime()  called again
    println("by name: " + x) // System.nanoTime() called again
  }

  calledByValue(System.nanoTime()) // prints one value and printed twice
  calledByName(System.nanoTime()) // print different times, but printed twice


  def infinite(): Int = 1 + infinite()
  def printFirst(x:Int, y: => Int) = println(x)

//  printFirst(infinite(), 34) -> Will fail
  printFirst(34, infinite()) // will run -> because inifinte() will never be evaluated

}
