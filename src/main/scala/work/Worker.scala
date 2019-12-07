package work

import java.nio.file.Path

trait Worker {
  val task: Task

  def work(path: Path): Runnable
}
