package util

import java.io.IOException

import impl.{BasketballPlayer, BasketballServiceImpl, HandballPlayer, HandballServiceImpl}

object FileParserService {

  import scala.io._

  def validateFilesFormat(files: List[String]): Unit = {
    val fileFormat = "(.*(\\w*\\.{1}txt))"

    if (files.count(s => !s.matches(fileFormat)) > 0) throw new IOException("Wrong file format.")
  }

  def validateDataFormat(firstLine: String): Unit = {
    val pattern = "[A-Z]".r
    pattern.findFirstIn(firstLine) match {
      case Some(_) =>
      case None => throw new IOException("Wrong file data.")
    }
  }

  def parseBasketballFiles(files: List[String]): BasketballServiceImpl = {

    validateFilesFormat(files)

    def parseBasketballFile(filePath: String): Set[BasketballPlayer] = {

      val source = Source.fromFile(filePath)
      val lines = source.getLines().toList
      source.close()
      val header = lines.head

      validateDataFormat(header)

      var players = Set[BasketballPlayer]()

      try {
        players = lines.tail
          .map(_.split(";"))
          .map(strings => BasketballPlayer(strings(0),
            strings(1),
            strings(2).toByte,
            strings(3),
            strings(4).toByte,
            strings(5).toByte,
            strings(6).toByte)).toSet
      } catch {
        case e: Exception => throw new IOException("Wrong file data.")
      }
      players
    }

    val players = files.flatMap(p => parseBasketballFile(p)).toSet
    impl.BasketballServiceImpl(players)
  }

  def parseHandballFiles(files: List[String]): HandballServiceImpl = {

    validateFilesFormat(files)

    def parseHandballFile(filePath: String): Set[HandballPlayer] = {

      val source = Source.fromFile(filePath)
      val lines = source.getLines().toList
      source.close()
      val header = lines.head

      validateDataFormat(header)

      var players = Set[HandballPlayer]()

      try {
        players = lines.tail
          .map(_.split(";"))
          .map(strings => HandballPlayer(strings(0),
            strings(1),
            strings(2).toByte,
            strings(3),
            strings(4).toByte,
            strings(5).toByte)).toSet
      } catch {
        case e: Exception => throw new IOException("Wrong file data.")
      }
      players
    }

    val players = files.flatMap(p => parseHandballFile(p)).toSet
    HandballServiceImpl(players)
  }
}