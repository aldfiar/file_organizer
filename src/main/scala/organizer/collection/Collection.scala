package organizer.collection

import better.files.File
import com.typesafe.scalalogging.StrictLogging

trait Collection[A] extends Affiliation {
  def exists(): Boolean

  def elements(): List[A]

  def path(): String

  override def toString: String = s"Collection - type: $affiliation, path: ${path()}"
}

class FileCollection[A <: File](override val affiliation: CollectionType, val root: File) extends Collection[File] with Affiliation with StrictLogging {
  def elements(): List[File] = {
    if (exists()) {
      logger.warn("Root : {} don't exists", root)
      List.empty
    }
    else {
      root.collectChildren(x => affiliation.extensions.contains(x.extension.getOrElse(""))).toList
    }
  }

  def exists(): Boolean = {
    root.exists
  }

  override def path(): String = root.toString()
}
