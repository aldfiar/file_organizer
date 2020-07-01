package files.converter.parsers

import java.nio.file.{Files, Path}
import java.util.concurrent.ThreadLocalRandom

import org.scalatest.{BeforeAndAfterEach, FunSuite}

class PathToComicsDescriptionTest extends FunSuite with BeforeAndAfterEach {
  private var path: Path = _

  override def beforeEach() {

  }

  def filesInParentFolder(): Path = {
    val root = Files.createTempDirectory("Strange author")
    val inner = Seq("story1", "story2", "story3")
    inner.map(x => Files.createTempDirectory(root, x)).foreach(x => for (i <- 1 to 10) Files.createTempFile(x, "file_%d".format(i), ".jpg"))
    root
  }

  def filesInFolder(): Path = {
    val root = Files.createTempDirectory("[Strange author] Strange story")
    val fileNumber = ThreadLocalRandom.current().nextInt(10)
    for (i <- 1 to fileNumber)
      Files.createTempFile(root, "file_%d".format(i), ".jpg")
    root
  }

  //
  //  test("Description from plain folder"){
  //    path = filesInFolder()
  //    val description = PathToComicsDescription.convert(path)
  //  }
}
