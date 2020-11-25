package graal

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations.{Benchmark, BenchmarkMode, Fork, Measurement, Mode, OutputTimeUnit, Warmup}


case class User(name: String, age: Int)

@Fork(5)
@Warmup(iterations = 1, batchSize = 10000)
@Measurement(iterations = 30, batchSize = 10000)
@BenchmarkMode(Array(Mode.SingleShotTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
class methodBenchMark {

  @Benchmark
  def run: Unit = {
    distinctViaFoldLeft(userList)
  }

  def userList: List[User] = {
    val list = collection.immutable.List[User]()
    for(i <- 0 until 100) {
      list :+ User(s"user$i", i)
    }
    list
  }


  private def distinctViaGroupBy(list: List[User]): List[User] = {
    list.groupBy(user => (user.name, user.age)).map(t => t._2.head).toList
  }


  private def distinctViaFoldLeft(list: List[User]): List[User] = {
    list.foldLeft(List[User]()) { (l, v) =>
      if(l.exists(user => user.name == v.name && user.age == v.age)) l else l :+ v
    }
  }


}
