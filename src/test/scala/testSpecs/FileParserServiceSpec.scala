package testSpecs

import java.io.IOException

import util.FileParserService._

class FileParserServiceSpec extends UnitSpec {
  private lazy val basketballFilePath = "basketballexample.txt" toCan
  private lazy val handballFilePath = "handballexample.txt" toCan
  private lazy val wrongFileData = "wrongfiledata.txt" toCan
  private lazy val wrongInnerData = "wronginnerdata.txt" toCan

  "Parsing of basketball file" should "return correct number of parsed entities" in {
    val basketballGame = parseBasketballFiles(List(basketballFilePath))

    basketballGame.players.size should be(6)
  }

  "Parsing of handball file" should "return correct number of parsed entities" in {
    val handballGame = parseHandballFiles(List(handballFilePath))

    handballGame.players.size should be(6)
  }

  "Wrong file format of basketball files" should "throw IOException" in {
    val wrongFilesList = List("12345.csv", "goodFile.txt")
    val expectedMessage = "Wrong file format."

    the [IOException] thrownBy parseBasketballFiles(wrongFilesList) should have message expectedMessage
  }

   "Wrong data format within basketball files" should "throw IOException" in {
     val fileWithWrongData = List(wrongFileData)
     val expectedMessage = "Wrong file data."

     the [IOException] thrownBy parseBasketballFiles(fileWithWrongData) should have message expectedMessage
   }

  "Wrong file format of handball files" should "throw IOException" in {
    val wrongFilesList = List("12345.csv", "goodFile.txt")
    val expectedMessage = "Wrong file format."

    the [IOException] thrownBy parseHandballFiles(wrongFilesList) should have message expectedMessage
  }

  "Wrong data format within handball files related to the header statement" should "throw IOException" in {
    val fileWithWrongData = List(wrongFileData)
    val expectedMessage = "Wrong file data."

    the [IOException] thrownBy parseHandballFiles(fileWithWrongData) should have message expectedMessage
  }

  "Wrong data format within handball files related to the entity mapping" should "throw IOException" in {
    val fileWithWrongData = List(wrongInnerData)
    val expectedMessage = "Wrong file data."

    the [IOException] thrownBy parseHandballFiles(fileWithWrongData) should have message expectedMessage
  }
}
