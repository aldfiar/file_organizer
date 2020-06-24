package organizer.managment

import better.files.File
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import organizer.collection.{Collection, CollectionType, FileCollection}

import scala.util.Random

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
}

class CollectionAdministratorTest extends FunSuite with BeforeAndAfterEach {
  private var collection: Collection[String] = _

  def fillRoot(path: Option[File], level: Int): Unit = {
    level match {
      case x if x >= 0 =>
        val inner = File.newTemporaryDirectory(s"test_inner_$level", parent = path)
        inner.createDirectory()

        val number = Random.nextInt(4)
        for (i <- 1 to number) {
          val f = File.newTemporaryFile(parent = Option.apply(inner), prefix = s"file_$number", suffix = ".jpg")
          f.createFile()
        }
    }
  }

  override protected def beforeEach(): Unit = {
    collection = new StringCollection()
  }

  test("Test collection convertion") {
    def task(some: String) = some.toInt

    val ca = new CollectionAdministrator()
    ca.manage(List.apply(collection), task = new TestTask(affiliation = BaseAffiliation.getAffiliation, task = task))

  }
  test("Test file collection conversion") {
    val parent = File("/tmp/some")
    parent match {
      case x if !x.exists => parent.createDirectory()
      case z if z.isRegularFile =>
        z.delete()
        z.createDirectory()
    }
    fillRoot(level = 3, path = Option.apply(parent))
    val fl = new FileCollection(affiliation = BaseAffiliation.getAffiliation, root = parent)
    val l = fl.elements()
  }

}
