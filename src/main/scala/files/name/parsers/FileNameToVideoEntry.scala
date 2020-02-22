package files.name.converters

import files.elements.VideoEntry
import files.name.format.{FileNameNumberFormatter, Formatter}
import files.name.parse.StringProcessor

object FileNameToVideoEntry extends FileNameToEntryParser[VideoEntry] {
  private val bracketRegEx = "[\\[({].*?[\\])}]".r
  private val symbolsToRemove = Seq("_", "~", "")

  def toVideoEntry(values: Array[String]): Option[VideoEntry] ={
    None
  }

  private[this] def splitFunction(filename: String): Array[String] = {

    bracketRegEx.replaceAllIn(filename, "").split("[ _]").filter(t => !symbolsToRemove.contains(t))
  }

  override def convert(filename: String): Option[VideoEntry] = {
    val arr = StringProcessor.process(filename, splitFunction)
    None
  }

  override def convert(filename: String, convertRules: Function[String, Option[VideoEntry]]): Option[VideoEntry] = {
  None
  }
}
