import org.scalatest.FunSuite

class HentaiNameParserTest extends FunSuite {
  test("Parse simple file") {
    val fileName = "Learn_Scala_faggot!_3.mp4"
    val parsed = HentaiNameParser.parse(fileName)
    assert(parsed == fileName)

  }
  test("Parse file name with unnecessary symbols") {
    val fileName = "Baka_User_XX_Use_Sudo_01_[F2A5991E].mkv"
    val parsed = HentaiNameParser.parse(fileName)
    assert(parsed == "Baka_User_XX_Use_Sudo_01.mkv")
  }
  test("Parse name with leading and trailing unnecessary symbols") {
    val fileName = "[Vasya-Raws]_Oh_my_Stack_overflow_3_The_Animation_-_02_[6031D3AD].mkv"
    val parsed = HentaiNameParser.parse(fileName)
    assert(parsed == "Oh_my_Stack_overflow_3_The_Animation_-_02.mkv")
  }
  test("Parse name with leading unnecessary symbol and bracket without underscore") {
    val fileName = "[AWS] Ricardo Milos dance OVA -02- (DVDRip 1280x720 h264 ac3).mkv"
    val parsed = HentaiNameParser.parse(fileName)
    assert(parsed == "Ricardo Milos dance OVA -02-.mkv")
  }
}
