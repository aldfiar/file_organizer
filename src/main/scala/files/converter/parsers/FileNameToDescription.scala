package files.converter.parsers

trait FileNameToDescription[A] {
  def convert(filename: String, convertRules: Function[String, A]): A = {
    convertRules.apply(filename)
  }

  def convert(filename: String): A
}
