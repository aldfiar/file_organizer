trait FileNameParser {
  def parse(filename: String): String
}

object HentaiNameParser extends FileNameParser {
  val symbols = Seq(('(', ')'), ('[', ']'), ('{', '}'))
  val symbolsToRemove = Seq('_', '-', '~')

  override def parse(filename: String): String = {
    val splittedName = filename.split("\\.")
    if (splittedName.length > 2) {
      throw new IllegalArgumentException("File:%s contains multiple dots".format(filename))
    }
    val extension = splittedName(1)
    var name = splittedName(0)
    for ((startSymbol, endSymbol) <- symbols)
      name = bracketFinder(name, startSymbol, endSymbol)

    val withoutSymbols = removeLeadingAndTrailing(name)
    val newName = withoutSymbols.trim
    newName.concat(".").concat(extension)
  }

  def removeLeadingAndTrailing(value: String): String = {
    var startPos = 0
    var endPos = value.length - 1
    var findPos = false
    while (!findPos) {
      if (!symbolsToRemove.contains(value(startPos))) {
        findPos = true
      }
      else
        startPos += 1
    }
    findPos = false
    while (!findPos) {
      if (!symbolsToRemove.contains(value(endPos))) {
        findPos = true
      }
      else
        endPos -= 1
    }
    value.substring(startPos, endPos + 1)
  }

  def bracketFinder(stringValue: String, startSymbol: Char, endSymbol: Char): String = stringValue.contains(startSymbol) && stringValue.contains(endSymbol) match {
    case true =>
      val startIndex = stringValue.indexOf(startSymbol)
      val endIndex = stringValue.indexOf(endSymbol)
      val removedPart = stringValue.substring(startIndex, endIndex + 1)
      val replaced = stringValue.replace(removedPart, "")
      bracketFinder(replaced, startSymbol, endSymbol)
    case false => stringValue
  }

}

