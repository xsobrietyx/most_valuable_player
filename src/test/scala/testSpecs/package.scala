import java.io.File

package object testSpecs {
  implicit class StringUtils(val str: String) {
    def toCan: String = {
      new File(".").getCanonicalPath + "/src/main/resources/" + str
    }
  }
}
