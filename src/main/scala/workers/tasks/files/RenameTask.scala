package workers.tasks.files

import java.nio.file.Path

import com.typesafe.scalalogging.StrictLogging
import files.name.converters.FileNameConverter

class RenameTask(parser: FileNameConverter) extends FileTask with StrictLogging {

  def execute(path: Path): Option[Path] = {
    val fileName = this.getFileName(path)
    val renamedPath = path.resolveSibling(parser.convert(fileName))
    this.moveFile(path, renamedPath)
  }

}
