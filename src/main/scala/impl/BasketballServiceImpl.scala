package impl

import interf.GameService

import scala.util.Sorting

case class BasketballServiceImpl(players: Set[BasketballPlayer]) extends GameService[BasketballPlayer] {
  override def findTeamWon: String = {
    val teams = players.map(p => (p.teamName, p.points * 2 + p.rebounds + p.assists))
      .groupBy(_._1)
      .mapValues(v => v.reduce((a, b) => (a._1, a._2 + b._2)))
      .mapValues(v => v._2).toArray

    Sorting.stableSort(teams, (e1: (String, Int), e2: (String, Int)) => e1._2 > e2._2)

    teams.head._1
  }

  override def findMostValuablePlayer: BasketballPlayer = {
    val playersWithPoints = players.map(p => {
      var points = p.points + p.rebounds + p.assists
      if (p.teamName.equals(findTeamWon)) points = points + 10
      (p.nickName, points)
    }).toArray

    Sorting.stableSort(playersWithPoints, (p1: (String, Int), p2: (String, Int)) => p1._2 > p2._2)

    val player = for {
      player <- players
      if player.nickName == playersWithPoints.head._1
    } yield player

    player.head
  }
}
