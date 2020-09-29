
object ErrorHandlingEither {


  sealed trait Either[+E, +A]
  case class Left[+E](value: E) extends Either[E, Nothing] {
    override def equals(that: Any): Boolean = ???
  }
  case class Right[+A](value: A) extends Either[Nothing, A] {
    override def equals(that: Any): Boolean = ???
  }

  // exercise 4.7
  trait Either[+E, +A] {
    def map[B](f: A => B): Either[E, B] = {
      this match {
        case Left(value) => Left(value)
        case Right(value) => Right(f(value))
      }
    }

    def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = {
      this match {
        case l @ Left(_) => l
        case Right(value) => f(value)
      }
    }
  }

}
