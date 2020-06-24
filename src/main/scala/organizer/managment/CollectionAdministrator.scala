package organizer.managment

import com.typesafe.scalalogging.StrictLogging
import organizer.collection.{Affiliation, Collection}
import organizer.executors.TaskExecutor

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

trait CollectionTask[A, Z] extends Affiliation {
  def task(): Function[A, Z]
}

class CollectionAdministrator extends StrictLogging {
  def manage[A, Z](collections: List[Collection[A]], task: CollectionTask[A, Z]): Unit = {
    val filtered = collections.filter(c => c.affiliation == task.affiliation)
    if (filtered.isEmpty) {
      logger.warn("Collections has different affiliation with task")
    } else {
      val futures = collections.flatMap(c => TaskExecutor.run(c.elements(), task.task()))
      Future.sequence(futures).onComplete {
        case Success(value) => logger.info(s"Complete converting collections $collections")
        case Failure(exception) => logger.error(s"Failed to convert collections $collections")
      }
    }
  }

}
