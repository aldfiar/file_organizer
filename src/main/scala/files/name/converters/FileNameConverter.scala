package files.name.converters

import files.name.format.Formatter

trait FileNameConverter {
  def convert(filename: String, formatter: Formatter): String

  def convert(filename: String): String
}
