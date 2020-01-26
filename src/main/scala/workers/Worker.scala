package workers

import java.nio.file.Path

trait Worker[A] {
  val task: WorkerTask[A]

  def work(path: Path): Runnable
}
