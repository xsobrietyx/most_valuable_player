package testutils

import java.io.File

import org.scalatest.{FlatSpec, Matchers}

class UnitSpec extends FlatSpec with Matchers {
  protected lazy val canonical: String = new File(".").getCanonicalPath + "/src/main/resources/"
}
