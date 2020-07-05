package organizer.extractors

import better.files.File
import com.typesafe.scalalogging.StrictLogging
import organizer.collection.{CollectionType, ParsedEntry}

object ComicsExtractor extends StrictLogging {
  val nameRegEx = """(.+)\s?_?\((.+)\)""".r

  def extract(file: File, affiliation:CollectionType): Option[ParsedEntry] = {
    if (file.isRegularFile) {
      logger.warn(s"Need folder for parsing. ${file} is not a folder")
      Option.empty
    } else {
      val osName = System.getProperty("os.name")
      val filename = if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) file.name.replace("_", " ") else file.name
      val authorInfo = extractAuthorInfo(filename)
      val (name, tags) = extractName(filename)
      val nameInfo = Map.apply("name"->name, "tags"-> tags.mkString(" "))
      val fullTags = authorInfo ++ nameInfo
      Option.apply(ParsedEntry(affiliation=affiliation, location = file, tags=fullTags))
    }
  }

  private def extractAuthorInfo(filename: String): Map[String, String] = {
    val nfi = filename.indexOf("[")
    val nli = filename.indexOf("]")
    if (nfi == nli) {
      Map.empty
    } else {
      val info = filename.substring(nfi + 1, nli)
      nameRegEx.findFirstMatchIn(info) match {
        case Some(value) =>
          Map.apply("author" -> value.group(2).strip(), "group" -> value.group(1).strip())
        case None => Map.apply("author" -> info.strip())
      }
    }
  }

  def authorInfo(filename: String): String = {
    val info = extractAuthorInfo(filename)
    val author = info("author")
    info.get("group") match {
      case Some(value) => s"$value ($author)"
      case None => s"$author"
    }
  }

  private def extractTags(tagsString: String): Seq[String] = {
    """[\[\(\{](.*?)[\]\)\}]""".r.findAllMatchIn(tagsString).map(c=>c.group(0)).toList
  }

  private def extractName(filename: String): Tuple2[String, Seq[String]] = {
    val nli = filename.indexOf("]")
    if (nli == -1) {
      ("", Seq.empty)
    } else {
      val nameWithoutAuthor = filename.substring(nli+1, filename.length)
      """[\w\d_  !~'\\-]+""".r.findFirstMatchIn(nameWithoutAuthor) match {
        case Some(value) => {
          val name = value.group(0)
          val tagsString = nameWithoutAuthor.replace(name, "")
          val tags = extractTags(tagsString)
          (name.strip(), tags)
        }
        case None => (nameWithoutAuthor.strip(), Seq.empty)
      }
    }
  }


  def name(filename: String, keepTags: Boolean = true): String = {
    val (name, tags) = extractName(filename)
    if (keepTags) {
      val tagsString = tags.mkString(" ")
      s"$name $tagsString".strip()
    } else {
      name
    }
  }

  def author(filename: String): String = {
    val info = extractAuthorInfo(filename)
    info("author")
  }

  def group(filename: String): String = {
    val info = extractAuthorInfo(filename)
    info.getOrElse("group", "")
  }

}
