package work

import java.nio.file.Path

trait Task {
  def execute(path: Path)
}
