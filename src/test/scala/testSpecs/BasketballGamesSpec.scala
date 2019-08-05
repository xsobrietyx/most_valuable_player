package testSpecs

import util.FileParserService.parseBasketballFiles

class BasketballGamesSpec extends UnitSpec {
  private lazy val basketballFilePath = "basketballexample.txt" toCan

  "The winner basketball team" should "should be verified correctly" in {
    val basketballGame = parseBasketballFiles(List(basketballFilePath))

    basketballGame.findTeamWon should be("Team B")
  }

  "The most valuable player of basketball game" should "be the correct person" in {
    val basketballGame = parseBasketballFiles(List(basketballFilePath))

    basketballGame.findMostValuablePlayer.nickName should be("nick4")
  }
}
