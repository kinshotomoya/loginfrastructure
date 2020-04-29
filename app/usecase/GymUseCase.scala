package usecase

import com.google.inject.Inject
import domain.repository.GymRepository
import repository.models.gym.MysqlSearchRequest
import usecase.models.gym.SearchRequest

import scala.concurrent.Future

class GymUseCase @Inject()(gymRepository: GymRepository){

  def search(request: SearchRequest) = {
    MysqlSearchRequest.of(request).map(gymRepository.search) match {
      case Some(res) => res
      case None => Future.successful(0L)
    }
  }
}
