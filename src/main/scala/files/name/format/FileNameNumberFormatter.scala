package files.name.format

object FileNameNumberFormatter extends Formatter {
  override def arrayToString(value: Array[String]): String = {
    val numbers = value.filter(t => t.exists(_.isDigit))
    val withoutNumbers = value.diff(numbers)
    val lastNumber = numbers.lastOption.map(t => " - %s".format(t)).getOrElse("")
    withoutNumbers.mkString(" ").concat(lastNumber)
  }

}
