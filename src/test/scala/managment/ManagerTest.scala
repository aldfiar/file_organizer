package managment

import java.nio.file.{Files, Path}
import java.util.concurrent.ThreadLocalRandom

import org.scalatest.{BeforeAndAfterEach, FunSuite}
import workers.WorkerTask


class ShowPathTask extends WorkerTask[Path] {
  def execute(path: Path): Option[Path] = {
    Option(path)
  }
}

class ManagerTest extends FunSuite with BeforeAndAfterEach {
  private var path: Path = _

  def fillRoot(path: Path, level: Int): Unit = {
    val inner = Files.createTempDirectory(path, "inner_%d".format(level))
    val fileNumber = ThreadLocalRandom.current().nextInt(10)
    for (i <- 1 to fileNumber) {
      Files.createTempFile(path, "file_%d".format(i), ".jpg")
    }

    level match {
      case 0 => ;
      case _ => this.fillRoot(inner, level - 1)
    }

  }

  override def beforeEach(): Unit = {
    path = Files.createTempDirectory("root")
    this.fillRoot(path, 5)
  }

  test("Manager split all files on workers") {

  }


}
