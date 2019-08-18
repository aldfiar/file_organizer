package parse

object StringProcessor {
  def process(original: String, preFunction: String => String, transformFunction: String => Array[String], postFunction: Array[String] => String): String = {
    preFunction.andThen(transformFunction).andThen(postFunction).apply(original)
  }

}
