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
