package testSpecs

import util.FileParserService.parseHandballFiles

class HandballGamesSpec extends UnitSpec {
  private lazy val handballFilePath = "handballexample.txt" toCan

  "The winner handball team" should "should be verified correctly" in {
    val handballGame = parseHandballFiles(List(handballFilePath))

    handballGame.findTeamWon should be("Team A")
  }

  "The most valuable player of handball game" should "be the correct person" in {
    val handballGame = parseHandballFiles(List(handballFilePath))

    handballGame.findMostValuablePlayer.nickName should be("nick2")
  }
}
