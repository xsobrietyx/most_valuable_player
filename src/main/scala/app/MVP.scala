package app

object MVP extends App {

  import java.io.File

  import interf.Games._

  type groupingType = Map[Games, List[String]]

  /**
    * Grouping file names by game type. First line of file is the game type descriptor.
    * @param games default games set
    * @return map with games as a key and list of file names as a value
    */
  def groupFilesByGames(games: (String, String) = ("BASKETBALL", "HANDBALL")): groupingType = {
    var gamesMap: groupingType = Map()

    import scala.io.Source

    new File(args(0)).listFiles().foreach(file => {
      val source = Source.fromFile(file)
      val lines = source.getLines().toList
      source.close()
      val filePath = file.getCanonicalPath
      lines.head match {
        case games._1 =>
          gamesMap += (BASKETBALL -> (gamesMap.getOrElse(BASKETBALL, List(filePath)) :+ filePath))
        case games._2 =>
          gamesMap += (HANDBALL -> (gamesMap.getOrElse(HANDBALL, List(filePath)) :+ filePath))
      }
    })

    gamesMap
  }

  /**
    * Main application method that invokes all necessary logic
    */
  def searchForMVP(): Unit = {
    val gamesMap = groupFilesByGames()

    import util.FileParserService

    gamesMap.keySet.foreach {
      case HANDBALL =>
        println(FileParserService.parseHandballFiles(gamesMap(HANDBALL)).findMostValuablePlayer)
      case BASKETBALL =>
        println(FileParserService.parseBasketballFiles(gamesMap(BASKETBALL)).findMostValuablePlayer)
    }
  }

  searchForMVP()

}
