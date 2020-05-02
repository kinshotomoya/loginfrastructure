package repository.models.gym

import domain.models.{StringBase, Keyword => DomainKeyword, Location => DomainLocation, Price => DomainPrice}
import domain.models.`enum`.SearchOptionType.{Keyword, Location, Price}
import usecase.models.gym.SearchRequest

// Tには、Keyword Price Location型が渡ってくるので、抽象化させてTと指定している
// ValueにはStringBaseを継承した型が入る
// +Tと共変を指定することで、MysqlSearchRequest[T]のサブクラスも代入できるようになる
case class MysqlSearchRequest[+T](column: String, value: T)

object MysqlSearchRequest {
  // MysqlSearchRequest[StringBase]という戻り型を返さないといけないが、
  // MysqlSearchRequest[+T]としていることで、
  // MysqlSearchRequest[DomainKeyword]、MysqlSearchRequest[DomainPrice]、MysqlSearchRequest[DomainLocation]
  // というStringBaseのサブクラスも代入できる！！！！
  // +Tを指定しない場合は、このメソッドの返り型は、厳格にMysqlSearchRequest[StringBase]のみになる
  def of(request: SearchRequest): Option[MysqlSearchRequest[StringBase]] = {
    request.searchOptionType match {
      case Keyword => {
        request.keyword.map(MysqlSearchRequest[DomainKeyword]("keyword", _))
      }
      case Price =>{
        request.price.map(MysqlSearchRequest[DomainPrice]("price", _))
      }
      case Location =>{
        request.location.map(MysqlSearchRequest[DomainLocation]("location", _))
      }
    }
  }
}

