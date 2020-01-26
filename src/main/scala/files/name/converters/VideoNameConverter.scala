package files.name.converters

import files.name.format.{FileNameNumberFormatter, Formatter}
import files.name.parse.StringProcessor

object VideoNameConverter extends FileNameConverter {
  private val bracketRegEx = "[\\[({].*?[\\])}]".r
  private val symbolsToRemove = Seq("_", "-", "~", "")

  override def convert(filename: String, format: Formatter): String = {
    StringProcessor.process(filename, splitFunction, format.arrayToString)
  }

  private[this] def splitFunction(filename: String): Array[String] = {
    bracketRegEx.replaceAllIn(filename, "").split("[ _|-]").filter(t => !symbolsToRemove.contains(t))
  }

  override def convert(filename: String): String = {
    StringProcessor.process(filename, splitFunction, FileNameNumberFormatter.arrayToString)
  }
}
