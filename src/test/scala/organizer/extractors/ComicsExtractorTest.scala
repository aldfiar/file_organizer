package organizer.extractors

import org.scalatest.{BeforeAndAfterEach, FunSuite}
import org.scalatest.Matchers._

class ComicsExtractorTest extends FunSuite with BeforeAndAfterEach {
  val comicsList = Seq("(B90) [Author] Scala FANTASY (Doggo)",
    "(C94) [whysoserious (Din)] Learing JavaScript That's Gross! [English] {some_tag}",
    "[BanaNa (Apple)] Big Evil 101 [english]{fixed}[colored][paper]",
    "[Last pod (Sergey Steam)] Another example of bad code")

  test("Author info from string") {
    val extracted = comicsList.map(c => ComicsExtractor.authorInfo(c)).toList
    extracted should equal(Seq("Author", "whysoserious (Din)", "BanaNa (Apple)", "Last pod (Sergey Steam)"))
  }

  test("Author from string") {
    val extracted = comicsList.map(c => ComicsExtractor.author(c)).toList
    extracted should equal(Seq("Author", "Din", "Apple", "Sergey Steam"))
  }

  test("Name from string"){
    val extracted = comicsList.map(c => ComicsExtractor.name(c)).toList
    extracted should equal(Seq("Scala FANTASY (Doggo)", "Learing JavaScript That's Gross! [English] {some_tag}", "Big Evil 101 [english] {fixed} [colored] [paper]", "Another example of bad code"))
  }






}
