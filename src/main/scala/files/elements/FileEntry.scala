package files.elements

import java.nio.file.Path

object FileTypes extends Enumeration {

  case class FileType(i: Int, extensions: Seq[String]) extends super.Val

  val Audio = FileType(nextId, Seq("mp3", "wav", "flac"))
  val Image = FileType(nextId, Seq("jpg", "png"))
  val Video = FileType(nextId, Seq("avi", "mpv", "mov"))
  val Text = FileType(nextId, Seq("txt", "pdf", "rtf"))
}

trait FileEntry {
  def path: Path

  def fileType: FileTypes.FileType
}

trait ReadableDescription {
  def author: String

  def name: String

  def series: String

  def isbn: String

  def page: Int

  def length: Int
}

case class AudioDescription(album: String, artist: String, trackNumber: Int, diskNumber: Int, length: Int)

case class VideoDescription(producer: String, name: String, series: String, number: Int, artists: Seq[String])

case class BookDescription(author: String, name: String, series: String, isbn: String, page: Int, length: Int) extends ReadableDescription


case class AudioEntry(fileType: FileTypes.FileType = FileTypes.Audio, path: Path, description: AudioDescription) extends FileEntry

case class ComicEntry(fileType: FileTypes.FileType = FileTypes.Image, path: Path, description: ReadableDescription) extends FileEntry

case class VideoEntry(fileType: FileTypes.FileType = FileTypes.Video, path: Path, description: VideoDescription) extends FileEntry

case class BookEntry(fileType: FileTypes.FileType = FileTypes.Text, path: Path, description: ReadableDescription) extends FileEntry