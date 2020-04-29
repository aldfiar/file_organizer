package files.converter

import java.nio.file.Path

import files.elements.ComicEntry

object PathToComicEntry extends PathToEntryConverter[ComicEntry] {
  override def toEntry(path: Path): Option[ComicEntry] = {
    Option.empty
  }
}
