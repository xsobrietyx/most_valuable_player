package interf

trait GameService[A] {
  def players: Set[A]

  def findTeamWon: String
  def findMostValuablePlayer: A
}
