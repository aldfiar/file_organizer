package organizer.collection

import better.files.File

case class ExtractionRule(rule: FileEntry => String, name: String)

case class OutputRule(rule: Map[String, String] => String, root: File)

class CollectionRule(val collectionType: CollectionType, val input: List[ExtractionRule], val output: OutputRule)
