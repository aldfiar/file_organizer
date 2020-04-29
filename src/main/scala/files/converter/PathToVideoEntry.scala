package files.converter

import java.nio.file.Path

import com.typesafe.scalalogging.StrictLogging
import files.converter.parsers.FileNameToVideoDescription
import files.elements.{FileTypes, VideoDescription, VideoEntry}

object PathToVideoEntry extends PathToEntryConverter[VideoEntry] with StrictLogging {
  override def toEntry(path: Path): Option[VideoEntry] = {
    val fileType = getType(path).get
    fileType match {
      case FileTypes.Video =>
        val name = getFileName(path)
        val description = FileNameToVideoDescription.convert(name)
        Option.apply(VideoEntry(path = path, description = description))
      case _ =>
        logger.warn(s"Path $path doesn't contain file with registered video extension")
        Option.empty
    }
  }
}
