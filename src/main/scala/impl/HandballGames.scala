package impl

import interf.Games

import scala.util.Sorting

case class HandballGames(players: Set[HandballPlayer]) extends Games[HandballPlayer] {
  override def findTeamWon: String = {
    val teams = players.map(p => (p.teamName, p.goalsMade * 2 - p.goalsReceived))
      .groupBy(_._1)
      .mapValues(v => v.reduce((a, b) => (a._1, a._2 + b._2)))
      .mapValues(v => v._2).toArray

    // TODO: remove duplication
    Sorting.stableSort(teams, (e1: (String, Int), e2: (String, Int)) => e1._2 > e2._2)

    teams.head._1
  }

  override def findMostValuablePlayer: HandballPlayer = {
    val playersWithPoints = players.map(p => {
      var points = p.goalsMade - p.goalsReceived
      if (p.teamName.equals(findTeamWon)) points = points + 10
      (p.nickName, points)
    }).toArray

    // TODO: remove duplication

    Sorting.stableSort(playersWithPoints, (p1: (String, Int), p2: (String, Int)) => p1._2 > p2._2)

    val player = for {
      player <- players
      if player.nickName == playersWithPoints.head._1
    } yield player

    player.head
  }
}
