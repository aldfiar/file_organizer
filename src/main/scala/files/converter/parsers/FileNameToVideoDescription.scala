package files.converter.parsers

import files.elements.VideoDescription

object FileNameToVideoDescription extends FileNameToDescription[VideoDescription] {
  private val bracketRegEx = "[\\[({].*?[\\])}]".r
  private val numberRegEx = """[-|~|_]?\d{1,2}[-|~|_]?""".r
  private val symbolsToRemove = Seq("_", "~", "")
  private val head = """^[-|~|_]""".r
  private val tail = """[-|~|_]$""".r

  private[this] def splitFunction(filename: String): Array[String] = {
    bracketRegEx.replaceAllIn(filename, "").split("[ _]").filter(t => !symbolsToRemove.contains(t))
  }
  private[this] def nameExtractor(array: Seq[String]):String = {
    val nameParts = array.filterNot(x => numberRegEx.pattern.matcher(x).matches())
    val name = nameParts.mkString(" ")
    val trimmedHead = head.replaceAllIn(name, "")
    val finalName = tail.replaceAllIn(trimmedHead, "").trim
    finalName
  }

  private [this] def numberExtractor(array: Seq[String]): Int = {
    val numbers = array.filter(x => numberRegEx.pattern.matcher(x).matches()).map(x=>head.replaceAllIn(x, "")).map(x=>tail.replaceAllIn(x, "")).map(_.toInt)
    numbers.headOption.getOrElse(0)
  }
  private [this] def seriesExtractor(array: Seq[String]): String = {
    //  TODO
   ""
  }

  private [this] def artistsExtractor(array: Seq[String]): Seq[String] = {
  //  TODO
    Seq.empty
  }

  private [this] def producerExtractor(array: Seq[String]): String = {
    //  TODO
    ""
  }

  override def convert(filename: String): VideoDescription = {
    val array = splitFunction(filename)
    VideoDescription(name = nameExtractor(array), number =  numberExtractor(array), series = seriesExtractor(array), artists =artistsExtractor(array), producer = producerExtractor(array))
  }
}

