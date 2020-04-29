package domain.models.`enum`

sealed abstract class SearchOptionType(override val code: String, override val name: String) extends Enum

object SearchOptionType extends Enum.Companion[SearchOptionType] {
  case object Keyword extends SearchOptionType("keyword", "キーワード")
  case object Price extends SearchOptionType("price", "値段")
  case object Location extends SearchOptionType("location", "場所")

  override val member: Seq[SearchOptionType] = Seq(
    Keyword,
    Price,
    Location
  )
}
