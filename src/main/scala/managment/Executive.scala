package managment

import java.nio.file.Path
import java.util.concurrent.ConcurrentLinkedQueue

import workers.Worker

import scala.collection.mutable

trait Executive[A] {
  private val queue = new ConcurrentLinkedQueue[Path]
  private val workers = new mutable.Queue[Worker[A]]


  def recursiveFileFinder(path: Path): Unit ={
    path.toFile.listFiles.filter(_.isFile).map(f => f.toPath).foreach(p => queue.add(p))
    path.toFile.listFiles.filter(_.isDirectory).map(f => f.toPath).foreach(p => recursiveFileFinder(p))
  }

}
