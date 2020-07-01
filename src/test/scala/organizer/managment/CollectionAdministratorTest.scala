package organizer.managment

import better.files.File
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import organizer.collection.{Collection, CollectionType, FileCollection}

import scala.util.Random
import org.scalatest.Matchers._
class TestTask[A <: String, Z <: Int](val affiliation: CollectionType, val task: Function[A, Z]) extends CollectionTask[A, Z]

object BaseAffiliation {
  private val aff = CollectionType(name = "test", extensions = List.apply(""))

  def getAffiliation: CollectionType = aff
}


class StringCollection[A <: String] extends Collection[String] {
  override def exists(): Boolean = true

  override def elements(): List[String] = List.fill(50)(Random.nextInt().toString)

  override def path(): String = "/tmp/some"

  override def affiliation: CollectionType = BaseAffiliation.getAffiliation

  override def filteredElements(filter: String => Boolean): List[String] = List.fill(10)(Random.nextInt().toString)
}

class CollectionAdministratorTest extends FunSuite with BeforeAndAfterEach {
  private var collection: Collection[String] = _

  def fillRoot(path: Option[File], level: Int): Unit = {
    level match {
      case x if x >= 0 =>
        val inner = File.newTemporaryDirectory(s"test_inner_$level", parent = path)
        inner.createIfNotExists()

        val number = Random.nextInt(4)
        for (i <- 1 to number) {
          val f = File.newTemporaryFile(parent = Option.apply(inner), prefix = s"file_$number", suffix = ".jpg")
          f.createFileIfNotExists()
        }
    }
  }

  override protected def beforeEach(): Unit = {
    collection = new StringCollection()
  }

  test("Test collection conversion") {
    def task(some: String) = some.toInt

    val ca = new CollectionAdministrator()
    ca.manage(List.apply(collection), task = new TestTask(affiliation = BaseAffiliation.getAffiliation, task = task))
  }

  test("Test file collection conversion") {
    val parent =  File.newTemporaryDirectory()
    parent.createDirectoryIfNotExists()
    fillRoot(level = 3, path = Option.apply(parent))
    val fl = new FileCollection(affiliation = BaseAffiliation.getAffiliation, root = parent)
    val l = fl.elements()
    l.length should not be(0)
  }

}
