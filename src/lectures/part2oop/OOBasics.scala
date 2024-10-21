package lectures.part2oop.oop

object OOBasics extends App {
  val person = new Person("John", 28)
  println(person) // will get string representation
  println(person.age) // will get 28
  //  printlnt(person.name) will error out
  println(person.x) // will get 2
  person.greet("Daniel")
  person.greet()

  val aBaby = new Person("James") // overloading done
  println(aBaby.age)

  val author1 = new Author(firstName = "Doug", surName = "Kim", birthYear = 1992)
  val author2 = new Author(firstName = "Julian", surName = "Park", birthYear = 1993)
  println(author1.fullName())

  val novel1 = new Novel(
    name = "Adventures",
    yearOfRelease = 2024,
    author = author1
  )

  println(novel1.name)
  println(novel1.authorAgeAtRelease())
  novel1.isWrittenBy(author2)
  println(novel1.author.fullName())
  val novel2: Novel = novel1.createNewCopy(2026)
  println(novel2.yearOfRelease)
  println(novel2.authorAgeAtRelease())

  val counter = new CounterWithImmutability(10)
  counter.incrementCount().print
  counter.incrementCount(3).incrementCount(3).incrementCount(3).print
}

// constructor
class Person(name: String, val age: Int) {
  // class parameters are NOT FIELDS
  // you have to add val or var in front of the parameters
  //body
  // this will be a field
  val x = 2

  println(1 + 3) // will be executed when an object is instantiated

  // use this.{field_name} to use the field of the object
  def greet(name: String): Unit = println(s"${this.name}: Hello $name!")

  //overloading: defining two functions depending on the parameters
  def greet(): Unit = println(s"$name: Hi, I am $name") // this will return the object's name field
  //  def greet(): Int = 42 //NOT OVERLOADING - compiler can't tell which one is which

  // multiple constructors (or Overloading constructors)
  // will overload the existing constructor (which will allow us not to include the age)
  def this(name: String) = this(name = name, age = 0)
}

/*
  Novel and a Writer
  Writer: first name, surname, year
    - method full name : concatenation of first and last name

  Novel: name, year of release, author
    - authorAge: Age of the author at the year of release
    - isWrittenBy(author)
    - copy (new year of release) = new instance of novel with the year of the release
* */

class Author(val firstName: String, val surName: String, val birthYear: Int) {
  def fullName(): String = s"$firstName $surName"
}

class Novel(val name: String, val yearOfRelease: Int, var author: Author) {
  def authorAgeAtRelease(): Int = this.yearOfRelease - author.birthYear

  def isWrittenBy(newAuthor: Author) = {
    this.author = newAuthor
  }

  def createNewCopy(newYearOfRelease: Int): Novel = new Novel(name = this.name, yearOfRelease = newYearOfRelease, author = this.author)
}

/*
* Counter Class
* - recieves an int value
* - method current count
* - method to increment/decrement => new Counter
* - overlad inc/dec to receive an amount*/

class Counter(var count: Int = 0) {
  def incrementCount(): Unit = this.count += 1

  def decrementCount(): Unit = this.count -= 1

  def incrementCount(amountToIncrement: Int): Unit = this.count += amountToIncrement

  def decrementCount(amountToDecrement: Int): Unit = this.count -= amountToDecrement
}

class CounterWithImmutability(val count: Int = 0) {
  def incrementCount(): CounterWithImmutability = {
    println("incrementing...")
    new CounterWithImmutability(this.count + 1)
  }

  def decrementCount(): CounterWithImmutability = {
    println("decrementing....")
    new CounterWithImmutability(this.count - 1)
  }

  def incrementCount(amountToIncrement: Int): CounterWithImmutability = {
    if (amountToIncrement <= 0) this
    else incrementCount.incrementCount(amountToIncrement - 1)
  }

  def decrementCount(amountToDecrement: Int): CounterWithImmutability = {
    if (amountToDecrement <= 0) this
    else decrementCount.decrementCount(amountToDecrement - 1)
  }

  def print = println(this.count)
}
