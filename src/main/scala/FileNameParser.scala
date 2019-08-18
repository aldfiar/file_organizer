

trait FileNameParser {
  def parse(filename: String, template: Array[String] => String): String
  def parse(filename: String): String
}

object HentaiNameParser extends FileNameParser {
  val bracketRegEx = "[\\[,(,{].*?[\\],),}]".r
  val symbolsToRemove = Seq("_", "-", "~", "")


  override def parse(filename: String, template: Array[String] => String): String = {
    val nameWithoutBrackets = bracketFinder(filename)
    val title = nameWithoutBrackets.split("[  _|-]").filter(t => !symbolsToRemove.contains(t))
    template.apply(title)
  }

  def bracketFinder(title: String): String = {
    var tempName = title
    val removed = bracketRegEx.findAllIn(title).toList
    for (el <- removed)
      tempName = tempName.replace(el, "")
    tempName
  }

  override def parse(filename: String): String = {
    val nameWithoutBrackets = bracketFinder(filename)
    val title = nameWithoutBrackets.split("[  _|-]").filter(t => !symbolsToRemove.contains(t))
    title.mkString(" ")
  }
}

