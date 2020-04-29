package files.converter

import java.nio.file.{Files, Path}

import files.elements.VideoEntry
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import org.scalatest.Matchers._

class PathToVideoEntryTest extends FunSuite with BeforeAndAfterEach {
  private var path: Path = _

  override def beforeEach(): Unit = {
    path = Files.createTempFile("Bishoujo_Java_-_Tsumi_to_batsu_no_shoujo_[E12D394A]", ".avi")
  }

  test("Path is video entry") {
    val result = PathToVideoEntry.toEntry(path)
    result should not be empty
    val entry = result.get
    entry shouldBe a[VideoEntry]

  }
  test("Entry contains original path") {
    val entry = PathToVideoEntry.toEntry(path).get
    entry.path should be theSameInstanceAs path
  }
  test("Entry has name") {
    val entry = PathToVideoEntry.toEntry(path).get
    entry.description.name should startWith
    "Bishoujo Java - Tsumi to batsu no shoujo"
  }

}
