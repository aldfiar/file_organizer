package organizer.executors

import java.util.concurrent.atomic.AtomicInteger

import org.scalatest.FunSuite
import org.scalatest.Matchers._

import scala.util.Random


class TaskExecutorTest extends FunSuite {

  test("Iteration test") {
    val collectionsCount = 10
    val collections = List.fill(collectionsCount)(Random.nextString(10))

    val count = new AtomicInteger()

    def increment(st: String): Int = count.incrementAndGet()

    TaskExecutor.run(collections, increment)

    count.get() shouldBe collectionsCount
  }


}
