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

f.andThen(math.sin)

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  // listのtailメソッドを実装する
  def tail[T](list: List[T]): List[T] = list match {
    case Nil => sys.error("error occurred")
    case Cons(_, tail: List[T]) => tail
  }

  // 最初の要素を別の値と置き換える関数
  def setHead[T](value: T, list: List[T]) = {
    list match {
      case Cons(_, tail: List[T]) => Cons(value, tail)
    }
  }

  // listの先頭からn個の要素を削除する関数
  def drop[A](l: List[A], n: Int): List[A] = {
    @tailrec
    def loop(acc: List[A], n: Int): List[A] = l match {
      case Nil => Nil
      case Cons(_, _) if n == 0 => acc
      case Cons(_, tail) => loop(tail, n-1)
    }
    loop(l, n)
  }

  // 述語とマッチする場合に限り、Listからその要素までの要素を削除する関数
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = {
    @tailrec
    def loop(acc: List[A]): List[A] = {
      l match {
        case Cons(head, tail) if f(head) => loop(tail)
        case _ => acc
      }
    }
    loop(l)
  }
}
