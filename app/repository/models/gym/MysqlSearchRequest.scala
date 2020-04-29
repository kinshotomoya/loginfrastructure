package repository.models.gym

import domain.models.StringBase
import domain.models.`enum`.SearchOptionType.{Keyword, Location, Price}
import usecase.models.gym.SearchRequest

// Tには、Keyword Price Location型が渡ってくるので、抽象化させてTと指定している
// ValueにはStringBaseを継承した型が入る
case class MysqlSearchRequest[T](column: String, value: T)

object MysqlSearchRequest {
  def of(request: SearchRequest): Option[MysqlSearchRequest[StringBase]] = {
    request.searchOptionType match {
      case Keyword => {
        request.keyword.map(MysqlSearchRequest("keyword", _))
      }
      case Price =>{
        request.price.map(MysqlSearchRequest("price", _))
      }
      case Location =>{
        request.location.map(MysqlSearchRequest("location", _))
      }
    }
  }
}
