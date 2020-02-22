package workers.tasks.files

import java.nio.file.Path

import com.typesafe.scalalogging.StrictLogging
import files.elements.VideoEntry
import files.name.converters.FileNameToEntryParser

class RenameTask(parser: FileNameToEntryParser[VideoEntry]) extends FileTask with StrictLogging {

  def execute(path: Path): Option[Path] = {
    val fileName = this.getFileName(path)
    val renamedPath = path.resolveSibling(parser.convert(fileName).get.path)
    this.moveFile(path, renamedPath)
  }

}
