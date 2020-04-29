package usecase.models.gym

import domain.models.{Keyword, Location, Price}
import domain.models.`enum`.{GymType, SearchOptionType}

case class SearchRequest(gymType: GymType, searchOptionType: SearchOptionType, keyword: Option[Keyword], price: Option[Price], location: Option[Location])
