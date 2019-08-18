package work

import java.nio.file.Path
import java.util.concurrent.{ConcurrentLinkedQueue, ExecutorService, Executors}

class Manager(val workersNumber: Int, val path: Path) {
  private val queue = new ConcurrentLinkedQueue[Path]
  private val workers = new Array[Worker](workersNumber)
  private val pool: ExecutorService = Executors.newFixedThreadPool(workersNumber)

  def start(task: Task): Unit = {

  }

}
