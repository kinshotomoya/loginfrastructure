import java.io.File

import scala.io.BufferedSource

object GraalTest extends App {

  val fileName: String = "GraalTest.txt"
  val encode: String = "UTF-8"

  try {
    val source: BufferedSource = scala.io.Source.fromFile(fileName, encode)
    val lines: Iterator[String] = source.getLines()
    val sortedTextList = lines.toList.mkString(" ").split(" ").sorted.toList
    val value = createMap(sortedTextList, Map.empty[String, Long])
    val top10Words = value.toList.sortBy(_._2).reverse.take(10)
    println(top10Words)
  } catch {
    case _ => println("error occurred")
  }

  def createMap(wordList: List[String], result: Map[String, Long]): Map[String, Long] = {
    wordList match {
      // 初めの時
      case head :: tail if result.isEmpty => {
        createMap(tail, result + (head -> 1L))
      }
      // 2回目以降
      case head :: tail => {
        addMap(head, tail, result, createMap)
      }
      case head :: Nil => {
        addMap(head, Nil, result, createMap)
      }
    }
  }

  private def addMap(head: String, tail: List[String], result: Map[String, Long], f: (List[String], Map[String, Long]) => Map[String, Long]) = {
    result.get(head) match {
      case Some(value) => {
        if(tail.isEmpty) result else f(tail, result.updated(head, value + 1L))
      }
      case None => {
        if(tail.isEmpty) result else f(tail, result + (head -> 1L))
      }
    }
  }
}
