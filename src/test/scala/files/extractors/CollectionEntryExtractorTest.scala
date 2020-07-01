package files.extractors


//class CollectionEntryExtractorTest extends FunSuite with BeforeAndAfterEach {
//  private val collectionType = CollectionType("video", List("avi"))
//  private val rules: List[ExtractionRule] = List(
//    ExtractionRule(collectionType, name = "name", extractionFunction = extractName)
//  )
//  private var extractor: CollectionEntryExtractor = _
//  private var file: File = _
//  private var entry: CollectionEntry = _
//
//  def extractName(file: File): String = {
//    val name = file.nameWithoutExtension
//    """[\w\D]+""".r.findFirstIn(name).getOrElse("")
//  }
//
//  override def beforeEach(): Unit = {
//    file = File.newTemporaryFile("Bishoujo_Java_-_Tsumi_to_batsu_no_shoujo_", ".avi")
//    extractor = new CollectionEntryExtractor(collectionType, rules)
//
//  }
//
//  test("Extract name from file") {
//    entry = CollectionEntry(collectionType, file)
//    val description = extractor.extract(entry)
//    description should not be empty
//  }
//}
