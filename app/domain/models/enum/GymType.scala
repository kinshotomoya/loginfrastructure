package domain.models.`enum`

// コンストラクタ引数にプロパティを指定する
// abstract classにすることで、フィールド値を指定できる（コンストラクタを持つことができる）
sealed abstract class GymType(override val code: String, override val name: String) extends Enum

// EnumオブジェクトのCompanion traitを継承している
// Companion traitは型traitになっており、さらにEnum型のサブクラスしか指定できないような
// 下限境界が指定されている
object GymType extends Enum.Companion[GymType] {
  case object GoldGym extends GymType("goldGym", "ゴールドジム")
  case object AnyTimeGYm extends GymType("anyTimeGym", "エニータイム")
  case object FastGym extends GymType("fastGym", "ファストジム")
  case object Cospa extends GymType("caspa", "コスパ")
  case object Konami extends GymType("konami", "コナミ")

  override val member: Seq[GymType] = Seq(
    GoldGym,
    AnyTimeGYm,
    FastGym,
    Cospa,
    Konami
  )
}
