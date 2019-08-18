package parse

import format.{FileNameNumberFormatter, Formatter}


object VideoFileNameParser extends FileNameParser {
  private val bracketRegEx = "[\\[({].*?[\\])}]".r
  private val symbolsToRemove = Seq("_", "-", "~", "")


  override def parse(filename: String, format: Formatter): String = {
    StringProcessor.process(filename, bracketFinder, splitFunction, format.arrayToString)
  }

  private[this] def formatTitle(title: Array[String], format: Formatter): String = {
    format.arrayToString(title)
  }

  private[this] def bracketFinder(title: String): String = {
    var tempName = title
    val removed = bracketRegEx.findAllIn(title).toList
    for (el <- removed)
      tempName = tempName.replace(el, "")
    tempName
  }

  override def parse(filename: String): String = {
    StringProcessor.process(filename, bracketFinder, splitFunction, FileNameNumberFormatter.arrayToString)
  }

  private[this] def splitFunction(filename: String): Array[String] = {
    filename.split("[  _|-]").filter(t => !symbolsToRemove.contains(t))
  }
}

