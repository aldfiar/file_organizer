package tasks

import java.nio.file.Path

import com.typesafe.scalalogging.StrictLogging
import parse.FileNameParser

class RenameTask(parser: FileNameParser) extends FileTask with StrictLogging {

  override def execute(path: Path): Path = {
    val fileName = this.getFileName(path)
    val renamedPath = path.resolveSibling(parser.parse(fileName))
    this.moveFile(path, renamedPath)
  }

}
