
object ErrorHandlingEither {


//  sealed trait Either[+E, +A]
  case class Left[+E](value: E) extends Either[E, Nothing] {
    override def equals(that: Any): Boolean = ???
  }
  case class Right[+A](value: A) extends Either[Nothing, A] {
    override def equals(that: Any): Boolean = ???
  }

  // exercise 4.6
  sealed trait Either[+E, +A] {
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

    def orElse[EE >: E, B >: A](b: => Either[EE, B]): Either[EE, B] = {
      this match {
        case Left(_) => b
        case Right(_) => this
      }
    }

    def map2[EE >: E, B , C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = {
      this match {
        case l @ Left(_) => l
        case Right(value) => b match {
          case l @ Left(_) => l
          case Right(value2) => Right(f(value, value2))
        }
      }
    }

    def map22[EE >: E, B , C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = {
      this.flatMap(x => b.map(y => f(x, y)))
    }


    // exercise 4.7


  }

}
