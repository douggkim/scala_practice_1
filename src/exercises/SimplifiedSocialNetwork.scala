package exercises
//
//2. overly simplified social network based on maps
//  Person = String
//Map (name, Friends )
//- add a person to the network
//  - remove
//- friend (mutual)
//- unfriend
//
//- number of friends of a person
//  - person with most friends
//  - how many people have NO friends
//  - if there is a social connection between two people (direct or not)

abstract class SocialNetwork {
  // Just defines the operations, implementation decides how to store data
  def addPerson(personName: String): SocialNetwork

  def removePerson(personName: String): SocialNetwork

  def addFriend(person1: String, person2: String): SocialNetwork

  def removeFriend(person1: String, person2: String): SocialNetwork

  def numberOfFriends(personName: String): Int

  def getNoFriendPeople: Set[String]

  def checkConnection(person1: String, person2: String): Boolean

  def getPersonWithMostFriends(): String

  protected def getNetwork: Map[String, Set[String]]
}

class MapBasedSocialNetwork(private val network: Map[String, Set[String]] = Map().withDefaultValue(null))
  extends SocialNetwork {

  protected def getNetwork: Map[String, Set[String]] = network

  override def addPerson(personName: String): MapBasedSocialNetwork =
    new MapBasedSocialNetwork(this.network + (personName -> Set.empty[String]))

  override def removePerson(personName: String): MapBasedSocialNetwork = {
    if (!network.contains(personName)) {
      throw new RuntimeException("Person not in network")
    } else {
      // First remove all friendships
      def removePersonAux(friends: Set[String], accNetwork: MapBasedSocialNetwork): MapBasedSocialNetwork = {
        if (friends.isEmpty) accNetwork
        else removePersonAux(friends.tail, accNetwork.removeFriend(personName, friends.head))
      }

      // Remove all friendships first
      val unfriended = removePersonAux(network(personName), this)

      // Then remove the person from the network
      new MapBasedSocialNetwork(unfriended.getNetwork - personName)
    }
  }


  override def addFriend(person1: String, person2: String): MapBasedSocialNetwork = {
    if (!this.network.contains(person1) || !this.network.contains(person2)) {
      throw new RuntimeException("people provided as inputs are not in the network")
    } else {
      val newNetworkForPerson1 = this.network(person1) + person2
      val newNetworkForPerson2 = this.network(person2) + person1
      new MapBasedSocialNetwork(
        this.network
          + (person1 -> newNetworkForPerson1)
          + (person2 -> newNetworkForPerson2)
      )
    }
  }

  override def removeFriend(person1: String, person2: String): MapBasedSocialNetwork =
    if (!this.network.contains(person1) || !this.network.contains(person2)) throw new RuntimeException("people provided as inputs are not in the network")
    else {
      val newNetworkForPerson1 = this.network(person1) - person2
      val newNetworkForPerson2 = this.network(person2) - person1
      new MapBasedSocialNetwork(
        this.network
          + (person1 -> newNetworkForPerson1)
          + (person2 -> newNetworkForPerson2)
      )
    }

  override def getPersonWithMostFriends(): String =
    this.network.maxBy(pair => pair._2.size)._1

  override def numberOfFriends(personName: String): Int =
    if (!this.network.contains(personName)) throw new RuntimeException("people provided as inputs are not in the network")
    else this.network(personName).size

  override def getNoFriendPeople: Set[String] =
    this.network.filter { case (name, friends) => friends.isEmpty }.keySet

  override def checkConnection(person1: String, person2: String): Boolean =
    if (!this.network.contains(person1) || !this.network.contains(person2)) throw new RuntimeException("people provided as inputs are not in the network")
    else dfs(person1, person2, Set.empty)

  private def dfs(current: String, target: String, visited: Set[String]): Boolean = {
    if (current == target) true
    else if (visited.contains(current)) false
    else {
      val newVisited = visited + current
      this.network(current).exists(friend => dfs(friend, target, newVisited))
    }
  }

}

object SimplifiedSocialNetworkTest extends App {
  println("Testing Social Network...")

  // Create empty network and add people
  var network = new MapBasedSocialNetwork()
  network = network.addPerson("Alice")
  network = network.addPerson("Bob")
  network = network.addPerson("Charlie")
  network = network.addPerson("David")
  network = network.addPerson("Eve")
  println("Added 5 people")

  // Test adding friends
  try {
    // Make Bob most popular (3 friends)
    network = network.addFriend("Bob", "Alice")
    network = network.addFriend("Bob", "Charlie")
    network = network.addFriend("Bob", "David")

    // Make Alice second most popular (2 friends)
    network = network.addFriend("Alice", "Charlie")

    // Eve has no friends

    println("Added friendships - Bob should have most friends (3)")
    println(s"Person with most friends: ${network.getPersonWithMostFriends()}") // Should be Bob
    println(s"Bob's friends count: ${network.numberOfFriends("Bob")}") // Should be 3
    println(s"Alice's friends count: ${network.numberOfFriends("Alice")}") // Should be 2

    // Remove Bob's friends
    network = network.removeFriend("Bob", "Alice")
    network = network.removeFriend("Bob", "Charlie")
    network = network.removeFriend("Bob", "David")
    println("\nRemoved all Bob's friends - Alice should now have most friends (1)")
    println(s"Person with most friends: ${network.getPersonWithMostFriends()}") // Should be Alice
    println(s"Bob's friends count: ${network.numberOfFriends("Bob")}") // Should be 0
    println(s"Alice's friends count: ${network.numberOfFriends("Alice")}") // Should be 1

  } catch {
    case e: RuntimeException => println(s"Caught unexpected error: ${e.getMessage}")
  }
}
