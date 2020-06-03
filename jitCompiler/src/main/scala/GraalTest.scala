import scala.annotation.tailrec
import scala.io.BufferedSource

object GraalTest extends App {

  val fileName: String = "GraalTest.txt"
  val encode: String = "UTF-8"

  // scala2.13.xでは、resources配下のファイルを以下のコマンド、相対パスで取得できる
  // scala.io.Source.fromResource(fileName)
  val source: BufferedSource = scala.io.Source.fromResource(fileName)
  val lines: Iterator[String] = source.getLines()
  val sortedTextList = lines.toList.mkString(" ").split(" ").sorted.toList
  val value = createMap(sortedTextList)
  val top10Words = value.toList.sortBy(_._2).reverse.take(10)
  println(top10Words)

  private def createMap(wordList: List[String]): Map[String, Long] = {
    @tailrec
    def loop(list: List[String], acc: Map[String, Long]): Map[String, Long] = {
      list match {
        // 初めの時
        case head :: tail if acc.isEmpty => {
          loop(tail, acc + (head -> 1L))
        }
        // 2回目以降
        case head :: tail => {
          acc.get(head) match {
            case Some(value) => {
              loop(tail, acc.updated(head, value + 1L))
            }
            case None => {
              loop(tail, acc + (head -> 1L))
            }
          }
        }
        case head :: Nil => {
          acc.get(head) match {
            case Some(value) => {
              acc.updated(head, value + 1L)
            }
            case None => {
              acc + (head -> 1L)
            }
          }
        }
        case Nil => acc
      }
    }
    loop(wordList, Map.empty[String, Long])
  }
}

