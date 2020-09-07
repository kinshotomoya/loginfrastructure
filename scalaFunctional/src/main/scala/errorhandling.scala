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
    // thisで対象のOptionオブジェクトを取得する
    // ↑as: Option[A]を受け取らなくても良くなる
    def map[B](f: A => B): Option[B] = {
      this match {
        case None => None
        case Some(value) => Some(f(value))
      }
    }

    def flatMap[B](f: A => Option[B]): Option[B] = {
      this match {
        case None => None
        case Some(value) => f(value)
      }
    }

    def getOrElse[B >: A](default: => B): B = {
      this match {
        case None => default
        case Some(value) => value
      }
    }

    def orElse[B >: A](ob: => Option[B]): Option[B] = {
      this match {
        case None => ob
        case s @ Some(_) => s
      }
    }

    def filter(f: A => Boolean): Option[A] = {
      this match {
        case None => None
        case s @ Some(value) if f(value) => s
      }
    }
  }

}
