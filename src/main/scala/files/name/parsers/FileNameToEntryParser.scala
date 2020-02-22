package files.name.converters

trait FileNameToEntryParser[A] {
  def convert(filename: String, convertRules: Function[String, Option[A]]): Option[A]

  def convert(filename: String): Option[A]
}
