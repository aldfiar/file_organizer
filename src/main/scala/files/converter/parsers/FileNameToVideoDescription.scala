package files.converter.parsers

import files.elements.VideoDescription

object FileNameToVideoDescription extends FileNameToDescription[VideoDescription] {
  private val bracketRegEx = "[\\[({].*?[\\])}]".r
  private val symbolsToRemove = Seq("_", "~", "")
  private val removeLeadingOrTrailing = Seq("-", "~", "-")

  private[this] def splitFunction(filename: String): Array[String] = {
    bracketRegEx.replaceAllIn(filename, "").split("[ _]").filter(t => !symbolsToRemove.contains(t))
  }

  override def convert(filename: String): VideoDescription = {
    val array = splitFunction(filename)
    val nameParts = array.filterNot(x => x.matches(""".?\d+.?"""))
    val numbers = array.filter(x => x.matches(""".?\d+.?""")).map(x=>x.replace("-", "")).map(_.toInt)
    var name = nameParts.mkString(" ")
    for (symbol <- removeLeadingOrTrailing) {
      name = name.stripPrefix(symbol).stripSuffix(symbol)
    }
    val finalName = name.strip()
    VideoDescription(name = finalName, number = numbers.headOption.getOrElse(0), series = "", artists = Seq.empty, producer = "")
  }
}

