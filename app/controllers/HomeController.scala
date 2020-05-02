package controllers

import domain.models.Keyword
import domain.models.`enum`.{GymType, SearchOptionType}
import javax.inject._
import play.api.mvc._
import usecase.GymUseCase
import usecase.models.gym.SearchRequest

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents, val gymUseCase: GymUseCase) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    gymUseCase.search(SearchRequest(GymType.GoldGym, SearchOptionType.Keyword, Some(Keyword("test")), None, None))
    Ok(views.html.index())
  }
}
