package managment

import java.nio.file.Path
import java.util.concurrent.{ConcurrentLinkedQueue, ExecutorService, Executors}

import com.typesafe.scalalogging.StrictLogging
import workers.WorkerTask

import scala.collection.mutable

object OrganizeCommands extends Enumeration {

  protected case class Command(name: String, task: Option[WorkerTask[Path]]) extends super.Val

  val Rename = Command("Rename", None)
  val Sort = Command("Sort", None)
}

object Manager extends StrictLogging {

  def start(threads: Int, rootPath: Path, command: Enumeration): Unit = {
    val pool = Executors.newFixedThreadPool(threads)
    logger.info(s"Receive command: $command")
    command match {
      case OrganizeCommands.Rename => {
        logger.info("St")
      }
      case _ => logger.error("This command is not available")
    }


  }


}
