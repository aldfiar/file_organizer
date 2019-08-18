package parse

import format.{FileNameNumberFormatter, Formatter}


object HentaiNameParser extends FileNameParser {
  val bracketRegEx = "[\\[,(,{].*?[\\],),}]".r
  val symbolsToRemove = Seq("_", "-", "~", "")


  override def parse(filename: String, format: Formatter): String = {
    val nameWithoutBrackets = bracketFinder(filename)
    val title = nameWithoutBrackets.split("[  _|-]").filter(t => !symbolsToRemove.contains(t))
    formatTitle(title, format)
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
    val nameWithoutBrackets = bracketFinder(filename)
    val title = nameWithoutBrackets.split("[  _|-]").filter(t => !symbolsToRemove.contains(t))
    formatTitle(title, FileNameNumberFormatter)
  }
}

