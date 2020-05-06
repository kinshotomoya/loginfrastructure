package domain.models

abstract class CurrencyZone {
  // 抽象型を定義
  // 上限境界・具象クラス側で具象型を指定するが、その際にはAbstractCurrencyを継承した型しか定義できない
  // 型を抽象化することで、Currencyにどんな型がきても対応できるようにしている
  type Currency <: AbstractCurrency
  def make(x: Long): Currency
  abstract class AbstractCurrency {
    val amount: Long
    def designation: String

    def + (that: Currency): Currency = make(this.amount + that.amount)

    def * (x: Double): Currency = make(this.amount * x.toLong)

    def from(other: CurrencyZone#AbstractCurrency): Currency = make(math.round(other.amount.toDouble * Converter.exchangeRate(other.designation)(this.designation)))

  }
  val CurrencyUnit: Currency
}


object US extends CurrencyZone {
  abstract class Dollar extends AbstractCurrency {
    override def designation: String = "USD"

    override def toString: String = (amount / CurrencyUnit.amount.toDouble) formatted("%." + decimals(CurrencyUnit.amount) + "f") + " " + designation

    private def decimals(n: Long): Int = if(n == 1) 0 else 1 + decimals(n / 10)
  }
  override type Currency = Dollar

  override def make(cents: Long): Dollar = new Dollar {
    override val amount: Long = cents
  }
  val Cent: Dollar = make(1)
  val Dollar: Dollar = make(100)
  val CurrencyUnit: Dollar = Dollar
}


object Europe extends CurrencyZone {
  override type Currency = Euro

  override def make(cents: Long): Euro = new Euro {
    override val amount: Long = cents
  }

  abstract class Euro extends AbstractCurrency {
    override def designation: String = "EUR"
  }

  val Cent: Euro = make(1)
  val Euro: Euro = make(100)
  val CurrencyUnit = Euro
}


object Japan extends CurrencyZone {
  override type Currency = Yen

  override def make(yen: Long): Yen = new Yen {
    override val amount: Long = yen
  }
  abstract class Yen extends AbstractCurrency {
    override def designation: String = "JPY"
  }
  val Yen = make(1)
  val CurrencyUnit = Yen
}

object Converter {
  val exchangeRate = Map(
    "USD" -> Map("USD" -> 1.0, "EUR" -> 0.7596, "JPY" -> 1.211),
    "EUR" -> Map("USD" -> 1.316, "EUR" -> 1.0, "JPY" -> 1.594),
    "JPY" -> Map("USD" -> 0.8257, "EUR" -> 0.6272, "JPY" -> 1.0)
  )
}
