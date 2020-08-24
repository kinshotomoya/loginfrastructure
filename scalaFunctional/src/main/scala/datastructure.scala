object datastructure {

  // exercise3.24

  // List(1, 2, 3, 4)
  // List(1, 2) => true
  // List(2, 3) => true
  // List(1, 3) => false
  def hasSubSequence[A](sup: List[A], sub: List[A]): Boolean = {
    sup match {
      case Nil if sub == Nil => true
      case head :: tail if checkList(sup, sub) => true
      case head :: tail => hasSubSequence(tail, sub)
    }
  }

  def checkList[A](sup: List[A], sub: List[A]): Boolean = {
    (sup, sub) match {
      case (_, Nil) => true
      case (head :: tail, subHead :: subTail) if head == subHead => checkList(tail, subTail)
      case _ => false
    }
  }


  sealed trait Tree[+A]
  case class Leaf[A](value: A) extends Tree[A]
  case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]


  // exercise 2.25
  def size[A](tree: Tree[A]): Int = {
    def loop[A](acc: Int, branch: Tree[A]): Int = {
      tree match {
        case Branch(left, right) => loop(acc + 1, left) + loop(acc + 1, right)
        case Leaf(_) => acc
      }
    }
    loop(1, tree)

    def reverse(list: List[Int]): List[Int] = {
      list.reverse
    }

    def add(list: List[Int]): List[Int] = {
      list.map(_ + 1)
    }


    val result: List[Int] => List[Int] = Function.chain(Seq(list => reverse(list), list => add(list)))

    result(List(1, 2, 3))

  }




}
