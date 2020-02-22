package files.name.parse

object StringProcessor {
  def process(original: String, transformFunction: String => Array[String], preFunction: Array[String] => Array[String], postFunction: Array[String] => Array[String]): Array[String] = {
    transformFunction.andThen(preFunction).andThen(postFunction).apply(original)
  }

  def process(original: String, transformFunction: String => Array[String], postFunction: Array[String] => Array[String]): Array[String] = {
    transformFunction.andThen(postFunction).apply(original)
  }
  def process(original: String, transformFunction: String => Array[String]):Array[String]= {
    transformFunction.apply(original)
  }

}

