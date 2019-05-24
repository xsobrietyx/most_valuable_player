package impl

import interf.{HandballRules, Player}

case class HandballPlayer(name: String, nickName: String, number: Byte, teamName: String, goalsMade: Byte, goalsReceived: Byte)
  extends Player
    with HandballRules
