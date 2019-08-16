import better.files.File

object Main {
  def main(args: Array[String]): Unit = {
    val startDirectory = File.apply(args(0))
    val childrens = startDirectory.children
    for (children <- childrens) println(children.path.getFileName)
  }
}
