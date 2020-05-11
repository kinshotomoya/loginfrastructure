package repository

import domain.models.StringBase
import domain.repository.GymRepository
import repository.models.gym.MysqlSearchRequest

import scala.concurrent.Future

class GymRepositoryImple extends GymRepository{
  // T <: StringBaseと上限境界を設けることで、request.value.nameとStringBaseのプロパティにアクセスできるようになる
  // Tだけだと、どんな型が来るのかわからないので、引数のプロパティにアクセスすることはできない
   override def search[T <: StringBase](request: MysqlSearchRequest[T]): Future[String] = {
    Future.successful(request.value.name)
  }
}
