package domain.models.`enum`

trait Enum extends Serializable with Product {
  val code: String
  val name: String
}

object Enum {
  // 上限境界を設定する。
  // EnumのEにはEnumのサブクラスしか指定できなくなる
  trait Companion[E <: Enum] {
    val member: Seq[E]
  }
}
