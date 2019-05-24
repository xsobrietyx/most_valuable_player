package impl

import interf.{BasketballRules, Player}

case class BasketballPlayer(name: String, nickName: String, number: Byte, teamName: String, points: Byte, rebounds: Byte, assists: Byte)
  extends Player
    with BasketballRules