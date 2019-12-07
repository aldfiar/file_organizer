package parse

import format.{FileNameNumberFormatter, Formatter}


object VideoFileNameParser extends FileNameParser {
  private val bracketRegEx = "[\\[({].*?[\\])}]".r
  private val symbolsToRemove = Seq("_", "-", "~", "")

  override def parse(filename: String, format: Formatter): String = {
    StringProcessor.process(filename, splitFunction, format.arrayToString)
  }

  private[this] def splitFunction(filename: String): Array[String] = {
    bracketRegEx.replaceAllIn(filename, "").split("[ _|-]").filter(t => !symbolsToRemove.contains(t))
  }

  override def parse(filename: String): String = {
    StringProcessor.process(filename, splitFunction, FileNameNumberFormatter.arrayToString)
  }
}

