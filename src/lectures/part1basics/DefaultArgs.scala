package lectures.part1basics

import scala.annotation.tailrec

object DefaultArgs extends App {
  @tailrec
  def trFact(n: Int, acc: Int = 1): Int = {
    if (n <= 1) acc
    else trFact(n - 1, acc * n)
  }

  val fact10 = trFact(10)
  println(fact10)
  // leading parameters cannot use defaultArgs
  def savePicture(width:Int = 300, height:Int =400, format:String = "jpg"): Unit = println(s"saving pictures in $format ($width * $height)")
  savePicture()
  savePicture(format="bmp")
}
