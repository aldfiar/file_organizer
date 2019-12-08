package tasks

import java.io.IOException
import java.nio.file.{Files, Path}

import com.typesafe.scalalogging.StrictLogging
import work.Task

abstract class FileTask extends Task with StrictLogging {

  protected def moveFile(oldPath: Path, newPath: Path): Path = {
    try {
      Files.move(oldPath, newPath)
      newPath
    } catch {
      case x: IOException =>
        logger.error(s"Cant't move file $oldPath to $newPath error $x")
        oldPath
      case _ =>
        logger.error(s"Cant't move file $oldPath to $newPath with unknown problem")
        oldPath
    }
  }

  /**
   * Get extension from file
   **/
  protected def getFileNameAndExtension(path: Path): (String, String) = {
    val fileName = this.getFileName(path)
    val extensionPosition = fileName.lastIndexOf(".")
    val extension = fileName.substring(extensionPosition + 1)
    val name = fileName.substring(0, extensionPosition)
    (name, extension)
  }

  protected def getFileName(path: Path): String = {
    path.getFileName.toString
  }

}
