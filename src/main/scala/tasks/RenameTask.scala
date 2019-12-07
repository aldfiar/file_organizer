package tasks

import java.nio.file.{Files, Path}

import com.typesafe.scalalogging.Logger
import parse.FileNameParser
import work.Task

class RenameTask(val parser: FileNameParser) extends Task {
  val logger = Logger(this.getClass.getName)

  override def execute(path: Path): Path = {
    val file = path.getFileName.toFile
    val fileName = file.getName

    val amendedFileName = parser.parse(fileName)
    val amendedPath = path.resolveSibling(amendedFileName)

    logger.debug(s"Change file name: $fileName to $amendedFileName")

    if (Files.exists(amendedPath)) {
      val duplicateTask = new DuplicateRenameTask()
      duplicateTask.execute(amendedPath)
    } else {
      Files.move(path, amendedPath)
    }
  }

}
