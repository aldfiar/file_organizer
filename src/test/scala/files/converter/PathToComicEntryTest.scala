package files.converter

import java.nio.file.{Files, Path}
import java.util.concurrent.ThreadLocalRandom

import org.scalatest.{BeforeAndAfterEach, FunSuite}

class PathToComicEntryTest extends FunSuite with BeforeAndAfterEach {
  private var path: Path = _

  override def beforeEach(): Unit = {
    path = Files.createTempFile("Bishoujo_Java_-_Tsumi_to_batsu_no_shoujo_[E12D394A]", ".avi")
  }

  def filesInParentFolder(): Unit = {
    val root = Files.createTempDirectory("Strange author")
    val inner = Seq("story1", "story2", "story3")
    inner.map(x => Files.createTempDirectory(root, x)).foreach(x => for (i <- 1 to 10) Files.createTempFile(x, "file_%d".format(i), ".jpg"))
  }

  def filesInFolder(): Unit = {
    val inner = Files.createTempDirectory("[Strange author] Strange story")
    val fileNumber = ThreadLocalRandom.current().nextInt(10)
    for (i <- 1 to fileNumber)
      Files.createTempFile(inner, "file_%d".format(i), ".jpg")
  }
}