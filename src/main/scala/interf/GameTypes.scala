package interf

object GameTypes extends Enumeration {
  type GameTypes = Value

  val BASKETBALL: this.Value = Value("BASKETBALL")
  val HANDBALL: this.Value = Value("HANDBALL")
}
