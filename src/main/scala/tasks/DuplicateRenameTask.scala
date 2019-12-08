package tasks

import java.nio.file.Path
import java.util.concurrent.ThreadLocalRandom

import com.typesafe.scalalogging.StrictLogging

class DuplicateRenameTask extends FileTask with StrictLogging {

  override def execute(path: Path): Path = {
    val (fileName, fileExtension) = this.getFileNameAndExtension(path)

    val randomNumber = ThreadLocalRandom.current().nextInt(100)

    val duplicateName = s"$fileName($randomNumber).$fileExtension"
    val duplicatePath = path.resolveSibling(duplicateName)

    logger.debug(s"Change file name: $fileName.$fileExtension to $duplicateName")

    this.moveFile(path, duplicatePath)
  }
}

