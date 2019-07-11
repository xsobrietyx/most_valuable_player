package testutils

import java.io.File

import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

class UnitSpec extends FlatSpec with Matchers with BeforeAndAfterAll {
  var canonical: String = ""

  override protected def beforeAll(): Unit = {
    canonical = new File(".").getCanonicalPath + "/src/main/resources/"
  }
}
