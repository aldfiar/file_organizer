package files.converter

import java.nio.file.Path

import files.elements.FileTypes
import files.elements.FileTypes.FileType

trait PathToEntryConverter[A] {

  def toEntry(path: Path): Option[A]

  def toEntryCustom(path: Path, transformFunction: Path => Option[A]): Option[A] = {
    transformFunction.apply(path)
  }

  protected def getFileName(path: Path):String = {
    val name = path.getFileName.toString.split("\\.").dropRight(1)
    name.mkString("")
  }

  protected def getType(path: Path): Option[FileType] = {
    val extension = path.getFileName.toString.split("\\.").last
    val result = FileTypes.values.find(x => x.extensions.contains(extension.toLowerCase))
    result.map(x=>FileTypes.valueToFileType(x))
  }

}
