package lectures.part3functionalprogramming

import java.util.Random

object _7Options extends App {
  // We should never do null check ourselves
  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)
  println(noOption)

  // null safe way to process maps
  val map = Map("key" -> "value")
  map.get("key") // Some(value)
  map.get("other") // None

  val numbers = List(1, 2, 3)
  numbers.headOption // Some(1)
  numbers.find(_ % 2 == 0) // Some(2)

  // Options were designed to deal with unsafe APIs
  // options: a wrapper for a value that might be present or not
  def unsafeMethod(): String = null

  val result = Some(unsafeMethod()) // WRONG -> Some might have null value
  // above breaks the whole point of having Options
  // converts null to None automatically!!
  val result2 = Option(unsafeMethod()) // Some or None depending on whether this value is null or not

  println(result)
  println(result2) // we'll get None here

  // chained methods
  def backupMethod(): String = "A valid result"

  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod())) // how you work with unsafe APIs
  // above: if `unsafeMethod()` fails, use `backupMethod()`

  // Design unsafe APIs
  def betterUnsafeMethod(): Option[String] = None

  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterCXhainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // getting literal value from the variable.  UNSAFE -> will throw null pointer error

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for comprehensions
  /*
  * Exercise
  *
  * */
  val config: Map[String, String] = Map(
    // fetched from elsewhere -> might or might not be there
    "host" -> "176.128.45.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] = {
      if (random.nextBoolean()) Some(new Connection)
      else None
    }
  }

  val host = config.get("host")
  val port = config.get("port")

  /*
  * if (h!= null)
  *   if (p!= null)
  *     return Connection.apply(h, p)
  *
  * return null
  * */
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))
  /* if (c != null)
        return c.connect
     else return null
  * */
  val connectionStatus = connection.map(c => c.connect)

  // if (connectionStatus == null) println(None) else print(Some(connectionstatus.get))
  println(connectionStatus)
  /*
  if (status != null)
  println(status)
  else
  Null
  * */
  connectionStatus.foreach(println)
  // try to establish a connection, if so - print the connect method

  // chained calls
  // Why flatMap? Because config.get("port") returns Option[String]
  // If we used map, we'd get Option[Option[String]]!
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // for-comprehensions
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)


}
