import better.files.File
import parse.HentaiNameParser

object Main {
  def main(args: Array[String]): Unit = {
    val startDirectory = File.apply(args(0))
    val childrens = startDirectory.children
    val filenames = childrens
      .map(t => t.path.getFileName)
      .map(t => t.toFile)
      .map(t => t.toString)
      .map(f => f.split("\\.").apply(0))
    val names = filenames.map(t => HentaiNameParser.parse(t))
    names.foreach(t => println(t))
  }
}
