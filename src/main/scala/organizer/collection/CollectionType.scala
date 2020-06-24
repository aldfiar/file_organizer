package organizer.collection

final case class CollectionType(name: String, extensions: List[String])

trait Affiliation {
  def affiliation: CollectionType
}
