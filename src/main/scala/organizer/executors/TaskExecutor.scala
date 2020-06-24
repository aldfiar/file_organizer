package organizer.executors

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object TaskExecutor {

  def run[A, Z](elements: List[A], task: A => Z): List[Future[Z]] = {
    elements.map(e => Future(task(e)))
  }

}
