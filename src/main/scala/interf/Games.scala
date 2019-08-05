package interf

trait Games[A] {
  def players: Set[A]

  def findTeamWon: String
  def findMostValuablePlayer: A
}
