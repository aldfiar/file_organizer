import files.name.converters.FileNameToVideoEntry
import org.scalatest.FunSuite

class VideoNameConvertersTest extends FunSuite {

  test("Parse simple file") {
    val fileName = "Learn_Scala_faggot!_3"
    val parsed = FileNameToVideoEntry.convert(fileName)
    assertResult("Learn Scala faggot! - 3")(parsed)
  }

  test("Parse file without number") {
    val fileName = "Bishoujo_Java_-_Tsumi_to_batsu_no_shoujo_[E12D394A]"
    val parsed = FileNameToVideoEntry.convert(fileName)
    assertResult("Bishoujo Java Tsumi to batsu no shoujo")(parsed)
  }

  test("Parse file name with unnecessary block") {
    val fileName = "Baka_User_XX_Use_Sudo_01_[F2A5991E]"
    val parsed = FileNameToVideoEntry.convert(fileName)
    assertResult("Baka User XX Use Sudo - 01")(parsed)
  }

  test("Parse name with leading and trailing unnecessary blocks") {
    val fileName = "[Vasya-Raws]_Oh_my_Stack_overflow_The_Animation_-_02_[6031D3AD]"
    val parsed = FileNameToVideoEntry.convert(fileName)
    assertResult("Oh my Stack overflow The Animation - 02")(parsed)
  }

  test("Parse name with leading and trailing unnecessary blocks with space inside") {
    val fileName = "[Vasya Raws]_Oh_my_Stack_overflow_The_Animation_-_02_[6031D3AD]"
    val parsed = FileNameToVideoEntry.convert(fileName)
    assertResult("Oh my Stack overflow The Animation - 02")(parsed)
  }

  test("Parse name with leading unnecessary blocks without underscore") {
    val fileName = "[AWS] Ricardo Milos dance OVA -02- (DVDRip 1280x720 h264 ac3)"
    val parsed = FileNameToVideoEntry.convert(fileName)
    assertResult("Ricardo Milos dance OVA - 02")(parsed)
  }

}
