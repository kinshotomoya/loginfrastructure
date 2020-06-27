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

