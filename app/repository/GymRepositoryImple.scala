package repository

import domain.models.StringBase
import domain.repository.GymRepository
import repository.models.gym.MysqlSearchRequest

import scala.concurrent.Future

class GymRepositoryImple extends GymRepository{
   override def search[T <: StringBase](request: MysqlSearchRequest[T]): Future[String] = {
    Future.successful(request.value.name)
  }
}

