package domain.repository

import com.google.inject.ImplementedBy
import domain.models.StringBase
import repository.GymRepositoryImple
import repository.models.gym.MysqlSearchRequest

import scala.concurrent.Future

@ImplementedBy(classOf[GymRepositoryImple])
trait GymRepository {
  // 上限境界を指定することで、MysqlSearchRequestの型パラメータには、StringBaseのサブクラスしか指定できないようになる
  // つまり、TにはStringBase, Keyword, Location, Priceしか渡せない
  // さらに、この上限境界をつけることで、StringBaseのフィールド値にアクセスできる
  def search[T <: StringBase](request: MysqlSearchRequest[T]): Future[String]
}



//class Person(val firstName: String, val lastName: String) extends Ordered[Person] {
//  override def compare(that: Person) = {
//    val lastNameComparison: Int = lastName.compareToIgnoreCase(that.lastName)
//    if(lastNameComparison != 0)
//      lastNameComparison
//    else
//      firstName.compareToIgnoreCase(that.firstName)
//  }
//
//  override def toString: String = firstName + " " + lastName
//
//}
//
//object PersonOrder {
//  val people = List(new Person("tomoya", "kinsho"), new Person("nana", "kinsho"))
//
//  //　上限境界が指定されているので、TにはOrderedを継承した型（Ordered[T]のサブクラス）しか指定できない
//  // なので、引数には、Ordered[T]のサブクラスのListしか受け取れない
//  // 今回は、List[Person]
//  def orderMergeSort[T <: Ordered[T]](xs: List[T]): List[T] = {
//
//  }
//}

