package domain.repository

import com.google.inject.ImplementedBy
import domain.models.StringBase
import repository.GymRepositoryImple
import repository.models.gym.MysqlSearchRequest

import scala.concurrent.Future

@ImplementedBy(classOf[GymRepositoryImple])
trait GymRepository {
  // 下限境界を指定することで、MysqlSearchRequestの型パラメータには、StringBaseのサブクラスしか指定できないようになる
  def search[T <: StringBase](request: MysqlSearchRequest[T]): Future[String]
}
