package interf

object Games extends Enumeration {
  type Games = Value

  val BASKETBALL: this.Value = Value("BASKETBALL")
  val HANDBALL: this.Value = Value("HANDBALL")
}
