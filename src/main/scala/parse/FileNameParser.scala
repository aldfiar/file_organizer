package parse

import format.Formatter

trait FileNameParser {
  def parse(filename: String, formatter: Formatter): String

  def parse(filename: String): String
}