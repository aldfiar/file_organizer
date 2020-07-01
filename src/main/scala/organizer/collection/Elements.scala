package organizer.collection

import better.files.File


trait FileEntry {
  def location: File
}

trait Tagged {
  def tags: Map[String, String]
}

trait Converted {
  def destination: File
}

case class ParsedEntry(affiliation: CollectionType, location: File, tags: Map[String, String]) extends Affiliation with FileEntry with Tagged

case class FinalEntry(affiliation: CollectionType, location: File, destination: File) extends Affiliation with FileEntry with Converted

