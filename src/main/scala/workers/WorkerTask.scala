package workers

import java.nio.file.Path

trait WorkerTask[A] {
  def execute(path: Path): Option[A]
}
