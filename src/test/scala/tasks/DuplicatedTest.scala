package tasks

import java.nio.file.{Files, Path}

import org.scalatest.{BeforeAndAfterEach, FunSuite}
import workers.tasks.files.DuplicateRenameTask

class DuplicatedTaskTest extends FunSuite with BeforeAndAfterEach {
  private val task: DuplicateRenameTask = new DuplicateRenameTask()
  private var path: Path = _

  override def beforeEach() {
    path = Files.createTempFile("Bishoujo_Java_-_Tsumi_to_batsu_no_shoujo_[E12D394A] ", ".avi")
  }

  test("Renamed file exists") {
    val number = path.toFile.getName.split(" ").takeRight(1).apply(0)
    val renamedPath = task.execute(path)

    val renamedExisted = task.execute(renamedPath.get)
    assume(renamedPath != renamedExisted, "Existed: %s, renamed: %s".format(renamedPath, renamedExisted))
  }

  override def afterEach() {
    Files.deleteIfExists(path)
  }
}
