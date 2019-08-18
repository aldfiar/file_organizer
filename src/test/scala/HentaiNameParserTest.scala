import org.scalatest.FunSuite

class HentaiNameParserTest extends FunSuite {

  test("Parse simple file") {
    val fileName = "Learn_Scala_faggot!_3"
    val parsed = HentaiNameParser.parse(fileName, template)
    assertResult("Learn Scala faggot! - 3")(parsed)

  }

  test("Parse file name with unnecessary symbols") {
    val fileName = "Baka_User_XX_Use_Sudo_01_[F2A5991E]"
    val parsed = HentaiNameParser.parse(fileName, template)
    assertResult("Baka User XX Use Sudo - 01")(parsed)
  }
  test("Parse name with leading and trailing unnecessary symbols") {
    val fileName = "[Vasya-Raws]_Oh_my_Stack_overflow_The_Animation_-_02_[6031D3AD]"
    val parsed = HentaiNameParser.parse(fileName, template)
    assertResult("Oh my Stack overflow The Animation - 02")(parsed)
  }
  test("Parse name with leading unnecessary symbol and bracket without underscore") {
    val fileName = "[AWS] Ricardo Milos dance OVA -02- (DVDRip 1280x720 h264 ac3)"
    val parsed = HentaiNameParser.parse(fileName, template)
    assertResult("Ricardo Milos dance OVA - 02")(parsed)
  }

  def template(elements: Array[String]): String = {
    val numbers = elements.filter(t => t.exists(_.isDigit))
    val withoutNumbers = elements.diff(numbers)
    val lastNumber = numbers.lastOption.map(t => " - %s".format(t)).getOrElse("")
    withoutNumbers.mkString(" ").concat(lastNumber)
  }

}
