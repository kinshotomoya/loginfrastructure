object Errorhandling {

  sealed trait Option[+A]
  case class Some[A](get: A) extends Option[A]
  case object None extends Option[Nothing]


  trait Animal {
    val name: String
  }
  case class Dog(override val name: String) extends Animal
  case class Cat(override val name: String) extends Animal

  val dog: Option[Animal] = Some(Dog("test"))
  val cat: Option[Cat] = Some(Cat("test2"))

  def test(animal: Option[Animal]) = println(animal)

  test(dog)
  test(cat)


  // exercise 4.1
  trait Option[+A] {
    def map[A, B](as: Option[A])(f: A => B): Option[B] = {
      as match {
        case None => None
        case Some(value) => Some(f(value))
      }
    }

    def flatMap[A, B](as: Option[A])(f: A => Option[B]): Option[B] = {
      as match {
        case None => None
        case Some(value) => f(value)
      }
    }

    def getOrElse[B >: A](as: Option[A])(default: => B): B = {
      as match {
        case None => default
        case Some(value) => value
      }
    }
  }

}
