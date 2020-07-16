import scala.annotation.tailrec

// 引数の部分的適応
def partial1[A, B, C](a: A, f: (A, B) => C): B => C = {
  b: B => f(a, b)
}

val rr: Int => Int = partial1(1, (x: Int, y: Int) => x + y)

// https://gist.github.com/takezoe/80b66f7b6e8fa0517139#%E5%BC%95%E6%95%B0%E3%81%AE%E9%83%A8%E5%88%86%E9%81%A9%E7%94%A8
// 部分的用の例1
// xの値を固定して、yの方を動的に変えれる
def test(x: Int, y: Int): Int = x + y
val ff: Int => Int = test(1, _: Int)
ff(3)

// 部分的用の例2
def area(pi: Double, r: Int): Double = pi * r * r
val f1: Int => Double = area(3.14, _)
f1(10)


object Exercise2s3 {
  def curry[A, B, C](f: (A, B) => C): A => (B => C) = {
    (a: A) => ((b: B) => f(a, b))
  }
}


object Exercise2s4 {
  def unCurry[A, B, C](f: A => (B => C)): (A, B) => C = {
    (a: A, b: B) => f(a)(b)
  }
}

object Exercise2s5 {
  def compose[A, B, C](f: B => C, g: A => B): A => C = {
    a: A => f(g(a))
  }
}

val f: Int => Double = (x: Int) => math.Pi / 2 - x


sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {

  def apply[A](as: A*): List[A] =
    if(as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
  // listのtailメソッドを実装する
  def tail[T](list: List[T]): List[T] = list match {
    case Nil => sys.error("error occurred")
    case Cons(_, tail: List[T]) => tail
  }

  // 最初の要素を別の値と置き換える関数
  def setHead[T](value: T, list: List[T]) = {
    list match {
      case Nil => println("error occurred")
      case Cons(_, tail: List[T]) => Cons(value, tail)
    }
  }

  // listの先頭からn個の要素を削除する関数
  def drop[A](l: List[A], n: Int): List[A] = {
    @tailrec
    def loop(acc: List[A], n: Int): List[A] = {
      acc match {
        case Cons(_, _) if n==0 => acc
        case Cons(_, tail) => loop(tail, n-1)
        case Nil => Nil
      }
    }
    loop(l, n)
  }

  // 述語とマッチする場合に限り、Listからその要素までの要素を削除する関数
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = {
    @tailrec
    def loop(acc: List[A]): List[A] = {
      acc match {
        case Nil => Nil
        case Cons(head, tail) if f(head) => loop(tail)
        case _ => acc
      }
    }
    loop(l)
  }

  def init[A](l: List[A]): List[A] = {
    l match {
      case Cons(head, tail) => Cons(head, init(tail))
      case Cons(_, tail) =>tail
    }
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(x, xs) => x * product(xs)
  }

  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B =
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }
}

object Exercise3s7 {
  // Q: 0.0を検出した場合に、直ちに再帰を中止して0.0を返せるか？
  def product2(ns: List[Double]): Double =
    foldRight(ns, 1.0, 0.0)(_ * _)
  // A: 引数に0.0の場合の結果値（0.0）を渡してあげると、できる。

  // Q: 大きなリストでfoldRightを呼び出した場合の短絡の仕組み
  // A: 計算速度が、リストの大きさ（長さ）に比例するので、単純に計算速度が遅くなる

  def foldRight[A, B](as: List[A], z: B, zero: B)(f: (A, B) => B): B =
    as match {
      case Nil => z
      case Cons(x, _) if x == 0 => zero
      case Cons(x, xs) => f(x, foldRight(xs, z, zero)(f))
    }
}

object Exercise3s8 {
  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B =
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
        // f(x, foldRight(xs, z)(f))
        // f(x, f(x, foldRight(xs, z)(f)))
    }

  foldRight(List(1, 2, 3), Nil: List[Int])(Cons(_, _))
  // 1. Cons(1, Cons(2, Cons(3, Nil)))

  def length[A](as: List[A]): Int = {
    foldRight(as, 0)((_, y) => y + 1)
  }
}

object Exercise3s9 {
  def length[A](as: List[A]): Int = {

    @tailrec
    def loop(acc: Int, list: List[A]): Int = {
      list match {
        case Nil => 0
        case Cons(_, Nil) => acc + 1
        case Cons(_, tail) => loop(acc + 1, tail)
      }
    }
    loop(0, as)
  }
}

// List(1, 2, 3), 0
object Exercise3s10 {
  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = {
    as match {
      case Nil => z
      case Cons(head, tail) => foldLeft(tail, f(z, head))(f)
        // List(1, 2, 3), 0
        // foldLeft(List(2, 3), f(1, 0))
        // foldLeft(List(3), f(2, 1))
        // foldLeft(Nil, f(3, 3))
        // 6
    }
  }

  def sum(as: List[Int]) = foldLeft(as, 0)((x, y) => x + y)

  def product(as: List[Int]) = foldLeft(as, 1)(_*_)

  def length[A](as: List[A]) = foldLeft(as, 0)((x, _) => x + 1)

  // List(1, 2, 3) => List(3, 2, 1)
  // Cons(3, Cons(2, Cons(1, NIl)))
  def reverse[A](as: List[A]): List[A] = foldLeft(as, List())((list, head) => Cons(head, list))
  // as = List(1, 2, 3)
  // foldLeft(List(1, 2, 3), List())((List(), 1) => Cons(1, List()))
  // foldLeft(List(2, 3), Cons(1, List()))((Cons(1, List()), 2) => Cons(2, Cons(1, List())))
  // foldLeft(List(3), Cons(2, Cons(1, List())))((Cons(2, Cons(1, List())), 3) => Cons(3, Cons(2, Cons(1, List()))))
  // foldLeft(List(), Cons(3, Cons(2, Cons(1, List()))))((Cons(2, Cons(1, List())), 3) => Cons(3, Cons(2, Cons(1, List()))))
  // => Cons(3, Cons(2, Cons(1, List())))

  // foldLeftを使ってfoldRightを実装する
  def foldRightTailrec[A, B](as: List[A], z: B)(f: (A, B) => B): B = {
    
  }
}
